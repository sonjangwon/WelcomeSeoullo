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

public class SecondHistoryFragment extends Fragment {
    View view;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;

    SecondHistoryFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.history_second, container, false);

        imageView1 = (ImageView) view.findViewById(R.id.secondHistoryImage);
        imageView2 = (ImageView) view.findViewById(R.id.secondHistoryImage2);
        imageView3 = (ImageView) view.findViewById(R.id.secondHistoryImage3);
        imageView4 = (ImageView) view.findViewById(R.id.secondHistoryImage4);
        imageView5 = (ImageView) view.findViewById(R.id.secondHistoryImage5);

        String imageURL1 = "http://postfiles9.naver.net/MjAxNzEwMjlfMTEw/MDAxNTA5MjgxMDA1OTgx.6ddKQuPdVVUjR1bq4KIsRd4gYzBIb9EccyTK5tSHTJwg.eikZOtiiqubTY3e0-k4-XMqU-L_RMozgOlcJmCMoeW8g.PNG.qkrgy1206/welcomeseoullo_source2_1.png?type=w1";
        String imageURL2 = "http://postfiles1.naver.net/MjAxNzEwMjlfMjM2/MDAxNTA5MjgxMDA4NDk1.he3zLGews68DWV3oCjVyh229AGUTF-IbMZhVJobsSj4g.GSbRxLhgSOfj_cBjWN9v0VRnoxHdjh5nxBamkF4STkog.PNG.qkrgy1206/welcomeseoullo_source2_2.png?type=w1";
        String imageURL3 = "http://postfiles15.naver.net/MjAxNzEwMjlfMzkg/MDAxNTA5MjgxMDA5MDU3.Ygkkei01c-KsWCU2e82XAJdKyRsuS2EQ9cqiItmQCBsg.RoDm3xwaEjvOQ1yOm4Wa19-JBr24yD0QLtIwIRGNZlQg.PNG.qkrgy1206/welcomeseoullo_source2_3.png?type=w1";
        String imageURL4 = "http://postfiles2.naver.net/MjAxNzEwMjlfMTI1/MDAxNTA5MjgxMDEyMjI5.ipfDIBsA4REFjk5k7f2Pkzc2K1xZo8ZIbwzhBV6rsN0g.zqgpris7e0JEI7b-pyrLM8zECz8knzUJghJLBd1MoGUg.PNG.qkrgy1206/welcomeseoullo_source2_4.png?type=w1";
        String imageURL5 = "http://postfiles4.naver.net/MjAxNzEwMjlfMjg0/MDAxNTA5MjgxMDE0OTE5.c65nbxrscNg0T0LAO9mIcWopq8K6NV5jaZgo7vVAaB0g.XaMa9O5KWXGfVAKEjiFN5zvUW4YYEVYzm0X5e72I-Ecg.PNG.qkrgy1206/welcomeseoullo_source2_5.png?type=w1";

        setImage(imageURL1, imageView1);
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
                .into(image);
    }
}
