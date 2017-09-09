package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PathInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_info);

        final Button byCarButton = (Button) findViewById(R.id.byCar);
        final Button byBusButton = (Button) findViewById(R.id.byBus);
        final Button onFootButton = (Button) findViewById(R.id.onFoot);
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        final Button byCarButton = (Button) findViewById(R.id.byCar);
//        final Button byBusButton = (Button) findViewById(R.id.byBus);
//        final Button onFootButton = (Button) findViewById(R.id.onFoot);
//        byCarButton.setTextColor(Color.parseColor("#FF0000"));
//        byCarButton.setOnClickListener(new EditText.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                byCarButton.setTextColor(Color.parseColor("#FF0000"));
//                byBusButton.setTextColor(Color.parseColor("#000000"));
//                onFootButton.setTextColor(Color.parseColor("#000000"));
//                switchFragment(view);
//            }
//
//        });
//        byBusButton.setOnClickListener(new EditText.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                byBusButton.setTextColor(Color.parseColor("#FF0000"));
//                byCarButton.setTextColor(Color.parseColor("#000000"));
//                onFootButton.setTextColor(Color.parseColor("#000000"));
//                switchFragment(view);
//            }
//
//        });
//        onFootButton.setOnClickListener(new EditText.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                onFootButton.setTextColor(Color.parseColor("#FF0000"));
//                byBusButton.setTextColor(Color.parseColor("#000000"));
//                byCarButton.setTextColor(Color.parseColor("#000000"));
//                switchFragment(view);
//            }
//
//        });
//    }

    public void switchFragment(View view){
        Fragment fr = new CarFragment();

        if(view == findViewById(R.id.byCar)){
            fr = new CarFragment();
            Toast.makeText(getApplicationContext(),"byCar",Toast.LENGTH_SHORT).show();

        }else if(view == findViewById(R.id.byBus)){
            fr = new BusFragment();
            Toast.makeText(getApplicationContext(),"byBus",Toast.LENGTH_SHORT).show();
        }else{
            fr = new FootFragment();
            Toast.makeText(getApplicationContext(),"onFoot",Toast.LENGTH_SHORT).show();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }




}
