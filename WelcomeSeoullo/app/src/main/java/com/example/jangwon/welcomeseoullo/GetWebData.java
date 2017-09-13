package com.example.jangwon.welcomeseoullo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by woga1 on 2017-09-10.
 */

public class GetWebData extends AsyncTask<Void, Void, Map<String,String>> {
    ArrayAdapter<String> adapter;
    @Override
    protected void onPreExecute(){
    }
    @Override
    protected Map<String,String> doInBackground(Void... params) {
        Log.e("AsyncTask", "do");
        Map result = new HashMap();
        int i = 1;
        try {
//            Document document = Jsoup.connect("http://www.nlotto.co.kr/common.do?method=main") .get();
//// 회차 정보 가져오기
//            Elements elements = document.select(".lotto_area #lottoDrwNo");
//            String title = elements.select("a").attr("href").toString();
//            result.put("latestLotto", elements.text());
            Document document = Jsoup.connect("http://seoullo7017.seoul.go.kr/SSF/J/NO/NEList.do").get();
            Log.e("AsyncTask", "connect");
            //Elements elements = document.select(".table_list02");
            Elements elements = document.getElementsByAttributeValue("class", "t_left");
            Log.e("AsyncTask", elements.text());
            String title = elements.select("a").attr("href").toString();
            Log.e("AsyncTask", title);
            //Elements elements = document.select("td.t_left > a");
            for (Element element : elements) {
                i++;
                //result.put("제목"+i,element.text());
                Log.e("새소식제목", element.text());
                adapter.add(element.text());
            }
            i = 1;
            Elements dayelements = document.select(".table_list02 tr td:eq(3)");
            for (Element element : dayelements) {
                i++;
                //result.put("날짜"+i,element.text());
                Log.e("공지사항업데이트날짜", element.text());
            }
//                Log.e("AsyncTask", title);
//                result.put("tv", elements.text());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Map<String, String> map) {
        Log.e("어싱크태ㅡ크","PostExecute");
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            arrayList.add(map.get("제목"+i) + " "+map.get("날짜"+i));

            Log.e("어싱크태ㅡ크",arrayList.get(i-1));
        }
    }

}
