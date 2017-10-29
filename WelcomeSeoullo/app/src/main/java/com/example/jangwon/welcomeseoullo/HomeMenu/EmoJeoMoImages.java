package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jangwon.welcomeseoullo.R;

import java.util.ArrayList;

/**
 * Created by woga1 on 2017-10-27.
 */

public class EmoJeoMoImages extends AppCompatActivity {

    LinearLayout imageViewLinear;
    ImageView imageView;

    Integer[] image1 = new Integer[]{R.drawable.img_card02_big_1,R.drawable.img_card02_big_2,R.drawable.img_card02_big_3,R.drawable.img_card02_big_4,R.drawable.img_card02_big_5,
            R.drawable.img_card02_big_6,R.drawable.img_card02_big_7,R.drawable.img_card02_big_8,R.drawable.img_card02_big_9};

    Integer[] image2 = new Integer[]{R.drawable.img_card03_big_1,R.drawable.img_card03_big_2,R.drawable.img_card03_big_3,R.drawable.img_card03_big_4,
            R.drawable.img_card03_big_5,R.drawable.img_card03_big_6,R.drawable.img_card03_big_7};

    Integer[] image3 = new Integer[]{R.drawable.img_card04_big_1,R.drawable.img_card04_big_2,R.drawable.img_card04_big_3,R.drawable.img_card04_big_4,
            R.drawable.img_card04_big_5,R.drawable.img_card04_big_6,R.drawable.img_card04_big_7,R.drawable.img_card04_big_8,R.drawable.img_card04_big_9,};;

    Integer[] image4 = new Integer[]{R.drawable.img_card010_big_1,R.drawable.img_card010_big_2,R.drawable.img_card010_big_3,R.drawable.img_card010_big_4,
            R.drawable.img_card010_big_5,R.drawable.img_card010_big_6};
    Integer[] image5 = new Integer[]{R.drawable.img_card011_big_1,R.drawable.img_card011_big_2,R.drawable.img_card011_big_3,R.drawable.img_card011_big_4,
            R.drawable.img_card011_big_5,R.drawable.img_card011_big_6,R.drawable.img_card011_big_7};

    Integer[] image6 = new Integer[]{R.drawable.img_card012_big_1,R.drawable.img_card012_big_2,R.drawable.img_card012_big_3,R.drawable.img_card012_big_4,
            R.drawable.img_card012_big_5,R.drawable.img_card012_big_6,R.drawable.img_card012_big_7,R.drawable.img_card012_big_8};

    Integer[] image7 = new Integer[]{R.drawable.img_card013_big_1,R.drawable.img_card013_big_2,R.drawable.img_card013_big_3,R.drawable.img_card013_big_4};

    Integer[] image8 = new Integer[]{R.drawable.img_card014_big_1,R.drawable.img_card014_big_2,R.drawable.img_card014_big_3,R.drawable.img_card014_big_4,
            R.drawable.img_card014_big_5,R.drawable.img_card014_big_6,R.drawable.img_card014_big_7,R.drawable.img_card014_big_8,R.drawable.img_card014_big_9};

    ArrayList<ImageItems> items;
    RecyclerView imageRecyclerView;
    NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emojeomoimages_layout);
        changeToStatusBar();
        Intent intent = getIntent();
        String num = intent.getExtras().getString("position");
//        mScrollView = (NestedScrollView) findViewById(R.id.ScrollView);
//        mScrollView.smoothScrollBy(100, 1000);

        imageRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        imageViewLinear = (LinearLayout) findViewById(R.id.linear);
        imageRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        imageRecyclerView.setLayoutManager(mLayoutManager);
        setImage(num);
        imageRecyclerView.setAdapter(new ImageRecyclerAdapter(getApplicationContext(), items, R.layout.emojeomoimages_layout));
    }
    public void setImage(String pageNum){
        switch (pageNum){
            case "0":
                images(image3,"1");
                break;
            case "1":
                images(image1,"2");
                break;
            case "2":
                images(image4, "3");
                break;
            case "3":
                images(image7, "4");
                break;
            case "4":
                images(image5, "5");
                break;
            case "5":
                images(image8, "6");
                break;
            case "6":
                images(image6, "7");
                break;
            case "7":
                images(image2, "8");
                break;
        }
    }
    public void images(Integer[] image, String cardNum) {
//        if (cardNum == "11" || cardNum == "1") {
//            for (int i = 0; i < image.length; i++) {
//
//                ImageView img = new ImageView(getApplicationContext());
//                img.setImageResource(image[i]);
//                if (i == 0) {
//                    img.setScaleType(ImageView.ScaleType.FIT_START);
//                }
//                img.setScaleType(ImageView.ScaleType.FIT_XY);
//                imageViewLinear.addView(img);
//            }
//        }
//        else {
        items = new ArrayList<>(image.length);
            for (int i = 0; i < image.length; i++) {
                items.add(new ImageItems(image[i]));
//                ImageView img = new ImageView(getApplicationContext());
//                img.setImageResource(image[i]);
//                img.setScaleType(ImageView.ScaleType.FIT_XY);
//                img.setLayoutParams(new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                imageViewLinear.addView(img);
            }

        //}
    }

    public void changeToStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryTranslucent));

            //상태바 남는 공간 활용
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }
}
