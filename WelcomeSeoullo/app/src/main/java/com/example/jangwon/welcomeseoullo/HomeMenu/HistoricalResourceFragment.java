package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jangwon.welcomeseoullo.MainActivity;
import com.example.jangwon.welcomeseoullo.R;


public class HistoricalResourceFragment extends Fragment {

    View view;

    public HistoricalResourceFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_historical_resource, container, false);



        return view;
    }

}
