package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
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

    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    MenuItem prevMenuItem;

    BlankFragment homeFragment;
    GuideInfoFragment guideInfoFragment;
    BlankFragment arFragment;
    PathInfoFragment pathInfoFragment;
    FacilityFragment facilityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(5);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        currentMenu = R.id.action_home;
        setupViewPager(viewPager);
        prevMenuItem = bottomNavigationView.getMenu().getItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        currentMenu = R.id.action_home;
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_facility:
                        currentMenu = R.id.action_facility;
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_AR:
                        currentMenu = R.id.action_AR;
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_route:
                        currentMenu = R.id.action_route;
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.action_settings:
                        currentMenu = R.id.action_settings;
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getFragmentManager());

        homeFragment = new BlankFragment();
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

    public void switchFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_right);
        fragmentTransaction.replace(R.id.viewPager, fragment);
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
