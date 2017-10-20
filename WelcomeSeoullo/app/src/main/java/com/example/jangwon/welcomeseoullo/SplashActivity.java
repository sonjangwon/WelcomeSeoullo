package com.example.jangwon.welcomeseoullo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

    int SPLASH_TIME=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        changeStatusBarColor();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ManagePublicData.getInstance().parsePublicToilet.execute();
                ManagePublicData.getInstance().parsePublicPark.execute();
                ManagePublicData.getInstance().parsePublicParkingLot.execute();
                ManagePublicData.getInstance().parseTraditionalMarket.execute();

//                ManagePublicData.getInstance().parsePublicToilet.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                ManagePublicData.getInstance().parsePublicPark.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                ManagePublicData.getInstance().parsePublicParkingLot.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                ManagePublicData.getInstance().parseTraditionalMarket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                overridePendingTransition(0,android.R.anim.fade_in);
                startActivity(new Intent(SplashActivity.this, GuideAppInfo.class));
                finish();
            }
        },SPLASH_TIME);
    }

    //효완이 코드 사용
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SplashActivity.this.finish();
        System.exit(0);
    }
}
