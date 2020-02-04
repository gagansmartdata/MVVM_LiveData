package com.example.mvvm_livedata.internal.constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by gagan on 07 Mar 2020.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Constants
{
	@interface Extras
	{
		String IS_EDIT           = "is_edit";
		String DATA              = "data";
		String TRANSITION_NAME_1 = "name";
		String TRANSITION_NAME_2 = "name2";
	}

	@interface RequestCode
	{
	}

}
