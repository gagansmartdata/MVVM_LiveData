package com.example.mvvm_livedata.test;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_livedata.R;
import com.example.mvvm_livedata.databinding.ActivityMainBinding;
import com.example.mvvm_livedata.internal.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> implements MainView
{

	@Override
	protected int setLayoutId()
	{
		return R.layout.activity_main;
	}

	@Override
	protected void onCreateActivityG()
	{
	}

	@Override
	public void initViews()
	{
		getDataBinder().tvTitle.setText(getViewModel().showText());
	}

	@Override
	protected MainViewModel initViewModel()
	{
		return new ViewModelProvider(this).get(MainViewModel.class);
	}

	@Override
	public String getText()
	{
		return "Dummy text here";
	}

	@Override
	public Context getActivityG()
	{
		return MainActivity.this;
	}
}
