package com.example.jangwon.welcomeseoullo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by woga1 on 2017-09-13.
 */

public class Test extends AppCompatActivity {
    private ViewPager pager;
    ViewFlipper flipper;
    ListView listview ;
    private MyViewPagerAdapter myViewPagerAdapter;
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
    AutoScrollViewPager viewPager;
    private Integer[] Images;
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        viewPager = (AutoScrollViewPager) findViewById(R.id.viewPager);
        ImageAdapter imgadapter = new ImageAdapter(this);

        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(imgadapter);

        viewPager.setAdapter(wrappedAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        viewPager.startAutoScroll();
        //pager= (ViewPager) findViewById(viewPager);
        //myViewPagerAdapter = new MyViewPagerAdapter();
        //Images = new Integer[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
        //pager.setAdapter(myViewPagerAdapter);
        //pager.addOnPageChangeListener(viewPagerPageChangeListener);

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
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.imagefragment, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imagefragment_imageview);
            imageView.setImageResource(Images[position]);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return Images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
