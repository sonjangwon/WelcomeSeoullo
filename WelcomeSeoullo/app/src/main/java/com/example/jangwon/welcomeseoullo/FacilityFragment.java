package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;


public class FacilityFragment extends Fragment {
    View view;

    String tag_name = null;
    boolean bSet = false;
    TextView publicDataTextView;

    PublicToiletVO publicToiletVO;
    PublicParkingLotVO publicParkingLotVO;
    PublicParkVO publicParkVO;
    TraditionalMarketVO traditionalMarketVO;

    public static ArrayList<PublicToiletVO> publicToiletVOArrayList;
    public static ArrayList<PublicParkingLotVO> publicParkingLotVOArrayList;
    public static ArrayList<PublicParkVO> publicParkVOArrayList;
    public static ArrayList<TraditionalMarketVO> traditionalMarketVOArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState == null){
            view = inflater.inflate(R.layout.fragment_facility, container, false);

            publicToiletVO = new PublicToiletVO();
            publicToiletVOArrayList = new ArrayList<PublicToiletVO>();
            publicParkingLotVO = new PublicParkingLotVO();
            publicParkingLotVOArrayList = new ArrayList<PublicParkingLotVO>();
            publicParkVO = new PublicParkVO();
            publicParkVOArrayList = new ArrayList<PublicParkVO>();
            traditionalMarketVO = new TraditionalMarketVO();
            traditionalMarketVOArrayList = new ArrayList<TraditionalMarketVO>();

            publicDataTextView = (TextView) view.findViewById(R.id.publicDataTextView);

            //Takes about 3 second loading time
            final ParsePublicToilet parsePublicToilet = new ParsePublicToilet();
            final ParsePublicParkingLot parsePublicParkingLot = new ParsePublicParkingLot();
            final ParsePublicPark parsePublicPark = new ParsePublicPark();
            final ParseTraditionalMarket parseTraditionalMarket = new ParseTraditionalMarket();
            parsePublicToilet.execute("");
            parsePublicParkingLot.execute("");
            parsePublicPark.execute("");
            parseTraditionalMarket.execute("");

            publicDataTextView.setText("");
            parsePublicToilet.onPostExecute("");

            final Button publicToiletButton = (Button) view.findViewById(R.id.publicToiletButton);
            final Button publicParkingLotButton = (Button) view.findViewById(R.id.publicParkingLotButton);
            final Button publicParkButton = (Button) view.findViewById(R.id.publicParkButton);
            final Button traditionalMarketButton = (Button) view.findViewById(R.id.traditionalMarketButton);

            publicToiletButton.setTextColor(Color.parseColor("#FF0000"));

            publicToiletButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#FF0000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#000000"));
                    publicParkButton.setTextColor(Color.parseColor("#000000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#000000"));
                    publicDataTextView.setText("");
                    parsePublicToilet.onPostExecute("");
                }
            });
            publicParkingLotButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#000000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#FF0000"));
                    publicParkButton.setTextColor(Color.parseColor("#000000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#000000"));
                    publicDataTextView.setText("");
                    parsePublicParkingLot.onPostExecute("");
                }
            });
            publicParkButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#000000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#000000"));
                    publicParkButton.setTextColor(Color.parseColor("#FF0000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#000000"));
                    publicDataTextView.setText("");
                    parsePublicPark.onPostExecute("");
                }
            });
            traditionalMarketButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#000000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#000000"));
                    publicParkButton.setTextColor(Color.parseColor("#000000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#FF0000"));
                    publicDataTextView.setText("");
                    parseTraditionalMarket.onPostExecute("");
                }
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public boolean checkDuplication(String ParkingLotName){
        for(int i=0;i<publicParkingLotVOArrayList.size();i++){
            if(publicParkingLotVOArrayList.get(i).getParkingLotName().equals(ParkingLotName)){
                return false;
            }
        }
        return true;
    }

    private class ParsePublicToilet extends AsyncTask<String, Integer, String> {

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
                                        if(calculateCoordinates(publicToiletVO.getToiletLatitude(), publicToiletVO.getToiletLongitude())){
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

            for (int i = 0; i < publicToiletVOArrayList.size(); i++) {
                publicDataTextView.append("\n");
                publicDataTextView.append(publicToiletVOArrayList.get(i).getToiletName() + " ");
                publicDataTextView.append(publicToiletVOArrayList.get(i).getToiletCategory() + " ");
                publicDataTextView.append(publicToiletVOArrayList.get(i).getToiletLatitude() + " ");
                publicDataTextView.append(publicToiletVOArrayList.get(i).getToiletLongitude() + " ");
            }
        }
    }


    private class ParsePublicParkingLot extends AsyncTask<String, Integer, String> {

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
                                        if(calculateCoordinates(publicParkingLotVO.getParkingLotLatitude(), publicParkingLotVO.getParkingLotLongitude())){
                                            publicParkingLotVOArrayList.add(new PublicParkingLotVO(publicParkingLotVO.getParkingLotName(), publicParkingLotVO.getParkingLotType(), publicParkingLotVO.getParkingLotLatitude(), publicParkingLotVO.getParkingLotLongitude()));
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

            for (int i = 0; i < publicParkingLotVOArrayList.size(); i++) {
                publicDataTextView.append("\n");
                publicDataTextView.append(publicParkingLotVOArrayList.get(i).getParkingLotName() + " ");
                publicDataTextView.append(publicParkingLotVOArrayList.get(i).getParkingLotType() + " ");
                publicDataTextView.append(publicParkingLotVOArrayList.get(i).getParkingLotLatitude() + " ");
                publicDataTextView.append(publicParkingLotVOArrayList.get(i).getParkingLotLongitude() + " ");
            }
        }
    }


    private class ParsePublicPark extends AsyncTask<String, Integer, String> {

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

            for (int i = 0; i < publicParkVOArrayList.size(); i++) {
                publicDataTextView.append("\n");
                publicDataTextView.append(publicParkVOArrayList.get(i).getParkName() + " ");
                publicDataTextView.append(publicParkVOArrayList.get(i).getParkLatitude() + " ");
                publicDataTextView.append(publicParkVOArrayList.get(i).getParkLongitude() + " ");
            }
        }
    }


    private class ParseTraditionalMarket extends AsyncTask<String, Integer, String> {

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
                                    if(calculateCoordinates(traditionalMarketVO.getMarketLatitude(), traditionalMarketVO.getMarketLongitude())){
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

            for (int i = 0; i < traditionalMarketVOArrayList.size(); i++) {
                publicDataTextView.append("\n");
                publicDataTextView.append(traditionalMarketVOArrayList.get(i).getMarketName() + " ");
                publicDataTextView.append(traditionalMarketVOArrayList.get(i).getMarketLatitude() + " ");
                publicDataTextView.append(traditionalMarketVOArrayList.get(i).getMarketLongitude() + " ");
            }
        }
    }
}
