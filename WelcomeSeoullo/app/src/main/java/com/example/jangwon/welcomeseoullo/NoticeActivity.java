package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.jangwon.welcomeseoullo.HomeMenu.PermissionRequester;
import com.example.jangwon.welcomeseoullo.HomeMenu.ViewContents;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity implements View.OnTouchListener{

    ViewFlipper flipper;
    ListView listview ;
    ArrayList<String> titleList = new ArrayList<String>();
    ArrayList<String> urlNumList = new ArrayList<String>();
    ArrayAdapter mAdapter;
    int count =0;
    int countIndexes = 0;
    //현재화면인덱스
    int currentIndex =0;
    //터치시작 x좌표
    float downX =0;
    //터치끝 x좌표
    float upX =0;
    //화면 스크린 높이 넓이
    int screenheight=0;
    int screenwidth=0;
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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenheight = displayMetrics.heightPixels;
        screenwidth = displayMetrics.widthPixels;

        flipper= (ViewFlipper)findViewById(R.id.viewFlipper);
        startImageSlide();
        listview = (ListView) findViewById(R.id.noticeList);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NoticeActivity.this, ViewContents.class);
                    intent.putExtra("urlNum", urlNumList.get(position));
                    startActivity(intent);
            }
        });
        Log.e("메인","작동끝");
    }

    public void startImageSlide()
    {
                //총 10개의 이미지 중 3개만 XML에서 ImageView로 만들었었음.
        //소스코드에서 ViewFipper에게 나머지 7개의 이미지를 추가하기위해
        //ImageView 7개를 만들어서 ViewFlipper에게 추가함
        //layout_width와 layout_height에 대한 특별한 지정이 없다면
        //기본적으로 'match_parent'로 설정됨.
        for(int i=0;i<4;i++){

            ImageView img= new ImageView(this);
//            ViewPager.LayoutParams params = (ViewPager.LayoutParams) img.getLayoutParams();
            img.setImageResource(R.drawable.image1+i);
            img.setOnTouchListener(this);

            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 212, getResources().getDisplayMetrics());
            Log.e("플립퍼높이", String.valueOf(px));
            //img.setMaxHeight((int)px);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            android.view.ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
//            layoutParams.width = screenwidth;
//            layoutParams.height =(int)px;
//            Log.e("dd", String.valueOf(screenwidth) +  String.valueOf(layoutParams.height));
            //img.setLayoutParams(new ViewGroup.LayoutParams(700, (int)px));
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

        Animation showIn= AnimationUtils.loadAnimation(this, R.anim.push_right_in);

        //ViewFlipper에게 등장 애니메이션 적용
        flipper.setInAnimation(showIn);

        //ViewFlipper의 View가 교체될 때 퇴장하는 View의 애니메이션
        //오른쪽으로 슬라이딩 되면 퇴장하는 애니메이션 리소스 파일 적용.
        //위와 다른 방법으로 애니메이션을 적용해봅니다.
        //첫번째 파라미터 : Context
        //두번재 파라미터 : 트윈(Tween) Animation 리소스 파일(오른쪽으로 슬라이딩되며 퇴장)
        flipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_out));
        flipper.setFlipInterval(2000);//플리핑 간격(1000ms)

        flipper.startFlipping();//자동 Flipping 시작
    }
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            downX = event.getX();
        }
        //터치종료
        else if(event.getAction()==MotionEvent.ACTION_UP){
            upX = event.getX();

            //왼쪽 -> 오른쪽
            if(upX < downX){
                //애니메이션
                flipper.stopFlipping();
                flipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_out));
                flipper.showNext();
                //인덱스체크 - 마지막화면이면 동작없음
//                if(currentIndex < (countIndexes-1)){
//                    flipper.showNext();
//
//                    currentIndex++;
//                    //인덱스 업데이트
//                }
            }
            //오른쪽 -> 왼쪽
            else if(upX > downX){
                //애니메이션 설정
                flipper.stopFlipping();
                flipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_out));
                flipper.showPrevious();
                //인덱스체크 - 첫번째화면이면 동작없음
//                if(currentIndex > 0){
//                    flipper.showPrevious();
//
//                    currentIndex--;
//                    //인덱스 업데이트
//                }
            }
            else if(upX == downX)
            {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.naver.com"));
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"이동", Toast.LENGTH_SHORT).show();
            }

            flipper.startFlipping();

        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();

        NewThread task = new NewThread();
        if(count ==0)
        {
            task.execute();
            Log.e("어싱크실행", task.getStatus().toString());
            count++;
        }
        else
        {
            task.cancel(true);
        }
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
    }


    public class NewThread extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            Log.e("NewThreadAsyncTask", "do");
            Document document = null;
            try {
                document = Jsoup.connect("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEList.do").get();
                Log.e("NoticeActivity", "connect");
                //Elements elements = document.select(".table_list02");
                Elements elements = document.getElementsByAttributeValue("class", "t_left");
                Log.e("NoticeActivity", elements.text());
                //Elements elements = document.select("td.t_left > a");
                for (Element element : elements) {
                    titleList.add(element.text());
                    //result.put("제목"+i,element.text());
                    Log.e("새소식제목", element.text());
                    String title = element.select("a").attr("href").toString();
                    String titleNum = title.substring(26,29);
                    urlNumList.add(titleNum);
                    //result.put("제목"+i,element.text());
                    Log.e("새소식제목 번호", titleNum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result) {

            mAdapter.notifyDataSetChanged();
            listview.setAdapter(mAdapter);
            listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

    }
}


