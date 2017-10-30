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
 * Created by woga1 on 2017-10-25.
 */

public class SecondCourseFragment extends Fragment {
    View view;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    String tag;
    public SecondCourseFragment(String tag) {
        this.tag = tag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.secondcourse_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.secondcourse);
        imageView2 = (ImageView) view.findViewById(R.id.secondcourse2);
        imageView3 = (ImageView) view.findViewById(R.id.secondcourse3);
        imageView4 = (ImageView) view.findViewById(R.id.secondcourse4);
        imageView5 = (ImageView) view.findViewById(R.id.secondcourse5);
        String imageURL1 = "http://postfiles2.naver.net/MjAxNzEwMjlfOSAg/MDAxNTA5MjI1Mjc1MDU2.pQa4l_SQkmMhd8sYF4T4BhIIfR0o-CUXOz956OLJPfAg.wf_4GbdsxHwbsjN-a9FZebzNGin3kv03EdpcyvNE_mgg.PNG.qkrgy1206/welcomeseoullo2_1.png?type=w1";
        String imageURL2 = "http://postfiles8.naver.net/MjAxNzEwMjlfMTgw/MDAxNTA5MjI1Mjc3MjQy.nzhzj_QhN1VfIIOeqsf1OjrjHoxGnUmUz6_msHNKjMog.ZvhQ3LwHVU6eawBrWqk-bWdcs7rjJ6rwGGazqDWSR5gg.PNG.qkrgy1206/welcomeseoullo2_2.png?type=w1";
        String imageURL3 = "http://postfiles15.naver.net/MjAxNzEwMjlfMjcz/MDAxNTA5MjI1Mjc4ODIy.DNfHxZhAGl1TbZam4o8EJqaVphQ69hqxFh4mUF-j-3og.EHp54V7Rkv1B8-G3uxnJT18Zs_wcde7h0vjxHgcPxQYg.PNG.qkrgy1206/welcomeseoullo2_3.png?type=w1";
        String imageURL4 = "http://postfiles6.naver.net/MjAxNzEwMjlfMTI3/MDAxNTA5MjI1MjgxMTE0.Iy6CthzOpHhzxlphzjRKz1-N6X7zxM_914GRZVRuspsg.wHTZatzXUfCwHXBinZ41WK0C9HBBYGLJ2i7HHyEChUkg.PNG.qkrgy1206/welcomeseoullo2_4.png?type=w1";
        String imageURL5 = "http://postfiles11.naver.net/MjAxNzEwMjlfNTMg/MDAxNTA5MjI1MjgzNTIx.YDAZSPwNJd9tR4-_6fMEHc1hBUEW9diqeAR_AS1scB0g.Kbmu18a5d_TiGMRHeZUTckWDWPiwgdgJAhSKGepkOKUg.PNG.qkrgy1206/welcomeseoullo2_5.png?type=w1";
        setImage(imageURL1, imageView);
        setImage(imageURL2, imageView2);
        setImage(imageURL3, imageView3);
        setImage(imageURL4, imageView4);
        setImage(imageURL5, imageView5);

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
