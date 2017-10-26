package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;

//    ArrayList<String> titleList = new ArrayList<String>(10);
//    ArrayList<String> urlNumList = new ArrayList<String>(10);
//    ArrayList<String> dateList = new ArrayList<String>(10);
//    ArrayList<String> urlList = new ArrayList<String>(10);
//    private Test.MyViewPagerAdapter myViewPagerAdapter;


    ArrayList<String> urlList = new ArrayList<String>(10);
    int count =0;
    //현재화면인덱스

    AutoScrollViewPager viewPager;
    private Integer[] Images;
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();
    //InfiniteViewPager view;


    private TextView[] dots;
    private int[] layouts;
    private LinearLayout dotsLayout;
    //load more recyclerView list
    TextView loadMoreText;

    //메인버튼
    Button btn_newNotice;
    Button btn_history;
    Button btn_usingTime;
    Button btn_walkingCourse;
    public HomeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_home, container, false);


        viewPager = (AutoScrollViewPager) view.findViewById(R.id.viewPager);
        ImageAdapter imgadapter = new ImageAdapter(getActivity());
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(imgadapter, getActivity().getApplicationContext());

        viewPager.setAdapter(wrappedAdapter);
        viewPager.setOnTouchListener(viewPagerTouchListener);
        viewPager.startAutoScroll();


//        items = new ArrayList<>();

//        loadMoreText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int index = NewsCrawling.getInstance().items.size();
//                int end = index + 2;
//                if (NewsCrawling.getInstance().items.size() < NewsCrawling.getInstance().titleList.size()) {
//                    for (int i = index; i < end; i++) {
//                        NewsCrawling.getInstance().items.add(new Item(NewsCrawling.getInstance().titleList.get(i)));
//                    }
//                    mAdapter.notifyDataSetChanged();
//                }
//                if (end == NewsCrawling.getInstance().titleList.size()) {
//                    loadMoreText.setVisibility(View.GONE);
//                }
//            }

                btn_newNotice = (Button) view.findViewById(R.id.Btn_newNotice);
                btn_walkingCourse = (Button) view.findViewById(R.id.Btn_walkingCourse);
                btn_history = (Button) view.findViewById(R.id.Btn_history);
                btn_usingTime = (Button) view.findViewById(R.id.Btn_usingTime);

                btn_newNotice.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), NewNoticeActivity.class);
                        startActivity(intent);
                    }
                });
                btn_walkingCourse.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), SeoulloCourseFragment.class);
                        startActivity(intent);
                    }
                });
                btn_history.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), HistoryFragment.class);
                        startActivity(intent);
                    }
                });
                btn_usingTime.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), UsingGuideTimeActivity.class);
                        startActivity(intent);
                    }
                });
                return view;
            }


            @Override
            public void setUserVisibleHint(boolean isVisibleToUser) {
                super.setUserVisibleHint(isVisibleToUser);
                if (isVisibleToUser) {

                } else {

                }
            }

            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }


            @Override
            public void onResume() {
                super.onResume();

//        NewThread task = new NewThread();
//        thumnailThread thumnailTask = new thumnailThread();
//
//        if (count == 0) {
//            task.execute();
//            thumnailTask.execute();
//            count++;
//        } else {
//            task.cancel(true);
//            thumnailTask.cancel(true);
//        }
            }


            @Override
            public void onDestroy() {
                super.onDestroy();
            }

//    private String _getImage(Document doc) {
//        // 2nd -> img in p
//        for (Element e1 : doc.getElementsByTag("p")) {
//            for (Element e2 : e1.getElementsByTag("img")) {
//                final String text = getValidPath(e2.attr("src"));
//                if (text != null && !getimageFirst) {
//                    //Log.e("imageurl", text);
//                    urlList.add(text);
//                    //Log.e("listSize", String.valueOf(urlNumList.size()) + ":" + String.valueOf(urlList.size()));
//                    getimageFirst = true;
//                    isEmptyImage++;
//                }
//            }
//        }
//        // etc empty
//        return "";
//    }
//
//    private String getValidPath(String url) {
//        try {
//            if (url.startsWith("http://") || url.startsWith("https://")) {
//                //Log.e("imageurl",url);
//                return url;
//            }
//
//            final URI ogpUri = new URI(this.url);
//            final URI imgUri = ogpUri.resolve(url);
//            return imgUri.toString();
//        } catch (URISyntaxException e) {
//            return url;
//        }
//    }
//
//    public class thumnailThread extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            Document document = null;
//            try {
//                for(int i=0; i<urlNumList.size(); i++) {
//                    document = Jsoup.connect("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEView.do?board_seq=" + urlNumList.get(i) + "&pageIndex=1&pageSize=10&searchCondition=all&searchKeyword=").get();
//                    _getImage(document);
//                    getimageFirst = false;
//                    if(isEmptyImage==0)
//                    {
//                        urlList.add("http://seoullo7017.seoul.go.kr/img/front/img_logo.png");
//                        Log.e("emptyImage", String.valueOf(i));
//                    }
//                    isEmptyImage = 0;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            for(int i=0; i<2; i++)
//            {
//                items.add(new Item(titleList.get(i), "  "+dateList.get(i),urlList.get(i)));
//            }
//
//            mRecyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(), items, R.layout.fragment_home));
//            mAdapter = mRecyclerView.getAdapter();
//            loadMoreText.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public class NewThread extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            Document document = null;
//            try {
//                document = Jsoup.connect("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEList.do").get();
//                Elements elements = document.getElementsByAttributeValue("class", "t_left");
//                //Elements elements = document.select("td.t_left > a");
//                for (Element element : elements) {
//                    String num = element.text();
//                    if(num.length()>40)
//                    {
//                        String n = num.substring(0,41);
//                        num = n + "...";
//                        titleList.add(num);
//                    }
//                    else {
//                        titleList.add(element.text());
//                    }
//                    String title = element.select("a").attr("href").toString();
//                    String titleNum = title.substring(26,29);
//                    urlNumList.add(titleNum);
//                }
//                Elements ss = document.select("tr td:eq(3)");
//                for (Element e : ss) {
//                    String year =  e.text().substring(0,4);
//                    String month = e.text().substring(5,7);
//                    String day = e.text().substring(8,10);
//                    String date = year+"년 "+ month+"월 "+day+"일";
//                    dateList.add(date);
//
//                    //Log.e("dd",date);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//        @Override
//        protected void onPostExecute(String result) {
//
//        }
//    }


            private int getItem(int i) {
                return viewPager.getCurrentItem() + i;
            }

            ViewPager.OnTouchListener viewPagerTouchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(getActivity().getApplicationContext(), "터치", Toast.LENGTH_SHORT);
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

            public void whenTouchImagePosition(int index) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                if (index == 0) {
                    intent.setData(Uri.parse("http://www.naver.com"));
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(), "1이동", Toast.LENGTH_SHORT).show();
                } else if (index == 1) {
                    intent.setData(Uri.parse("http://www.daum.net"));
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(), "2이동", Toast.LENGTH_SHORT).show();

                } else if (index == 2) {
                    intent.setData(Uri.parse("http://www.naver.com"));
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(), "3이동", Toast.LENGTH_SHORT).show();
                }
            }
}
