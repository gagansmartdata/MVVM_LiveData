package com.example.mvvm_livedata.web;

import com.example.mvvm_livedata.internal.ApplicationClass;
import com.example.mvvm_livedata.internal.UtillsG;
import com.example.mvvm_livedata.internal.contract.Viewable;
import com.example.mvvm_livedata.utils.DialogHelper;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by gagan on 4/2/20.
 **/
public class APICallHelper
{
	private static final APICallHelper ourInstance = new APICallHelper();

	public static APICallHelper getInstance()
	{
		return ourInstance;
	}

	private APICallHelper()
	{
	}
	/**
	 * get API interface created by retrofit instance using the interface class.
	 *
	 * @param retroInterface - API interface class.
	 * @param <I>
	 * @return API interface created by <b>Retrofit</b> Instance.
	 */
	protected final <I> I getRetrofitInstance(Class<I> retroInterface) {
		return ApplicationClass.getRetrofit().create(retroInterface);
	}


	/**
	 * Create a network call and return back the response using {@link ApiCallBack}
	 * and errors are handled using {@link Viewable#displayError(String)} method.
	 *
	 * @param observables - observable returned by API interface
	 * @param callBack    - generic call back.
	 * @param <D>         - type of data which should be returned by the call back.
	 */
	protected <D> Disposable createApiRequest(Viewable viewable,Observable<D> observables, final ApiCallBack<D> callBack) {

		if (viewable == null) {
			return null;
		}

		return (observables
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableObserver<D>()
				{
					@Override
					public void onNext(@NonNull D s)
					{
						if (viewable == null) {
							return ;
						}
						viewable.hideLoading();
						BaseModel baseModel = (BaseModel) s;
						if (baseModel.getStatus()) {
							callBack.onSuccess((D) s);
						} else {
							callBack.onFailure(baseModel.getMessage());
						}
					}

					@Override
					public void onError(@NonNull Throwable e)
					{

						try {

							if (viewable == null) {
								return ;
							}
							viewable.hideLoading();
							if (e instanceof IOException) {
								DialogHelper.getInstance().showInformation(viewable.getActivityG(),
										"Please check your network connection", output ->
												callBack.onFailure("Please check your network connection"));
								return;
							}


							String       string  = ((HttpException) e).response().errorBody().string();
							final String message = new JSONObject(string).getString("message");

							String status = new JSONObject(string).getString("status");
							if (status.equals("EXPIRED")) {  //access token expired
								//                                getView().getLocalData().logOut();
								UtillsG.showToast(message, viewable.getActivityG(), true);
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
					public void onComplete()
					{

					}
				}));
	}
}
