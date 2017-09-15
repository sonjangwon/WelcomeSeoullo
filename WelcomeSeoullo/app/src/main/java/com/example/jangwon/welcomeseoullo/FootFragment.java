package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

public class FootFragment extends Fragment {

    View view;
    TMapData tmapdata = new TMapData();
    TMapView tmapview;
    TextView totalTimeTextView;
    TextView totalDistanceTextView;
    TextView calorieTextView;
    int hour=0;
    int min=0;
    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_foot, container, false);

        totalTimeTextView = (TextView) view.findViewById(R.id.footTotalTime);
        totalDistanceTextView = (TextView) view.findViewById(R.id.footTotalDistance);
        calorieTextView = (TextView) view.findViewById(R.id.footCalorie);
        // 사용자의 위치 수신을 위한 세팅 //
        settingGPS();
        // 사용자의 현재 위치 //
        getMyLocation();
        mapView(view);
        drawLine();

        return view;
    }

    //맵 띄우기
    public void mapView(View view)
    {
        //선언
        tmapview = new TMapView(getActivity());
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.footMapView);  //getActivity().findViewByID 아니다 ㅅㅂ

        tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");

        tmapview.setCompassMode(true);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(11);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);
        relativeLayout.addView(tmapview);
    }

    //경로 나타내기
    public void drawLine()
    {
        TMapPoint startPoint = new TMapPoint(currentLatitude,currentLongitude);    //현재위치
        TMapPoint endPoint = new TMapPoint(37.5536067,126.96961950000002);  //서울로7017
        tmapview.setLocationPoint(startPoint.getLongitude(),startPoint.getLatitude());
        PathTracker pathTracker = new PathTracker("footPath",startPoint,endPoint);
//        pathData.findPathTime();
        SystemClock.sleep(3000);
        int totalDistance = pathTracker.getTotalDistance();
        int totalTime = pathTracker.getTotalTime();

//        totalTimeTextView.setText(String.valueOf(totalTime));
//        totalDistanceTextView.setText(String.valueOf(totalDistance));
        if(totalTime>=3600)
        {
            hour=(totalTime/3600);
            min=(totalTime/60)-(hour*60);
            totalTimeTextView.setText(String.valueOf(hour)+"시간"+String.valueOf(min)+"분");
        }
        else
        {
            min=(totalTime/60);
            totalTimeTextView.setText(String.valueOf(min)+"분");
        }
        totalDistanceTextView.setText(String.valueOf(totalDistance/(double)1000)+"km");
        calorieTextView.setText(String.valueOf((totalTime/60)*6)+"kcal");
        Log.e(" 총 시간 ","true");
        Log.e(" 총 시간 ",":  " + totalTime);
        Log.e(" 총 거리 ",":  " + totalDistance);

        tmapdata.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, startPoint, endPoint,  new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine tMapPolyLine) {
                tMapPolyLine.setLineColor(Color.GRAY);
                tMapPolyLine.setLineWidth(15);
                tmapview.addTMapPath(tMapPolyLine);
            }
        });


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
}
