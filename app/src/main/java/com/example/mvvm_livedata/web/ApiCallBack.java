package com.example.mvvm_livedata.web;

/**
 * Created by Gagan on 9/2/18.
 */

/**
 * callback used for api response
 *
 * @param <G> type of data value in api response.
 */
public interface ApiCallBack<G> {

    /**
     * in case of success
     *
     * @param data
     */
    void onSuccess(G data);

    /**
     * in case of error in API.
     *
     * @param error error message to show.
     */
    void onFailure(String error);
}
