package com.example.jangwon.welcomeseoullo;

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
import android.widget.Toast;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

public class MapGuideFragment extends Fragment {
    //주변경로안내 맵가이드 Fragment

    Double[] latitude = new Double[3];
    Double[] longitude = new Double[3];
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
    double lat1 = 37.537145;
    double lng1 = 127.08613500000001;
    double lat2 = 37.52989;
    double lng2 = 126.96477500000003;
    double lat3 = 37.545593;
    double lng3 = 126.97980329999996;

    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    TextView addressTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        view = inflater.inflate(R.layout.fragment_map_guide, container, false);

        addressTextView = (TextView) view.findViewById(R.id.addressTextView);
        zoonInFrameLayout = (FrameLayout) view.findViewById(R.id.zoonInFrameLayout);
        zoomOutFrameLayout = (FrameLayout) view.findViewById(R.id.zoomOutFrameLayout);
        zoomInButton = (ImageButton) view.findViewById(R.id.zoomInButton);
        zoomOutButton = (ImageButton) view.findViewById(R.id.zoomOutButton);
        gpsButton = (ImageButton) view.findViewById(R.id.gpsButton);
        latitude[0] = Double.valueOf("37.540389");
        latitude[1]= Double.valueOf("37.538289");
        latitude[2]= Double.valueOf("37.565102");
        longitude[0] = Double.valueOf("127.06923600000005");
        longitude[1]= Double.valueOf("127.12338350000005");
        longitude[2]= Double.valueOf("127.00776500000006");

        //현재위치 받아오는 위도,경도,상세주소
        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
        currentAddress = ManagementLocation.getInstance().getCurrentAddress();



        mapView(view);
        addMarker();

        tmapview.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem markerItem) {
                String strMessage = "";
                strMessage = "ID: " + markerItem.getID() + " " + "Title " + markerItem.getCalloutTitle();
                Toast.makeText(getActivity(),strMessage,Toast.LENGTH_LONG).show();
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
        tmapview.setCompassMode(true);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(11);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);
        relativeLayout.addView(tmapview);


    }

    //마커 추가하기
    private void addMarker() {
        ManagePublicData.getInstance().getPublicParkingLotVOArrayList();
        ManagePublicData.getInstance().getPublicParkVOArrayList();
        ManagePublicData.getInstance().getPublicToiletVOArrayList();
        ManagePublicData.getInstance().getTraditionalMarketVOArrayList();

        if(ManagementLocation.getInstance().getSortSpinner()=="전체" | ManagementLocation.getInstance().getSortSpinner()=="공공화장실") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicToiletVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLongitude()));
//                double distance = distanceTracker(tpoint);
//                Log.e("공공화장실",String.valueOf(distance));
//                Log.e("공공화장실",String.valueOf(ManagementLocation.getInstance().getDistanceSpinner()));
                if(distance<ManagementLocation.getInstance().getDistanceSpinner())
                {
                    TMapMarkerItem tItem1 = new TMapMarkerItem();
                    tItem1.setTMapPoint(tpoint);
                    tItem1.setVisible(tItem1.VISIBLE);
                    tItem1.setName("tlem1");


                    tItem1.setCalloutTitle(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletName());
//                    tItem1.setCalloutSubTitle("ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLongitude())");
                    tItem1.setCanShowCallout(true);
                    tItem1.setAutoCalloutVisible(true);


//                    tItem1.setCalloutTitle( String.valueOf(i) );
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder1);
                    tItem1.setIcon(bitmap);
                    tmapview.bringMarkerToFront(tItem1);
                    tmapview.addMarkerItem("공공화장실" + String.valueOf(i)  , tItem1);
                }
            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전체" | ManagementLocation.getInstance().getSortSpinner()=="주차장") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkingLotVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotLongitude()));
//                double distance = distanceTracker(tpoint);
//                Log.e("주차장",String.valueOf(distance));
                if(distance<ManagementLocation.getInstance().getDistanceSpinner()) {
                    TMapMarkerItem tItem2 = new TMapMarkerItem();
                    tItem2.setTMapPoint(tpoint);
//                    tItem2.setCalloutTitle("신재혁");

                    tItem2.setVisible(tItem2.VISIBLE);
                    tItem2.setName("tlem2");
                    tItem2.setCalloutTitle(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotName());
//                    tItem2.setCalloutSubTitle("서울");
                    tItem2.setCanShowCallout(true);
                    tItem2.setAutoCalloutVisible(true);

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder2);
                    tItem2.setIcon(bitmap);
                    tmapview.bringMarkerToFront(tItem2);
                    tmapview.addMarkerItem("주차장" + String.valueOf(i), tItem2);


                }
            }

        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전체" | ManagementLocation.getInstance().getSortSpinner()=="공원") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkLongitude()));
//                double distance = distanceTracker(tpoint);
                if(distance<ManagementLocation.getInstance().getDistanceSpinner()) {
                    TMapMarkerItem tItem3 = new TMapMarkerItem();
                    tItem3.setTMapPoint(tpoint);
                    tItem3.setCanShowCallout(true);
                    tItem3.setAutoCalloutVisible(true);
                    tItem3.setCalloutTitle(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkName());
                    tItem3.setVisible(TMapMarkerItem.VISIBLE);
                    tItem3.setName("tlem3");
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder3);
                    tItem3.setIcon(bitmap);
                    tmapview.bringMarkerToFront(tItem3);
                    tmapview.addMarkerItem("공원" + String.valueOf(i), tItem3);
                }
            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전체" | ManagementLocation.getInstance().getSortSpinner()=="전통시장") {
            for (int i = 0; i < ManagePublicData.getInstance().getTraditionalMarketVOArrayList().size(); i++) {
                TMapPoint tpoint = new TMapPoint(Double.valueOf(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketLatitude()),
                        Double.valueOf(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketLongitude()));
//                double distance = distanceTracker(tpoint);
                if(distance<ManagementLocation.getInstance().getDistanceSpinner()) {
                    TMapMarkerItem tItem4 = new TMapMarkerItem();
                    tItem4.setTMapPoint(tpoint);
                    tItem4.setCanShowCallout(true);     //풍선뷰 사용여부결정
                    tItem4.setAutoCalloutVisible(true);
                    tItem4.setCalloutTitle(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketName());
                    tItem4.setVisible(TMapMarkerItem.VISIBLE);
                    tItem4.setName("tlem4");
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder4);
                    tItem4.setIcon(bitmap);
                    tmapview.bringMarkerToFront(tItem4);
                    tmapview.addMarkerItem("전통시장" + String.valueOf(i), tItem4);
                }
            }
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(), "onActivityCreated()");
        addMarker();
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
        Log.d(this.getClass().getSimpleName(), "onStart()");
        addMarker();
        super.onStart();
    }




}
