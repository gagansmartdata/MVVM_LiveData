package com.example.mvvm_livedata.test;

import com.example.mvvm_livedata.internal.base.BaseViewModel;

/**
 * Created by Gagan on 4/2/20.
 **/
public class MainViewModel extends BaseViewModel<MainView>
{
	public String showText()
	{
		return getView().getText();
	}

	@Override
	public void onResume()
	{
		getView().displayError("resumed");
	}
}
