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
import android.widget.Toast;

public class PathInfoFragment extends Fragment {

    View view;
    String getString;
    Fragment fr;
    View currentView;
    static String test="";

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

            final Button byCarButton = (Button) view.findViewById(R.id.byCar);
            final Button byBusButton = (Button) view.findViewById(R.id.byBus);
            final Button onFootButton = (Button) view.findViewById(R.id.onFoot);

            byCarButton.setTextColor(Color.parseColor("#FF0000"));
            switchFragment(byCarButton);

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
                    getActivity().finish();

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

            //getString = intent.getExtras().getString("Path");
//            Intent intent = getActivity().getIntent();
//            getString = intent.getExtras().getString("Path");
//
//            getString = "";


            if(test.equals("carPath"))
            {
                Toast.makeText(getActivity().getApplicationContext(),getString,Toast.LENGTH_SHORT).show();
                byCarButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                onFootButton.setTextColor(Color.parseColor("#000000"));
                switchFragment("carPath");
            }
            //BusActivity에서 전달받은 값이 footPath 경우
            else if(test.equals("footPath"))
            {
                onFootButton.setTextColor(Color.parseColor("#FF0000"));
                byBusButton.setTextColor(Color.parseColor("#000000"));
                byCarButton.setTextColor(Color.parseColor("#000000"));
                switchFragment("footPath");
            }
            else {
                byCarButton.setTextColor(Color.parseColor("#FF0000"));
            }
        }
        return view;
    }

    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
    public void switchFragment(View view){

        if(view == view.findViewById(R.id.byCar)){
            fr = new CarFragment();
            //Toast.makeText(getApplicationContext(),"byCar",Toast.LENGTH_SHORT).show();

        }else if(view == view.findViewById(R.id.byBus)){
            fr = new BusFragment();
            //Toast.makeText(getApplicationContext(),"byBus",Toast.LENGTH_SHORT).show();
        }else{
            fr = new FootFragment();
            //Toast.makeText(getApplicationContext(),"onFoot",Toast.LENGTH_SHORT).show();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }

    //대중경로Activity에서 자동차경로 혹은 도보경로를 클릭한 경우
    public void switchFragment(String path){

        if(path == "carPath"){
            fr = new CarFragment();
            //Toast.makeText(getApplicationContext(),"byCar",Toast.LENGTH_SHORT).show();

        }else{
            fr = new FootFragment();
            //Toast.makeText(getApplicationContext(),"onFoot",Toast.LENGTH_SHORT).show();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }
}
