package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.HomeMenu.NewsCrawling;
import com.example.jangwon.welcomeseoullo.HomeMenu.PrefManager;

import java.util.ArrayList;
import java.util.List;

public class GuideAppInfo extends Activity {
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnStart;
    private PrefManager prefManager;
    private ImageView imageISeoulU;
    int count = 0;

    private static final int MY_PERMISSIONS_REQUEST_ACCOUNTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //crawling 부분인데 나중에 추가할 예정
        NewsCrawling.getInstance().newThread.execute();

        // Checking for first time launch - before calling setContentView()
        //sharedPrefence에 저장ㅇ해놓고 실행안되게
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(true);
        }
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_guide2);


        //비디오 배경 설정
        MyVideoView mVideoView = (MyVideoView) findViewById(R.id.bgVideoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnStart = (Button) findViewById(R.id.btn_start);
        imageISeoulU = (ImageView) findViewById(R.id.i_seoul_u);
        btnStart.setVisibility(View.INVISIBLE);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.activity_slide1,
                R.layout.activity_slide2,
                R.layout.activity_slide3 };

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    launchHomeScreen();
            }
        });

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];
        //해당화면 일때
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        //해당화면 아닐때
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) { //이부분 -1이라고 고침
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);

        alertCheckGPS();
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layouts.length - 1) {
                btnStart.setVisibility(View.VISIBLE);

            } else {
                btnStart.setVisibility(View.INVISIBLE);

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
            GuideAppInfo.this.finish();
            System.exit(0);
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    //핸드폰 고유 번호를 만들기 위해서 사용자에게 권한 획득 과정, API 23이상인 경우에만 해당, 런타임 중에 권한 획득
    private boolean checkAndRequestPermissions() {

        int permissionPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int permissionFineLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCoarseLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionMedia = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

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
        if (permissionMedia != PackageManager.PERMISSION_GRANTED){
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST_ACCOUNTS);
            return false;
        }
        else{

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return true;
        }
    }

    //핸드폰 고유 번호를 만들기 위해서 사용자에게 권한 획득 과정 Permission 처리후 callback
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCOUNTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                else {
                    //You did not accept the request can not use the functionality.
                    Toast.makeText(GuideAppInfo.this, "권한사용을 동의해주셔야 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
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
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            moveConfigGPS();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
        else {
            checkAndRequestPermissions();
        }
    }

    // GPS 설정화면으로 이동
    private void moveConfigGPS() {

//        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        startActivity(gpsOptionsIntent);

        Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(myIntent,100);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        isGPSEnabled();
    }

    private void isGPSEnabled(){
        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean statusOfGPS = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(statusOfGPS){
            checkAndRequestPermissions();
        }
        else{
            alertCheckGPS();
        }
    }
}
