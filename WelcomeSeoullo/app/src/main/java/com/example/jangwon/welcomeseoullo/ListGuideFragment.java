package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListGuideFragment extends Fragment {
    //주변경로안내 리스트뷰 Fragment


    ListViewAdapter adapter;
    ListView listview;
    List<String> LIST_MENU ;

    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_guide, container, false);

        LIST_MENU = new ArrayList();
        listview = (ListView) view.findViewById(R.id.guideListView) ;

        adapter = new ListViewAdapter();
//        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;

        listview.setAdapter(adapter) ;
        listview(view);
//        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListItemView item = (ListItemView) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Toast.makeText(getActivity(),titleStr,Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),descStr,Toast.LENGTH_SHORT).show();
                // TODO : use item data.
                switchFragment();
                ManageListToMap.getInstance().setClickedLatitude(Double.valueOf(descStr.split(",")[0]));
                ManageListToMap.getInstance().setClickedLongitude(Double.valueOf(descStr.split(",")[1]));
                ManageListToMap.getInstance().setFragmentCondition("map");
            }
        }) ;



        return view;

    }

    //버튼으로 리스트뷰, 맵포인트를 클릭한 경우 각 프레그먼트가 실행된다.
    public void switchFragment(){
        Fragment fr = new MapGuideFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fagment_mapGuide, fr);
        fragmentTransaction.commit();
    }
    //리스트뷰 띄우기
    public void listview(View view) {
        String address="";
        if(ManagementLocation.getInstance().getSortSpinner()=="공공화장실") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicToiletVOArrayList().size(); i++) {
//                address = reverseGeocoder(Double.valueOf(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLatitude()),
//                        Double.valueOf( ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLongitude()));
                adapter.addItem(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletName(),
                        ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLatitude()+","+ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLongitude());

            }
        }
            if (ManagementLocation.getInstance().getSortSpinner() == "주차장") {
                for (int i = 0; i < ManagePublicData.getInstance().getPublicParkingLotVOArrayList().size(); i++) {
                    adapter.addItem(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotName(),ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotLatitude());
                }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="공원") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkVOArrayList().size(); i++) {
                adapter.addItem(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkName(),ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkName());
            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전통시장") {
            for (int i = 0; i < ManagePublicData.getInstance().getTraditionalMarketVOArrayList().size(); i++) {
                adapter.addItem(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketName(),ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketName());
            }
        }
    }

    //역 지오코딩(위도경도를 상세주소로 변경)
    public String reverseGeocoder(double currentLatitude,double currentLongitude)
    {
        final Geocoder geocoder = new Geocoder(getActivity());
        List<android.location.Address> list = null;
        String currentAddress="";
        try {
            list = geocoder.getFromLocation(currentLatitude, currentLongitude, 1); // 위도,경도,얻어올 값의 개수
            Log.e("latitude", String.valueOf(currentLatitude));
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size()==0) {
                Log.e("noList", "noList");

            } else {
//                tv.setText(list.get(0).toString());
                currentAddress=list.get(0).getAddressLine(0).toString().substring(5);
                Log.e("currentAddress", currentAddress);
            }
        }
        return currentAddress;


    }
}
