package com.example.pc.android_advance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by PC on 2/8/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<item> galleryList;

    public MyAdapter(Context applicationContext, ArrayList<item> galleryList){
         this.galleryList = galleryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, int i) {
        viewholder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewholder.img.setImageResource(galleryList.get(i).getImg());
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;

        public ViewHolder (View view){
            super(view);

            img = (ImageView) view.findViewById(R.id.imageView);
        }
    }

}
