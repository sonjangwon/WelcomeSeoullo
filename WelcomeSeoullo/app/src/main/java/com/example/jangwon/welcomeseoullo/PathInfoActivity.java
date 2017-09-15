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
    //현재위치에서 서울로7017까지 경로안내를 제공하는 Activity
    String getString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_info);

        //BusActivity에서 자동차경로 혹은 도보경로의 버튼을 누를때를 구분하기 위하여 값을 전달받아 구분한다.
        //Intent intent = getIntent();
        //getString = intent.getExtras().getString("Path");

        Toast.makeText(getApplicationContext(),getString,Toast.LENGTH_SHORT).show();
        final Button byCarButton = (Button) findViewById(R.id.byCar);
        final Button byBusButton = (Button) findViewById(R.id.byBus);
        final Button onFootButton = (Button) findViewById(R.id.onFoot);

        //BusActivity에서 전달받은 값이 carPath인 경우
        if(getString.equals("carPath"))
        {
            Toast.makeText(getApplicationContext(),getString,Toast.LENGTH_SHORT).show();
            byCarButton.setTextColor(Color.parseColor("#FF0000"));
            byBusButton.setTextColor(Color.parseColor("#000000"));
            onFootButton.setTextColor(Color.parseColor("#000000"));
            switchFragment("carPath");
        }
        //BusActivity에서 전달받은 값이 footPath 경우
        else if(getString.equals("footPath"))
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
                startActivity(new Intent(PathInfoActivity.this, BusPathActivity.class));
                finish();
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
    }

    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
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

    //대중경로Activity에서 자동차경로 혹은 도보경로를 클릭한 경우
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
