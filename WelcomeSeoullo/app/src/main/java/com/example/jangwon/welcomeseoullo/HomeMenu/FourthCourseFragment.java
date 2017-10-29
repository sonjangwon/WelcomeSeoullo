package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jangwon.welcomeseoullo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by woga1 on 2017-10-25.
 */

public class FourthCourseFragment extends Fragment {
    View view;
    ImageView imageView;
    String tag;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;

    public FourthCourseFragment(String tag) {
        this.tag = tag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fourthcourse_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.fourthcourse);
        imageView2 = (ImageView) view.findViewById(R.id.fourthcourse2);
        imageView3 = (ImageView) view.findViewById(R.id.fourthcourse3);
        imageView4 = (ImageView) view.findViewById(R.id.fourthcourse4);
        imageView5 = (ImageView) view.findViewById(R.id.fourthcourse5);
        String imageURL1 = "http://postfiles3.naver.net/MjAxNzEwMjlfMjI3/MDAxNTA5MjI1ODEzNTQ4.g_ufYJdEPlIH8LNBhHGZLO62e7G8GMadrxu8Q8sDE7kg.Q8Hz4r3dFuksY90lyaySLSZhpiZzfFPPLMKCut2M03Ig.PNG.qkrgy1206/welcomeseoullo4_1.png?type=w1";
        String imageURL2 = "http://postfiles3.naver.net/MjAxNzEwMjlfMTEx/MDAxNTA5MjI1ODE2Mzk0.afke7-qO_n_EMJHJV3g0v4vH8HofKVH_gDUrwJq6mTQg.JqLaeuWZaZFMysowdMNXSMZFYO5b1ssAIWmk-fMvC2Ig.PNG.qkrgy1206/welcomeseoullo4_2.png?type=w1";
        String imageURL3 = "http://postfiles11.naver.net/MjAxNzEwMjlfMjk5/MDAxNTA5MjI1ODE4Njc2.L1_h5Ev99FLhu2NVIoXzWdJns1VMGw9cUQtKz2gwW8og.zJxNKGL85bTHT2jAcdcGI8C--tr5P3sqtlAtbVqctoUg.PNG.qkrgy1206/welcomeseoullo4_3.png?type=w1";
        String imageURL4 = "http://postfiles7.naver.net/MjAxNzEwMjlfMTg0/MDAxNTA5MjI1ODIwMzAy.j9Sav5JBKLYS5Gte3sZ0uomnMB2ZOxR3roQmtdts8_0g._v9DaZ8yFauMU_XAj2coUUAgWrLY3CGREXg7dbucC8gg.PNG.qkrgy1206/welcomeseoullo4_4.png?type=w1";
        String imageURL5 = "http://postfiles1.naver.net/MjAxNzEwMjlfMTYg/MDAxNTA5MjI1ODIxOTE2.jaVmfgyAfcF-2WeCCkY3pNgy4vplcUqtwYv-JBjZASYg.5DFiK-jx08t5DDOgmPvsAmdo03BR8VYdE_tqfdOucGIg.PNG.qkrgy1206/welcomeseoullo4_5.png?type=w1";
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL1)
                .into(imageView);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL2)
                .into(imageView2);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL3)
                .into(imageView3);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL4)
                .into(imageView4);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL5)
                .into(imageView5);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//
//        if(tag == "SeoulloCourse") {
//            int imageHeight = options.outHeight;
//            int imageWidth = options.outWidth;
//            imageView.setImageBitmap(
//                    decodeSampledBitmapFromResource(getResources(), img_card02_big_4, imageWidth, imageHeight));
////            imageView.setImageResource(R.drawable.welcomeseoullo4);
//            imageView.setScaleType(ImageView.ScaleType.FIT_START);
//        }
//        else if(tag == "History")
//        {
//            int imageHeight = options.outHeight;
//            int imageWidth = options.outWidth;
//            imageView.setImageBitmap(
//                    decodeSampledBitmapFromResource(getResources(), R.drawable.welcomeseoullo_source4, imageWidth, imageHeight));
//
//        }
        return view;
    }

    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
