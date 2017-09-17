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

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

public class MapGuideFragment extends Fragment {

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

        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
        currentAddress = ManagementLocation.getInstance().getCurrentAddress();



        mapView(view);


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
        addMarker();
    }

    //마커 추가하기
    private void addMarker() {
        double lat1 = 37.537145;
        double lng1 = 127.08613500000001;
        double lat2 = 37.52989;
        double lng2 = 126.96477500000003;
        double lat3 = 37.545593;
        double lng3 = 126.97980329999996;

        if(GuideInfoActivity.sortContent.equals("전체")|GuideInfoActivity.sortContent.equals("공공화장실")) {
            for (int i = 0; i < 1; i++) {
                TMapPoint tpoint = new TMapPoint(lat1, lng1);
                TMapMarkerItem tItem1 = new TMapMarkerItem();
                tItem1.setTMapPoint(tpoint);
                tItem1.setName("SKT타워");
                tItem1.setVisible(TMapMarkerItem.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder1);
                tItem1.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem1);
                tmapview.addMarkerItem(String.valueOf(i) + "1번", tItem1);
            }
        }
        if(GuideInfoActivity.sortContent.equals("전체")|GuideInfoActivity.sortContent.equals("주차장")) {
            for (int i = 0; i < 3; i++) {
                TMapPoint tpoint = new TMapPoint(latitude[i], longitude[i]);
                TMapMarkerItem tItem2 = new TMapMarkerItem();
                tItem2.setTMapPoint(tpoint);
                tItem2.setName("SKT타워");
                tItem2.setVisible(TMapMarkerItem.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder2);
                tItem2.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem2);
                tmapview.addMarkerItem(String.valueOf(i) + "2번", tItem2);
            }
        }
        if(GuideInfoActivity.sortContent.equals("전체")|GuideInfoActivity.sortContent.equals("공원")) {
            for (int i = 0; i < 3; i++) {
                TMapPoint tpoint = new TMapPoint(lat2, lng2);
                TMapMarkerItem tItem3 = new TMapMarkerItem();
                tItem3.setTMapPoint(tpoint);
                tItem3.setName("SKT타워");
                tItem3.setVisible(TMapMarkerItem.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder3);
                tItem3.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem3);
                tmapview.addMarkerItem(String.valueOf(i) + "3번", tItem3);
            }
        }
        if(GuideInfoActivity.sortContent.equals("전체")|GuideInfoActivity.sortContent.equals("전통시장")) {
            for (int i = 0; i < 3; i++) {
                TMapPoint tpoint = new TMapPoint(lat3, lng3);
                TMapMarkerItem tItem4 = new TMapMarkerItem();
                tItem4.setTMapPoint(tpoint);
                tItem4.setName("SKT타워");
                tItem4.setVisible(TMapMarkerItem.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapholder4);
                tItem4.setIcon(bitmap);
                tmapview.bringMarkerToFront(tItem4);
                tmapview.addMarkerItem(String.valueOf(i) + "4번", tItem4);
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
