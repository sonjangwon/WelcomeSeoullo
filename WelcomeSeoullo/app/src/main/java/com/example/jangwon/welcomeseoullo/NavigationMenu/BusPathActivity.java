package com.example.jangwon.welcomeseoullo.NavigationMenu;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;

import java.io.IOException;
import java.util.List;

@SuppressLint("setJavaScriptEnabled")
public class BusPathActivity extends AppCompatActivity {
    //버스 경로안내
    String busUrl;
    View view;

    // 사용자 위치 수신기
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    TextView startPointAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_path);

        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
        currentAddress = ManagementLocation.getInstance().getCurrentAddress();

        busUrl= "https://m.map.naver.com/directions/?ex=126.96961950000002&ey=37.5536067&eex="+currentLongitude+"&eey="+currentLatitude+"&edid=11630456&incomeUrl=https%3A%2F%2Fm.map.naver.com%2Fsearch2%2Fsearch.nhn%3Fquery%3D%25EC%2584%259C%25EC%259A%25B8%25EC%2597%25AD%26sm%3Dhty#/publicTransit/list/"+currentAddress+","+currentLongitude+","+currentLatitude+",,,false,/서울로7017,126.9697727,37.5580094,,,false,/0";
        webView();
        final Button byCarButton = (Button) findViewById(R.id.byCar);
        final Button byBusButton = (Button) findViewById(R.id.byBus);
        final Button onFootButton = (Button) findViewById(R.id.onFoot);
        startPointAddress = (TextView) findViewById(R.id.startPointAddress);
        startPointAddress.setText(currentAddress);
        byBusButton.setTextColor(Color.parseColor("#FF0000"));

        //버스경로안내에서 자동차경로를 클릭한 경우
        byCarButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                byCarButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                onFootButton.setTextColor(Color.parseColor("#000000"));

//                PathInfoFragment.byCarButton.callOnClick();
                onBackPressed();
            }
        });

        byBusButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
            }

        });

        //버스경로안내에서 도보경로를 클릭한 경우
        onFootButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                onFootButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                byCarButton.setTextColor(Color.parseColor("#000000"));

//                PathInfoFragment.onFootButton.callOnClick();
                onBackPressed();
            }
        });
    }

    //busUrl로 받은 웹주소를 웹뷰를 이용하여 띄운다.
    private void webView() {

        WebView webViewTest=(WebView)findViewById(R.id.webViewTest);
        webViewTest.getSettings().setJavaScriptEnabled(true);
        webViewTest.setWebViewClient(new WebViewClient());
        webViewTest.loadUrl(busUrl.toString());
    }

    //역 지오코딩(위도경도를 상세주소로 변경)
    public void reverseGeocoder()
    {
        final Geocoder geocoder = new Geocoder(this);
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(
                    currentLatitude, // 위도
                    currentLongitude, // 경도
                    10); // 얻어올 값의 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size()==0) {
                Log.e("noList", "noList");
            } else {
//                tv.setText(list.get(0).toString());
                currentAddress=list.get(0).getAddressLine(0).toString().substring(5);

            }
        }


    }

}
