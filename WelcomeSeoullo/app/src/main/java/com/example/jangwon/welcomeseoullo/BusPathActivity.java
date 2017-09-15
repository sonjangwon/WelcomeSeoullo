package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

@SuppressLint("setJavaScriptEnabled")
public class BusPathActivity extends AppCompatActivity {

    String busUrl;
    View view;
    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    TextView startPointAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_path);
        // 사용자의 위치 수신을 위한 세팅 //
        settingGPS();
        // 사용자의 현재 위치 //
        getMyLocation();
        //위도경도를 상세주소로 변경
        reverseGeocoder();
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
                Intent intent = new Intent(BusPathActivity.this, PathInfoActivity.class);
                intent.putExtra("Path","carPath");
                startActivityForResult(intent, 1);
                finish();
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
                Intent intent = new Intent(BusPathActivity.this, PathInfoActivity.class);
                intent.putExtra("Path","footPath");
                startActivityForResult(intent, 1);
                finish();
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
                currentAddress=list.get(0).getAddressLine(0).toString();

            }
        }


    }
    //현재위치 받아오기
    private void getMyLocation() {
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // 사용자 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                currentLongitude = currentLocation.getLongitude();
                currentLatitude = currentLocation.getLatitude();
                Log.d("Main", "longtitude=" + currentLongitude + ", latitude=" + currentLatitude);
            }
        }
    }


    // GPS 를 받기 위한 매니저와 리스너 설정
    private void settingGPS() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //위치가 바뀔경우
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                // TODO 위도, 경도로 하고 싶은 것
//                Log.e("Latitude2", String.valueOf(latitude));
//                Log.e("Longitude2", String.valueOf(longitude));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
    }
}
