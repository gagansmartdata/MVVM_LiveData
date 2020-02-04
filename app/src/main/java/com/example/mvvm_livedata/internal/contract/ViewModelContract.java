package com.example.mvvm_livedata.internal.contract;

import androidx.annotation.NonNull;

/**
 * Android contract for every MVP Presenter
 */
public interface ViewModelContract<V extends Viewable> {

    /**
     * Every ViewModelContract must implement onStart state
     * It is called in Activity#onStart()
     */
    void onStart();

    /**
     * Every ViewModelContract must implement onViewCreated state
     */
    void onViewCreated();

    /**
     * Every ViewModelContract must implement onResume state
     * It is called in Activity#onResume()
     */
    void onResume();


    /**
     * Every ViewModelContract must implement onPause state
     * It is called in Activity#onPause()
     */
    void onPause();


    /**
     * Every ViewModelContract must implement onStop state
     * It is called in Activity#onStop()
     */
    void onStop();


    /**
     * Every ViewModelContract must attach a Viewable
     * attach viewable interface.
     *
     * @param viewable Viewable
     */
    void attachView(@NonNull V viewable);


    /**
     * Every ViewModelContract must detach its Viewable
     * It is called in Activity#onDestroy()
     */
    void detachView();


    /**
     * Every ViewModelContract must be able to access to its attached View
     *
     * @return {@link V} Viewable interface.
     */
    V getView();

}
