package com.example.jangwon.welcomeseoullo.ARMenu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.R;

import static android.content.Context.MODE_PRIVATE;

public class BlankFragment extends Fragment {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button test;
    private ImageView firstGuideImage;
    private ImageView secondGuideImage;
    private ImageView thirdGuideImage;

    public BlankFragment(){

    }

    View view;

    //SharedPreference 값 불러오기
    private String getPreferences(String key){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        return pref.getString(key, "");
    }

    //SharedPreference boolean 값 불러오기
    private boolean getBooleanPreferences(String key){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        return pref.getBoolean(key, true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_blank, container, false);

//        //비디오 배경 설정
//        MyVideoView mVideoView = (MyVideoView) view.findViewById(R.id.bgVideoView2);
//        Uri uri = Uri.parse("android.resource://com.example.jangwon.welcomeseoullo/" + R.raw.splash_video);
//        mVideoView.setVideoURI(uri);
//        mVideoView.start();
//        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mediaPlayer.setLooping(true);
//            }
//        });

        viewPager = (ViewPager) view.findViewById(R.id.ar_view_pager);
        dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        test = (Button) view.findViewById(R.id.ar_btn_start);
        test.setVisibility(View.INVISIBLE);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.ar_slide1, R.layout.ar_slide2, R.layout.ar_slide3
        };

        // adding bottom dots
        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        Button test = (Button)view.findViewById(R.id.ar_btn_start);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arIntent = new Intent(getActivity(), ARMainActivity.class);
//                arIntent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                startActivity(arIntent);
//                startActivity(new Intent(getActivity(), ARMainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        return view;
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];
        //해당화면 일때
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        //해당화면 아닐때
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) { //이부분 -1이라고 고침
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layouts.length - 1) {
                test.setVisibility(View.VISIBLE);

            } else {
                test.setVisibility(View.INVISIBLE);

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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


}
