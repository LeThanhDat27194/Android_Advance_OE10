package com.example.pc.android_advance_lesson3_le_thanh_dat_oe10;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by PC on 2/26/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Bitmap> mBitmapList;

    RecyclerAdapter(List<Bitmap> bitmapList){
        this.mBitmapList = bitmapList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mBitmapList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBitmapList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageGallery;

        ViewHolder(View itemView) {
            super(itemView);
            mImageGallery = itemView.findViewById(R.id.img_view);
        }

        public void setData(Bitmap bitmap) {
            mImageGallery.setImageBitmap(bitmap);
        }
    }
}
