package com.example.jangwon.welcomeseoullo.FacilityMenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jangwon.welcomeseoullo.ManagementLocation;
import com.example.jangwon.welcomeseoullo.PublicData.ManagePublicData;
import com.example.jangwon.welcomeseoullo.R;

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
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                // TODO : use item data.
                switchFragment();
                ManageListToMap.getInstance().setClickedLatitude(Double.valueOf(descStr.split(",")[0]));
                ManageListToMap.getInstance().setClickedLongitude(Double.valueOf(descStr.split(",")[1]));
                ManageListToMap.getInstance().setClickedPlaceName(titleStr);
                ManageListToMap.getInstance().setFragmentCondition("map");
                ManageListToMap.getInstance().setClickedListView(true);

                //한 fragment에서 다른fragment 함수 호출해야한다.
                GuideInfoFragment.changeButtonIcon.sendEmptyMessage(0);
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
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.colortoilet),ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletName(),
                        ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLatitude()+","+ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletLongitude());
//                adapter.addItem(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletName(),
//                        address);

            }
        }
        if (ManagementLocation.getInstance().getSortSpinner() == "주차장") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkingLotVOArrayList().size(); i++) {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.colorparkinglot),
                        ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotName(),
                        ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotLatitude()+","+ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotLongitude());
            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="공원") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkVOArrayList().size(); i++) {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.colorpark),
                        ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkName(),
                        ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkLatitude()+","+ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkLongitude());

            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전통시장") {
            for (int i = 0; i < ManagePublicData.getInstance().getTraditionalMarketVOArrayList().size(); i++) {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.colormarket),
                        ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketName(),
                        ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketLatitude()+","+ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketLongitude());
            }
        }
    }

}
