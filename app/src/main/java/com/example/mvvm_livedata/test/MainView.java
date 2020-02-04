package com.example.mvvm_livedata.test;

import com.example.mvvm_livedata.internal.contract.Viewable;

/**
 * Created by gagan on 4/2/20.
 **/
public interface MainView extends Viewable<MainViewModel>
{
	String getText();
}
