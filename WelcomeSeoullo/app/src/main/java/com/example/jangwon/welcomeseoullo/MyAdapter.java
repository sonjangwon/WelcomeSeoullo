package com.example.jangwon.welcomeseoullo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by woga1 on 2017-09-16.
 */

public class MyAdapter extends PagerAdapter {
    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public MyAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        Log.e("MyAdapter", String.valueOf(position));
        View myImageLayout = inflater.inflate(R.layout.imageitem, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.imageView2);
        for(int i =0; i<images.size(); i++)
        {
            Log.e("MyAdapterImages", images.get(position).toString());
        }
        myImage.setImageResource(images.get(position));

        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
