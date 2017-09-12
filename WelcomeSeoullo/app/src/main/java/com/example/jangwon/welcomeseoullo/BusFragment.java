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

    StringBuilder busUrl= new StringBuilder("https://m.map.naver.com/directions/?ex=126.96961950000002&ey=37.5536067&eex=126.8915131&eey=37.5089833&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/list/세종대학교,127.07313899999997,37.5502596,,,false,/서울로7017,126.9697727,37.5580094,,,false,/0");
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