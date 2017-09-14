package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListGuideFragment extends Fragment {

//    static final String[] LIST_MENU = {"LIST1","LIST2","LIST3"};
    ArrayAdapter adapter;

    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_guide, container, false);

        final String[] LIST_MENU = {"LIST1","LIST2","LIST3"};
        ListView listview = (ListView) view.findViewById(R.id.guideListView) ;
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;

        listview.setAdapter(adapter) ;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;

                // TODO : use strText
            }
        }) ;


//        listview(view);
        return view;

    }

    //리스트뷰 띄우기
    public void listview(View view) {
        ListView listView = (ListView) view.findViewById(R.id.guideListView);
    }
}
