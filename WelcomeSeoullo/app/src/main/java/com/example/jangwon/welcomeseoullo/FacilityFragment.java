package com.example.jangwon.welcomeseoullo;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FacilityFragment extends Fragment {
    View view;

    static TextView publicDataTextView;
    static ProgressBar progressBar;

    ManagePublicData managePublicData = ManagePublicData.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState == null){
            view = inflater.inflate(R.layout.fragment_facility, container, false);

            publicDataTextView = (TextView) view.findViewById(R.id.publicDataTextView);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

            if(managePublicData.getPublicToiletVOArrayList().isEmpty()){
                managePublicData.parsePublicToilet.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else{
                publicDataTextView.setText("");
                managePublicData.parsePublicToilet.onPostExecute("");
            }

            final Button publicToiletButton = (Button) view.findViewById(R.id.publicToiletButton);
            final Button publicParkingLotButton = (Button) view.findViewById(R.id.publicParkingLotButton);
            final Button publicParkButton = (Button) view.findViewById(R.id.publicParkButton);
            final Button traditionalMarketButton = (Button) view.findViewById(R.id.traditionalMarketButton);

            publicToiletButton.setTextColor(Color.parseColor("#FF0000"));

            publicToiletButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#FF0000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#000000"));
                    publicParkButton.setTextColor(Color.parseColor("#000000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#000000"));

                    progressBar.setVisibility(View.VISIBLE);
                    publicDataTextView.setText("");
                    managePublicData.parsePublicToilet.onPostExecute("");
                }
            });
            publicParkingLotButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#000000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#FF0000"));
                    publicParkButton.setTextColor(Color.parseColor("#000000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#000000"));

                    progressBar.setVisibility(View.VISIBLE);
                    if(managePublicData.getPublicParkingLotVOArrayList().isEmpty()){
                        managePublicData.parsePublicParkingLot.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                    publicDataTextView.setText("");
                    managePublicData.parsePublicParkingLot.onPostExecute("");
                }
            });
            publicParkButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#000000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#000000"));
                    publicParkButton.setTextColor(Color.parseColor("#FF0000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#000000"));

                    progressBar.setVisibility(View.VISIBLE);
                    if(managePublicData.getPublicParkVOArrayList().isEmpty()){
                        managePublicData.parsePublicPark.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                    publicDataTextView.setText("");
                    managePublicData.parsePublicPark.onPostExecute("");
                }
            });
            traditionalMarketButton.setOnClickListener(new EditText.OnClickListener(){
                @Override
                public void onClick(View view) {
                    publicToiletButton.setTextColor(Color.parseColor("#000000"));
                    publicParkingLotButton.setTextColor(Color.parseColor("#000000"));
                    publicParkButton.setTextColor(Color.parseColor("#000000"));
                    traditionalMarketButton.setTextColor(Color.parseColor("#FF0000"));

                    progressBar.setVisibility(View.VISIBLE);
                    if(managePublicData.getTraditionalMarketVOArrayList().isEmpty()){
                        managePublicData.parseTraditionalMarket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                    publicDataTextView.setText("");
                    managePublicData.parseTraditionalMarket.onPostExecute("");
                }
            });

        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
