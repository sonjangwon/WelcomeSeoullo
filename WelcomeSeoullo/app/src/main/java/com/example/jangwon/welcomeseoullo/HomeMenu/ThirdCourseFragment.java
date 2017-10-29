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

public class ThirdCourseFragment extends Fragment {
    View view;
    ImageView imageView;
    String tag;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    public ThirdCourseFragment(String tag) {
        this.tag = tag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.thirdcourse_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.thirdcourse);
        imageView2 = (ImageView) view.findViewById(R.id.thirdcourse2);
        imageView3 = (ImageView) view.findViewById(R.id.thirdcourse3);
        imageView4 = (ImageView) view.findViewById(R.id.thirdcourse4);
        imageView5 = (ImageView) view.findViewById(R.id.thirdcourse5);
        String imageURL1 = "http://postfiles8.naver.net/MjAxNzEwMjlfMjQ0/MDAxNTA5MjI1NjE1ODk2.aduaUjRKtHOL0bP53fG5vgdIXSZRTyHpQPcicvFzM4kg.8DECH624LGchRNHMdfwT8V8MtEuFzDQhZYlV8rSIHtgg.PNG.qkrgy1206/welcomeseoullo3_1.png?type=w1";
        String imageURL2 = "http://postfiles7.naver.net/MjAxNzEwMjlfMjMw/MDAxNTA5MjI1NjE4MTg4.sQ7__dtxiYTKX7T8cR6KpPX384olgxZneuB5xPkbTvkg.e13STPqIvtBPVXxnFlHKfv1Vuj_DBf2QGIzcszxhKhcg.PNG.qkrgy1206/welcomeseoullo3_2.png?type=w1";
        String imageURL3 = "http://postfiles9.naver.net/MjAxNzEwMjlfMjM2/MDAxNTA5MjI1NjE5OTk0.wF9a63k52PGw3UMVDgiJjkmedu21IDWh2HkhChHW3nsg.baHbCsgfkOlKORjUXHNyfZTtmXM72_XQqKA0FDX_ER8g.PNG.qkrgy1206/welcomeseoullo3_3.png?type=w1";
        String imageURL4 = "http://postfiles13.naver.net/MjAxNzEwMjlfOTUg/MDAxNTA5MjI1NjIyNTIx.aitEURxVOE3xJ7ckg2-tdRcBXuwrA5bZnRjCteantDgg.K7AHlLxJViKNtsG8Pps5wYb2qT-AAIXCkCr0k09rEDQg.PNG.qkrgy1206/welcomeseoullo3_4.png?type=w1";
        String imageURL5 = "http://postfiles16.naver.net/MjAxNzEwMjlfMjI2/MDAxNTA5MjI1NjI0NTAw.GufwMkbk9ACLPmGKrmdcqA769OFibgiUmkWPkt2dnRsg.XeQKnhBXq9f3XMeFbA4mIGesYl_Ix4sSzRdk4SdLhn8g.PNG.qkrgy1206/welcomeseoullo3_5.png?type=w1";
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
//                    decodeSampledBitmapFromResource(getResources(), R.drawable.img_card02_big_3, imageWidth, imageHeight));
//            imageView.setScaleType(ImageView.ScaleType.FIT_START);
//        }
//        else if(tag == "History")
//        {
//            int imageHeight = options.outHeight;
//            int imageWidth = options.outWidth;
//            imageView.setImageBitmap(
//                    decodeSampledBitmapFromResource(getResources(), R.drawable.welcomeseoullo_source3, imageWidth, imageHeight));
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
