package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import java.util.TimerTask;

import static com.example.jangwon.welcomeseoullo.R.id.totalPayment;

public class CarFragment extends Fragment {

    View view;
    TMapData tmapdata = new TMapData();
    TMapView tmapview;
    TextView totalTimeTextView;
    TextView totalDistanceTextView;
    TextView totalPaymentTextView;
    LinearLayout startTmap;
    int totalDistance;
    int totalTime;
    int taxiFare;
    int hour=0;
    int min=0;
    TelephonyManager telephonyManager;
    String networkoper;
    TimerTask tt;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_car, container, false);

        totalTimeTextView = (TextView) view.findViewById(R.id.totalTime);
        totalDistanceTextView = (TextView) view.findViewById(R.id.totalDistance);
        totalPaymentTextView = (TextView) view.findViewById(totalPayment);
        startTmap = (LinearLayout) view.findViewById(R.id.startTmap) ;
        startTmap.setOnClickListener(new LinearLayout.OnClickListener(){
            @Override
            public void onClick(View view) {
                TmapNavigation();
            }

        });
        mapView(view);
        drawLine();
        tt = new TimerTask() {
            @Override
            public void run() {

            }
        };




        return view;
    }
    //맵 띄우기
    public void mapView(View view)
    {
        //선언
        tmapview = new TMapView(getActivity());
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.carMapView);  //getActivity().findViewByID 아니다 ㅅㅂ

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
        PathTracker pathTracker = new PathTracker("carPath",startPoint,endPoint);
//        pathData.findPathTime();
        SystemClock.sleep(1500);
        totalDistance = pathTracker.getTotalDistance();
        totalTime = pathTracker.getTotalTime();
        taxiFare = pathTracker.getTaxiFare();

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
        totalPaymentTextView.setText(String.valueOf(taxiFare)+"원");


        Log.e(" 총 시간 ","true");
        Log.e(" 총 시간 ",":  " + totalTime);
        Log.e(" 총 거리 ",":  " + totalDistance);
        Log.e(" 택시 요금 ",":  " + taxiFare);

        tmapdata.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, startPoint, endPoint,  new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine tMapPolyLine) {
                tMapPolyLine.setLineColor(Color.GRAY);
                tMapPolyLine.setLineWidth(15);
                tmapview.addTMapPath(tMapPolyLine);
            }
        });


    }
    private void TmapNavigation() {
        showTmapInstallDialog();
    }

    private void showTmapInstallDialog() {

        telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        networkoper = telephonyManager.getNetworkOperatorName();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        alertDialogBuilder
                .setMessage(getString(R.string.alertTmapInstall))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.install),
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                if (networkoper.equals("SKTelecom")) {
                                    Log.i("통신사", "skt");
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://onesto.re/0000163382"));
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.skt.tmap.ku"));
                                    startActivity(intent);
                                }
                            }
                        })
                .setNegativeButton(getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();
        // 다이얼로그 보여주기
        alertDialog.show();
    }
}
