package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.skp.Tmap.TMapView;

public class GuideInfoFragment extends Fragment {

    View view;

    public GuideInfoFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState == null){

            view = inflater.inflate(R.layout.fragment_guide_info, container, false);

            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.mapview);
            final TMapView tmapview = new TMapView(this.getActivity());

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

            Spinner sortSpinner = (Spinner) view.findViewById(R.id.sortSpinner);
            Spinner distanceSpinner = (Spinner) view.findViewById(R.id.distanceSpinner);

            //스피너 어댑터 설정
            ArrayAdapter sortAdapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.sort,android.R.layout.simple_spinner_item);
            ArrayAdapter distanceAdapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.distance,android.R.layout.simple_spinner_item);
            sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sortSpinner.setAdapter(sortAdapter);

            //스피너 이벤트 발생
            sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //각 항목 클릭시 포지션값을 토스트에 띄운다.
                    //Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
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
                    //Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }
        return view;
    }
}
