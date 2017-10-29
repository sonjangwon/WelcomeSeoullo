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
 * Created by woga1 on 2017-10-29.
 */

public class FourthHistoryFragment extends Fragment {

    View view;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    FourthHistoryFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.history_fourth, container, false);
        imageView1 = (ImageView) view.findViewById(R.id.fourthHistoryImage);
        imageView2 = (ImageView) view.findViewById(R.id.fourthHistoryImage2);
        imageView3 = (ImageView) view.findViewById(R.id.fourthHistoryImage3);
        imageView4 = (ImageView) view.findViewById(R.id.fourthHistoryImage4);

        String imageURL1 = "http://postfiles10.naver.net/MjAxNzEwMjlfMTMx/MDAxNTA5MjgxMTk0NDcx.WXHOmiXXqRnPFtgc4C_MHxpGaEOPr9vHhwwI15u4kYwg.1vBY9XsX0PSEgQQ3PT3AxxUpafr55t_KYcXW7Eo2NCAg.PNG.qkrgy1206/welcomeseoullo_source4_1.png?type=w1";
        String imageURL2 = "http://postfiles8.naver.net/MjAxNzEwMjlfNDEg/MDAxNTA5MjgxMTk3Mjc3.WgYNr4qnkz-LWJczq73t_83qU5ypl-lm90llp3vuQPEg.RPGyuxBqRZscbLo1kkNm7ZqSKYG8OowWDttRir3MT2kg.PNG.qkrgy1206/welcomeseoullo_source4_2.png?type=w1";
        String imageURL3 = "http://postfiles14.naver.net/MjAxNzEwMjlfMjQ3/MDAxNTA5MjgxMTk3OTQ4.aakQGT8bjuC8ng-dKNrZ101VGXxlh3mvYGE1ARjMf4Ig.yY8iI4EQJeNPzV57pvDBlsuamMlZXsB-xx5NVduB0Ogg.PNG.qkrgy1206/welcomeseoullo_source4_3.png?type=w1";
        String imageURL4 = "http://postfiles12.naver.net/MjAxNzEwMjlfMjE1/MDAxNTA5MjgxMTk5MTEw.JaQJE2mtPip55YvVBRMS6ZyS3MLo63sI-Blpdz4d62Ug.3i5H_OVtsukRrXejVivGmClfmoakk_Pb8V0XEq-Wui4g.PNG.qkrgy1206/welcomeseoullo_source4_4.png?type=w1";

        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL1)
                .into(imageView1);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL2)
                .into(imageView2);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL3)
                .into(imageView3);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL4)
                .into(imageView4);

        return view;
    }

}
