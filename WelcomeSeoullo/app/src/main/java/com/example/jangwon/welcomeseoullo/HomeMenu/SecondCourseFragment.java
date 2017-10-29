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

public class SecondCourseFragment extends Fragment {
    View view;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    String tag;
    public SecondCourseFragment(String tag) {
        this.tag = tag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.secondcourse_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.secondcourse);
        imageView2 = (ImageView) view.findViewById(R.id.secondcourse2);
        imageView3 = (ImageView) view.findViewById(R.id.secondcourse3);
        imageView4 = (ImageView) view.findViewById(R.id.secondcourse4);
        imageView5 = (ImageView) view.findViewById(R.id.secondcourse5);
        String imageURL1 = "http://postfiles2.naver.net/MjAxNzEwMjlfOSAg/MDAxNTA5MjI1Mjc1MDU2.pQa4l_SQkmMhd8sYF4T4BhIIfR0o-CUXOz956OLJPfAg.wf_4GbdsxHwbsjN-a9FZebzNGin3kv03EdpcyvNE_mgg.PNG.qkrgy1206/welcomeseoullo2_1.png?type=w1";
        String imageURL2 = "http://postfiles8.naver.net/MjAxNzEwMjlfMTgw/MDAxNTA5MjI1Mjc3MjQy.nzhzj_QhN1VfIIOeqsf1OjrjHoxGnUmUz6_msHNKjMog.ZvhQ3LwHVU6eawBrWqk-bWdcs7rjJ6rwGGazqDWSR5gg.PNG.qkrgy1206/welcomeseoullo2_2.png?type=w1";
        String imageURL3 = "http://postfiles15.naver.net/MjAxNzEwMjlfMjcz/MDAxNTA5MjI1Mjc4ODIy.DNfHxZhAGl1TbZam4o8EJqaVphQ69hqxFh4mUF-j-3og.EHp54V7Rkv1B8-G3uxnJT18Zs_wcde7h0vjxHgcPxQYg.PNG.qkrgy1206/welcomeseoullo2_3.png?type=w1";
        String imageURL4 = "http://postfiles6.naver.net/MjAxNzEwMjlfMTI3/MDAxNTA5MjI1MjgxMTE0.Iy6CthzOpHhzxlphzjRKz1-N6X7zxM_914GRZVRuspsg.wHTZatzXUfCwHXBinZ41WK0C9HBBYGLJ2i7HHyEChUkg.PNG.qkrgy1206/welcomeseoullo2_4.png?type=w1";
        String imageURL5 = "http://postfiles11.naver.net/MjAxNzEwMjlfNTMg/MDAxNTA5MjI1MjgzNTIx.YDAZSPwNJd9tR4-_6fMEHc1hBUEW9diqeAR_AS1scB0g.Kbmu18a5d_TiGMRHeZUTckWDWPiwgdgJAhSKGepkOKUg.PNG.qkrgy1206/welcomeseoullo2_5.png?type=w1";
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
//                    decodeSampledBitmapFromResource(getResources(), R.drawable.img_card02_big_2, imageWidth, imageHeight));
//            imageView.setScaleType(ImageView.ScaleType.FIT_START);
//        }
//        else if(tag == "History")
//        {
//            int imageHeight = options.outHeight;
//            int imageWidth = options.outWidth;
//            imageView.setImageBitmap(
//                    decodeSampledBitmapFromResource(getResources(), R.drawable.welcomeseoullo_source2, imageWidth, imageHeight));
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
