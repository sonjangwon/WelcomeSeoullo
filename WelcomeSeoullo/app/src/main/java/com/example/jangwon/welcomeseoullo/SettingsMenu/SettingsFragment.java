package com.example.jangwon.welcomeseoullo.SettingsMenu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_PRIVATE;
import static com.example.jangwon.welcomeseoullo.R.xml.preference;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(preference);

        final CheckBoxPreference openPublicPreference = (CheckBoxPreference) findPreference("open_public");
        openPublicPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue.toString().equals("true")){
                    openPublicPreference.setSummary("다른 사람들도 사진을 볼 수 있습니다.");
                    saveBooleanPreferences("openPublic", true);
                }
                else{
                    openPublicPreference.setSummary("비공개 상태입니다.");
                    saveBooleanPreferences("openPublic", false);
                }
                return true;
            }
        });

        final Preference fullName = findPreference("full_name");
        fullName.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                fullName.setSummary(newValue.toString());
                savePreferences("fullName", fullName.getSummary().toString());
                return true;
            }
        });

        final Preference emailAddress = findPreference("email_address");
        emailAddress.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                emailAddress.setSummary(newValue.toString());
                savePreferences("emailAddress", emailAddress.getSummary().toString());
                return true;
            }
        });
    }

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

    //SharedPreference 값 저장하기
    private void savePreferences(String key, String data){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, data);
        editor.commit();
    }

    //SharedPreference boolean 값 저장하기
    private void saveBooleanPreferences(String key, boolean data){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }

    //SharedPreference 값(Key Data) 삭제하기
    private void removePreferences(String key){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    //SharedPreference 전체 값(ALL Data) 삭제하기
    private void removeAllPreferences(){
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}