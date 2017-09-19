package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListGuideFragment extends Fragment {
    //주변경로안내 리스트뷰 Fragment

    ArrayAdapter adapter;
    ListView listview;
    List<String> LIST_MENU ;

    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_guide, container, false);

        LIST_MENU = new ArrayList();
        listview = (ListView) view.findViewById(R.id.guideListView) ;
        listview(view);

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;

        listview.setAdapter(adapter) ;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(getActivity(),strText,Toast.LENGTH_SHORT).show();
                // TODO : use strText
            }
        }) ;

        return view;

    }
    //리스트뷰 띄우기
    public void listview(View view) {

        if(ManagementLocation.getInstance().getSortSpinner()=="전체"|ManagementLocation.getInstance().getSortSpinner()=="공공화장실") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicToiletVOArrayList().size(); i++) {
                LIST_MENU.add(ManagePublicData.getInstance().getPublicToiletVOArrayList().get(i).getToiletName());
            }
        }
            if (ManagementLocation.getInstance().getSortSpinner() == "전체" | ManagementLocation.getInstance().getSortSpinner() == "주차장") {
                for (int i = 0; i < ManagePublicData.getInstance().getPublicParkingLotVOArrayList().size(); i++) {
                    LIST_MENU.add(ManagePublicData.getInstance().getPublicParkingLotVOArrayList().get(i).getParkingLotName());
                }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전체"|ManagementLocation.getInstance().getSortSpinner()=="공원") {
            for (int i = 0; i < ManagePublicData.getInstance().getPublicParkVOArrayList().size(); i++) {
                LIST_MENU.add(ManagePublicData.getInstance().getPublicParkVOArrayList().get(i).getParkName());
            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전체"|ManagementLocation.getInstance().getSortSpinner()=="전통시장") {
            for (int i = 0; i < ManagePublicData.getInstance().getTraditionalMarketVOArrayList().size(); i++) {
                LIST_MENU.add(ManagePublicData.getInstance().getTraditionalMarketVOArrayList().get(i).getMarketName());
            }
        }
    }
}
