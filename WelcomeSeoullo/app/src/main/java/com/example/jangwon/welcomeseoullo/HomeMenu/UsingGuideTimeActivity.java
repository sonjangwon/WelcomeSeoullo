package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jangwon.welcomeseoullo.R;

/**
 * Created by woga1 on 2017-10-25.
 */

//메인에서 이용안내 및 시간 액티베이터
public class UsingGuideTimeActivity extends AppCompatActivity {
    //리플릿다운로드?부분 보여주고 안내시간 이용 시간은 이미지뷰로 나타냄
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usingtime_layout);
    }
}
