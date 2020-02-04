package com.example.mvvm_livedata.internal.base;

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
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseFragmentNoViewModel<B extends ViewDataBinding> extends Fragment
{
    protected B                binding;
    protected View             view;
    protected SharedPrefHelper sharedPrefHelper;

       /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public SharedPrefHelper getLocalData() {
        if (sharedPrefHelper == null) {
            sharedPrefHelper = SharedPrefHelper.getInstance(getActivity());
        }
        return sharedPrefHelper;
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
    }


    /**
     * {@inheritDoc}
     */
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
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new TransparentProgressDialog(getActivity(), R.mipmap.ic_launcher);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    public B getDataBinder() {
        return binding;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void onCreateActivityG();

}
