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

public class MainActivity extends AppCompatActivity {

    private TextView textHome;
    private TextView textRoute;
    private TextView textAR;
    private TextView textFacility;
    private TextView textSettings;

    Fragment fr;

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

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        fr = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                textHome.setVisibility(View.VISIBLE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.GONE);

                                fr = new CarFragment();
                                break;
                            case R.id.action_route:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.VISIBLE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.GONE);

                                fr = new RouteFragment();
                                break;
                            case R.id.action_AR:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.VISIBLE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.GONE);

                                fr = new CarFragment();
                                break;
                            case R.id.action_facility:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.VISIBLE);
                                textSettings.setVisibility(View.GONE);

                                fr = new BusFragment();
                                break;
                            case R.id.action_settings:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.VISIBLE);

                                fr = new FootFragment();
                                break;
                        }
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_place, fr);
                        fragmentTransaction.commit();
                        return true;
                    }
                });
    }

//    public void selectFrag(View view){
//        Fragment fr;
//
//        if(view == findViewById(R.id.action_route)){
//            fr = new RouteFragment();
//        }
//        else{
//            fr = new RouteFragment();
//        }
//
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_place, fr);
//        fragmentTransaction.commit();
//    }


}
