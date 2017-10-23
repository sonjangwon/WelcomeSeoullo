package com.example.jangwon.welcomeseoullo.NavigationMenu;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;

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
    double currentLatitude;
    double currentLongitude;

    TMapPoint startPoint;
    TMapPoint endPoint;

    public FootFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_foot, container, false);

        totalTimeTextView = (TextView) view.findViewById(R.id.footTotalTime);
        totalDistanceTextView = (TextView) view.findViewById(R.id.footTotalDistance);
        calorieTextView = (TextView) view.findViewById(R.id.footCalorie);

        //현재 위도경도 받아오기
        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();

        mapView(view);
        drawLine();

        return view;
    }

    //맵 띄우기
    public void mapView(View view)
    {
        //선언
        tmapview = new TMapView(getActivity());
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.footMapView);

        startPoint = new TMapPoint(currentLatitude,currentLongitude);    //현재위치
        endPoint = new TMapPoint(37.5536067,126.96961950000002);  //서울로7017

//        Bitmap start = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.startpoint);
//        Bitmap end = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.arrivalpoint);
//        tmapview.setTMapPathIcon(start, end);

//        tmapview.setSKPMapApiKey("cad2cc9b-a3d5-3c32-8709-23279b7247f9");
        tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");
        ArrayList<TMapPoint> point = new ArrayList<TMapPoint>();

        point.add(startPoint);
        point.add(endPoint);
        Bitmap start = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.test);
        Bitmap end = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.test0);
        start = Bitmap.createScaledBitmap(start, 110, 110, true);
        end = Bitmap.createScaledBitmap(end, 110, 110, true);
        tmapview.setTMapPathIcon(start, end);
        tmapview.getDisplayTMapInfo(point);
        tmapview.setCompassMode(false);
        tmapview.setIconVisibility(false);
        tmapview.setZoomLevel(11);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
//        tmapview.setTrackingMode(true);
//        tmapview.setSightVisible(true);
        relativeLayout.addView(tmapview);
    }

    //경로 나타내기
    public void drawLine()
    {
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

}
