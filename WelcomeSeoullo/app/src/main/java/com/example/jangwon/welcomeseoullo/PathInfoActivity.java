package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PathInfoActivity extends AppCompatActivity {

    String getString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_info);

        Intent intent = getIntent();
        getString = intent.getStringExtra("carPath");
        getString = intent.getStringExtra("footPath");

        final Button byCarButton = (Button) findViewById(R.id.byCar);
        final Button byBusButton = (Button) findViewById(R.id.byBus);
        final Button onFootButton = (Button) findViewById(R.id.onFoot);
//        final Button test = (Button) findViewById(R.id.test);
        if(getString=="carPath")
        {
            byCarButton.setTextColor(Color.parseColor("#FF0000"));
            byBusButton.setTextColor(Color.parseColor("#000000"));
            onFootButton.setTextColor(Color.parseColor("#000000"));
            switchFragment("carPath");
        }
        else if(getString=="footPath")
        {
            onFootButton.setTextColor(Color.parseColor("#FF0000"));
            byBusButton.setTextColor(Color.parseColor("#000000"));
            byCarButton.setTextColor(Color.parseColor("#000000"));
            switchFragment("footPath");
        }
        else {
            byCarButton.setTextColor(Color.parseColor("#FF0000"));
        }
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
                startActivity(new Intent(PathInfoActivity.this, Test2Activity.class));
                finish();
//                switchFragment(view);
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
//        test.setOnClickListener(new EditText.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(PathInfoActivity.this, Test2Activity.class));
//            }
//
//        });
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

    public void switchFragment(String path){
        Fragment fr = new CarFragment();

        if(path == "carPath"){
            fr = new CarFragment();
            Toast.makeText(getApplicationContext(),"byCar",Toast.LENGTH_SHORT).show();

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
