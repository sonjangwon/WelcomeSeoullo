package com.example.jangwon.welcomeseoullo.NavigationMenu;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.LoadingDialog;
import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapInfo;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapTapi;
import com.skp.Tmap.TMapView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TimerTask;

public class CarFragment extends Fragment {
    //자동차 경로안내

    View view;
    TMapData tmapdata = new TMapData();
    TMapView tmapview;
    TextView totalTimeTextView;
    TextView totalDistanceTextView;
    TextView totalPaymentTextView;
    LinearLayout startTmap;
    ImageButton startTmapButton;

    int totalDistance;
    int totalTime;
    int taxiFare;
    int hour=0;
    int min=0;

    TelephonyManager telephonyManager;
    String networkoper;
    TimerTask tt;
    // 사용자 위치 수신기
    double currentLatitude;
    double currentLongitude;
    TMapPoint startPoint;
    TMapPoint endPoint;

    public CarFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        LoadingDialog.getInstance().progressON(getActivity());

        view = inflater.inflate(R.layout.fragment_car, container, false);

        totalTimeTextView = (TextView) view.findViewById(R.id.totalTime);
        totalDistanceTextView = (TextView) view.findViewById(R.id.totalDistance);
        totalPaymentTextView = (TextView) view.findViewById(R.id.totalPayment);
        startTmapButton = (ImageButton)view.findViewById(R.id.startTmapButton);
        startTmap = (LinearLayout) view.findViewById(R.id.startTmap) ;
        startTmapButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                TmapNavigation();
            }
        });

        //현재 위도경도 받아오기
        currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
        currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();

        mapView(view);
        tt = new TimerTask() {
            @Override
            public void run() {

            }
        };

        LoadingDialog.getInstance().progressOFF();

        return view;
    }



    //맵 띄우기
    public void mapView(View view)
    {
        //선언
        tmapview = new TMapView(getActivity());
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.carMapView);

        startPoint = new TMapPoint(currentLatitude,currentLongitude);    //현재위치
        endPoint = new TMapPoint(37.5536067,126.96961950000002);  //서울로7017

//        tmapview.setSKPMapApiKey("cad2cc9b-a3d5-3c32-8709-23279b7247f9");
        tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");
        ArrayList<TMapPoint> point = new ArrayList<TMapPoint>();

        point.add(startPoint);
        point.add(endPoint);
        Bitmap start = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.startpoint);
        Bitmap end = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.arrivalpoint);
        start = Bitmap.createScaledBitmap(start, 110, 110, true);
        end = Bitmap.createScaledBitmap(end, 110, 110, true);
        tmapview.setTMapPathIcon(start, end);
        TMapInfo info = tmapview.getDisplayTMapInfo(point);
        tmapview.setCompassMode(false);
        tmapview.setIconVisibility(false);
        tmapview.setZoomLevel(11);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
//        tmapview.setTrackingMode(true);
        tmapview.setMapPosition(TMapView.POSITION_DEFAULT);
//        tmapview.setSightVisible(true);
        relativeLayout.addView(tmapview);
        drawLine();
    }

    //경로 나타내기
    public void drawLine()
    {
        tmapview.setLocationPoint(startPoint.getLongitude(),startPoint.getLatitude());
        PathTracker pathTracker = new PathTracker("carPath",startPoint,endPoint);
//        pathData.findPathTime();
        SystemClock.sleep(3000);
        totalDistance = pathTracker.getTotalDistance();
        totalTime = pathTracker.getTotalTime();
        taxiFare = pathTracker.getTaxiFare();

        //총 시간이 1시간이 넘을 경우
        if(totalTime>=3600)
        {
            hour=(totalTime/3600);
            min=(totalTime/60)-(hour*60);
            totalTimeTextView.setText(String.valueOf(hour)+"시간"+String.valueOf(min)+"분");
        }
        //총 시간이 1시간 미만일 경우
        else
        {
            min=(totalTime/60);
            totalTimeTextView.setText(String.valueOf(min)+"분");
        }

        totalDistanceTextView.setText(String.valueOf(totalDistance/(double)1000)+"km");

        //택시요금이 천원 이상일 경우
        if(taxiFare>=1000)
        {
            DecimalFormat df = new DecimalFormat("###,###.####");
            totalPaymentTextView.setText(String.valueOf( String.format(df.format(taxiFare))+"원"));
        }
        else
        {
            totalPaymentTextView.setText(String.valueOf(taxiFare)+"원");
        }


        //자동차 경로를 polyLine을 이용하여 그린다.
        tmapdata.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, startPoint, endPoint,  new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine tMapPolyLine) {
                tMapPolyLine.setLineColor(Color.GRAY);
                tMapPolyLine.setLineWidth(15);
                tmapview.addTMapPath(tMapPolyLine);
            }
        });

    }
    //안내시작버튼을 누른 경우
    private void TmapNavigation() {
        Intent startLink1 = getActivity().getPackageManager().getLaunchIntentForPackage("com.skt.skaf.l001mtm091");
        Intent startLink2 = getActivity().getPackageManager().getLaunchIntentForPackage("com.skt.tmap.ku");
        Intent startLink3 = getActivity().getPackageManager().getLaunchIntentForPackage("com.skt.skaf.l001mtm092");

        if (startLink1 != null || startLink2 != null || startLink3 != null) {
            tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");
            TMapTapi tmaptapi = new TMapTapi(getActivity());
            tmaptapi.invokeRoute("서울로7017", (float) 126.96961950000002, (float) 37.5536067);
        } else {
//        Tmap이 설치되지 않은 경우
            showTmapInstallDialog();
        }
    }

    //Tmap설치로 인도하는 부분
    private void showTmapInstallDialog() {

        telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        networkoper = telephonyManager.getNetworkOperatorName();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder
                .setMessage(getString(R.string.alertTmapInstall))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.install),
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                if (networkoper.equals("SKTelecom")) {
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
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
