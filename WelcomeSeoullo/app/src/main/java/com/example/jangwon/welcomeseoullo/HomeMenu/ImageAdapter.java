package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jangwon.welcomeseoullo.R;

public class ImageAdapter extends PagerAdapter {
	Context context;
    Bitmap galImage;
    BitmapFactory.Options options;

    private boolean[] isInstantiated;
    private int[] GalImages = new int[] { R.drawable.img_card04_big_main1, R.drawable.img_card02_big_main2, R.drawable.img_card010_big_main3,
            R.drawable.img_card013_big_main4, R.drawable.img_card011_big_main5, R.drawable.img_card014_big_main6, R.drawable.img_card012_big_main7,
            R.drawable.img_card03_big_main8
    };
    public ImageView[] imageViews;

    ImageAdapter(Context context){
        this.context=context;
        options = new BitmapFactory.Options();
    }
    @Override
    public int getCount() {
        return GalImages.length;
    }

    public int[] getImages(){return GalImages;}

    public void finish()
    {
        for ( int i=0; i < GalImages.length; i++ )
        {
            if ( isInstantiated[i] )
            {
                //Drawable drawable = imageViews.sGalImages[i];
                //if (drawable instanceof BitmapDrawable)
                {
                    //Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                    //bitmap.recycle();

                    //imageviews[i] = null;
                    isInstantiated[i] = false;

                }
                //drawable.setCallback(null);
            }
        }
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e("ViewPager new", String.valueOf(position));
        ImageView imageView = new ImageView(context);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        options.inSampleSize = 2;
        //imageView.setImageResource(GalImages[position]);

        galImage = BitmapFactory.decodeResource(context.getResources(), GalImages[position], options);

        imageView.setImageBitmap(galImage);

        ((ViewPager)container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.e("POSITION", "Destroy Position::" + position);
        ((ViewPager)container).removeView((ImageView) object);
    }
  }
