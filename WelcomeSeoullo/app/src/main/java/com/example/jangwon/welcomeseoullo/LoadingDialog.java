package com.example.jangwon.welcomeseoullo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;

public class LoadingDialog {

    private static LoadingDialog loadingDialog;

    AppCompatDialog progressDialog;
    ImageView img_loading_frame;
    AnimationDrawable frameAnimation;

    public static LoadingDialog getInstance() {
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog();
        }
        return loadingDialog;
    }

    private LoadingDialog() {

    }

    public void progressON(Activity activity) {

        if (activity == null || activity.isFinishing()) {
            return;
        }

//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressSET(message);
//        } else {
//
//            progressDialog = new AppCompatDialog(activity);
//            progressDialog.setCancelable(false);
//            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            progressDialog.setContentView(R.layout.progress_loading);
//            progressDialog.show();
//        }

        progressDialog = new AppCompatDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_loading);
        progressDialog.show();

        img_loading_frame = (ImageView) progressDialog.findViewById(R.id.iv_frame_loading);
        frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();
        img_loading_frame.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });

    }

    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
