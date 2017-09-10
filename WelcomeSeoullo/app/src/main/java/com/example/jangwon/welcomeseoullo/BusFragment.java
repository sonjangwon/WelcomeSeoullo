package com.example.jangwon.welcomeseoullo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("setJavaScriptEnabled")
public class BusFragment extends Fragment {


    StringBuilder busUrl= new StringBuilder("https://m.map.naver.com/directions/?ename=%EC%84%9C%EC%9A%B8%EC%97%AD%20%EA%B2%BD%EB%B6%80%EC%84%A0&ex=126.96961950000002&ey=37.5536067&eex=126.8915131&eey=37.5089833&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/list/%25EA%25B4%2591%25EC%25A7%2584%25EA%25B5%25AC%2520%25EA%25B5%25B0%25EC%259E%2590%25EB%258F%2599,127.0738887,37.5515277,,,true,/%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%2520%25EA%25B2%25BD%25EB%25B6%2580%25EC%2584%25A0,126.9706856,37.5545373,126.9720453,37.5546894,false,11630456/0");
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_bus, container, false);
        webView();
        return view;
    }
    private void webView() {

        WebView webView=(WebView)view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(busUrl.toString());
    }

}
