package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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
                        intent.putExtra("urlNum", urlNumList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                }));

        items = new ArrayList<>();

        return view;
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
            }
            catch (IOException e) {
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

            mRecyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(), items, R.layout.fragment_notice));
            mAdapter = mRecyclerView.getAdapter();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        NewThread task = new NewThread();
        if (count == 0) {
            task.execute();
            count++;
        } else {
            task.cancel(true);
        }
    }

}