package com.example.mvvm_livedata.internal.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mvvm_livedata.internal.SharedPrefHelper;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by gagan on 05 Jul 2020.
 */

public abstract class BaseActivityNoViewModel<B extends ViewDataBinding> extends AppCompatActivity
{
    protected B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, setLayoutId());
        onCreateViewG();
    }

    protected abstract void onCreateViewG();


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void displayError(String message) {
        if (setParentView() != null) {
            Snackbar snackbar = Snackbar.make(setParentView(), message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }


    public abstract Activity getActivityG();

    public B getDataBinder() {
        return binding;
    }

    public SharedPrefHelper getLocalData() {
        return SharedPrefHelper.getInstance(getApplicationContext());
    }

    protected abstract int setLayoutId();

    protected View setParentView() {
        return findViewById(android.R.id.content);
    }
}

