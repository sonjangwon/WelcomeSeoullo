package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

                // TODO : use strText
            }
        }) ;

        return view;

    }
    //리스트뷰 띄우기
    public void listview(View view) {

        if(ManagementLocation.getInstance().getSortSpinner()=="전체"|ManagementLocation.getInstance().getSortSpinner()=="공공화장실") {
            for (int i = 0; i < 2; i++) {
                LIST_MENU.add("공공화장실");
            }
        }
            if (ManagementLocation.getInstance().getSortSpinner() == "전체" | ManagementLocation.getInstance().getSortSpinner() == "주차장") {
                for (int i = 0; i < 3; i++) {
                    LIST_MENU.add("주차장");
                }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전체"|ManagementLocation.getInstance().getSortSpinner()=="공원") {
            for (int i = 0; i < 3; i++) {
                LIST_MENU.add("공원");
            }
        }
        if(ManagementLocation.getInstance().getSortSpinner()=="전체"|ManagementLocation.getInstance().getSortSpinner()=="전통시장") {
            for (int i = 0; i < 3; i++) {
                LIST_MENU.add("전통시장");
            }
        }
    }
}
