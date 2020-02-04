package com.example.mvvm_livedata.internal.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.mvvm_livedata.R;
import com.example.mvvm_livedata.internal.SharedPrefHelper;
import com.example.mvvm_livedata.internal.TransparentProgressDialog;
import com.example.mvvm_livedata.internal.contract.ViewModelContract;
import com.example.mvvm_livedata.internal.contract.Viewable;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseFragment<B extends ViewDataBinding, T extends ViewModelContract> extends Fragment implements Viewable<T>
{
    protected T                presenter;
    protected B                binding;
    protected View             view;
    protected SharedPrefHelper sharedPrefHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public SharedPrefHelper getLocalData() {
        if (sharedPrefHelper == null) {
            sharedPrefHelper = SharedPrefHelper.getInstance(getActivityG());
        }
        return sharedPrefHelper;
    }

    @Override
    public Context getActivityG() {
        return getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        view = binding.getRoot();
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreateActivityG();
        getViewModel().onViewCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroyView() {
        getViewModel().detachView();
        super.onDestroyView();
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
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        getViewModel().onResume();
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(String message) {
        if (getParentView() != null) {
            Snackbar.make(getParentView(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    public View getParentView() {
        return view;
    }

    private TransparentProgressDialog progressDialog;

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
        return presenter;
    }


    public B getDataBinder() {
        return binding;
    }

    protected abstract T initViewModel();

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void onCreateActivityG();

}
