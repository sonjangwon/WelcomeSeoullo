package com.example.jangwon.welcomeseoullo;

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
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                textHome.setVisibility(View.VISIBLE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.GONE);
                                break;
                            case R.id.action_route:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.VISIBLE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.GONE);
                                break;
                            case R.id.action_AR:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.VISIBLE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.GONE);
                                break;
                            case R.id.action_facility:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.VISIBLE);
                                textSettings.setVisibility(View.GONE);
                                break;
                            case R.id.action_settings:
                                textHome.setVisibility(View.GONE);
                                textRoute.setVisibility(View.GONE);
                                textAR.setVisibility(View.GONE);
                                textFacility.setVisibility(View.GONE);
                                textSettings.setVisibility(View.VISIBLE);
                                break;
                        }
                        return false;
                    }
                });
    }
}
