package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class PathInfoActivity extends AppCompatActivity {
    //현재위치에서 서울로7017까지 경로안내를 제공하는 Activity
    String getString;

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    TextView startPointAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_info);

        //BusActivity에서 자동차경로 혹은 도보경로의 버튼을 누를때를 구분하기 위하여 값을 전달받아 구분한다.
        Intent intent = getIntent();
        getString = intent.getExtras().getString("Path");

        Toast.makeText(getApplicationContext(),getString,Toast.LENGTH_SHORT).show();
        final Button byCarButton = (Button) findViewById(R.id.byCar);
        final Button byBusButton = (Button) findViewById(R.id.byBus);
        final Button onFootButton = (Button) findViewById(R.id.onFoot);
        startPointAddress = (TextView) findViewById(R.id.startPointAddress);
        //BusActivity에서 전달받은 값이 carPath인 경우
        if(getString.equals("carPath"))
        {
            Toast.makeText(getApplicationContext(),getString,Toast.LENGTH_SHORT).show();
            byCarButton.setTextColor(Color.parseColor("#FF0000"));
            byBusButton.setTextColor(Color.parseColor("#000000"));
            onFootButton.setTextColor(Color.parseColor("#000000"));
            switchFragment("carPath");
        }
        //BusActivity에서 전달받은 값이 footPath 경우
        else if(getString.equals("footPath"))
        {
            onFootButton.setTextColor(Color.parseColor("#FF0000"));
            byBusButton.setTextColor(Color.parseColor("#000000"));
            byCarButton.setTextColor(Color.parseColor("#000000"));
            switchFragment("footPath");
        }
        else {
            byCarButton.setTextColor(Color.parseColor("#FF0000"));
        }
        byCarButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                //자동차 경로안내 버튼을 누를 경우
                byCarButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                onFootButton.setTextColor(Color.parseColor("#000000"));
                switchFragment(view);
            }

        });
        byBusButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                //대중교통 경로안내 버튼을 누를 경우
                byBusButton.setTextColor(Color.parseColor("#FF0000"));
                byCarButton.setTextColor(Color.parseColor("#000000"));
                onFootButton.setTextColor(Color.parseColor("#000000"));
                startActivity(new Intent(PathInfoActivity.this, BusPathActivity.class));
                finish();
            }

        });
        onFootButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                //도보 경로안내 버튼을 누를 경우
                onFootButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                byCarButton.setTextColor(Color.parseColor("#000000"));
                switchFragment(view);
            }

        });

        // 사용자의 위치 수신을 위한 세팅 //
        settingGPS();
        // 사용자의 현재 위치 //
        getMyLocation();
        //위도경도를 상세주소로 변경
        reverseGeocoder();
    }

    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
    public void switchFragment(View view){
        Fragment fr = new CarFragment();

        if(view == findViewById(R.id.byCar)){
            fr = new CarFragment();
            Toast.makeText(getApplicationContext(),"byCar",Toast.LENGTH_SHORT).show();

        }else if(view == findViewById(R.id.byBus)){
            fr = new BusFragment();
            Toast.makeText(getApplicationContext(),"byBus",Toast.LENGTH_SHORT).show();
        }else{
            fr = new FootFragment();
            Toast.makeText(getApplicationContext(),"onFoot",Toast.LENGTH_SHORT).show();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }

    //대중경로Activity에서 자동차경로 혹은 도보경로를 클릭한 경우
    public void switchFragment(String path){
        Fragment fr = new CarFragment();

        if(path == "carPath"){
            fr = new CarFragment();
            Toast.makeText(getApplicationContext(),"byCar",Toast.LENGTH_SHORT).show();

        }else{
            fr = new FootFragment();
            Toast.makeText(getApplicationContext(),"onFoot",Toast.LENGTH_SHORT).show();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
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
                startPointAddress.setText(list.get(0).getAddressLine(0).toString());
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
//                CarFragment mainFragment = (CarFragment) getFragmentManager().findFragmentById(R.id.fragment_place);
//                mainFragment.loadData(currentLatitude,currentLongitude);
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
