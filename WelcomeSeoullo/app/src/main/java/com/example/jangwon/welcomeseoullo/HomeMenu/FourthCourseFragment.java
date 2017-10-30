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
//        setImage(imageURL1, imageView);
//        setImage(imageURL2, imageView2);
//        setImage(imageURL3, imageView3);
//        setImage(imageURL4, imageView4);
//        setImage(imageURL5, imageView5);
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
        return view;
    }
    public void setImage(String url, ImageView image)
    {
        Picasso.with(getActivity().getApplicationContext())
                .load(url)
                .skipMemoryCache()
                .into(image);
    }
}
