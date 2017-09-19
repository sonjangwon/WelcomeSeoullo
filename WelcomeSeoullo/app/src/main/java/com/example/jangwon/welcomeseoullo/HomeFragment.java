package com.example.jangwon.welcomeseoullo;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    View view;

    private ViewPager pager;
    ViewFlipper flipper;
    ListView listview ;
    private Test.MyViewPagerAdapter myViewPagerAdapter;
    ArrayList<String> titleList = new ArrayList<String>();
    ArrayList<String> urlNumList = new ArrayList<String>();
    ArrayList<String> dateList = new ArrayList<String>();
    //ArrayAdapter mAdapter;
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
    InfiniteViewPager infiniteViewPager;

    //카드뷰 선언--------------------------------
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<MyData> myDataset;

    public HomeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (AutoScrollViewPager) view.findViewById(R.id.viewPager);
        ImageAdapter imgadapter = new ImageAdapter(getActivity());
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(imgadapter);

        viewPager.setAdapter(wrappedAdapter);
        viewPager.setOnTouchListener(viewPagerTouchListener);
        viewPager.startAutoScroll();
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataset = new ArrayList<>();

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
                myDataset.add(new MyData(titleList.get(i), "  "+dateList.get(i)));
            }
            mRecyclerView.setAdapter(new MyAdapter(myDataset));
            mRecyclerView.getAdapter().notifyDataSetChanged();

        }
    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {

    }
    ViewPager.OnTouchListener viewPagerTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
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
        }
        else if(index == 1)
        {
            intent.setData(Uri.parse("http://www.daum.net"));
            startActivity(intent);
        }
        else if(index == 2)
        {
            intent.setData(Uri.parse("http://www.naver.com"));
            startActivity(intent);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private ArrayList<MyData> mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView titleTextView;
            public TextView dateTextView;
            Button detailView;
            public ViewHolder(View view) {
                super(view);
                titleTextView = (TextView)view.findViewById(R.id.titleText);
                dateTextView = (TextView)view.findViewById(R.id.dateText);
                detailView = (Button) view.findViewById(R.id.button2);
            }
        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<MyData> myDataset) {
            this.mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_view, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.titleTextView.setText(mDataset.get(position).title_text);
            holder.dateTextView.setText(mDataset.get(position).date_text);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    class MyData{
        public String title_text;
        public String date_text;
        public int img;
        public MyData(String text, String text2){
            this.title_text = text;
            this.date_text = text2;
        }
    }
}
