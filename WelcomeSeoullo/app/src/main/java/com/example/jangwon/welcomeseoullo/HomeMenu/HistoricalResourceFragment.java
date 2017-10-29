package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.R;


public class HistoricalResourceFragment extends Fragment {

    View view;
    ViewPager vp;
    LinearLayout linearLayout;
    String tagName = "History";
    public HistoricalResourceFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_historical_resource, container, false);

        vp = (ViewPager) view.findViewById(R.id.historyPager);
        linearLayout = (LinearLayout) view.findViewById(R.id.historyTabLinearLayout);

        TextView tab_first = (TextView) view.findViewById(R.id.History1);
        TextView tab_second = (TextView) view.findViewById(R.id.History2);
        TextView tab_third = (TextView) view.findViewById(R.id.History3);
        TextView tab_fourth = (TextView) view.findViewById(R.id.History4);

        vp.setAdapter(new pagerAdapter(getFragmentManager()));
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

        return view;
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
    private class pagerAdapter extends android.support.v13.app.FragmentStatePagerAdapter
    {
        public pagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new FirstHistoryFragment();
                case 1:
                    return new SecondHistoryFragment();
                case 2:
                    return new ThirdHistoryFragment();
                case 3:
                    return new FourthHistoryFragment();
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
