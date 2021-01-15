package com.azhyun.massxj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.SupplyInfoActivity;
import com.azhyun.massxj.utils.Constant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tl on 2019/1/10.
 */

public class SupplyInfoAdapter extends RecyclerView.Adapter<SupplyInfoAdapter.SupplyInfoHolder> {
    private final ArrayList<String> list;
    private final Context content;

    public SupplyInfoAdapter(ArrayList<String> list, Context supplyInfoActivity) {
        this.list = list;
        this.content = supplyInfoActivity;
    }

    @Override
    public SupplyInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supply_info_image_recyclerview, parent, false);
        return new SupplyInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(SupplyInfoHolder holder, int position) {
        Glide.with(content)
                .load(Constant.IMG_URL + list.get(position))
                .placeholder(R.drawable.err)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class SupplyInfoHolder extends RecyclerView.ViewHolder{

        private final ImageView image;

        public SupplyInfoHolder(View itemView) {
            super(itemView);
           image =  (ImageView)itemView.findViewById(R.id.img);
        }
    }
}
