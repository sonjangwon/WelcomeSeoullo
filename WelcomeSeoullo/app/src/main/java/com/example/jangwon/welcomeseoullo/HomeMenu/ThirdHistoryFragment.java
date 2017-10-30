package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jangwon.welcomeseoullo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by woga1 on 2017-10-29.
 */

public class ThirdHistoryFragment extends Fragment {

    View view;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    ThirdHistoryFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.history_third, container, false);
        imageView1 = (ImageView) view.findViewById(R.id.thirdHistoryImage);
        imageView2 = (ImageView) view.findViewById(R.id.thirdHistoryImage2);
        imageView3 = (ImageView) view.findViewById(R.id.thirdHistoryImage3);
        imageView4 = (ImageView) view.findViewById(R.id.thirdHistoryImage4);

        String imageURL1 = "http://postfiles12.naver.net/MjAxNzEwMjlfMjU2/MDAxNTA5MjgxMTg5MjQ3.IFxHwCgGDr68DA2VJlq-xhxqxOLsD5e46MBsG-N3V-og.AhcvmYvLH3MjlBWPDlUZuRrPgkjvlfjPcdXPkWjYcTIg.PNG.qkrgy1206/welcomeseoullo_source3_1.png?type=w1";
        String imageURL2 = "http://postfiles13.naver.net/MjAxNzEwMjlfMzgg/MDAxNTA5MjgxMTkxNjQw.v9-3hoENRVe3ciSIsux5gO0CH6wYI022-CMip8-97pQg.7H89CaoJ320N_6rOrfBtW9vD4gm5Kpw_3T4hNnppEmgg.PNG.qkrgy1206/welcomeseoullo_source3_2.png?type=w1";
        String imageURL3 = "http://postfiles12.naver.net/MjAxNzEwMjlfMjU5/MDAxNTA5MjgxMTkyMTU0._3XlIV4STMXTZmox3CBLqCTCJKUuHDuCyiVoxHgBeiUg.sDl8gF6MTUiG5NnZSaI0g72YiU3GWB_jhYRiyP6XN1Eg.PNG.qkrgy1206/welcomeseoullo_source3_3.png?type=w1";
        String imageURL4 = "http://postfiles5.naver.net/MjAxNzEwMjlfMjc5/MDAxNTA5MjgxMTk0MDAw.tI2RZlWnSoAS2Ajcv0ipJWxzPv0k5e-DtefVnC1gzOcg.WuKSquyAbe4EBwRLOpGysNxbh1phOy_CMO5IQj_e-58g.PNG.qkrgy1206/welcomeseoullo_source3_4.png?type=w1";

        setImage(imageURL1, imageView1);
        setImage(imageURL2, imageView2);
        setImage(imageURL3, imageView3);
        setImage(imageURL4, imageView4);

        return view;
    }

    public void setImage(String url, ImageView image)
    {
        Picasso.with(getActivity().getApplicationContext())
                .load(url)
                .skipMemoryCache()
                .into(image);
    }
}
