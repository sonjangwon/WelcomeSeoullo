package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jaehyukshin on 2017. 10. 23..
 */

public class NewsCrawling {

    private static NewsCrawling newsCrawling;

    public ArrayList<String> titleList = new ArrayList<String>();
    public ArrayList<String> urlNumList = new ArrayList<String>();
    public ArrayList<String> dateList = new ArrayList<String>();

    public ArrayList<Item> items;

    public NewThread newThread;

    public static NewsCrawling getInstance() {
        if(newsCrawling == null){
            newsCrawling = new NewsCrawling();
        }
        return newsCrawling;
    }

    private NewsCrawling() {
        newThread = new NewThread();
        items = new ArrayList<>(20);
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
                items.add(new Item(titleList.get(i), "  " + dateList.get(i)));
            }
        }
    }
}
