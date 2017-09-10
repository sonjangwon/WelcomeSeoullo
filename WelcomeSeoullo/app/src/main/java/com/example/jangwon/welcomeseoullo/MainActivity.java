package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private TextView textHome;
    private TextView textRoute;
    private TextView textAR;
    private TextView textFacility;
    private TextView textSettings;

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int currentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHome = (TextView) findViewById(R.id.text_home);
        textRoute = (TextView) findViewById(R.id.text_route);
        textAR = (TextView) findViewById(R.id.text_AR);
        textFacility = (TextView) findViewById(R.id.text_facility);
        textSettings = (TextView) findViewById(R.id.text_settings);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        fragment = new CarFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_place, fragment);
        fragmentTransaction.commit();
        currentMenu = bottomNavigationView.getSelectedItemId();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        fragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                if(currentMenu == item.getItemId()){
                                    Toast.makeText(getApplicationContext(), "이미 고른 메뉴", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    textHome.setVisibility(View.VISIBLE);
                                    textRoute.setVisibility(View.GONE);
                                    textAR.setVisibility(View.GONE);
                                    textFacility.setVisibility(View.GONE);
                                    textSettings.setVisibility(View.GONE);

                                    fragment = new CarFragment();
                                    currentMenu = item.getItemId();
                                    switchFragment();
                                }
                                break;
                            case R.id.action_route:
                                if(currentMenu == item.getItemId()){
                                    Toast.makeText(getApplicationContext(), "이미 고른 메뉴", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    textHome.setVisibility(View.GONE);
                                    textAR.setVisibility(View.GONE);
                                    textFacility.setVisibility(View.GONE);
                                    textSettings.setVisibility(View.GONE);

                                    fragment = new RouteFragment();
                                    currentMenu = item.getItemId();
                                    switchFragment();
                                }
                                break;
                            case R.id.action_AR:
                                Toast.makeText(getApplicationContext(), "AR 기능 예정", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_facility:
                                if(currentMenu == item.getItemId()){
                                    Toast.makeText(getApplicationContext(), "이미 고른 메뉴", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    textHome.setVisibility(View.GONE);
                                    textRoute.setVisibility(View.GONE);
                                    textAR.setVisibility(View.GONE);
                                    textFacility.setVisibility(View.VISIBLE);
                                    textSettings.setVisibility(View.GONE);

                                    fragment = new BusFragment();
                                    currentMenu = item.getItemId();
                                    switchFragment();
                                }
                                break;
                            case R.id.action_settings:
                                if(currentMenu == item.getItemId()){
                                    Toast.makeText(getApplicationContext(), "이미 고른 메뉴", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    textHome.setVisibility(View.GONE);
                                    textRoute.setVisibility(View.GONE);
                                    textAR.setVisibility(View.GONE);
                                    textFacility.setVisibility(View.GONE);
                                    textSettings.setVisibility(View.VISIBLE);

                                    fragment = new FootFragment();
                                    currentMenu = item.getItemId();
                                    switchFragment();
                                }
                                break;
                        }
                        return true;
                    }
                });
    }

    public void switchFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_place, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
