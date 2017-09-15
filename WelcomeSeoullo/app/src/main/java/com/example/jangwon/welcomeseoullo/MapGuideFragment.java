package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

import java.io.IOException;
import java.util.List;

import static com.example.jangwon.welcomeseoullo.R.drawable.test;

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


        // 사용자의 위치 수신을 위한 세팅 //
        settingGPS();

        // 사용자의 현재 위치 //
        getMyLocation();

        //위도경도를 상세주소로 변경
        reverseGeocoder();
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

    //역 지오코딩(위도경도를 상세주소로 변경)
    public void reverseGeocoder()
    {
        final Geocoder geocoder = new Geocoder(getActivity());
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(
                    currentLatitude, // 위도
                    currentLongitude, // 경도
                    10); // 얻어올 값의 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size()==0) {
                Log.e("noList", String.valueOf(latitude));
            } else {
//                tv.setText(list.get(0).toString());
                Log.e("listGet", list.get(0).getAddressLine(0).toString());
                ((GuideInfoActivity)getActivity()).changeCurrentLoationText(list.get(0).getAddressLine(0).toString());
            }
        }


    }

    //현재위치 받아오기
    private void getMyLocation() {
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // 사용자 권한 요청
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                currentLongitude = currentLocation.getLongitude();
                currentLatitude = currentLocation.getLatitude();
                Log.d("Main", "longtitude=" + currentLongitude + ", latitude=" + currentLatitude);
            }
        }
    }


    // GPS 를 받기 위한 매니저와 리스너 설정
    private void settingGPS() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //위치가 바뀔경우
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                // TODO 위도, 경도로 하고 싶은 것
//                Log.e("Latitude2", String.valueOf(latitude));
//                Log.e("Longitude2", String.valueOf(longitude));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
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
        double lat = 37.537145;
        double lng = 127.08613500000001;

        for(int i=0; i<3; i++) {
            TMapPoint point = new TMapPoint(lat, lng);
            TMapMarkerItem marker = new TMapMarkerItem();
            marker.setTMapPoint(point);
            tmapview.addMarkerItem("marker", marker);
        }
        for(int i=0; i<3; i++) {
            TMapPoint tpoint = new TMapPoint(latitude[i], longitude[i]);
            TMapMarkerItem tItem = new TMapMarkerItem();
            tItem.setTMapPoint(tpoint);
            tItem.setName("SKT타워");
            tItem.setVisible(TMapMarkerItem.VISIBLE);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), test);
            tItem.setIcon(bitmap);
// 핀모양으로 된 마커를 사용할 경우 마커 중심을 하단 핀 끝으로 설정.
            tItem.setPosition((float) 0.5, (float) 0.9); // 마커의 중심점을 하단, 중앙으로 설정
            tmapview.bringMarkerToFront(tItem);
            tmapview.addMarkerItem(String.valueOf(i), tItem);
        }

    }


}
