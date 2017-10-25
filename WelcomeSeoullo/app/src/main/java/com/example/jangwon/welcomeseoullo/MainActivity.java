package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.ARMenu.BlankFragment;
import com.example.jangwon.welcomeseoullo.FacilityMenu.GuideInfoFragment;
import com.example.jangwon.welcomeseoullo.HomeMenu.HomeFragment;
import com.example.jangwon.welcomeseoullo.NavigationMenu.PathInfoFragment;
import com.example.jangwon.welcomeseoullo.SettingsMenu.SettingsFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends Activity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private static final int MY_PERMISSIONS_REQUEST_ACCOUNTS = 1;

    int currentMenu;
    BottomNavigationView bottomNavigationView;

    private ViewPager mainViewPager;
    ViewPagerAdapter adapter;
    MenuItem prevMenuItem;

    HomeFragment homeFragment;
    GuideInfoFragment guideInfoFragment;
    BlankFragment arFragment;
    PathInfoFragment pathInfoFragment;
    SettingsFragment settingsFragment;

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation = null;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeStatusBarColor();

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

        alertCheckGPS();

        checkAndRequestPermissions();
    }

    //효완이 코드 사용
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getFragmentManager());

        homeFragment = new HomeFragment();
        guideInfoFragment = new GuideInfoFragment();
        arFragment = new BlankFragment();
        pathInfoFragment = new PathInfoFragment();
        settingsFragment = new SettingsFragment();

        adapter.addFragment(homeFragment);
        adapter.addFragment(guideInfoFragment);
        adapter.addFragment(arFragment);
        adapter.addFragment(pathInfoFragment);
        adapter.addFragment(settingsFragment);

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(currentMenu == R.id.action_home){
            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
                removeAllPreferences();
                MainActivity.this.finish();
                System.exit(0);
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

    //앱 종료 시, SharedPreference 값(ALL Data) 삭제하기
    private void removeAllPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    //SharedPreference String 값 저장하기
    private void savePreferences(String key, String data){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, data);
        editor.commit();
    }

    private String GetDevicesUUID(Context mContext){

        final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();

        return deviceId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onResume() {
        super.onResume();
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/barefoot_regular.ttf");
//        for(int i=0;i<5;i++){
//            applyFontToMenuItem(bottomNavigationView.getMenu().getItem(i), typeface);
//        }
    }

    private void applyFontToMenuItem(MenuItem mi, Typeface font) {
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    //현재위치 받아오기
    private void getMyLocation() {
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 사용자 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            //서울시 앱 공모전 심사를 위한 wifi-network를 통한 현재 위치 인식, 실내 현재위치를 받아올 때 좋음
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, locationListener);

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.NETWORK_PROVIDER;
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
                currentAddress=list.get(0).getAddressLine(0).toString().substring(5);
                Log.e("currentAddress2", currentAddress);
            }
        }
    }

    //핸드폰 고유 번호를 만들기 위해서 사용자에게 권한 획득 과정, API 23이상인 경우에만 해당, 런타임 중에 권한 획득
    private boolean checkAndRequestPermissions() {

        int permissionPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int permissionFineLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCoarseLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (permissionFineLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionCoarseLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST_ACCOUNTS);
            return false;
        }
        else{
            // save UUID value in SharedPreference
            savePreferences("UUID", GetDevicesUUID(getApplicationContext()));
            savePreferences("language", "Korean");



            // 사용자의 위치 수신을 위한 세팅 //
            settingGPS();
            // 사용자의 현재 위치 //
            getMyLocation();
            //위도경도를 상세주소로 변경
            reverseGeocoder();

            ManagementLocation.getInstance().setCurrentLatitude(currentLatitude);
            ManagementLocation.getInstance().setCurrentLongitude(currentLongitude);
            ManagementLocation.getInstance().setCurrentAddress(currentAddress);
            return true;
        }
    }

    //핸드폰 고유 번호를 만들기 위해서 사용자에게 권한 획득 과정 Permission 처리후 callback
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCOUNTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission Granted Successfully. Write working code here.
                    // save UUID value in SharedPreference
                    savePreferences("UUID", GetDevicesUUID(getApplicationContext()));
                    savePreferences("language", "Korean");



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
                else {
                    //You did not accept the request can not use the functionality.
                    Toast.makeText(MainActivity.this, "권한사용을 동의해주셔야 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
                    //권한 획득이 거부되면 수행해야 할 적업이 표시됨
                    //일반적으로 작업을 처리할 메서드를 호출
                    checkAndRequestPermissions();
                }
                break;
        }
    }

    //gps체크
    private void alertCheckGPS() { //gps 꺼져있으면 켤 껀지 체크
        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(!locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("'어서와, 서울로 7017'을 이용하시려면 \n[위치] 권한을 허용해 주세요")
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveConfigGPS();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        else {

        }
    }

    // GPS 설정화면으로 이동
    private void moveConfigGPS() {
        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }
}