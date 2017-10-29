package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jangwon.welcomeseoullo.MainActivity;
import com.example.jangwon.welcomeseoullo.R;

public class MainHomeFragment extends Fragment {

    View view;
    Fragment fragment;

    HomeFragment homeFragment;

    public FragmentManager fm;
    public FragmentTransaction fragmentTransaction;

    public MainHomeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_main_home, container, false);

        homeFragment = new HomeFragment();

        switchFragment(homeFragment);
        MainActivity.isHomeFragmentVisible = true;

        return view;
    }

    public void switchFragment(Fragment switchingFragment){

        fragment = switchingFragment;

        fm = getFragmentManager();
        //fm = getChildFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_home_fragment_place, fragment).addToBackStack(null).commit();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }
}