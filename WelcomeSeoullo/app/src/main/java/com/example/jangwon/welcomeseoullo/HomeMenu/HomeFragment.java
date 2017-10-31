package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.jangwon.welcomeseoullo.R;

public class HomeFragment extends Fragment {

    View view;
    Fragment fragment;

    ClickableAutoViewPager viewPager;

    //메인버튼
    ImageButton imageNotice;
    ImageButton imageWalkingCourse;
    ImageButton imageHistorical;
    ImageButton imageGuidance;
    Button btn_newNotice;
    Button btn_history;
    Button btn_usingTime;
    Button btn_walkingCourse;
    LinearLayout noticeSection;
    LinearLayout walkingCourseSection;
    LinearLayout historicalSection;
    LinearLayout guidanceSection;

    public HomeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ClickableAutoViewPager) view.findViewById(R.id.viewPager);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ImageAdapter imgadapter = new ImageAdapter(getActivity());

        viewPager.setAdapter(imgadapter);
        viewPager.startAutoScroll();

        imageNotice = (ImageButton) view.findViewById(R.id.image_notice);
        imageWalkingCourse = (ImageButton) view.findViewById(R.id.image_walking_course);
        imageHistorical = (ImageButton) view.findViewById(R.id.image_historical);
        imageGuidance = (ImageButton) view.findViewById(R.id.image_guidance);

        btn_newNotice = (Button) view.findViewById(R.id.Btn_newNotice);
        btn_walkingCourse = (Button) view.findViewById(R.id.Btn_walkingCourse);
        btn_history = (Button) view.findViewById(R.id.Btn_history);
        btn_usingTime = (Button) view.findViewById(R.id.Btn_usingTime);

        noticeSection = (LinearLayout) view.findViewById(R.id.notice_section);
        walkingCourseSection = (LinearLayout) view.findViewById(R.id.walking_course_section);
        historicalSection = (LinearLayout) view.findViewById(R.id.historical_section);
        guidanceSection = (LinearLayout) view.findViewById(R.id.guidance_section);



        final Bundle bndlanimation = ActivityOptions.makeCustomAnimation(
                getActivity().getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();

        imageNotice.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), NoticeActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        imageWalkingCourse.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), SeoulloCourseActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        imageHistorical.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), HistoricalResourceActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        imageGuidance.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), GuidanceActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });



        btn_newNotice.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), NoticeActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        btn_walkingCourse.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), SeoulloCourseActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        btn_history.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), HistoricalResourceActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        btn_usingTime.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), GuidanceActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });



        noticeSection.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), NoticeActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        walkingCourseSection.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), SeoulloCourseActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        historicalSection.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), HistoricalResourceActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });
        guidanceSection.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity().getApplicationContext(), GuidanceActivity.class);
                startActivity(slideactivity, bndlanimation);
            }
        });



        viewPager.setOnItemClickListener(new ClickableAutoViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int pageNum = position % 8;
                Intent intent = new Intent(getActivity().getApplicationContext(), EmoJeoMoImages.class);
                intent.putExtra("position", String.valueOf(pageNum));
                startActivity(intent);
            }
        });

        return view;
    }
}
