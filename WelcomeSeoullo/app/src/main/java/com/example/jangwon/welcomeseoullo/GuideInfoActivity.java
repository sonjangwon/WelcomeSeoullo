package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;




public class GuideInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_info);




        Spinner sortSpinner = (Spinner)findViewById(R.id.sortSpinner);
        Spinner distanceSpinner = (Spinner)findViewById(R.id.distanceSpinner);
        final ImageButton listImageButton = (ImageButton)findViewById(R.id.listImageButton);
        final ImageButton mapPointImageButton = (ImageButton)findViewById(R.id.mapPointImageButton);

        listImageButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                switchFragment(view);
                mapPointImageButton.setBackgroundResource(R.drawable.reversemappoint);
//                listImageButton.setBackgroundResource(R.drawable.list);
            }

        });
        mapPointImageButton.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                switchFragment(view);
                mapPointImageButton.setBackgroundResource(R.drawable.mappoint);
//                listImageButton.setBackgroundResource(R.drawable.list);
            }

        });

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

    //버튼으로 리스트뷰, 맵포인트를 클릭한 경우 각 프레그먼트가 실행된다.
    public void switchFragment(View view){
        Fragment fr = new MapGuideFragment();

        if(view == findViewById(R.id.mapPointImageButton)){
            fr = new MapGuideFragment();
            Toast.makeText(getApplicationContext(),"mapPointImageButton",Toast.LENGTH_SHORT).show();
        }
        else if(view == findViewById(R.id.listImageButton)){
            fr = new ListGuideFragment();
            Toast.makeText(getApplicationContext(),"listImageButton",Toast.LENGTH_SHORT).show();

        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fagment_mapGuide, fr);
        fragmentTransaction.commit();
    }
    //Fragment안에서 자동차경로, 도보경로를 클릭한 경우
    public void switchFragments(View view){
        Fragment fr = new CarFragment();

        if(view == findViewById(R.id.byCar)){
            fr = new CarFragment();
            Toast.makeText(getApplicationContext(),"byCar",Toast.LENGTH_SHORT).show();

        }else if(view == findViewById(R.id.byBus)){
            fr = new BusFragment();
            Toast.makeText(getApplicationContext(),"byBus",Toast.LENGTH_SHORT).show();
        }else{
            fr = new FootFragment();
            Toast.makeText(getApplicationContext(),"onFoot",Toast.LENGTH_SHORT).show();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }
}
