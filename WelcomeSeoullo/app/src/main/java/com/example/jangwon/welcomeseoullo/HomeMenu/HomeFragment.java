package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;

//    private Test.MyViewPagerAdapter myViewPagerAdapter;


    ArrayList<String> urlList = new ArrayList<String>(10);
    int count =0;
    //현재화면인덱스

    AutoScrollViewPager viewPager;
    private Integer[] Images;
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();
    //InfiniteViewPager view;


    //메인버튼
    Button btn_newNotice;
    Button btn_history;
    Button btn_usingTime;
    Button btn_walkingCourse;
    public HomeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_home, container, false);


        viewPager = (AutoScrollViewPager) view.findViewById(R.id.viewPager);
        ImageAdapter imgadapter = new ImageAdapter(getActivity());
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(imgadapter, getActivity().getApplicationContext());

        viewPager.setAdapter(wrappedAdapter);
        viewPager.setOnTouchListener(viewPagerTouchListener);
        viewPager.startAutoScroll();

        btn_newNotice = (Button) view.findViewById(R.id.Btn_newNotice);
        btn_walkingCourse = (Button) view.findViewById(R.id.Btn_walkingCourse);
        btn_history = (Button) view.findViewById(R.id.Btn_history);
        btn_usingTime = (Button) view.findViewById(R.id.Btn_usingTime);

        btn_newNotice.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), NewNoticeActivity.class);
                startActivity(intent);
            }
        });
        btn_walkingCourse.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SeoulloCourseFragment.class);
                startActivity(intent);
            }
        });
        btn_history.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), NewNoticeActivity.class);
                startActivity(intent);
            }
        });
        btn_usingTime.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), UsingGuideTimeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
        else {

        }
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        return true;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }






    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnTouchListener viewPagerTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Toast.makeText(getActivity().getApplicationContext(),"터치", Toast.LENGTH_SHORT);
            return false;
        }
    };

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.imagefragment, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imagefragment_imageview);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(Images[position]);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return Images.length;
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

    public void whenTouchImagePosition(int index)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        if(index ==0){
            intent.setData(Uri.parse("http://www.naver.com"));
            startActivity(intent);
            Toast.makeText(getActivity().getApplicationContext(),"1이동", Toast.LENGTH_SHORT).show();
        }
        else if(index == 1)
        {
            intent.setData(Uri.parse("http://www.daum.net"));
            startActivity(intent);
            Toast.makeText(getActivity().getApplicationContext(),"2이동", Toast.LENGTH_SHORT).show();

        }
        else if(index == 2)
        {
            intent.setData(Uri.parse("http://www.naver.com"));
            startActivity(intent);
            Toast.makeText(getActivity().getApplicationContext(),"3이동", Toast.LENGTH_SHORT).show();
        }
    }
}
