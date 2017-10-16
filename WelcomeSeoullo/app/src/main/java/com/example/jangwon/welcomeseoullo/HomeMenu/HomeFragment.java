package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

import com.example.jangwon.welcomeseoullo.LoadingDialog;
import com.example.jangwon.welcomeseoullo.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;

    private Test.MyViewPagerAdapter myViewPagerAdapter;
    ArrayList<String> titleList = new ArrayList<String>();
    ArrayList<String> urlNumList = new ArrayList<String>();
    ArrayList<String> dateList = new ArrayList<String>();
    int count =0;
    //현재화면인덱스

    AutoScrollViewPager viewPager;
    private Integer[] Images;
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();
    //InfiniteViewPager view;

    //카드뷰 선언--------------------------------
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Item> items;

    private TextView[] dots;
    private int[] layouts;
    private LinearLayout dotsLayout;

    public HomeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (AutoScrollViewPager) view.findViewById(R.id.viewPager);
        ImageAdapter imgadapter = new ImageAdapter(getActivity());
//        view = new InfiniteViewPager(this);
//        view = (InfiniteViewPager) findViewById(R.id.viewPager);
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(imgadapter, getActivity().getApplicationContext());

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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

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
                        Intent intent = new Intent(getActivity(), ViewContents.class);
                        intent.putExtra("urlNum", urlNumList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getActivity().getApplicationContext(),position+"번 째 아이템 롱 클릭",Toast.LENGTH_SHORT).show();
                    }
                }));

        //mRecyclerView.setNestedScrollingEnabled(false);
        items = new ArrayList<>();
        Log.e("메인","작동끝");

        return view;
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        return true;
    }
    @Override
    public void onResume() {
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
    public  void onDestroy(){
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
                    String num = element.text();
                    if(num.length()>32)
                    {
                        String n = num.substring(0,33);
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

                    Log.e("dd",date);
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
                items.add(new Item(titleList.get(i), "  "+dateList.get(i)));
            }
            mRecyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(), items, R.layout.test));

            //홈 화면 로드 완료하면 로딩화면 종료, 신재혁 추가
            LoadingDialog.getInstance().progressOFF();
        }
    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnTouchListener viewPagerTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Toast.makeText(getActivity().getApplicationContext(),"터치", Toast.LENGTH_SHORT);
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
            Window window = getActivity().getWindow();
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
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            Toast.makeText(getActivity().getApplicationContext(),"1이동", Toast.LENGTH_SHORT).show();
        }
        else if(index == 1)
        {
            intent.setData(Uri.parse("http://www.daum.net"));
            startActivity(intent);
            Toast.makeText(getActivity().getApplicationContext(),"2이동", Toast.LENGTH_SHORT).show();

        }
        else if(index == 2)
        {
            intent.setData(Uri.parse("http://www.naver.com"));
            startActivity(intent);
            Toast.makeText(getActivity().getApplicationContext(),"3이동", Toast.LENGTH_SHORT).show();

        }
    }

}
