package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


public class GuideInfoActivity extends AppCompatActivity {


    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    TextView addressTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_info);

        addressTextView = (TextView)findViewById(R.id.addressTextView);
        Spinner sortSpinner = (Spinner)findViewById(R.id.sortSpinner);
        Spinner distanceSpinner = (Spinner)findViewById(R.id.distanceSpinner);
        final ImageButton listImageButton = (ImageButton)findViewById(R.id.listImageButton);
        final ImageButton mapPointImageButton = (ImageButton)findViewById(R.id.mapPointImageButton);

        listImageButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                switchFragment(view);
                mapPointImageButton.setBackgroundResource(R.drawable.reversemappoint);
//                listImageButton.setBackgroundResource(R.drawable.list);
            }

        });
        mapPointImageButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                switchFragment(view);
                mapPointImageButton.setBackgroundResource(R.drawable.mappoint);
//                listImageButton.setBackgroundResource(R.drawable.list);
            }

        });

        //스피너 어댑터 설정
        ArrayAdapter sortAdapter = ArrayAdapter.createFromResource(this,R.array.sort,android.R.layout.simple_spinner_item);
        ArrayAdapter distanceAdapter = ArrayAdapter.createFromResource(this,R.array.distance,android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        //스피너 이벤트 발생
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //각 항목 클릭시 포지션값을 토스트에 띄운다.
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpinner.setAdapter(distanceAdapter);

        //스피너 이벤트 발생
        distanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //각 항목 클릭시 포지션값을 토스트에 띄운다.
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 사용자의 위치 수신을 위한 세팅 //
        settingGPS();
        // 사용자의 현재 위치 //
        getMyLocation();
        //위도경도를 상세주소로 변경
        reverseGeocoder();

    }

    //버튼으로 리스트뷰, 맵포인트를 클릭한 경우 각 프레그먼트가 실행된다.
    public void switchFragment(View view){
        Fragment fr = new MapGuideFragment();

        if(view == findViewById(R.id.mapPointImageButton)){
            fr = new MapGuideFragment();
            Toast.makeText(getApplicationContext(),"mapPointImageButton",Toast.LENGTH_SHORT).show();
        }
        else if(view == findViewById(R.id.listImageButton)){
            fr = new ListGuideFragment();
            Toast.makeText(getApplicationContext(),"listImageButton",Toast.LENGTH_SHORT).show();

        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fagment_mapGuide, fr);
        fragmentTransaction.commit();
    }
    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
    public void switchFragments(View view){
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
    //에러걸린부분
    public void changeCurrentLoationText(String location)
    {
//        TextView addressTextView2 = (TextView)findViewById(R.id.addressTextView);
//        Log.e("GuideInfoActivity",location);
//        addressTextView2.setText(location);
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
                addressTextView.setText(list.get(0).getAddressLine(0).toString());
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
