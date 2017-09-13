package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RouteFragment extends Fragment {

    View view;
    Fragment fr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState == null){
            view = inflater.inflate(R.layout.fragment_route, container, false);

            final Button byCarButton = (Button) view.findViewById(R.id.byCar);
            final Button byBusButton = (Button) view.findViewById(R.id.byBus);
            final Button onFootButton = (Button) view.findViewById(R.id.onFoot);
            byCarButton.setTextColor(Color.parseColor("#FF0000"));
            byCarButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    byCarButton.setTextColor(Color.parseColor("#FF0000"));
                    byBusButton.setTextColor(Color.parseColor("#000000"));
                    onFootButton.setTextColor(Color.parseColor("#000000"));
                    switchFragment(view);
                }
            });
            byBusButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    byBusButton.setTextColor(Color.parseColor("#FF0000"));
                    byCarButton.setTextColor(Color.parseColor("#000000"));
                    onFootButton.setTextColor(Color.parseColor("#000000"));
                    switchFragment(view);
                }
            });
            onFootButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onFootButton.setTextColor(Color.parseColor("#FF0000"));
                    byBusButton.setTextColor(Color.parseColor("#000000"));
                    byCarButton.setTextColor(Color.parseColor("#000000"));
                    switchFragment(view);
                }
            });
        }
        return view;
    }

    public void switchFragment(View view){

        if(view == view.findViewById(R.id.byCar)){
            fr = new CarFragment();
        }else if(view == view.findViewById(R.id.byBus)){
            fr = new BusFragment();
        }else{
            fr = new FootFragment();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
