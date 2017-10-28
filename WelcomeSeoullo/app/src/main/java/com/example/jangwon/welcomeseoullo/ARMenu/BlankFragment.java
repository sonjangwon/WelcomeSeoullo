package com.example.jangwon.welcomeseoullo.ARMenu;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.MyVideoView;
import com.example.jangwon.welcomeseoullo.R;

import static android.content.Context.MODE_PRIVATE;

public class BlankFragment extends Fragment {

    public BlankFragment(){

    }

    View view;

    //SharedPreference 값 불러오기
    private String getPreferences(String key){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        return pref.getString(key, "");
    }

    //SharedPreference boolean 값 불러오기
    private boolean getBooleanPreferences(String key){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        return pref.getBoolean(key, true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_blank, container, false);

        //비디오 배경 설정
        MyVideoView mVideoView = (MyVideoView) view.findViewById(R.id.bgVideoView2);
        Uri uri = Uri.parse("android.resource://com.example.jangwon.welcomeseoullo/" + R.raw.splash_video);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        Button test = (Button)view.findViewById(R.id.button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        getPreferences("fullName") + " " + getPreferences("emailAddress") + "\n" + getPreferences("UUID") + "\n"
                        + getBooleanPreferences("openPublic") + " " + getPreferences("language"),
                        Toast.LENGTH_SHORT
                ).show();
                Intent arIntent = new Intent(getActivity(), ARMainActivity.class);
                arIntent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                startActivity(arIntent);
//                startActivity(new Intent(getActivity(), ARMainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

        return view;
    }
}
