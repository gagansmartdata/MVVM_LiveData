package com.example.mvvm_livedata.features.main;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mvvm_livedata.R;
import com.example.mvvm_livedata.databinding.ActivityMainBinding;
import com.example.mvvm_livedata.internal.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
{

	@Override
	public Context getActivityG()
	{
		return MainActivity.this;
	}

	@Override
	protected MainViewModel initViewModel()
	{
		return new ViewModelProvider(this).get(MainViewModel.class);
	}

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
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
				R.id.navigation_home, R.id.navigation_feeds, R.id.navigation_messages)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
		NavigationUI.setupWithNavController(getDataBinder().navView, navController);
	}
}
