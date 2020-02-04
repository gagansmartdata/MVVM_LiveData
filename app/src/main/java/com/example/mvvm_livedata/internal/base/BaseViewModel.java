package com.example.mvvm_livedata.internal.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_livedata.internal.ApplicationClass;
import com.example.mvvm_livedata.internal.UtillsG;
import com.example.mvvm_livedata.internal.contract.ViewModelContract;
import com.example.mvvm_livedata.internal.contract.Viewable;
import com.example.mvvm_livedata.utils.DialogHelper;
import com.example.mvvm_livedata.web.ApiCallBack;
import com.example.mvvm_livedata.web.BaseModel;
import com.example.mvvm_livedata.web.CallbackG;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class BaseViewModel<V extends Viewable> extends ViewModel implements ViewModelContract<V>
{
    private   V          viewable;
    //for multiple request we can use compositeDisposable,
    // but currently we use Disposable,as there can be only one request.
    protected Disposable compositeDisposable;

    public Disposable getDisposable() {
        return compositeDisposable;
    }

    public void clearSubscriptions() {
        if (getDisposable() != null) {
            getDisposable().dispose();
        }
    }

    @Override
    public void onStart() {
        // No-op by default
    }

    @Override
    public void onViewCreated() {
//        views are created ,now its time to initialize them..
        if (getView() != null) {
            getView().initViews();
        }
    }

    @Override
    public void onResume() {
        // No-op by default
    }

    @Override
    public void onPause() {
        // No-op by default
    }

    @Override
    public void onStop() {
        // No-op by default
    }

    @Override
    public void attachView(@NonNull V viewable) {
        this.viewable = viewable;
    }


    @Override
    public void detachView() {
        this.viewable = null;
        clearSubscriptions();
    }

    @Override
    public V getView() {
        return viewable;
    }

    /**
     * get API interface created by retrofit instance using the interface class.
     *
     * @param retroInterface - API interface class.
     * @param <G>
     * @return API interface created by <b>Retrofit</b> Instance.
     */
    protected final <G> G getRetrofitInstance(Class<G> retroInterface) {
        return ApplicationClass.getRetrofit().create(retroInterface);
    }


    /**
     * Create a network call and return back the response using {@link ApiCallBack}
     * and errors are handled using {@link Viewable#displayError(String)} method.
     *
     * @param observables - observable returned by API interface
     * @param callBack    - generic call back.
     * @param <V>         - type of data which should be returned by the call back.
     */
    protected <V> void createApiRequest(Observable<V> observables, final ApiCallBack<V> callBack) {

        if (ApplicationClass.getAccessToken().isEmpty()) {
            if (getView() == null) {
                return;
            }
            ApplicationClass.resetAll(getView().getLocalData()); //if access token is empty, reinitialize it
        }

        compositeDisposable = (observables
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<V>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull V s) {
                        if (getView() == null) {
                            return;
                        }
                        getView().hideLoading();
                        BaseModel baseModel = (BaseModel) s;
                        if (baseModel.getStatus()) {
                            callBack.onSuccess((V) s);
                        }
                        else {
                            callBack.onFailure(baseModel.getMessage());
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        try {
                            if (getView() == null) {
                                return;
                            }
                            getView().hideLoading();

                            if (e instanceof IOException) {
                                DialogHelper.getInstance().showInformation(getView().getActivityG(),
                                        "Please check your network connection", new CallbackG<String>() {
                                            @Override
                                            public void onResponse(String output) {
                                                callBack.onFailure("Please check your network connection");
                                            }
                                        });
                                return;
                            }


                            String       string  = ((HttpException) e).response().errorBody().string();
                            final String message = new JSONObject(string).getString("message");

                            String status = new JSONObject(string).getString("status");
                            if (status.equals("EXPIRED")) {  //access token expired
//                                getView().getLocalData().logOut();
                                UtillsG.showToast(message, getView().getActivityG(), true);
//                                SplashActivity.start(getView().getActivityG());
                                return;
                            } else if (status.equalsIgnoreCase("UPDATE REQUIRED")) {   //need to update app.

                                return;
                            }


                            callBack.onFailure(message);

                        } catch (Exception e1) {
                            callBack.onFailure("Response not in correct format.");
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

}
