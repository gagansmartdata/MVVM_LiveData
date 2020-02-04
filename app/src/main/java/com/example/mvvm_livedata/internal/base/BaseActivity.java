package com.example.mvvm_livedata.internal.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mvvm_livedata.R;
import com.example.mvvm_livedata.internal.SharedPrefHelper;
import com.example.mvvm_livedata.internal.TransparentProgressDialog;
import com.example.mvvm_livedata.internal.UtillsG;
import com.example.mvvm_livedata.internal.contract.ViewModelContract;
import com.example.mvvm_livedata.internal.contract.Viewable;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Gagan on 03 Feb 2020.
 */
public abstract class BaseActivity<B extends ViewDataBinding, T extends ViewModelContract> extends AppCompatActivity implements Viewable<T>
{
    protected T                         viewModel;
    protected B                         binding;
    private   TransparentProgressDialog progressDialog;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onStart();
    }



    @Override
    protected void onPause() {
        try {
            UtillsG.hideKeyboard(getActivityG(), getCurrentFocus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        getViewModel().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, setLayoutId());

        viewModel=initViewModel();
        viewModel.attachView(this);

        onCreateActivityG();
        getViewModel().onViewCreated();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onStop() {
        getViewModel().onStop();
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        getViewModel().detachView();
        viewModel = null;
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(String message) {
        if (setParentView() != null) {
            Snackbar snackbar = Snackbar.make(setParentView(), message, Snackbar.LENGTH_LONG);
//            TextView snackViewText = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
//            snackViewText.setBackgroundColor(ContextCompat.getColor(getActivityG(), R.color.black));
//            snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivityG(), R.color.black));
            snackbar.show();
        }
    }

    protected abstract T initViewModel();

    @Override
    public SharedPrefHelper getLocalData() {
        return SharedPrefHelper.getInstance(getApplicationContext());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getViewModel() {
        return viewModel;
    }

    public B getDataBinder() {
        return binding;
    }


    @Override
    public void injectViewModel(T presenter)
    {
        this.viewModel = presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new TransparentProgressDialog(getActivityG(), R.mipmap.ic_launcher);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    protected abstract int setLayoutId();

    protected View setParentView() {
        return findViewById(android.R.id.content);
    }

    /**
     * injectPresenter( @link Presentable);
     * attachView(this);
     */
    protected abstract void onCreateActivityG();
}
