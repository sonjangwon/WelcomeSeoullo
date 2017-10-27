package com.example.jangwon.welcomeseoullo.FacilityMenu;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.PublicData.ManagePublicData;
import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;
import com.skp.Tmap.util.HttpConnect;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MapGuideFragment extends Fragment {
    //주변경로안내 맵가이드 Fragment

    View view;
    TMapData tmapdata = new TMapData();
    TMapView tmapview;
    FrameLayout zoonInFrameLayout;
    FrameLayout zoomOutFrameLayout;
    ImageButton zoomInButton;
    ImageButton zoomOutButton;
    ImageButton gpsButton;
    int totalDistance;
    double distance =0;

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    TextView addressTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("onCreate","true");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        view = inflater.inflate(R.layout.fragment_map_guide, container, false);
        Log.e("onCreateView","true");
        addressTextView = (TextView) view.findViewById(R.id.addressTextView);
        zoonInFrameLayout = (FrameLayout) view.findViewById(R.id.zoonInFrameLayout);
        zoomOutFrameLayout = (FrameLayout) view.findViewById(R.id.zoomOutFrameLayout);
        zoomInButton = (ImageButton) view.findViewById(R.id.zoomInButton);
        zoomOutButton = (ImageButton) view.findViewById(R.id.zoomOutButton);
        gpsButton = (ImageButton) view.findViewById(R.id.gpsButton);

        //현재위치 받아오는 위도,경도,상세주소
        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
        currentAddress = ManagementLocation.getInstance().getCurrentAddress();
        Log.e("sortOnCreateVIew",ManagementLocation.getInstance().getSortSpinner());
//        if(ManagementLocation.getInstance().getSortSpinner()=="") {
//            ManagementLocation.getInstance().setSortSpinner("공공화장실");
//        }
        mapView(view);
        addMarker();



        tmapview.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem markerItem) {
                String strMessage = "";
                strMessage = "ID: " + markerItem.getID() + " " + "Title " + markerItem.getCalloutTitle();
//                .showAlertDialog(MapGuideFragment.this, "Callout Right Button", strMessage);
            }
        });
        zoonInFrameLayout.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tmapview.getZoomLevel() != 19) {
                    tmapview.MapZoomIn();
                }
            }
        });

        zoomOutFrameLayout.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tmapview.getZoomLevel() != 7) {
                    tmapview.MapZoomOut();
                }
            }
        });

        zoomInButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tmapview.getZoomLevel() != 19) {
                    tmapview.MapZoomIn();
                }
            }
        });
        zoomOutButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tmapview.getZoomLevel() != 7) {
                    tmapview.MapZoomOut();
                }
            }
        });

        gpsButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                tmapview.setTrackingMode(true);
                tmapview.setSightVisible(true);
            }
        });

        return view;

    }

    //맵 띄우기
    public void mapView(View view)
    {
        //선언
        tmapview = new TMapView(getActivity());
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.guideMapView);  //getActivity().findViewByID 아니다 ㅅㅂ

//        tmapview.setSKPMapApiKey("cad2cc9b-a3d5-3c32-8709-23279b7247f9");
        tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");
        tmapview.setLocationPoint(currentLongitude,currentLatitude);
        tmapview.setCenterPoint(126.970325,37.556152);
        tmapview.setCompassMode(false);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(false);
        tmapview.setSightVisible(false);
        relativeLayout.addView(tmapview);


    }

    //마커 추가하기
    private void addMarker() {

        ManagePublicData.getInstance().getPublicParkingLotVOArrayList();
        ManagePublicData.getInstance().getPublicParkVOArrayList();
        ManagePublicData.getInstance().getPublicToiletVOArrayList();
        ManagePublicData.getInstance().getTraditionalMarketVOArrayList();

        if(ManagementLocation.getInstance().getSortSpinner()=="공공화장실") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicToiletVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLongitude()));

                TMapMarkerItem tItem1 = new TMapMarkerItem();
                tItem1.setTMapPoint(tpoint);
                tItem1.setVisible(tItem1.VISIBLE);
                tItem1.setName(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletName());


                tItem1.setCalloutTitle(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletName());
                tItem1.setCanShowCallout(true);
                tItem1.setAutoCalloutVisible(false);


                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder1);
                bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
                tItem1.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem1);
                tmapview.addMarkerItem("공공화장실" + String.valueOf(i)  , tItem1);

            }
        }

        if(ManagementLocation.getInstance().getSortSpinner()=="주차장") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkingLotVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotLongitude()));
                TMapMarkerItem tItem2 = new TMapMarkerItem();
                tItem2.setTMapPoint(tpoint);

                tItem2.setVisible(tItem2.VISIBLE);
                tItem2.setName(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotName());
                tItem2.setCalloutTitle(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotName());
