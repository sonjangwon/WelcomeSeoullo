package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jangwon.welcomeseoullo.R;

public class ViewContents extends Activity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        String num = intent.getExtras().getString("urlNum");
        changeToStatusBar();
        WebView webView = (WebView)findViewById(R.id.web);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEView.do?board_seq="+num+"&pageIndex=1&pageSize=10&searchCondition=all&searchKeyword=");
    }

    public void changeToStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            //상태바 남는 공간 활용
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
