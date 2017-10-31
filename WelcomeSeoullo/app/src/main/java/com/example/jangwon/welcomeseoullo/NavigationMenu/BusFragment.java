package com.example.jangwon.welcomeseoullo.NavigationMenu;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;

@SuppressLint("setJavaScriptEnabled")
public class BusFragment extends Fragment  {

    // 사용자 위치 수신기
    double currentLatitude;
    double currentLongitude;
    String currentAddress;

    String busUrl;
    View view;
    WebView webView;

    // has a touch press started?
    private boolean touchStarted = false;
    // co-ordinates of image
    private int x, y;

    public BusFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_bus, container, false);

        webView=(WebView) view.findViewById(R.id.webView);

        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
        currentAddress = ManagementLocation.getInstance().getCurrentAddress();
//        https://m.map.naver.com/directions/?ex=126.96961950000002&ey=37.5536067&eex=126.96961950000002&eey=37.553606&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/list/"+currentAddress+","+currentLongitude+","+currentLatitude+",,,false,/서울로7017,126.9697727,37.5580094,,,false,/0
//        busUrl= "https://m.map.naver.com/directions/?ex=126.96961950000002&ey=37.5536067&eex="+currentLongitude+"&eey="+currentLatitude+"&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/list/"+currentAddress+","+currentLongitude+","+currentLatitude+",,,false,/서울로7017,126.9697727,37.5580094,,,false,/0";
//        busUrl = "https://m.map.daum.net/actions/publicRoute?startLoc="+currentAddress+"&sxEnc=MNPNPP&syEnc=QNLPQQM&endLoc=서울로7017&exEnc=LVMPRP&eyEnc=QNLSUUW&ids=P7949668%2CP27327842&service=";
//        busUrl="https://www.google.co.kr/maps/dir/%EC%84%B8%EC%A2%85%EB%8C%80%ED%95%99%EA%B5%90+%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B4%91%EC%A7%84%EA%B5%AC+%EA%B5%B0%EC%9E%90%EB%8F%99+%EB%8A%A5%EB%8F%99%EB%A1%9C+209/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%A4%91%EA%B5%AC+%EB%A7%8C%EB%A6%AC%EB%8F%991%EA%B0%80+1+%EC%84%9C%EC%9A%B8%EB%A1%9C7017/@37.5606405,126.9969973,13.26z/data=!4m14!4m13!1m5!1m1!1s0x357ca4d0720eecc1:0x1a7ad975c6b5e4eb!2m2!1d127.073139!2d37.5502596!1m5!1m1!1s0x357ca2607008c5c3:0xa35240ec632d4307!2m2!1d126.9715781!2d37.5566149!3e3";
        busUrl = "https://m.map.naver.com/directions/?ex=126.96961950000002&ey=37.5536067&eex="+currentLongitude+"&eey="+currentLatitude+"&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/detail/"+currentAddress+","+currentLongitude+","+currentLatitude+",,,false,13479327/%25EC%2584%259C%25EC%259A%25B8%25EB%25A1%259C7017,126.9697727,37.5580094,,,false,/0/0/map/0";
        webView();
        if((int)ManagementLocation.getInstance().getCurrentLatitude()!=0 && (int)currentLatitude==0) {
            currentLatitude=ManagementLocation.getInstance().getCurrentLatitude();
            currentLongitude=ManagementLocation.getInstance().getCurrentLongitude();


        }

//웹뷰 버튼이벤트 투명이나 흰색이나 일단 해보기
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if((int)ManagementLocation.getInstance().getCurrentLatitude()!=0 && (int)currentLatitude==0) {
            currentLatitude=ManagementLocation.getInstance().getCurrentLatitude();
            currentLongitude=ManagementLocation.getInstance().getCurrentLongitude();
            busUrl = "https://m.map.naver.com/directions/?ex=126.96961950000002&ey=37.5536067&eex="+currentLongitude+"&eey="+currentLatitude+"&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/detail/"+currentAddress+","+currentLongitude+","+currentLatitude+",,,false,13479327/%25EC%2584%259C%25EC%259A%25B8%25EB%25A1%259C7017,126.9697727,37.5580094,,,false,/0/0/map/0";
            webView.loadUrl(busUrl.toString());
//            webView.reload();
        }
    }

    //busUrl로 받은 웹주소를 웹뷰를 이용하여 띄운다.
    private void webView() {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
//                webView.loadUrl(busUrl);
//                webView.goBack();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

        });

        webView.loadUrl(busUrl.toString());
//        webView.setOnTouchListener(gestureListener);
        webView.setHorizontalScrollBarEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.setScrollbarFadingEnabled(true);

    }
}
