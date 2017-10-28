package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.R;

import java.util.ArrayList;
import java.util.Timer;

public class HomeFragment extends Fragment {

    View view;

//    ArrayList<String> titleList = new ArrayList<String>(10);
//    ArrayList<String> urlNumList = new ArrayList<String>(10);
//    ArrayList<String> dateList = new ArrayList<String>(10);
//    ArrayList<String> urlList = new ArrayList<String>(10);
//    private Test.MyViewPagerAdapter myViewPagerAdapter;


    ArrayList<String> urlList = new ArrayList<String>(10);
    //현재화면인덱스

    //AutoScrollViewPager viewPager;
    ClickableAutoViewPager viewPager;
    private Integer[] Images;
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();
    //InfiniteViewPager view;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    int currentPage = 0;
    Timer timer;

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
        viewPager = (ClickableAutoViewPager) view.findViewById(R.id.viewPager);

        ImageAdapter imgadapter = new ImageAdapter(getActivity());
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(imgadapter, getActivity().getApplicationContext());

        viewPager.setAdapter(wrappedAdapter);
        viewPager.startAutoScroll();
        viewPager.setOnItemClickListener(new ClickableAutoViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int pageNum = position % 9;
                Intent intent = new Intent(getActivity().getApplicationContext(), EmoJeoMoImages.class);
                intent.putExtra("position", String.valueOf(pageNum));
                Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position)+String.valueOf(pageNum), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
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
            private void changeStatusBarColor() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getActivity().getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                }
            }

}
