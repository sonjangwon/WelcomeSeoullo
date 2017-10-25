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

public class SecondCourseFragment extends Fragment {
    View view;
    ImageView imageView;
    public SecondCourseFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.seoulloinfo_imagefragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.fragmentImage);

        imageView.setImageResource(R.drawable.seoullou1);
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        return view;
    }
}
