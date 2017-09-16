package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by woga1 on 2017-09-16.
 */

public class ImageFragment extends Fragment{
    public static ImageFragment newInstance(int imagerUrl){
        Bundle args = new Bundle();
        args.putInt("imageUrl", imagerUrl);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imagefragment, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imagefragment_imageview);
        imageView.setImageResource(getArguments().getInt("imageUrl"));
        return view;
    }
}
