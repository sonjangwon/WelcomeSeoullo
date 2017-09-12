package com.example.jangwon.welcomeseoullo;

import android.util.Log;

import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.util.HttpConnect;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Jangwon on 2017-09-09.
 */

public class PathTracker {

    int totalTime ;
    int totalDistance ;
    int taxiFare ;



    public PathTracker(final String pathType, final TMapPoint startpoint, final TMapPoint endpoint) {  //final TMapData.TMapPathType type,
        (new Thread() {
            public void run() {
                try {
                    Document e = null;
                    TMapPolyLine polyline = new TMapPolyLine();
                    StringBuilder uri = new StringBuilder();
                    uri.append("https://apis.skplanetx.com/tmap/");
                    if(pathType == "carPath") {
                        uri.append("routes?version=1");
                    } else if(pathType == "footPath") {
                        uri.append("routes/pedestrian?version=1");
                    }
                    StringBuilder content = new StringBuilder();
                    content.append("reqCoordType=WGS84GEO&resCoordType=WGS84GEO&format=xml");
                    content.append("&startY=").append(startpoint.getLatitude());
                    content.append("&startX=").append(startpoint.getLongitude());
                    content.append("&endY=").append(endpoint.getLatitude());
                    content.append("&endX=").append(endpoint.getLongitude());
                    content.append("&startName=").append(URLEncoder.encode("출발지", "UTF-8"));
                    content.append("&endName=").append(URLEncoder.encode("도착지", "UTF-8"));
                    StringBuilder StringEx1;
                    StringEx1 = new StringBuilder();
                    StringEx1.append(uri.toString());
                    StringEx1.append("&appKey=").append("500adabd-fcb2-34fd-af42-022c6611b9a7");
                    URLConnection con = HttpConnect.postHttps(StringEx1.toString(), content.toString(), false);
                    try {
                        HttpURLConnection ez = (HttpURLConnection)con;
                        e = HttpConnect.getDocument(con);
                    } catch (Exception ezx) {
                        Log.i("error","에러남ㅋㅋ");
                    }
                    if(e != null) {
                        NodeList list = e.getElementsByTagName("tmap:totalTime");
                        NodeList list2 = e.getElementsByTagName("tmap:totalDistance");
                        NodeList list3 = e.getElementsByTagName("tmap:taxiFare");

                        Node timeItem = list.item(0);
                        Node distanceItem = list2.item(0);
                        Node taxiFareItem = list3.item(0);
                        totalDistance = Integer.parseInt(distanceItem.getTextContent());
                        totalTime = Integer.parseInt(timeItem.getTextContent());
                        taxiFare = Integer.parseInt(taxiFareItem.getTextContent());

                        Integer bun = Integer.parseInt(timeItem.getTextContent()) / 60;
                        Log.e(" 총 시간 ",":  "+ bun/60 + "시간 " + bun%60 + "분");
                        Log.e(" 총 거리 ",":  " + Integer.parseInt(distanceItem.getTextContent()));
                        Log.e(" 택시 요금 ",":  " + Integer.parseInt(taxiFareItem.getTextContent()));
                    }
                } catch (Exception e) {
                    Log.i("error","error");
                }
            }
        }).start();
    }

    public int getTotalDistance(){
        return totalDistance;
    }

    public int getTotalTime(){
        return totalTime;
    }

    public int getTaxiFare(){
        return taxiFare;
    }



}
