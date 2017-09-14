package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map_guide, container, false);
        latitude[0] = Double.valueOf("37.540389");
        latitude[1]= Double.valueOf("37.538289");
        latitude[2]= Double.valueOf("37.565102");
        longitude[0] = Double.valueOf("127.06923600000005");
        longitude[1]= Double.valueOf("127.12338350000005");
        longitude[2]= Double.valueOf("127.00776500000006");

        mapView(view);
        return view;

    }

    //맵 띄우기
    public void mapView(View view)
    {
        //선언
        tmapview = new TMapView(getActivity());
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.guideMapView);  //getActivity().findViewByID 아니다 ㅅㅂ

        tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");

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
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
            tItem.setIcon(bitmap);
// 핀모양으로 된 마커를 사용할 경우 마커 중심을 하단 핀 끝으로 설정.
            tItem.setPosition((float) 0.5, (float) 0.9); // 마커의 중심점을 하단, 중앙으로 설정
            tmapview.bringMarkerToFront(tItem);
            tmapview.addMarkerItem(String.valueOf(i), tItem);
        }

    }


}
