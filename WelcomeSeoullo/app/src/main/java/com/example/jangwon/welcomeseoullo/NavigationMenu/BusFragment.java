package com.example.jangwon.welcomeseoullo.NavigationMenu;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;

@SuppressLint("setJavaScriptEnabled")
public class BusFragment extends Fragment  {

    // 사용자 위치 수신기
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    boolean firstScreen = true;
    int touchX =0;
    int touchY =0;
    String busUrl;
    View view;
    WebView webView;

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
        busUrl = "https://m.map.daum.net/actions/publicRoute?startLoc="+currentAddress+"&sxEnc=MNPNPP&syEnc=QNLPQQM&endLoc=서울로7017&exEnc=LVMPRP&eyEnc=QNLSUUW&ids=P7949668%2CP27327842&service=";
        webView();
//웹뷰 버튼이벤트 투명이나 흰색이나 일단 해보기
        return view;
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

            @SuppressWarnings("deprecation")  //수정하기
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Toast.makeText(getActivity(), "shouldOverrideUrlLoading", Toast.LENGTH_SHORT).show();
                webView.setBackgroundColor(Color.rgb(0,255,255));
                return true;
            }
//             @Override
//             public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                 Toast.makeText(getActivity(), "shouldOverrideUrlLoading", Toast.LENGTH_SHORT).show();
//                 Toast.makeText(getActivity(),request.toString(),Toast.LENGTH_SHORT).show();
//                 Uri requested = Uri.parse(busUrl.toString());
//                 String scheme = requested.getScheme();
//
//                 if (scheme != null && scheme.contains("detail")) {
//                     Toast.makeText(getActivity(),"detail",Toast.LENGTH_SHORT).show();
//                     Log.d("WebView", "overriden"); //***
//
//                 return true; // true를 리턴하면 WebView는 해당 URL을 렌더하지 않는다.
//                 } else {
//                     return super.shouldOverrideUrlLoading(view, request);
//                 }
//             }


        });

        webView.loadUrl(busUrl.toString());
//        webView.setOnTouchListener(gestureListener);
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

//                webView.setBackgroundResource(Color.blue(22));

//               firstScreen=false;
//               if(touchX == 0 && touchY == 0)
//               {
//                   webView.goBack();
//                   touchX = (int)motionEvent.getX();
//                   touchY = (int)motionEvent.getY();
////                   Toast.makeText(getActivity(),"onTouch",Toast.LENGTH_SHORT).show();
//                   Log.e("testt","onTouch");
////                   webView.onPause();
//               }
//               if(motionEvent.getAction() == MotionEvent.ACTION_UP)
//               {
//                   int nowTouchX = (int)motionEvent.getX();
//                   int nowTouchY = (int)motionEvent.getY();
//
//                   if(Math.abs(touchX - nowTouchX)  >= 10 || Math.abs(touchY - nowTouchY)  >= 10)
//                   {
////                       webView.setVerticalScrollBarEnabled(true);
//                       Toast.makeText(getActivity(), "스크롤이벤트가 발생했습니다", Toast.LENGTH_SHORT).show();
//                       Log.e("testt","스크롤이벤트가");
//                   }
//
//                   touchX=0;
//                   touchY=0;
//               }
//               if(motionEvent.getAction() == MotionEvent.ACTION_MOVE)
//               {
//                   webView.computeScroll();
////                   Toast.makeText(getActivity(), "ACTION_MOVE", Toast.LENGTH_SHORT).show();
//                   Log.e("testt","ACTION_MOVE");
//               }
//               if(motionEvent.getAction() == MotionEvent.ACTION_SCROLL)
//               {
//                   Toast.makeText(getActivity(), "ACTION_SCROLL", Toast.LENGTH_SHORT).show();
//                   Log.e("testt","ACTION_SCROLL");
//               }
//               if(motionEvent.getAction() == MotionEvent.ACTION_BUTTON_RELEASE) {
//                   Toast.makeText(getActivity(), "ACTION_BUTTON_RELEASE", Toast.LENGTH_SHORT).show();
//                   Log.e("testt","ACTION_BUTTON_RELEASE");
//               }

                return false;
            }
//        });
        });
    }



}
