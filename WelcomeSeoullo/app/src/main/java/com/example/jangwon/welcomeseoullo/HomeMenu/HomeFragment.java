package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.MainActivity;
import com.example.jangwon.welcomeseoullo.R;

import java.util.ArrayList;
import java.util.Timer;

public class HomeFragment extends Fragment {

    View view;
    Fragment fragment;

    ArrayList<String> urlList = new ArrayList<String>(10);
    //현재화면인덱스

    //AutoScrollViewPager viewPager;
    ClickableAutoViewPager viewPager;
    private Integer[] Images;
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();
    //InfiniteViewPager view;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    int currentPage = 0;
    Timer timer;

    private TextView[] dots;
    private int[] layouts;
    private LinearLayout dotsLayout;
    //load more recyclerView list
    TextView loadMoreText;

    //메인버튼
    Button btn_newNotice;
    Button btn_history;
    Button btn_usingTime;
    Button btn_walkingCourse;

    NoticeFragment noticeFragment;
    SeoulloCourseFragment seoulloCourseFragment;
    HistoricalResourceFragment historicalResourceFragment;
    GuidanceFragment guidanceFragment;

    public HomeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ClickableAutoViewPager) view.findViewById(R.id.viewPager);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ImageAdapter imgadapter = new ImageAdapter(getActivity());

        viewPager.setAdapter(imgadapter);
        viewPager.startAutoScroll();

        btn_newNotice = (Button) view.findViewById(R.id.Btn_newNotice);
        btn_walkingCourse = (Button) view.findViewById(R.id.Btn_walkingCourse);
        btn_history = (Button) view.findViewById(R.id.Btn_history);
        btn_usingTime = (Button) view.findViewById(R.id.Btn_usingTime);

        noticeFragment = new NoticeFragment();
        seoulloCourseFragment = new SeoulloCourseFragment();
        historicalResourceFragment = new HistoricalResourceFragment();
        guidanceFragment = new GuidanceFragment();

        btn_newNotice.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                switchFragment(noticeFragment);
                MainActivity.isHomeFragmentVisible = false;
            }
        });
        btn_walkingCourse.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                switchFragment(seoulloCourseFragment);
                MainActivity.isHomeFragmentVisible = false;
            }
        });
        btn_history.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                switchFragment(historicalResourceFragment);
                MainActivity.isHomeFragmentVisible = false;
            }
        });

        btn_usingTime.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                switchFragment(guidanceFragment);
                MainActivity.isHomeFragmentVisible = false;}
        });

        viewPager.setOnItemClickListener(new ClickableAutoViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int pageNum = position % 8;
                Intent intent = new Intent(getActivity().getApplicationContext(), EmoJeoMoImages.class);
                intent.putExtra("position", String.valueOf(pageNum));
                //Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position)+String.valueOf(pageNum), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        return view;
    }

    public void switchFragment(Fragment switchingFragment){

        fragment = switchingFragment;

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_home_fragment_place, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
