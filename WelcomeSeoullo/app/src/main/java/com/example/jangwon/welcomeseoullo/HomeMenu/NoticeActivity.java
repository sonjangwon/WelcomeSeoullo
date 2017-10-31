package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.jangwon.welcomeseoullo.R;

public class NoticeActivity extends Activity {

    //카드뷰 선언--------------------------------
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    //SwipeRefreshLayout mSwipeRefreshLayout;
    NestedScrollView mScrollView;
    ImageView centerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notice);

        changeStatusBarColor();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animation3, R.anim.animation4);
    }

    //상태바 없애기
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryTranslucent));
        }
    }
}
