package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsCrawling {

    private static NewsCrawling newsCrawling;

    public ArrayList<String> titleList = new ArrayList<String>(10);
    public ArrayList<String> urlNumList = new ArrayList<String>(10);
    public ArrayList<String> dateList = new ArrayList<String>(10);
    public ArrayList<String> urlList = new ArrayList<String>(10);

    public ArrayList<Item> items;

    //이미지 파싱
    private String url;
    boolean getimageFirst = false;
    int isEmptyImage = 0;

    public NewThread newThread;

    public static NewsCrawling getInstance() {
        if(newsCrawling == null){
            newsCrawling = new NewsCrawling();
        }
        return newsCrawling;
    }

    private NewsCrawling() {
        newThread = new NewThread();
        items = new ArrayList<>();
    }

    public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Document document = null;
            try {
                document = Jsoup.connect("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEList.do").get();
                Elements elements = document.getElementsByAttributeValue("class", "t_left");

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
                }

                for(int i=0; i<urlNumList.size(); i++){
                    urlList.add("http://seoullo7017.seoul.go.kr/img/front/img_logo.png");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            for(int i=0; i<10; i++) {
                items.add(new Item(titleList.get(i)));
            }
        }
    }
}
