package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * Created by woga1 on 2017-09-16.
 */

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    int[] Images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4 };

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ImageFragment().newInstance(Images[position]);
    }

    @Override
    public int getCount() {
        return Images.length;
    }
}
