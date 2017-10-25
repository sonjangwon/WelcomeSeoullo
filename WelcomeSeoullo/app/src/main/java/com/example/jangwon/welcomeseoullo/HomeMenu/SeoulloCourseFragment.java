package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.R;

/**
 * Created by woga1 on 2017-10-25.
 */

//서울로 도보 관광코스를 보여주는 액티베이터
public class SeoulloCourseFragment extends AppCompatActivity{
    //버튼 클릭시 남산코스 무슨코스 다 보여줌 목록으로 근데 그 하위프래그먼트 생기게해서 보여줄것같은데 조사 필요
    ViewPager vp;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walkingcourse_layout);

        vp = (ViewPager) findViewById(R.id.pager);
        linearLayout = (LinearLayout) findViewById(R.id.tabLinearLayout);

        TextView tab_first = (TextView)findViewById(R.id.NamsanCourse);
        TextView tab_second = (TextView)findViewById(R.id.JunglimCourse);
        TextView tab_third = (TextView)findViewById(R.id.ChungpaCourse);
        TextView tab_fourth = (TextView) findViewById(R.id.allCourse);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        tab_first.setOnClickListener(movePageListener);
        tab_first.setTag(0);
        tab_second.setOnClickListener(movePageListener);
        tab_second.setTag(1);
        tab_third.setOnClickListener(movePageListener);
        tab_third.setTag(2);
        tab_fourth.setOnClickListener(movePageListener);
        tab_fourth.setTag(3);

        tab_first.setSelected(true);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                int i = 0;
                while(i<4)
                {
                    if(position==i)
                    {
                        linearLayout.findViewWithTag(i).setSelected(true);
                    }
                    else
                    {
                        linearLayout.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();

            int i = 0;
            while(i<4)
            {
                if(tag==i)
                {
                    linearLayout.findViewWithTag(i).setSelected(true);
                }
                else
                {
                    linearLayout.findViewWithTag(i).setSelected(false);
                }
                i++;
            }

            vp.setCurrentItem(tag);
        }
    };
    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new FirstCourseFragment();
                case 1:
                    return new SecondCourseFragment();
                case 2:
                    return new ThirdCourseFragment();
                case 3:
                    return new FourthCourseFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 4;
        }
    }
}
