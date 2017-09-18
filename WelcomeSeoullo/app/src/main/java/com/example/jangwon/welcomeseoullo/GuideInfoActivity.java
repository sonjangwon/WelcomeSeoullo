package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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
    //주변 경로안내 Activity

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    String nowFragment="map";
    TextView addressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_info);

        //현재 위도경도,상세주소 받아오기
        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
        currentAddress = ManagementLocation.getInstance().getCurrentAddress();

        addressTextView = (TextView)findViewById(R.id.addressTextView);

        Spinner sortSpinner = (Spinner)findViewById(R.id.sortSpinner);
        Spinner distanceSpinner = (Spinner)findViewById(R.id.distanceSpinner);
        ManagementLocation.getInstance().setSortSpinner("전체");
        ManagementLocation.getInstance().setDistanceSpinner("2km");
        final ImageButton listImageButton = (ImageButton)findViewById(R.id.listImageButton);
        final ImageButton mapPointImageButton = (ImageButton)findViewById(R.id.mapPointImageButton);

        listImageButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                nowFragment="list";
                switchFragment(view);
                mapPointImageButton.setBackgroundResource(R.drawable.reversemappoint);
                listImageButton.setBackgroundResource(R.drawable.reverselistpoint);

            }

        });
        mapPointImageButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                nowFragment="map";
                switchFragment(view);
                mapPointImageButton.setBackgroundResource(R.drawable.mappoint);
                listImageButton.setBackgroundResource(R.drawable.listpoint);

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
//                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                switch (parent.getItemAtPosition(position).toString()) {
                    case "전체":
                        Toast.makeText(getApplicationContext(), "전체", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setSortSpinner("전체");
                        break;
                    case "공공화장실":
                        Toast.makeText(getApplicationContext(), "공공화장실", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setSortSpinner("공공화장실");
                        break;
                    case "주차장":
                        Toast.makeText(getApplicationContext(), "주차장", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setSortSpinner("주차장");
                        break;
                    case "공원":
                        Toast.makeText(getApplicationContext(), "공원", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setSortSpinner("공원");
                        break;
                    case "전통시장":
                        Toast.makeText(getApplicationContext(), "전통시장", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setSortSpinner("전통시장");
                        break;
                }
                switchFragments(nowFragment);
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
//                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                switch (parent.getItemAtPosition(position).toString()) {
                    case "500m":
                        Toast.makeText(getApplicationContext(), "500m", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setDistanceSpinner("500m");
                        break;
                    case "1km":
                        Toast.makeText(getApplicationContext(), "1km", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setDistanceSpinner("1km");
                        break;
                    case "1.5km":
                        Toast.makeText(getApplicationContext(), "1.5km", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setDistanceSpinner("1.5km");
                        break;
                    case "2km":
                        Toast.makeText(getApplicationContext(), "2km", Toast.LENGTH_SHORT).show();
                        ManagementLocation.getInstance().setDistanceSpinner("2km");
                        break;
                }
                switchFragments(nowFragment);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        settingGPS();
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
        fragmentTransaction.add(R.id.fagment_mapGuide, fr);
        fragmentTransaction.commit();
    }
    //Spinner로 fragment가 재생성 될 때
    public void switchFragments(String nowFragment){
        Fragment fr = new MapGuideFragment();

        if(nowFragment == "map"){
            fr = new MapGuideFragment();

        }else if(nowFragment == "list") {
            fr = new ListGuideFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fagment_mapGuide, fr);
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
                addressTextView.setText(list.get(0).getAddressLine(0).toString().substring(5));
                Log.e("addressTextView",list.get(0).getAddressLine(0).toString().substring(5));
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
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                // TODO 위도, 경도로 하고 싶은 것

                Log.e("Latitude2", String.valueOf(currentLatitude));
                Log.e("Longitude2", String.valueOf(currentLongitude));
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
