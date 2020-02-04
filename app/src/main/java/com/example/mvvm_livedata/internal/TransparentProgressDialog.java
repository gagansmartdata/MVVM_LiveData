package com.example.mvvm_livedata.internal;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

public class TransparentProgressDialog extends Dialog {


    private ProgressBar iv;

    public TransparentProgressDialog(Context context, int resourceIdOfImage) {

        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        FrameLayout layout = new FrameLayout(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        layout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        iv = new ProgressBar(context);
        FrameLayout.LayoutParams paramsProgress = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsProgress.gravity = Gravity.CENTER;
        iv.setLayoutParams(paramsProgress);
        layout.addView(iv);
        addContentView(layout, params);
    }

    @Override
    public void show() {
        super.show();
//DEFINE ANIMATION WHICH YOU WANT
//        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f,
//                Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF,
//                .5f);
//        anim.setInterpolator(new LinearInterpolator());
//        anim.setRepeatCount(Animation.INFINITE);
//        anim.setDuration(700);
//        iv.setAnimation(anim);
//        iv.startAnimation(anim);
    }
}