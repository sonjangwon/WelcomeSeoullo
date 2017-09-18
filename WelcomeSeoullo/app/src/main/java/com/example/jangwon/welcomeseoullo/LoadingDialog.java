package com.example.jangwon.welcomeseoullo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;

class LoadingDialog {

    private static LoadingDialog loadingDialog;

    AppCompatDialog progressDialog;
    ImageView img_loading_frame;
    AnimationDrawable frameAnimation;
//    TextView tv_progress_message;

    static LoadingDialog getInstance() {
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog();
        }
        return loadingDialog;
    }

    private LoadingDialog() {

    }

    public void progressON(Activity activity, String message) {

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

//        tv_progress_message = (TextView) progressDialog.findViewById(tv_progress_message);
//        if (!TextUtils.isEmpty(message)) {
//            tv_progress_message.setText(message);
//        }

    }

//    public void progressSET(String message) {
//
//        if (progressDialog == null || !progressDialog.isShowing()) {
//            return;
//        }
//
//        tv_progress_message = (TextView) progressDialog.findViewById(R.id.tv_progress_message);
//        if (!TextUtils.isEmpty(message)) {
//            tv_progress_message.setText(message);
//        }
//    }

    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            //progressDialog.cancel();
        }
    }

}
