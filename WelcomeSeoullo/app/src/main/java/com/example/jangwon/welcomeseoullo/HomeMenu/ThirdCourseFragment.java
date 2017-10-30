package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
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
                .skipMemoryCache()
                .into(imageView);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL2)
                .skipMemoryCache()
                .into(imageView2);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL3)
                .skipMemoryCache()
                .into(imageView3);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL4)
                .skipMemoryCache()
                .into(imageView4);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL5)
                .skipMemoryCache()
                .into(imageView5);
//        setImage(imageURL1, imageView);
//        setImage(imageURL2, imageView2);
//        setImage(imageURL3, imageView3);
//        setImage(imageURL4, imageView4);
//        setImage(imageURL5, imageView5);

        return view;
    }

    public void setImage(String url, ImageView image)
    {

    }
}
