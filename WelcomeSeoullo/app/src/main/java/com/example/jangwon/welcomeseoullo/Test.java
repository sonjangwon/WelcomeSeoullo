package com.example.jangwon.welcomeseoullo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by woga1 on 2017-09-13.
 */

public class Test extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        String n = intent.getExtras().getString("title");
        String n2 = "윤이상 탄생 100주년 기념 '프롬나드 콘서트' 공연 안내"+

        "세계적인 작곡가 윤이상 선생님 탄생 100주년을 맞아 서울문화재단 주관하에 윤이상 탄생 100주년 기념 프롬나드 콘서트가 개최됩니다."+

                "여러 행사중 저희 서울로7017 윤슬에서 '윤슬음'행사도 진행되니 많은 참여 바랍니다."+

                "- 프로그램명 : 윤슬음(音)"+
                "- 일시 : 2017. 9. 15.(금) 20시~20시 30분"+
                "- 장소 : 윤슬 ( 서울로7017 만리동광장 남측 위치)"+
                "- 관람인원 : 150명(선착순 마감)"+
                "- 내용 : 윤이상의 네곡의 오페라 중 마지막 곡이었던 <심청>에 담긴 자기 희생과 구원, 박애의 가치를 국악과 힙합의 사운드로 재해석하여 장르를 넘나드는 새로운 형태의 야외 음악 공연";
        TextView tv1 = (TextView) findViewById(R.id.title);
        TextView tv2 = (TextView) findViewById(R.id.content);
        tv1.setText(n);
        tv2.setText(n2);
    }
}
