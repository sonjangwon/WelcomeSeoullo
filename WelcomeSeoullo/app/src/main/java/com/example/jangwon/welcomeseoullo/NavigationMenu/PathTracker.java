package com.example.jangwon.welcomeseoullo.NavigationMenu;

import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.util.HttpConnect;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;

public class PathTracker {
    //경로안내 정보를 제공하는 클래스
    int totalTime ;
    int totalDistance ;
    int taxiFare ;

    //클래스가 생성될때 경로유형, 출발지, 도착지를 매개변수로 받음
    public PathTracker(final String pathType, final TMapPoint startpoint, final TMapPoint endpoint) {  //final TMapData.TMapPathType type,
        (new Thread() {
            public void run() {
                try {
                    Document e = null;
                    TMapPolyLine polyline = new TMapPolyLine();
                    StringBuilder uri = new StringBuilder();
                    uri.append("https://apis.skplanetx.com/tmap/");
                    //자동차 경로안내일 경우
                    if(pathType == "carPath") {
                        uri.append("routes?version=1");
                    }
                    //도보 경로안내일 경우
                    else if(pathType == "footPath") {
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
//                    StringEx1.append("&appKey=").append("cad2cc9b-a3d5-3c32-8709-23279b7247f9");
                    StringEx1.append("&appKey=").append("500adabd-fcb2-34fd-af42-022c6611b9a7");
                    URLConnection con = HttpConnect.postHttps(StringEx1.toString(), content.toString(), false);
                    try {
                        HttpURLConnection ez = (HttpURLConnection)con;
                        e = HttpConnect.getDocument(con);
                    } catch (Exception ezx) {
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
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }
    //총 거리
    public int getTotalDistance(){
        return totalDistance;
    }
    //총 시간
    public int getTotalTime(){
        return totalTime;
    }
    //택시요금
    public int getTaxiFare(){
        return taxiFare;
    }
}
