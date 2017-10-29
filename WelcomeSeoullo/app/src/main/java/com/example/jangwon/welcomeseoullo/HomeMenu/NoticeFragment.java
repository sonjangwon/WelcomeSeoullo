package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.R;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    View view;

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


    public NoticeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_notice, container, false);

        mScrollView = (NestedScrollView) view.findViewById(R.id.nestedScrollView);
        centerImage = (ImageView) view.findViewById(R.id.newNoticeImage);
        mScrollView.smoothScrollBy(100, 1000);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity().getApplicationContext(),new LinearLayoutManager(getActivity()).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), ViewContents.class);
                        intent.putExtra("urlNum", NewsCrawling.getInstance().urlNumList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                }));

        //items = new ArrayList<>();
        mRecyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(), NewsCrawling.getInstance().items, R.layout.fragment_notice));

        return view;
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