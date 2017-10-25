package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jangwon.welcomeseoullo.R;

public class ImageAdapter extends PagerAdapter {
	Context context;
    private int[] GalImages = new int[] {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3, R.drawable.img4, R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9
    };
    ImageAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return GalImages.length;
    }

    public int[] getImages(){return GalImages;}

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        //int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        //imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(GalImages[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
  }
