package com.example.jangwon.welcomeseoullo;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
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
import com.skp.Tmap.TMapTapi;
import com.skp.Tmap.TMapView;

import java.text.DecimalFormat;
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
    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    Location currentLocation=null;
    double currentLatitude;
    double currentLongitude;
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
        // 사용자의 위치 수신을 위한 세팅 //
        settingGPS();
        // 사용자의 현재 위치 //
        getMyLocation();
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
        TMapPoint startPoint = new TMapPoint(currentLatitude,currentLongitude);    //현재위치
        TMapPoint endPoint = new TMapPoint(37.5536067,126.96961950000002);  //서울로7017
        tmapview.setLocationPoint(startPoint.getLongitude(),startPoint.getLatitude());
        PathTracker pathTracker = new PathTracker("carPath",startPoint,endPoint);
//        pathData.findPathTime();
        SystemClock.sleep(2000);
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
        if(startLink1 == null)
        {
            Log.e("startLink1","null");
        }
        if(startLink2 == null)
        {
            Log.e("startLink2","null");
        }
        if(startLink3 == null)
        {
            Log.e("startLink3","null");
        }
        if (startLink1 != null || startLink2 != null || startLink3 != null) {
            tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");
            TMapTapi tmaptapi = new TMapTapi(getActivity());
            tmaptapi.invokeRoute("서울로7017", (float) 126.96961950000002, (float) 37.5536067);
        } else {
//        Tmap이 설치되지 않은 경우
            Log.e("showTmapInstallDialog","null");
            showTmapInstallDialog();
        }
    }

    //Tmap설치로 인도하는 부분
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
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
