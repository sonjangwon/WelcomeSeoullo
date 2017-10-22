package com.example.jangwon.welcomeseoullo.FacilityMenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.R;

import java.io.IOException;
import java.util.List;


public class GuideInfoFragment extends Fragment {

    View view;
    // 사용자 위치 수신기
    private LocationManager locationManager;
    private LocationListener locationListener;
    double currentLatitude;
    double currentLongitude;
    String currentAddress;
    TextView addressTextView;
    static LinearLayout listGuideLinearLayout;
    static LinearLayout mapGuideLinearLayout;
    static String nowFragment="map";

    static Button mapGuideButton;
    static Button listGuideButton;
    Button toiletButton;
    Button parkinglotButton;
    Button parkButton;
    Button marketButton;

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

            view = inflater.inflate(R.layout.fragment_guide_info, container, false);

            mapGuideButton = (Button)view.findViewById(R.id.mapGuideButton);
            listGuideButton = (Button)view.findViewById(R.id.listGuideButton);
            toiletButton = (Button)view.findViewById(R.id.toiletButton);
            parkinglotButton = (Button)view.findViewById(R.id.parkinglotButton);
            parkButton = (Button)view.findViewById(R.id.parkButton);
            marketButton = (Button)view.findViewById(R.id.marketButton);

            listGuideLinearLayout = (LinearLayout) view.findViewById(R.id.listGuideLinearLayout);
            mapGuideLinearLayout = (LinearLayout) view.findViewById(R.id.mapGuideLinearLayout);

            //현재 위도경도,상세주소 받아오기
            currentLatitude = ManagementLocation.getInstance().getCurrentLatitude();
            currentLongitude = ManagementLocation.getInstance().getCurrentLongitude();
            currentAddress = ManagementLocation.getInstance().getCurrentAddress();

            addressTextView = (TextView) view.findViewById(R.id.addressTextView);



            ManagementLocation.getInstance().setSortSpinner("공공화장실");
            switchFragments(nowFragment);

            listGuideButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    nowFragment="list";
                    switchFragment(view);
                    mapGuideButton.setTextColor(Color.parseColor("#bdbdbd"));
                    mapGuideButton.setBackgroundResource(R.drawable.nonclickbutton);
                    listGuideButton.setBackgroundResource(R.drawable.clickrightbutton);
                    listGuideButton.setTextColor(Color.parseColor("#7BA293"));
                    listGuideButton.bringToFront();
                    listGuideButton.invalidate();
                    listGuideLinearLayout.bringToFront();
                    listGuideLinearLayout.invalidate();

                }
            });
            mapGuideButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    nowFragment="map";
                    switchFragment(view);
                    listGuideButton.setBackgroundResource(R.drawable.nonclickbutton);
                    listGuideButton.setTextColor(Color.parseColor("#c0c5ce"));
                    mapGuideButton.bringToFront();
                    mapGuideButton.setTextColor(Color.parseColor("#7BA293"));
                    mapGuideButton.setBackgroundResource(R.drawable.roundbutton);
                    mapGuideButton.invalidate();
                    mapGuideLinearLayout.bringToFront();
                    mapGuideLinearLayout.invalidate();
                }
            });

            toiletButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ManagementLocation.getInstance().setSortSpinner("공공화장실");
                    toiletButton.setTextColor(Color.parseColor("#7BA293"));
                    parkinglotButton.setTextColor(Color.parseColor("#c0c5ce"));
                    parkButton.setTextColor(Color.parseColor("#c0c5ce"));
                    marketButton.setTextColor(Color.parseColor("#c0c5ce"));
                    switchFragments(nowFragment);
                }
            });
            parkinglotButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ManagementLocation.getInstance().setSortSpinner("주차장");
                    parkinglotButton.setTextColor(Color.parseColor("#7BA293"));
                    toiletButton.setTextColor(Color.parseColor("#c0c5ce"));
                    parkButton.setTextColor(Color.parseColor("#c0c5ce"));
                    marketButton.setTextColor(Color.parseColor("#c0c5ce"));
                    switchFragments(nowFragment);
                }
            });
            parkButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ManagementLocation.getInstance().setSortSpinner("공원");
                    parkButton.setTextColor(Color.parseColor("#7BA293"));
                    parkinglotButton.setTextColor(Color.parseColor("#c0c5ce"));
                    toiletButton.setTextColor(Color.parseColor("#c0c5ce"));
                    marketButton.setTextColor(Color.parseColor("#c0c5ce"));
                    switchFragments(nowFragment);
                }
            });
            marketButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ManagementLocation.getInstance().setSortSpinner("전통시장");
                    marketButton.setTextColor(Color.parseColor("#7BA293"));
                    parkinglotButton.setTextColor(Color.parseColor("#c0c5ce"));
                    parkButton.setTextColor(Color.parseColor("#c0c5ce"));
                    toiletButton.setTextColor(Color.parseColor("#c0c5ce"));
                    switchFragments(nowFragment);
                }
            });

        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        settingGPS();
        reverseGeocoder();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public static Handler changeButtonIcon = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            listGuideButton.setBackgroundResource(R.drawable.nonclickbutton);
            listGuideButton.setTextColor(Color.parseColor("#c0c5ce"));
            mapGuideButton.bringToFront();
            mapGuideButton.setTextColor(Color.parseColor("#000000"));
            mapGuideButton.setBackgroundResource(R.drawable.roundbutton);
            mapGuideButton.invalidate();
            mapGuideLinearLayout.bringToFront();
            mapGuideLinearLayout.invalidate();
            nowFragment="map";
//            ManageListToMap.getInstance().setFragmentCondition("map");
        }
    };

    //버튼으로 리스트뷰, 맵포인트를 클릭한 경우 각 프레그먼트가 실행된다.
    public void switchFragment(View view){
        Fragment fr = new MapGuideFragment();

        if(view == view.findViewById(R.id.mapGuideButton)){
            fr = new MapGuideFragment();
        }
        else if(view == view.findViewById(R.id.listGuideButton)){
            fr = new ListGuideFragment();

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            addressTextView.setText(ManagementLocation.getInstance().getCurrentAddress());
        }
        else {

        }
    }

}
