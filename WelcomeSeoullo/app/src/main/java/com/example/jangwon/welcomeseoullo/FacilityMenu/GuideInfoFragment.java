package com.example.jangwon.welcomeseoullo.FacilityMenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.LoadingDialog;
import com.example.jangwon.welcomeseoullo.ManageListToMap;
import com.example.jangwon.welcomeseoullo.ManagePublicData;
import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;

import java.io.IOException;
import java.util.List;

import static android.R.attr.src;

public class GuideInfoFragment extends Fragment {

    View view;
//    public static ImageView imageView1;
//    public static ImageView imageView2;
    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    String nowFragment="map";
    TextView addressTextView;

    static ImageButton listImageButton ;
    static ImageButton mapPointImageButton ;

    public static Context mContext;

    public GuideInfoFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState == null){

//            imageView1 = (ImageButton)view.findViewById(R.id.mapPointImageButton);
//            imageView2 = (ImageButton)view.findViewById(R.id.listImageButton);

            view = inflater.inflate(R.layout.fragment_guide_info, container, false);

            //현재 위도경도,상세주소 받아오기
            currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
            currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
            currentAddress = ManagementLocation.getInstance().getCurrentAddress();

            addressTextView = (TextView) view.findViewById(R.id.addressTextView);
//            LoadingDialog.getInstance().progressON(getActivity());
            listImageButton = (ImageButton) view.findViewById(R.id.listImageButton);
            mapPointImageButton = (ImageButton) view.findViewById(R.id.mapPointImageButton);

            mapPointImageButton.setBackgroundResource(R.drawable.mappoint);
            listImageButton.setBackgroundResource(R.drawable.listpoint);

            Spinner sortSpinner = (Spinner) view.findViewById(R.id.sortSpinner);
            Spinner distanceSpinner = (Spinner) view.findViewById(R.id.distanceSpinner);
            ManagementLocation.getInstance().setSortSpinner("공공화장실");
            ManagementLocation.getInstance().setDistanceSpinner("2km");


            listImageButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    nowFragment="list";
                    switchFragment(view);
                    mapPointImageButton.setBackgroundResource(R.drawable.reversemappoint);
                    listImageButton.setBackgroundResource(R.drawable.reverselistpoint);
                }
            });
            mapPointImageButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    nowFragment="map";
                    switchFragment(view);
                    mapPointImageButton.setBackgroundResource(R.drawable.mappoint);
                    listImageButton.setBackgroundResource(R.drawable.listpoint);
                }
            });

            //스피너 어댑터 설정
            ArrayAdapter sortAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.sort,android.R.layout.simple_spinner_item);
            ArrayAdapter distanceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.distance,android.R.layout.simple_spinner_item);
            sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sortSpinner.setAdapter(sortAdapter);

            //스피너 이벤트 발생
            sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    switch (parent.getItemAtPosition(position).toString()) {
                        case "공공화장실":
                            ManagementLocation.getInstance().setSortSpinner("공공화장실");
                            break;
                        case "주차장":
                            ManagementLocation.getInstance().setSortSpinner("주차장");
                            break;
                        case "공원":
                            ManagementLocation.getInstance().setSortSpinner("공원");
                            break;
                        case "전통시장":
                            ManagementLocation.getInstance().setSortSpinner("전통시장");
                            break;
                    }
                    switchFragments(nowFragment);
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

                    switch (parent.getItemAtPosition(position).toString()) {
                        case "500m":
                            ManagementLocation.getInstance().setDistanceSpinner("500m");
                            break;
                        case "1km":
                            ManagementLocation.getInstance().setDistanceSpinner("1km");
                            break;
                        case "1.5km":
                            ManagementLocation.getInstance().setDistanceSpinner("1.5km");
                            break;
                        case "2km":
                            ManagementLocation.getInstance().setDistanceSpinner("2km");
                            break;
                    }
                    switchFragments(nowFragment);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(),"onResume",Toast.LENGTH_SHORT).show();
        settingGPS();
        ListToMapCheck();
        reverseGeocoder();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(),"onStart",Toast.LENGTH_SHORT).show();
        ListToMapCheck();
    }


    public void ChangeListIconToMap()
    {
        mapPointImageButton.setBackgroundResource(R.drawable.mappoint);
        listImageButton.setBackgroundResource(R.drawable.listpoint);
    }


    public void ListToMapCheck()
    {
        if(ManageListToMap.getInstance().getFragmentCondition()=="map"){
            Toast.makeText(getActivity(),"map2",Toast.LENGTH_SHORT).show();
            ManageListToMap.getInstance().setFragmentCondition("list");

        }
    }


    public static Handler changeButtonIcon = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mapPointImageButton.setBackgroundResource(R.drawable.mappoint);
            listImageButton.setBackgroundResource(R.drawable.listpoint);
        }
    };

    //버튼으로 리스트뷰, 맵포인트를 클릭한 경우 각 프레그먼트가 실행된다.
    public void switchFragment(View view){
        Fragment fr = new MapGuideFragment();

        if(view == view.findViewById(R.id.mapPointImageButton)){
            fr = new MapGuideFragment();
            //Toast.makeText(getApplicationContext(),"mapPointImageButton",Toast.LENGTH_SHORT).show();
        }
        else if(view == view.findViewById(R.id.listImageButton)){
            fr = new ListGuideFragment();
            //Toast.makeText(getApplicationContext(),"listImageButton",Toast.LENGTH_SHORT).show();

        }

        FragmentManager fm = getFragmentManager()   ;
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fagment_mapGuide, fr);
        fragmentTransaction.commit();
    }
    //Spinner로 fragment 재생성 될 때, 자동차경로, 도보경로를 클릭한 경우
    public void switchFragments(String nowFragment){
        Fragment fr = new MapGuideFragment();

        if(nowFragment == "map"){
            fr = new MapGuideFragment();

        }else if(nowFragment == "list") {
            fr = new ListGuideFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fagment_mapGuide, fr);
        fragmentTransaction.commit();
    }

    //역 지오코딩(위도경도를 상세주소로 변경)
    public void reverseGeocoder()
    {
        final Geocoder geocoder = new Geocoder(getActivity());
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(
                    currentLatitude, // 위도
                    currentLongitude, // 경도
                    10); // 얻어올 값의 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size()==0) {
                Log.e("noList", "noList");
            } else {
//                tv.setText(list.get(0).toString());
                addressTextView.setText(list.get(0).getAddressLine(0).toString().substring(5));
                Log.e("addressTextView",list.get(0).getAddressLine(0).toString().substring(5));
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
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                // TODO 위도, 경도로 하고 싶은 것

                Log.e("Latitude2", String.valueOf(currentLatitude));
                Log.e("Longitude2", String.valueOf(currentLongitude));
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
