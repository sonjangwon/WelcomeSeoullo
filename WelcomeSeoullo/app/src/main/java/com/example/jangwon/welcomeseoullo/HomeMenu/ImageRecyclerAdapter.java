package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jangwon.welcomeseoullo.R;

import java.util.List;

/**
 * Created by woga1 on 2017-10-28.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    Context context;
    List<ImageItems> items;
    int item_layout;
    public ImageRecyclerAdapter(Context context, List<ImageItems> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.cardviewImageView);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ImageItems item = items.get(position);
        holder.icon.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.icon.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
