package com.example.jangwon.welcomeseoullo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("setJavaScriptEnabled")
public class Test2Activity extends AppCompatActivity {

    StringBuilder busUrl= new StringBuilder("https://m.map.naver.com/directions/?ex=126.96961950000002&ey=37.5536067&eex=126.8915131&eey=37.5089833&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/list/세종대학교,127.07313899999997,37.5502596,,,false,/서울로7017,126.9697727,37.5580094,,,false,/0");
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        webView();
        final Button byCarButton = (Button) findViewById(R.id.byCar);
        final Button byBusButton = (Button) findViewById(R.id.byBus);
        final Button onFootButton = (Button) findViewById(R.id.onFoot);
        byBusButton.setTextColor(Color.parseColor("#FF0000"));
        byCarButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                byCarButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                onFootButton.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(Test2Activity.this, PathInfoActivity.class);
                intent.putExtra("carPath", "carPath");
                startActivityForResult(intent, 1);
                finish();
            }

        });
        byBusButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
//                byBusButton.setTextColor(Color.parseColor("#FF0000"));
//                byCarButton.setTextColor(Color.parseColor("#000000"));
//                onFootButton.setTextColor(Color.parseColor("#000000"));
//                startActivity(new Intent(PathInfoActivity.this, Test2Activity.class));
//                switchFragment(view);
            }

        });
        onFootButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                onFootButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                byCarButton.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(Test2Activity.this, PathInfoActivity.class);
                intent.putExtra("footPath", "footPath");
                startActivityForResult(intent, 1);
                finish();
            }

        });
    }

    private void webView() {

        WebView webViewTest=(WebView)findViewById(R.id.webViewTest);
        webViewTest.getSettings().setJavaScriptEnabled(true);
        webViewTest.setWebViewClient(new WebViewClient());
        webViewTest.loadUrl(busUrl.toString());
    }
}
