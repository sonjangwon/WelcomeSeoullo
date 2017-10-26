package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jangwon.welcomeseoullo.R;

/**
 * Created by woga1 on 2017-10-25.
 */

public class ThirdCourseFragment extends Fragment {
    View view;
    ImageView imageView;
    String tag;
    public ThirdCourseFragment(String tag) {
        this.tag = tag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.seoulloinfo_imagefragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.fragmentImage);

        if(tag == "SeoulloCourse") {
            imageView.setImageResource(R.drawable.welcomeseoullo3);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
        }
        else if(tag == "History")
        {
            imageView.setImageResource(R.drawable.welcomeseoullo_source3);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
        }
        return view;
    }
}
