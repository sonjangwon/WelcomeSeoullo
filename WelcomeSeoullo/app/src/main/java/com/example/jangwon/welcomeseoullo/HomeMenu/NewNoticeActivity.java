package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by woga1 on 2017-10-25.
 */
//새소식 버튼 누르면 연결되는 액티비티
public class NewNoticeActivity extends AppCompatActivity {
    //이곳엔 이미지뷰하나랑 리사이컬뷰 목록만 사용될예정

    //크롤링해서 새소식 데이터담을 리스트
    ArrayList<String> titleList = new ArrayList<String>(10);
    ArrayList<String> urlNumList = new ArrayList<String>(10);
    ArrayList<String> dateList = new ArrayList<String>(10);
    int count = 0;
    //카드뷰 선언--------------------------------
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Item> items;

    private TextView[] dots;
    private int[] layouts;
    private LinearLayout dotsLayout;
    //refresh에 필요
    //SwipeRefreshLayout mSwipeRefreshLayout;
    NestedScrollView mScrollView;
    ImageView centerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newnotice_layout);
        //mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        centerImage = (ImageView) findViewById(R.id.newNoticeImage);
        mScrollView.smoothScrollBy(100, 1000);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getApplicationContext(),new LinearLayoutManager(this).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), ViewContents.class);
                        intent.putExtra("urlNum", urlNumList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                }));

        items = new ArrayList<>();
        mRecyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), NewsCrawling.getInstance().items, R.layout.newnotice_layout));
//        loadMoreText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int index = items.size();
//                int end = index + 2;
//                if(items.size() <titleList.size()) {
//                    for (int i = index; i < end; i++) {
//                        items.add(new Item(titleList.get(i), "  " + dateList.get(i)));
//                    }
//                    //mAdapter.notifyDataSetChanged();
//                    //수정한 부분 될지 안될지 아직 확인 x
//                    mRecyclerView.notify();
//                }
//                if(end== titleList.size())
//                {
//                    loadMoreText.setVisibility(View.GONE);
//                }
//            }
//        });

    }
//    public void refreshView() {
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mRecyclerView.removeAllViewsInLayout();
//                centerImage.setVisibility(View.GONE);
//                mRecyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.fragment_home));
//                centerImage.setVisibility(View.VISIBLE);
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });
//        mSwipeRefreshLayout.setColorSchemeResources(
//                R.color.orangeGolfwang
//        );
//    }

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
                    if(num.length()>26)
                    {
                        String n = num.substring(0,27);
                        num = n + " ....";
                        titleList.add(num);
                    }
                    else {
                        titleList.add(element.text());
                    }
                    String title = element.select("a").attr("href").toString();
                    String titleNum = title.substring(26,29);
                    urlNumList.add(titleNum);
                }
//                Elements ss = document.select("tr td:eq(3)"); //서울로 새소식 날짜 담을 크롤링
//                for (Element e : ss) {
//                    String year =  e.text().substring(0,4);
//                    String month = e.text().substring(5,7);
//                    String day = e.text().substring(8,10);
//                    String date = year+"년 "+ month+"월 "+day+"일";
//                    dateList.add(date);
//
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            for(int i=0; i<titleList.size(); i++)
            {
                items.add(new Item(titleList.get(i)));
            }

            mRecyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.newnotice_layout));
            mAdapter = mRecyclerView.getAdapter();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
//        NewThread task = new NewThread();
//        if (count == 0) {
//            task.execute();
//            //Log.e("어싱크실행", task.getStatus().toString());
//            count++;
//        } else {
//            task.cancel(true);
//        }
    }
}
