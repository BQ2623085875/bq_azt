package com.azhyun.massxj.adapter.azt.caigou;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.ProductInfoImagesActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by wkk on 2020/9/8.
 */

public class CaiGouCommitImgAdapter extends RecyclerView.Adapter<CaiGouCommitImgAdapter.Holder> {
    private ArrayList<String> imglist;
    private Context context;

    public CaiGouCommitImgAdapter(ArrayList<String> list, Context context) {
        this.imglist = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.caigou_commit_item, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        String s = imglist.get(position);

        Glide.with(context).load(s).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductInfoImagesActivity.newIntent(context, imglist, position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imglist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final ImageView img;

        public Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
