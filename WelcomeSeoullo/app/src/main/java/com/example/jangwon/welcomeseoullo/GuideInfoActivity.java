package com.example.jangwon.welcomeseoullo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.skp.Tmap.TMapView;

public class GuideInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_info);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.mapview);
        final TMapView tmapview = new TMapView(this);

        tmapview.setSKPMapApiKey("500adabd-fcb2-34fd-af42-022c6611b9a7");
        //지정 위치 설정
        tmapview.setLocationPoint(126.96961950000002,37.5536067);
        tmapview.setCompassMode(true);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
//        tmapview.setMapType(TMapView.MAPTYPE_TRAFFIC); //실시간 교통지도
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);

        relativeLayout.addView(tmapview);

        Spinner sortSpinner = (Spinner)findViewById(R.id.sortSpinner);
        Spinner distanceSpinner = (Spinner)findViewById(R.id.distanceSpinner);

        //스피너 어댑터 설정
        ArrayAdapter sortAdapter = ArrayAdapter.createFromResource(this,R.array.sort,android.R.layout.simple_spinner_item);
        ArrayAdapter distanceAdapter = ArrayAdapter.createFromResource(this,R.array.distance,android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        //스피너 이벤트 발생
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //각 항목 클릭시 포지션값을 토스트에 띄운다.
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpinner.setAdapter(distanceAdapter);

        //스피너 이벤트 발생
        distanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //각 항목 클릭시 포지션값을 토스트에 띄운다.
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