//                    tItem2.setCalloutSubTitle("서울");
                tItem2.setCanShowCallout(true);
                tItem2.setAutoCalloutVisible(false);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder2);
                bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
                tItem2.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem2);
                tmapview.addMarkerItem("주차장" + String.valueOf(i), tItem2);


            }


        }
        if(ManagementLocation.getInstance().getSortSpinner()=="공원") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkLongitude()));
                TMapMarkerItem tItem3 = new TMapMarkerItem();
                tItem3.setTMapPoint(tpoint);
                tItem3.setCanShowCallout(true);
                tItem3.setAutoCalloutVisible(false);
                tItem3.setCalloutTitle(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkName());
                tItem3.setVisible(TMapMarkerItem.VISIBLE);
                tItem3.setName(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkName());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder3);
                bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
                tItem3.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem3);
                tmapview.addMarkerItem("공원" + String.valueOf(i), tItem3);

            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전통시장") {
            for (int i = 0; i < ManagePublicData.getInstance().getTraditionalMarketVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketLongitude()));
                TMapMarkerItem tItem4 = new TMapMarkerItem();
                tItem4.setTMapPoint(tpoint);
                tItem4.setCanShowCallout(true);     //풍선뷰 사용여부결정
                tItem4.setAutoCalloutVisible(false);
                tItem4.setCalloutTitle(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketName());
                tItem4.setVisible(TMapMarkerItem.VISIBLE);
                tItem4.setName(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketName());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder4);
                bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
                tItem4.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem4);
                tmapview.addMarkerItem("전통시장" + String.valueOf(i), tItem4);

            }
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(), "onActivityCreated()");
//        addMarker();
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
        Log.d(this.getClass().getSimpleName(), "onStart()");
        Log.e("onStart","true");
        addMarker();
        if(ManageListToMap.getInstance().getClickedListView()==true) {
            tmapview.setCenterPoint(ManageListToMap.getInstance().getClickedLongitude(), ManageListToMap.getInstance().getClickedLatitude());
            ListToMap();
            ManageListToMap.getInstance().setClickedListView(false);
        }
        else if(ManageListToMap.getInstance().getClickedListView()==false) {
            tmapview.setCenterPoint(126.970325, 37.556152);
            addMarker();
        }
        super.onStart();
    }

    public void ListToMap(){
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.mapholder1);
        if(ManageListToMap.getInstance().getFragmentCondition()=="map"){
//            tmapview.setCenterPoint(ManageListToMap.getInstance().getClickedLongitude(), ManageListToMap.getInstance().getClickedLatitude());
            TMapPoint tpoint = new TMapPoint(ManageListToMap.getInstance().getClickedLatitude(), ManageListToMap.getInstance().getClickedLongitude());
            TMapMarkerItem tItem5 = new TMapMarkerItem();
            tItem5.setTMapPoint(tpoint);
            tmapview.setZoomLevel(15);
            tItem5.setCalloutTitle(ManageListToMap.getInstance().getClickedPlaceName());
            tItem5.setCanShowCallout(true);
            tItem5.setAutoCalloutVisible(true);
            if (ManagementLocation.getInstance().getSortSpinner() == "공공화장실") {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder1);
            } else if (ManagementLocation.getInstance().getSortSpinner() == "주차장") {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder2);
            } else if (ManagementLocation.getInstance().getSortSpinner() == "공원") {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder3);
            } else if (ManagementLocation.getInstance().getSortSpinner() == "전통시장") {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder4);
            }
            bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
            tItem5.setIcon(bitmap);
            tmapview.bringMarkerToFront(tItem5);
            tmapview.addMarkerItem("ListToMap", tItem5);
//            ManageListToMap.getInstance().setClickedListView(false);
//            GuideInfoFragment.imageView1.setImageResource(R.drawable.reversemappoint);
//            GuideInfoFragment.imageView2.setImageResource(R.drawable.listpoint);

        }
    }

    //클래스가 생성될때 경로유형, 출발지, 도착지를 매개변수로 받음
    public double distanceTracker(final TMapPoint endpoint) {  //final TMapData.TMapPathType type,
        (new Thread() {
            public void run() {
                try {
                    Document e = null;
                    TMapPolyLine polyline = new TMapPolyLine();
                    StringBuilder uri = new StringBuilder();
                    uri.append("https://apis.skplanetx.com/tmap/");
                    //자동차 경로안내일 경우
                    uri.append("routes?version=1");

                    StringBuilder content = new StringBuilder();
                    content.append("reqCoordType=WGS84GEO&resCoordType=WGS84GEO&format=xml");
                    content.append("&startY=").append(currentLatitude);
                    content.append("&startX=").append(currentLongitude);
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
                        NodeList list2 = e.getElementsByTagName("tmap:totalDistance");
                        Node distanceItem = list2.item(0);
                        totalDistance = Integer.parseInt(distanceItem.getTextContent());
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        return totalDistance;
    }


}
