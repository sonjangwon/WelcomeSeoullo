package com.example.jangwon.welcomeseoullo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.skp.Tmap.TMapView;




public class GuideInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_info);


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.mapview);
        final TMapView tmapview = new TMapView(this);

        tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");
        tmapview.setLocationPoint(126.96961950000002,37.5536067);
        tmapview.setCompassMode(true);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(15);
//        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
        tmapview.setMapType(TMapView.MAPTYPE_TRAFFIC); //실시간 교통지도
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);

        relativeLayout.addView(tmapview);
    }
}
