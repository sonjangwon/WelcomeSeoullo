package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
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
    TextView totalPaymentTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_foot, container, false);

        totalTimeTextView = (TextView) view.findViewById(R.id.footTotalTime);
        totalDistanceTextView = (TextView) view.findViewById(R.id.footTotalDistance);
        totalPaymentTextView = (TextView) view.findViewById(R.id.footTotalPayment);


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
        TMapPoint startPoint = new TMapPoint(37.5502596,127.07313899999997);    //세종대학교
        TMapPoint endPoint = new TMapPoint(37.5536067,126.96961950000002);  //서울로7017
        tmapview.setLocationPoint(startPoint.getLongitude(),startPoint.getLatitude());
        PathTracker pathTracker = new PathTracker(startPoint,endPoint);
//        pathData.findPathTime();

        int totalDistance = pathTracker.getTotalDistance();
        int totalTime = pathTracker.getTotalTime();

//        totalTimeTextView.setText(String.valueOf(totalTime));
//        totalDistanceTextView.setText(String.valueOf(totalDistance));

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
