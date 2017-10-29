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

public class FirstHistoryFragment extends Fragment {
    View view;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;

    FirstHistoryFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.history_first, container, false);
        imageView1 = (ImageView) view.findViewById(R.id.firstHistoryImage);
        imageView2 = (ImageView) view.findViewById(R.id.firstHistoryImage2);
        imageView3 = (ImageView) view.findViewById(R.id.firstHistoryImage3);
        imageView4 = (ImageView) view.findViewById(R.id.firstHistoryImage4);
        imageView5 = (ImageView) view.findViewById(R.id.firstHistoryImage5);

        String imageURL1 = "http://postfiles12.naver.net/MjAxNzEwMjlfMTkg/MDAxNTA5MjgwODIxNjIx.jN2fZlqQ0tg1V7LeAd6aCudPuXtTO6207ckxgGFptvQg.GMP1gWfU1sTWEnTeJczCU9e_x9UhsA47MOb4CpP9_kMg.PNG.qkrgy1206/welcomeseoullo_source1_1.png?type=w1";
        String imageURL2 = "http://postfiles7.naver.net/MjAxNzEwMjlfODAg/MDAxNTA5MjgwODIzMzg4.9yvuCoXUKBYfVvWPPzN3plxVhMsC9UiFhBk_YvrWQ98g.OqGj9i046xFdnQeNDWzqRrsJT_V--puwjmAQ9b3m2cEg.PNG.qkrgy1206/welcomeseoullo_source1_2.png?type=w1";
        String imageURL3 = "http://postfiles10.naver.net/MjAxNzEwMjlfNjIg/MDAxNTA5MjgwODI0MzY4.XiXTUZ9ZlOm4RcOmi1S3tOoXbkl0djKlF5ExIlP64jog.5JDz3OwjhWSSPK9NqJLfsXE_PtD937IPKpZiZ33r8pcg.PNG.qkrgy1206/welcomeseoullo_source1_3.png?type=w1";
        String imageURL4 = "http://postfiles4.naver.net/MjAxNzEwMjlfMjcz/MDAxNTA5MjgwODI4MzQ0.8oVkvXbBK1BDT5UwyWXEaL0hDFJDa-N4vJ__Fiqv2VEg.apMo2j4JO1YcxDyoSNXRCK5_zfk44xB-GkH2UJqEdvMg.PNG.qkrgy1206/welcomeseoullo_source1_4.png?type=w1";
        String imageURL5 = "http://postfiles2.naver.net/MjAxNzEwMjlfMTIg/MDAxNTA5MjgwODMwNzE1.hYuiytE9ZarybsXfnUemEYmncgxuWqXYb7bpS_Kafv8g.uh9owley3vK_xkvXAPK1LMZr-EdG6QR6Otswtvxt-rMg.PNG.qkrgy1206/welcomeseoullo_source1_5.png?type=w1";
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL1)
                .into(imageView1);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL2)
                .into(imageView2);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL3)
                .into(imageView3);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL4)
                .into(imageView4);
        Picasso.with(getActivity().getApplicationContext())
                .load(imageURL5)
                .into(imageView5);


        return view;
    }
}
