package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int currentMenu;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        fragment = new BlankFragment();
        switchFragment();
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
                                    fragment = new BlankFragment();
                                    currentMenu = item.getItemId();
                                    switchFragment();
                                }
                                break;

                            case R.id.action_facility:
                                if(currentMenu == item.getItemId()){
                                    Toast.makeText(getApplicationContext(), "이미 고른 메뉴", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    fragment = new GuideInfoFragment();
                                    currentMenu = item.getItemId();
                                    switchFragment();
                                }
                                break;

                            case R.id.action_AR:
                                Toast.makeText(getApplicationContext(), "AR 기능 예정", Toast.LENGTH_SHORT).show();
                                fragment = new BlankFragment();
                                currentMenu = item.getItemId();
                                switchFragment();
                                break;

                            case R.id.action_route:
                                if(currentMenu == item.getItemId()){
                                    Toast.makeText(getApplicationContext(), "이미 고른 메뉴", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    fragment = new PathInfoFragment();
                                    currentMenu = item.getItemId();
                                    switchFragment();
                                }
                                break;

                            case R.id.action_settings:
                                if(currentMenu == item.getItemId()){
                                    Toast.makeText(getApplicationContext(), "이미 고른 메뉴", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    fragment = new BusFragment();
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
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.commit();
    }

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
}
