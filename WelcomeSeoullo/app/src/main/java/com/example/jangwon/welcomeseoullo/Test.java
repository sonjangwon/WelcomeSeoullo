package com.example.jangwon.welcomeseoullo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by woga1 on 2017-09-13.
 */

public class Test extends AppCompatActivity {

    private MyViewPagerAdapter myViewPagerAdapter;
    ArrayList<String> titleList = new ArrayList<String>();
    ArrayList<String> urlNumList = new ArrayList<String>();
    ArrayList<String> dateList = new ArrayList<String>();
    ArrayList<String> urlList = new ArrayList<String>();
    int count = 0;
    //현재화면인덱스

    AutoScrollViewPager viewPager;
    private Integer[] Images;
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();
    InfiniteViewPager view;

    //카드뷰 선언--------------------------------
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Item> items;

    private TextView[] dots;
    private int[] layouts;
    private LinearLayout dotsLayout;
    //refresh에 필요
    PagerAdapter wrappedAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NestedScrollView mScrollView;
    ImageView centerImage;
    //이미지 파싱
    private String url;
    boolean getimageFirst = false;
    int isEmptyImage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        centerImage = (ImageView) findViewById(R.id.imageView2);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        viewPager = (AutoScrollViewPager) findViewById(R.id.viewPager);
        mScrollView.smoothScrollBy(100, 1000);
//        mScrollView.post(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                ObjectAnimator.ofInt(mScrollView, "scrollY", mScrollView.getBottom()).setDuration(1000).start(); }
//        });
        refreshView();
        ImageAdapter imgadapter = new ImageAdapter(this);
//        view = new InfiniteViewPager(this);
//        view = (InfiniteViewPager) findViewById(R.id.viewPager);
        wrappedAdapter = new InfinitePagerAdapter(imgadapter, getApplicationContext());

        viewPager.setAdapter(wrappedAdapter);
        viewPager.setOnTouchListener(viewPagerTouchListener);
        viewPager.startAutoScroll();
//        myViewPagerAdapter = new MyViewPagerAdapter();
        //Images = new Integer[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
//
//        pager.setAdapter(myViewPagerAdapter);
//        pager.addOnPageChangeListener(viewPagerPageChangeListener);
        //CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        //indicator.setViewPager(viewPager);
        //dotsLayout = (LinearLayout) findViewById(R.id.dotLayouts);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getApplicationContext(), new LinearLayoutManager(this).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(Test.this, ViewContents.class);
                        intent.putExtra("urlNum", urlNumList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
                    }
                }));

        //mRecyclerView.setNestedScrollingEnabled(false);
        items = new ArrayList<>();
        Log.e("메인", "작동끝");
    }

    public void refreshView() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.removeAllViewsInLayout();
                viewPager.setVisibility(View.GONE);
                centerImage.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                mRecyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.test));
                centerImage.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light
        );
    }

    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        NewThread task = new NewThread();
        thumnailThread thumnailTask = new thumnailThread();

        if (count == 0) {
            task.execute();
            thumnailTask.execute();
            Log.e("어싱크실행", task.getStatus().toString());
            count++;
        } else {
            task.cancel(true);
            thumnailTask.cancel(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String _getImage(Document doc) {
        // 2nd -> img in p
        for (Element e1 : doc.getElementsByTag("p")) {
                for (Element e2 : e1.getElementsByTag("img")) {
                    final String text = getValidPath(e2.attr("src"));
                    if (text != null && !getimageFirst) {
                        //Log.e("imageurl", text);
                        urlList.add(text);
                        //Log.e("listSize", String.valueOf(urlNumList.size()) + ":" + String.valueOf(urlList.size()));
                        getimageFirst = true;
                        isEmptyImage++;
                    }
                }
            }
        // etc empty
        return "";
    }

    public String getEmptyImage(String url) {
        return url;
    }

    private String getValidPath(String url) {
        try {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                //Log.e("imageurl",url);
                return url;
            }

            final URI ogpUri = new URI(this.url);
            final URI imgUri = ogpUri.resolve(url);
            return imgUri.toString();
        } catch (URISyntaxException e) {
            return url;
        }
    }
    public class thumnailThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Document document = null;
            try {
                for(int i=0; i<urlNumList.size(); i++) {
                    document = Jsoup.connect("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEView.do?board_seq=" + urlNumList.get(i) + "&pageIndex=1&pageSize=10&searchCondition=all&searchKeyword=").get();
                    _getImage(document);
                    getimageFirst = false;
                    if(isEmptyImage==0)
                    {
                        urlList.add("http://seoullo7017.seoul.go.kr/img/front/img_logo.png");
                        Log.e("emptyImage", String.valueOf(i));
                    }
                    isEmptyImage = 0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            for(int i=0; i<titleList.size(); i++)
            {
                items.add(new Item(titleList.get(i), "  "+dateList.get(i),urlList.get(i)));
            }
            mRecyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.test));
        }
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
                    String num = element.text();
                    if(num.length()>40)
                    {
                        String n = num.substring(0,41);
                        num = n + "...";
                        titleList.add(num);
                    }
                    else {
                        titleList.add(element.text());
                    }
                    String title = element.select("a").attr("href").toString();
                    String titleNum = title.substring(26,29);
                    urlNumList.add(titleNum);
                }
                Elements ss = document.select("tr td:eq(3)");
                for (Element e : ss) {
                    String year =  e.text().substring(0,4);
                    String month = e.text().substring(5,7);
                    String day = e.text().substring(8,10);
                    String date = year+"년 "+ month+"월 "+day+"일";
                    dateList.add(date);

                    //Log.e("dd",date);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result) {



        }
    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnTouchListener viewPagerTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Toast.makeText(getApplicationContext(),"터치", Toast.LENGTH_SHORT);
            return false;
        }
    };
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
        public Object instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.imagefragment, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imagefragment_imageview);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
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
    public void whenTouchImagePosition(int index)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        if(index ==0){
            intent.setData(Uri.parse("http://www.naver.com"));
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"1이동", Toast.LENGTH_SHORT).show();
        }
        else if(index == 1)
        {
            intent.setData(Uri.parse("http://www.daum.net"));
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"2이동", Toast.LENGTH_SHORT).show();

        }
        else if(index == 2)
        {
            intent.setData(Uri.parse("http://www.naver.com"));
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"3이동", Toast.LENGTH_SHORT).show();

        }
    }
}
