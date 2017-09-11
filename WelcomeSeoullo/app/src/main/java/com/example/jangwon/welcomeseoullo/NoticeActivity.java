package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * Created by woga1 on 2017-09-07.
 */
public class NoticeActivity extends Activity {

    ViewFlipper flipper;
    static ListView listview;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Log.e("메인","작동");
        PermissionRequester.Builder request = new PermissionRequester.Builder(this);

        request.create().request( Manifest.permission.INTERNET , 10000 , new PermissionRequester.OnClickDenyButtonListener() {

            @Override
            public void onClick(Activity activity) {
                Toast.makeText(getApplicationContext(), "인터넷 권한이 필요합니다.", Toast.LENGTH_SHORT).show(); activity.finish();
            }
        } );

        //ViewFlipper 객체 참조

//
//
//        flipper= (ViewFlipper)findViewById(R.id.viewFlipper);
        listview = (ListView) findViewById(R.id.noticeList);
        tv = (TextView) findViewById(R.id.textView);
        Log.e("메인","작동끝");
    }

    public void startImageSlide()
    {
                //총 10개의 이미지 중 3개만 XML에서 ImageView로 만들었었음.
        //소스코드에서 ViewFipper에게 나머지 7개의 이미지를 추가하기위해
        //ImageView 7개를 만들어서 ViewFlipper에게 추가함
        //layout_width와 layout_height에 대한 특별한 지정이 없다면
        //기본적으로 'match_parent'로 설정됨.
        for(int i=0;i<2;i++){

            ImageView img= new ImageView(this);
//            ViewPager.LayoutParams params = (ViewPager.LayoutParams) img.getLayoutParams();
            img.setImageResource(R.drawable.image3+i);
            flipper.addView(img);
        }
        //ViewFlipper가 View를 교체할 때 애니메이션이 적용되도록 설정
        //애니메이션은 안드로이드 시스템이 보유하고 있는  animation 리소스 파일 사용.
        //ViewFlipper의 View가 교체될 때 새로 보여지는 View의 등장 애니메이션
        //AnimationUtils 클래스 : 트윈(Tween) Animation 리소스 파일을 Animation 객체로 만들어 주는 클래스
        //AnimationUtils.loadAnimaion() - 트윈(Tween) Animation 리소스 파일을 Animation 객체로 만들어 주는 메소드
        //첫번째 파라미터 : Context
        //두번재 파라미터 : 트윈(Tween) Animation 리소스 파일(여기서는 안드로이드 시스템의 리소스 파일을 사용
        //                    (왼쪽에서 슬라이딩되며 등장)

        Animation showIn= AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        //ViewFlipper에게 등장 애니메이션 적용
        flipper.setInAnimation(showIn);

        //ViewFlipper의 View가 교체될 때 퇴장하는 View의 애니메이션
        //오른쪽으로 슬라이딩 되면 퇴장하는 애니메이션 리소스 파일 적용.
        //위와 다른 방법으로 애니메이션을 적용해봅니다.
        //첫번째 파라미터 : Context
        //두번재 파라미터 : 트윈(Tween) Animation 리소스 파일(오른쪽으로 슬라이딩되며 퇴장)
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        flipper.setFlipInterval(2000);//플리핑 간격(1000ms)

        flipper.startFlipping();//자동 Flipping 시작
    }


    @Override
    protected void onResume(){
        super.onResume();
        GetWebData get = new GetWebData();
        get.execute();

    }
}


