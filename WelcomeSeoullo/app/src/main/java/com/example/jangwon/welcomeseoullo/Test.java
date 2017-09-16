package com.example.jangwon.welcomeseoullo;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by woga1 on 2017-09-13.
 */

public class Test extends AppCompatActivity {
    private ViewPager pager;
    ViewFlipper flipper;
    ListView listview ;
    ArrayList<String> titleList = new ArrayList<String>();
    ArrayList<String> urlNumList = new ArrayList<String>();
    ArrayAdapter mAdapter;
    int count =0;
    //현재화면인덱스
    int currentPage =0;
    //터치시작 x좌표
    float downX =0;
    //터치끝 x좌표
    float upX =0;
    //화면 스크린 높이 넓이
    int height=0;
    int width=0;
    private Integer[] Images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4 };
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        pager= (ViewPager) findViewById(R.id.viewPager);
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), ImgArray);
      // MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getFragmentManager());
        pager.setAdapter(myAdapter);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        listview = (ListView) findViewById(R.id.noticeList2);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Test.this, ViewContents.class);
                intent.putExtra("urlNum", urlNumList.get(position));
                startActivity(intent);
            }
        });
        Log.e("메인","작동끝");
    }
    public void init()
    {
        for(int i=0;i<Images.length;i++){
            ImgArray.add(Images[i]);
        }
        pager.setAdapter(new MyAdapter(Test.this, ImgArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == Images.length) {
                    currentPage = 0;
                }
                pager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    public void startImageSlide()
    {
        for(int i=0;i<4;i++){

            ImageView img= new ImageView(this);
//            ViewPager.LayoutParams params = (ViewPager.LayoutParams) img.getLayoutParams();
            img.setImageResource(R.drawable.image1+i);
            //img.setOnTouchListener(this);
            img.setMaxWidth(width);
            img.setMaxHeight(height);
            pager.addView(img);
        }

    }
//    public boolean onTouch(View v, MotionEvent event)
//    {
//        if(event.getAction()==MotionEvent.ACTION_DOWN){
//            downX = event.getX();
//        }
//        //터치종료
//        else if(event.getAction()==MotionEvent.ACTION_UP){
//            upX = event.getX();
//
//            //왼쪽 -> 오른쪽
//            if(upX < downX){
//                //애니메이션
//                flipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in));
//                flipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_out));
//
//                //인덱스체크 - 마지막화면이면 동작없음
//                if(currentIndex < (countIndexes-1)){
//                    flipper.showNext();
//
//                    currentIndex++;
//                    //인덱스 업데이트
//                }
//            }
//            //오른쪽 -> 왼쪽
//            else if(upX > downX){
//                //애니메이션 설정
//                flipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_in));
//                flipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_out));
//
//                //인덱스체크 - 첫번째화면이면 동작없음
//                if(currentIndex > 0){
//                    flipper.showPrevious();
//
//                    currentIndex--;
//                    //인덱스 업데이트
//                }
//            }
//            else if(upX == downX)
//            {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse("http://www.naver.com"));
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"이동", Toast.LENGTH_SHORT).show();
//            }
//        }
//        return true;
//    }
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


    public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Document document = null;
            try {
                document = Jsoup.connect("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEList.do").get();
                Elements elements = document.getElementsByAttributeValue("class", "t_left");
                //Elements elements = document.select("td.t_left > a");
                for (Element element : elements) {
                    titleList.add(element.text());
                    String title = element.select("a").attr("href").toString();
                    String titleNum = title.substring(26,29);
                    urlNumList.add(titleNum);
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


    private int getItem(int i) {
        return pager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
//        startActivity(new Intent(getApplicationContext(), NoticeActivity.class));
//        finish();
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


}
