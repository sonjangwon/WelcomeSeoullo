package com.example.jangwon.welcomeseoullo.NavigationMenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;

import static com.example.jangwon.welcomeseoullo.R.id.byCar;

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
    Button onFootButton;
    boolean isFragmentShownAgain = false;
    ImageButton byCarImageButton;
    ImageButton byBusImageButton;
    ImageButton onFootImageButton;
    LinearLayout byCarLinearlayout;
    LinearLayout byBusLinearLayout;
    LinearLayout onFootLinearLayout;
    LinearLayout byCarLayout;
    LinearLayout byBusLayout;
    LinearLayout onFootLayout;

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

            byCarImageButton = (ImageButton) view.findViewById(R.id.byCarImageButton);
            byBusImageButton = (ImageButton) view.findViewById(R.id.byBusImageButton);
            onFootImageButton = (ImageButton) view.findViewById(R.id.onFootImageButton);
            byCarLinearlayout = (LinearLayout) view.findViewById(R.id.byCarLinearLayout);
            byBusLinearLayout = (LinearLayout) view.findViewById(R.id.byBusLinearLayout);
            onFootLinearLayout = (LinearLayout) view.findViewById(R.id.onFootLinearLayout);
            byCarLayout = (LinearLayout) view.findViewById(R.id.byCarLayout);
            byBusLayout = (LinearLayout) view.findViewById(R.id.byBusLayout);
            onFootLayout = (LinearLayout) view.findViewById(R.id.onFootLayout);

            startPointAddress = (Button) view.findViewById(R.id.startPointAddress);

            currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
            currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
            currentAddress = ManagementLocation.getInstance().getCurrentAddress();

            startPointAddress.setText(currentAddress);
            byCarButton.setOnClickListener(new LinearLayout.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //자동차 경로안내 버튼을 누를 경우
                    byCarImageButton.setBackgroundResource(R.drawable.clickedcar);
                    byBusImageButton.setBackgroundResource(R.drawable.nonclickbus);
                    onFootImageButton.setBackgroundResource(R.drawable.nonclickwalker);
                    byCarButton.setTextColor(Color.parseColor("#7BA293"));
                    byBusButton.setTextColor(Color.parseColor("#c0c5ce"));
                    onFootButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarLayout.setBackgroundColor(Color.parseColor("#FDA293"));
                    byBusLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    onFootLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    switchFragment("자동차");
                }
            });
            byCarImageButton.setOnClickListener(new LinearLayout.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //자동차 경로안내 버튼을 누를 경우
                    byBusImageButton.setBackgroundResource(R.drawable.nonclickbus);
                    onFootImageButton.setBackgroundResource(R.drawable.nonclickwalker);
                    byCarImageButton.setBackgroundResource(R.drawable.clickedcar);
                    byCarButton.setTextColor(Color.parseColor("#7BA293"));
                    byBusButton.setTextColor(Color.parseColor("#c0c5ce"));
                    onFootButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarLayout.setBackgroundColor(Color.parseColor("#FDA293"));
                    byBusLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    onFootLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    switchFragment("자동차");
                }
            });
            byBusButton.setOnClickListener(new LinearLayout.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //대중교통 경로안내 버튼을 누를 경우
                    byBusImageButton.setBackgroundResource(R.drawable.clickedbus);
                    byCarImageButton.setBackgroundResource(R.drawable.nonclickcar);
                    onFootImageButton.setBackgroundResource(R.drawable.nonclickwalker);
                    byBusButton.setTextColor(Color.parseColor("#7BA293"));
                    byCarButton.setTextColor(Color.parseColor("#c0c5ce"));
                    onFootButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    byBusLayout.setBackgroundColor(Color.parseColor("#FDA293"));
                    onFootLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    switchFragment("대중교통");
                }
            });
            byBusImageButton.setOnClickListener(new LinearLayout.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //대중교통 경로안내 버튼을 누를 경우
                    byBusImageButton.setBackgroundResource(R.drawable.clickedbus);
                    byCarImageButton.setBackgroundResource(R.drawable.nonclickcar);
                    onFootImageButton.setBackgroundResource(R.drawable.nonclickwalker);
                    byBusButton.setTextColor(Color.parseColor("#7BA293"));
                    byCarButton.setTextColor(Color.parseColor("#c0c5ce"));
                    onFootButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    byBusLayout.setBackgroundColor(Color.parseColor("#FDA293"));
                    onFootLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    switchFragment("대중교통");
                }
            });
            onFootButton.setOnClickListener(new LinearLayout.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //도보 경로안내 버튼을 누를 경우
                    onFootImageButton.setBackgroundResource(R.drawable.clickedwalker);
                    byCarImageButton.setBackgroundResource(R.drawable.nonclickcar);
                    byBusImageButton.setBackgroundResource(R.drawable.nonclickbus);
                    onFootButton.setTextColor(Color.parseColor("#7BA293"));
                    byBusButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    byBusLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    onFootLayout.setBackgroundColor(Color.parseColor("#FDA293"));

                    switchFragment("도보");
                }
            });
            onFootImageButton.setOnClickListener(new LinearLayout.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //도보 경로안내 버튼을 누를 경우
                    onFootImageButton.setBackgroundResource(R.drawable.clickedwalker);
                    byCarImageButton.setBackgroundResource(R.drawable.nonclickcar);
                    byBusImageButton.setBackgroundResource(R.drawable.nonclickbus);
                    onFootButton.setTextColor(Color.parseColor("#7BA293"));
                    byBusButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarButton.setTextColor(Color.parseColor("#c0c5ce"));
                    byCarLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    byBusLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    onFootLayout.setBackgroundColor(Color.parseColor("#FDA293"));

                    switchFragment("도보");
                }
            });
            byCarButton.callOnClick();
            fr = new CarFragment();
        }
        else{
            fr = new CarFragment();
        }
        return view;
    }

    public static Handler buttonClickHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byCarButton.callOnClick();
        }
    };

    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
    public void switchFragment(String path){

        if(path=="자동차"){
            fr = new CarFragment();
        }else if(path=="대중교통"){
            fr = new BusFragment();
        }else if(path=="도보"){
            fr = new FootFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment_place, fr);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            startPointAddress.setText(ManagementLocation.getInstance().getCurrentAddress());
            if(!isFragmentShownAgain){
//                byBusButton.callOnClick();
                isFragmentShownAgain = true;//drawline test
            }
        } else {

        }
    }
}