package com.example.jangwon.welcomeseoullo;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class ManagePublicData {

    private static ManagePublicData managePublicData;

    PublicToiletVO publicToiletVO;
    PublicParkingLotVO publicParkingLotVO;
    PublicParkVO publicParkVO;
    TraditionalMarketVO traditionalMarketVO;

    private ArrayList<PublicToiletVO> publicToiletVOArrayList;
    private ArrayList<PublicParkingLotVO> publicParkingLotVOArrayList;
    private ArrayList<PublicParkVO> publicParkVOArrayList;
    private ArrayList<TraditionalMarketVO> traditionalMarketVOArrayList;

    public ParsePublicToilet parsePublicToilet;
    public ParsePublicParkingLot parsePublicParkingLot;
    public ParsePublicPark parsePublicPark;
    public ParseTraditionalMarket parseTraditionalMarket;

    LoadingDialog loadingDialog;

    String tag_name = null;
    boolean bSet = false;

    public static ManagePublicData getInstance() {
        if(managePublicData == null){
            managePublicData = new ManagePublicData();
        }
        return managePublicData;
    }

    private ManagePublicData() {
        publicToiletVO = new PublicToiletVO();
        publicParkingLotVO = new PublicParkingLotVO();
        publicParkVO = new PublicParkVO();
        traditionalMarketVO = new TraditionalMarketVO();

        publicToiletVOArrayList = new ArrayList<PublicToiletVO>();
        publicParkingLotVOArrayList = new ArrayList<PublicParkingLotVO>();
        publicParkVOArrayList = new ArrayList<PublicParkVO>();
        traditionalMarketVOArrayList = new ArrayList<TraditionalMarketVO>();

        parsePublicToilet = new ParsePublicToilet();
        parsePublicParkingLot = new ParsePublicParkingLot();
        parsePublicPark = new ParsePublicPark();
        parseTraditionalMarket = new ParseTraditionalMarket();

        loadingDialog = LoadingDialog.getInstance();
    }

    public ArrayList<PublicToiletVO> getPublicToiletVOArrayList(){
        return publicToiletVOArrayList;
    }
    public void setPublicToiletVOArrayList(ArrayList<PublicToiletVO> publicToiletVOArrayList){
        this.publicToiletVOArrayList = publicToiletVOArrayList;
    }

    public ArrayList<PublicParkingLotVO> getPublicParkingLotVOArrayList(){
        return publicParkingLotVOArrayList;
    }
    public void setPublicParkingLotVOArrayList(ArrayList<PublicParkingLotVO> publicParkingLotVOArrayList){
        this.publicParkingLotVOArrayList = publicParkingLotVOArrayList;
    }

    public ArrayList<PublicParkVO> getPublicParkVOArrayList(){
        return publicParkVOArrayList;
    }
    public void setPublicParkVOArrayList(ArrayList<PublicParkVO> publicParkVOArrayList){
        this.publicParkVOArrayList = publicParkVOArrayList;
    }

    public ArrayList<TraditionalMarketVO> getTraditionalMarketVOArrayList(){
        return traditionalMarketVOArrayList;
    }
    public void setTraditionalMarketVOArrayList(ArrayList<TraditionalMarketVO> traditionalMarketVOArrayList){
        this.traditionalMarketVOArrayList = traditionalMarketVOArrayList;
    }



    public boolean calculateCoordinates(String latitude, String longitude){
        if(37.535936 < Double.valueOf(latitude) && Double.valueOf(latitude) < 37.571883 &&
                126.949482 < Double.valueOf(longitude) && Double.valueOf(longitude) < 126.995575){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean calculatePublicToiletCoordinates(String latitude, String longitude){
        if(37.546160 < Double.valueOf(latitude) && Double.valueOf(latitude) < 37.564250 &&
                126.960205 < Double.valueOf(longitude) && Double.valueOf(longitude) < 126.983023){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkDuplication(String ParkingLotName){
        for(int i=0;i<publicParkingLotVOArrayList.size();i++){
            if(publicParkingLotVOArrayList.get(i).getParkingLotName().equals(ParkingLotName)){
                return false;
            }
        }
        return true;
    }



    public class ParsePublicToilet extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            for(int i = 1;i<5000;i+=1000){
                try {
                    String url = "http://openapi.seoul.go.kr:8088/484450544d776f673334724444756d/xml/SearchPublicToiletPOIService/" + i + "/" + (i+999) + "/";
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    URL xmlUrl = new URL(url);
                    xmlUrl.openConnection().getInputStream();
                    parser.setInput(xmlUrl.openStream(), "utf-8");
                    int eventType = parser.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        if (eventType == XmlPullParser.START_TAG) {
                            tag_name = parser.getName();
                            if (tag_name.equals("FNAME") | tag_name.equals("ANAME") | tag_name.equals("Y_WGS84") | tag_name.equals("X_WGS84"))
                                bSet = true;
                        } else if (eventType == XmlPullParser.TEXT) {
                            if (bSet) {
                                String data = parser.getText();
                                switch (tag_name) {
                                    case "FNAME":
                                        publicToiletVO.setToiletName(data);
                                        break;
                                    case "ANAME":
                                        publicToiletVO.setToiletCategory(data);
                                        break;
                                    case "X_WGS84":
                                        publicToiletVO.setToiletLongitude(data);
                                        break;
                                    case "Y_WGS84":
                                        publicToiletVO.setToiletLatitude(data);
                                        if(calculatePublicToiletCoordinates(publicToiletVO.getToiletLatitude(), publicToiletVO.getToiletLongitude())){
                                            publicToiletVOArrayList.add(new PublicToiletVO(publicToiletVO.getToiletName(), publicToiletVO.getToiletCategory(), publicToiletVO.getToiletLatitude(), publicToiletVO.getToiletLongitude()));
                                            Log.v("test", publicToiletVOArrayList.get(publicToiletVOArrayList.size() - 1).getToiletName());
                                        }
                                        break;
                                }
                                bSet = false;
                            }
                        } else if (eventType == XmlPullParser.END_TAG) {
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

    }



    public class ParsePublicParkingLot extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            for(int i = 1;i<9000;i+=1000){
                try {
                    String url = "http://openapi.seoul.go.kr:8088/47536e6b45776f6731384d42465450/xml/SearchParkingInfo/" + i + "/" + (i+999) + "/";
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    URL xmlUrl = new URL(url);
                    xmlUrl.openConnection().getInputStream();
                    parser.setInput(xmlUrl.openStream(), "utf-8");
                    int eventType = parser.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        if (eventType == XmlPullParser.START_TAG) {
                            tag_name = parser.getName();
                            if (tag_name.equals("PARKING_NAME") | tag_name.equals("PARKING_TYPE_NM") | tag_name.equals("LAT") | tag_name.equals("LNG"))
                                bSet = true;
                        } else if (eventType == XmlPullParser.TEXT) {
                            if (bSet) {
                                String data = parser.getText();
                                switch (tag_name) {
                                    case "PARKING_NAME":
                                        publicParkingLotVO.setParkingLotName(data);
                                        break;
                                    case "PARKING_TYPE_NM":
                                        publicParkingLotVO.setParkingLotType(data);
                                        break;
                                    case "LAT":
                                        publicParkingLotVO.setParkingLotLatitude(data);
                                        break;
                                    case "LNG":
                                        publicParkingLotVO.setParkingLotLongitude(data);
                                        if(calculateCoordinates(publicParkingLotVO.getParkingLotLatitude(), publicParkingLotVO.getParkingLotLongitude())
                                                && checkDuplication(publicParkingLotVO.getParkingLotName())){
                                            publicParkingLotVOArrayList.add(new PublicParkingLotVO(publicParkingLotVO.getParkingLotName(), publicParkingLotVO.getParkingLotType(), publicParkingLotVO.getParkingLotLatitude(), publicParkingLotVO.getParkingLotLongitude()));
                                            Log.v("test", publicParkingLotVOArrayList.get(publicParkingLotVOArrayList.size() - 1).getParkingLotName());
                                        }
                                        break;
                                }
                                bSet = false;
                            }
                        } else if (eventType == XmlPullParser.END_TAG) {
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

    }



    public class ParsePublicPark extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String url = "http://openapi.seoul.go.kr:8088/73644c6e74776f6737344242635851/xml/SearchParkInformationByAddressService/1/100";
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                URL xmlUrl = new URL(url);
                xmlUrl.openConnection().getInputStream();
                parser.setInput(xmlUrl.openStream(), "utf-8");
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if (eventType == XmlPullParser.START_TAG) {
                        tag_name = parser.getName();
                        if (tag_name.equals("P_PARK") | tag_name.equals("LONGITUDE") | tag_name.equals("LATITUDE"))
                            bSet = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet) {
                            String data = parser.getText();
                            switch (tag_name) {
                                case "P_PARK":
                                    publicParkVO.setParkName(data);
                                    break;
                                case "LONGITUDE":
                                    publicParkVO.setParkLongitude(data);
                                    break;
                                case "LATITUDE":
                                    publicParkVO.setParkLatitude(data);
                                    if(calculateCoordinates(publicParkVO.getParkLatitude(), publicParkVO.getParkLongitude())){
                                        publicParkVOArrayList.add(new PublicParkVO(publicParkVO.getParkName(), publicParkVO.getParkLongitude(), publicParkVO.getParkLatitude()));
                                    }
                                    break;
                            }
                            bSet = false;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }



    public class ParseTraditionalMarket extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String url = "http://openapi.seoul.go.kr:8088/77736a6948776f673839684b6f474c/xml/ListTraditionalMarket/1/400";
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                URL xmlUrl = new URL(url);
                xmlUrl.openConnection().getInputStream();
                parser.setInput(xmlUrl.openStream(), "utf-8");
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if (eventType == XmlPullParser.START_TAG) {
                        tag_name = parser.getName();
                        if (tag_name.equals("M_NAME") | tag_name.equals("LAT") | tag_name.equals("LNG"))
                            bSet = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet) {
                            String data = parser.getText();
                            switch (tag_name) {
                                case "M_NAME":
                                    traditionalMarketVO.setMarketName(data);
                                    break;
                                case "LAT":
                                    traditionalMarketVO.setMarketLatitude(data);
                                    break;
                                case "LNG":
                                    traditionalMarketVO.setMarketLongitude(data);
                                    if (calculateCoordinates(traditionalMarketVO.getMarketLatitude(), traditionalMarketVO.getMarketLongitude())) {
                                        traditionalMarketVOArrayList.add(new TraditionalMarketVO(traditionalMarketVO.getMarketName(), traditionalMarketVO.getMarketLatitude(), traditionalMarketVO.getMarketLongitude()));
                                    }
                                    break;
                            }
                            bSet = false;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}
