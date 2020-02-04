package com.example.mvvm_livedata.features.login;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_livedata.R;
import com.example.mvvm_livedata.databinding.ActivityLoginBinding;
import com.example.mvvm_livedata.internal.base.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> implements LoginView
{

	public static void start(Context context) {
	    Intent starter = new Intent(context, LoginActivity.class);
	    context.startActivity(starter);
	}

	@Override
	protected LoginViewModel initViewModel()
	{
		return new ViewModelProvider(this).get(LoginViewModel.class);
	}

	@Override
	protected int setLayoutId()
	{
		return R.layout.activity_login;
	}

	@Override
	protected void onCreateActivityG()
	{

	}

	@Override
	public void initViews()
	{

	}

	@Override
	public Context getActivityG()
	{
		return LoginActivity.this;
	}
}
