package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PathInfoFragment extends Fragment {

    View view;
    Fragment fr;

    // 사용자 위치 수신기
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    TextView startPointAddress;

    static Button byCarButton;
    static Button byBusButton;
    static Button onFootButton;

    public PathInfoFragment(){

    }

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

            view = inflater.inflate(R.layout.fragment_path_info, container, false);

            byCarButton = (Button) view.findViewById(R.id.byCar);
            byBusButton = (Button) view.findViewById(R.id.byBus);
            onFootButton = (Button) view.findViewById(R.id.onFoot);

            startPointAddress = (TextView) view.findViewById(R.id.startPointAddress);

            currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
            currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
            currentAddress = ManagementLocation.getInstance().getCurrentAddress();

            startPointAddress.setText(currentAddress);

            byCarButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //자동차 경로안내 버튼을 누를 경우
                    byCarButton.setTextColor(Color.parseColor("#FF0000"));
                    byBusButton.setTextColor(Color.parseColor("#000000"));
                    onFootButton.setTextColor(Color.parseColor("#000000"));

                    switchFragment(view);
                }
            });
            byBusButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //대중교통 경로안내 버튼을 누를 경우
                    byBusButton.setTextColor(Color.parseColor("#FF0000"));
                    byCarButton.setTextColor(Color.parseColor("#000000"));
                    onFootButton.setTextColor(Color.parseColor("#000000"));

                    startActivity(new Intent(getActivity(), BusPathActivity.class));
                }
            });
            onFootButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //도보 경로안내 버튼을 누를 경우
                    onFootButton.setTextColor(Color.parseColor("#FF0000"));
                    byBusButton.setTextColor(Color.parseColor("#000000"));
                    byCarButton.setTextColor(Color.parseColor("#000000"));

                    switchFragment(view);
                }
            });

            byCarButton.callOnClick();
        }
        else{

        }
        return view;
    }

    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
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
        fragmentTransaction.commitAllowingStateLoss();
    }

}