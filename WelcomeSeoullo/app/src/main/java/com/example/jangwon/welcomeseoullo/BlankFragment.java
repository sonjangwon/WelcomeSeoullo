package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BlankFragment extends Fragment {

    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return view = inflater.inflate(R.layout.fragment_blank, container, false);
    }
}
