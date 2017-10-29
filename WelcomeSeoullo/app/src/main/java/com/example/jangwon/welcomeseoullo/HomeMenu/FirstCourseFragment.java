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

public class FirstCourseFragment extends Fragment {
    View view;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    String tag;
    public FirstCourseFragment(String tag) {
        this.tag =tag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.firstcourse_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.firstcourse);
        imageView2 = (ImageView) view.findViewById(R.id.firstcourse2);
        imageView3 = (ImageView) view.findViewById(R.id.firstcourse3);
        imageView4 = (ImageView) view.findViewById(R.id.firstcourse4);
        imageView5 = (ImageView) view.findViewById(R.id.firstcourse5);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles11.naver.net/MjAxNzEwMjlfMTE4/MDAxNTA5MjI0MzQ5ODk3.scLyXilVus5cXQkprZ7FAmhUhv17_o2GpMCEIk0O27kg.1MrYVgQVy4gLJpcWxV0EoUj1AMSBL2eVpSb964twldQg.PNG.qkrgy1206/welcomeseoullo1_1.png?type=w1")
                .into(imageView);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles5.naver.net/MjAxNzEwMjlfMTgg/MDAxNTA5MjI0MzUyODI2.G02pQ-4fDz9FjNzKhK0xACIrPNP9IueeMVeYikWik5wg.Dv-wYTB1kOyHSj6hox7FF6qguZyq5JlZKvXaHiNeOogg.PNG.qkrgy1206/welcomeseoullo1_2.png?type=w1")
                .into(imageView2);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles12.naver.net/MjAxNzEwMjlfMTcy/MDAxNTA5MjI0MzU2MzAy.1jvuPzBMhriJf9jmQSamlbwLdolGIGif72al9O8fxSMg.Eb3i2TlYweIbmkO6Ntgq-0B-9b8bAq84ia7t8Jc1LH0g.PNG.qkrgy1206/welcomeseoullo1_4.png?type=w1")
                .into(imageView3);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles12.naver.net/MjAxNzEwMjlfMTcy/MDAxNTA5MjI0MzU2MzAy.1jvuPzBMhriJf9jmQSamlbwLdolGIGif72al9O8fxSMg.Eb3i2TlYweIbmkO6Ntgq-0B-9b8bAq84ia7t8Jc1LH0g.PNG.qkrgy1206/welcomeseoullo1_4.png?type=w1")
                .into(imageView4);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles16.naver.net/MjAxNzEwMjlfMTg4/MDAxNTA5MjI0MzU4MTY3.ZFWFMubFanGZsqeZxWaTKemMslyAlnrOMtLBJ_g4wk8g.KyoT1O97H2KuN8kchxdn4c-QCsg9lrJrpy7L09hbfCEg.PNG.qkrgy1206/welcomeseoullo1_5.png?type=w1")
                .into(imageView5);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        int imageHeight = options.outHeight;
//        int imageWidth = options.outWidth;
//        imageView.setImageBitmap(
//                decodeSampledBitmapFromResource(getResources(), R.id.firstcourse, imageWidth, imageHeight));
//        if(tag == "SeoulloCourse") {
//            int imageHeight = options.outHeight;
//            int imageWidth = options.outWidth;
//            imageView.setImageBitmap(
//                    decodeSampledBitmapFromResource(getResources(), R.drawable.img_card02_big_1, imageWidth, imageHeight));
//            imageView.setScaleType(ImageView.ScaleType.FIT_START);
//        }
//        else if(tag == "History")
//        {
//            int imageHeight = options.outHeight;
//            int imageWidth = options.outWidth;
//            imageView.setImageBitmap(
//                    decodeSampledBitmapFromResource(getResources(), R.drawable.welcomeseoullo_source1, imageWidth, imageHeight));
//            imageView.setScaleType(ImageView.ScaleType.FIT_START);
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
