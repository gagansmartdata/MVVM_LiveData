package com.example.mvvm_livedata.features.splash;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.example.mvvm_livedata.R;
import com.example.mvvm_livedata.databinding.ActivitySplashBinding;
import com.example.mvvm_livedata.features.login.LoginActivity;
import com.example.mvvm_livedata.internal.base.BaseActivityNoViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class SplashActivity extends BaseActivityNoViewModel<ActivitySplashBinding>
{

	@SuppressLint("CheckResult")
	@Override
	protected void onCreateViewG()
	{
		Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> {
			LoginActivity.start(getActivityG());
		});
	}

	@Override
	public Activity getActivityG()
	{
		return SplashActivity.this;
	}

	@Override
	protected int setLayoutId()
	{
		return R.layout.activity_splash;
	}
}
