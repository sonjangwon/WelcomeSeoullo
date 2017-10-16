package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.FacilityMenu.GuideInfoFragment;
import com.example.jangwon.welcomeseoullo.HomeMenu.HomeFragment;
import com.example.jangwon.welcomeseoullo.NavigationMenu.PathInfoFragment;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    int currentMenu;
    BottomNavigationView bottomNavigationView;

    private ViewPager mainViewPager;
    ViewPagerAdapter adapter;
    MenuItem prevMenuItem;

    HomeFragment homeFragment;
    GuideInfoFragment guideInfoFragment;
    BlankFragment arFragment;
    PathInfoFragment pathInfoFragment;
    FacilityFragment facilityFragment;

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        mainViewPager.setOffscreenPageLimit(5);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        currentMenu = R.id.action_home;
        setupViewPager(mainViewPager);
        prevMenuItem = bottomNavigationView.getMenu().getItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        currentMenu = R.id.action_home;
                        mainViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_facility:
                        currentMenu = R.id.action_facility;
                        mainViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_AR:
                        currentMenu = R.id.action_AR;
                        mainViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_route:
                        currentMenu = R.id.action_route;
                        mainViewPager.setCurrentItem(3);
                        break;
                    case R.id.action_settings:
                        currentMenu = R.id.action_settings;
                        mainViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                currentMenu = bottomNavigationView.getMenu().getItem(position).getItemId();
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 사용자의 위치 수신을 위한 세팅 //
        settingGPS();
        // 사용자의 현재 위치 //
        getMyLocation();
        //위도경도를 상세주소로 변경
        reverseGeocoder();

        ManagementLocation.getInstance().setCurrentLatitude(currentLatitude);
        ManagementLocation.getInstance().setCurrentLongitude(currentLongitude);
        ManagementLocation.getInstance().setCurrentAddress(currentAddress);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getFragmentManager());

        homeFragment = new HomeFragment();
        guideInfoFragment = new GuideInfoFragment();
        arFragment = new BlankFragment();
        pathInfoFragment = new PathInfoFragment();
        facilityFragment = new FacilityFragment();

        adapter.addFragment(homeFragment);
        adapter.addFragment(guideInfoFragment);
        adapter.addFragment(arFragment);
        adapter.addFragment(pathInfoFragment);
        adapter.addFragment(facilityFragment);

        viewPager.setAdapter(adapter);
    }

//    public void switchFragment(){
//        fragmentManager = getFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_right);
//        fragmentTransaction.replace(R.id.viewPager, fragment);
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//        fragmentTransaction.commit();
//    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(currentMenu == R.id.action_home){
            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
            }
            else {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bottom_navigation_main, menu);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BMDOHYEON_ttf.ttf");
        for(int i=0;i<5;i++){
            applyFontToMenuItem(bottomNavigationView.getMenu().getItem(i), typeface);
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void applyFontToMenuItem(MenuItem mi, Typeface font) {
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
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
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, locationListener);
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locationListener);

            //서울시 앱 공모전 심사를 위한 wifi-network를 통한 현재 위치 인식, 실내 현재위치를 받아올 때 좋음
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, locationListener);

            // 수동으로 위치 구하기
//            String locationProvider = LocationManager.GPS_PROVIDER;
            String locationProvider = LocationManager.NETWORK_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                currentLongitude = currentLocation.getLongitude();
                currentLatitude = currentLocation.getLatitude();
                Log.d("Main", "longtitude=" + currentLongitude + ", latitude=" + currentLatitude);
//                CarFragment mainFra// gment = (CarFragment) getFragmentManager().findFragmentById(R.id.fragment_place);
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

                currentLongitude = latitude;
                currentLatitude = longitude;
                Log.e("Latitude2", String.valueOf(latitude));
                Log.e("Longitude2", String.valueOf(longitude));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
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
                Log.e("currentAddress2", currentAddress);
            }
        }
    }

}