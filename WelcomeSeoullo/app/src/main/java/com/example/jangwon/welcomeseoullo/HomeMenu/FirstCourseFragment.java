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
                .skipMemoryCache()
                .into(imageView);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles5.naver.net/MjAxNzEwMjlfMTgg/MDAxNTA5MjI0MzUyODI2.G02pQ-4fDz9FjNzKhK0xACIrPNP9IueeMVeYikWik5wg.Dv-wYTB1kOyHSj6hox7FF6qguZyq5JlZKvXaHiNeOogg.PNG.qkrgy1206/welcomeseoullo1_2.png?type=w1")
                .skipMemoryCache()
                .into(imageView2);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles6.naver.net/MjAxNzEwMjlfMTc2/MDAxNTA5MjI0MzU0Mjkz.qWjHVru-C-msCtxRX0CBYFRCSi2B1hT1ANouyYKzvwQg.tDWtg6BK5Lqj6YLpa1P2apUrTzRjyO_WVMkP0rwPTigg.PNG.qkrgy1206/welcomeseoullo1_3.png?type=w1")
                .skipMemoryCache()
                .into(imageView3);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles12.naver.net/MjAxNzEwMjlfMTcy/MDAxNTA5MjI0MzU2MzAy.1jvuPzBMhriJf9jmQSamlbwLdolGIGif72al9O8fxSMg.Eb3i2TlYweIbmkO6Ntgq-0B-9b8bAq84ia7t8Jc1LH0g.PNG.qkrgy1206/welcomeseoullo1_4.png?type=w1")
                .skipMemoryCache()
                .into(imageView4);
        Picasso.with(getActivity().getApplicationContext())
                .load("http://postfiles16.naver.net/MjAxNzEwMjlfMTg4/MDAxNTA5MjI0MzU4MTY3.ZFWFMubFanGZsqeZxWaTKemMslyAlnrOMtLBJ_g4wk8g.KyoT1O97H2KuN8kchxdn4c-QCsg9lrJrpy7L09hbfCEg.PNG.qkrgy1206/welcomeseoullo1_5.png?type=w1")
                .skipMemoryCache()
                .into(imageView5);

        return view;
    }

    public void setImage(int imageId, ImageView image)
    {
        Picasso.with(getActivity().getApplicationContext())
                .load(imageId)
                .into(image);
    }

}
