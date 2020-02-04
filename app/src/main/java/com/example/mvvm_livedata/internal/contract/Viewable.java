package com.example.mvvm_livedata.internal.contract;

import android.content.Context;

import com.example.mvvm_livedata.internal.SharedPrefHelper;

/**
 * Android contract for every MVVM View
 */
public interface Viewable<T>
{

    /**
     * initialize all views here.
     */
    void initViews();

    SharedPrefHelper getLocalData();

    Context getActivityG();

    /**
     * Every Viewable must be able to access to its attached Presenter
     *
     * @return ViewModel
     */
    T getViewModel();

    /**
     * Every Viewable must be able to inject its Presenter
     *
     * @param viewModel ViewModelContract
     */
    void injectViewModel(T viewModel);

    /**
     * Every Viewable must have a error message system
     */
    void displayError(String message);

    /**
     * Every Viewable must implement one show loading feature
     */
    void showLoading();

    /**
     * Every Viewable must implement one hide loading feature
     */
    void hideLoading();

}
