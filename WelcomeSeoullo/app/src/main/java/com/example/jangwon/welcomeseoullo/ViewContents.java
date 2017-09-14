package com.example.jangwon.welcomeseoullo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by woga1 on 2017-09-13.
 */

public class ViewContents extends Activity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        String num = intent.getExtras().getString("urlNum");
        WebView webView = (WebView)findViewById(R.id.web);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEView.do?board_seq="+num+"&pageIndex=1&pageSize=10&searchCondition=all&searchKeyword=");
    }
}
