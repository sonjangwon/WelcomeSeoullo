package com.example.jangwon.welcomeseoullo.NavigationMenu;

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
import android.widget.LinearLayout;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;

public class PathInfoFragment extends Fragment {

    View view;
    Fragment fr;

    // 사용자 위치 수신기
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    Button startPointAddress;
    LinearLayout categoryLinearLayout;


    static Button byCarButton;
    static Button byBusButton;
    static Button onFootButton;

    boolean isFragmentShownAgain = false;

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

            categoryLinearLayout = (LinearLayout) view.findViewById(R.id.categoryLinearLayout);

            categoryLinearLayout.bringToFront();
            categoryLinearLayout.invalidate();

            byCarButton = (Button) view.findViewById(R.id.byCar);
            byBusButton = (Button) view.findViewById(R.id.byBus);
            onFootButton = (Button) view.findViewById(R.id.onFoot);

            startPointAddress = (Button) view.findViewById(R.id.startPointAddress);

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

                    switchFragment(view);
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

//            byCarButton.callOnClick();
        }
        else{

        }
        return view;
    }

    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
    public void switchFragment(View view){

//        LoadingDialog.getInstance().progressON(getActivity());
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LoadingDialog.getInstance().progressOFF();
//            }
//        }, 5000);

        if(view == view.findViewById(R.id.byCar)){
            fr = new CarFragment();
        }else if(view == view.findViewById(R.id.byBus)){
            fr = new BusPathFragment();
        }else{
            fr = new FootFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            startPointAddress.setText(ManagementLocation.getInstance().getCurrentAddress());
            if(!isFragmentShownAgain){
                byCarButton.callOnClick();
                isFragmentShownAgain = true;
            }
        } else {

        }
    }
}