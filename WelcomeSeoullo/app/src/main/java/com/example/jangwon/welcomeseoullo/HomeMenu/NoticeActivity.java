package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.R;

import java.util.ArrayList;

public class NoticeActivity extends Activity {

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

    //SwipeRefreshLayout mSwipeRefreshLayout;
    NestedScrollView mScrollView;
    ImageView centerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notice);

        mScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        centerImage = (ImageView) findViewById(R.id.newNoticeImage);
        mScrollView.smoothScrollBy(100, 1000);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
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
                        intent.putExtra("urlNum", NewsCrawling.getInstance().urlNumList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                }));

        mRecyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), NewsCrawling.getInstance().items, R.layout.fragment_notice));
    }
}
