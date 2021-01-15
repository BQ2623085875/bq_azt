package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.azhyun.massxj.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by tl on 2018/8/17.
 */

public class MyTrusteeshipAdapter extends RecyclerView.Adapter<MyTrusteeshipAdapter.MyTrusteeshipHolder>{
    private final List<String> list;
    private final int num;
//    private OnItemClickListener mOnItemClickLitener;

    public MyTrusteeshipAdapter(List<String> list, int num) {
        this.list = list;
        this.num= num;
    }

    @Override
    public MyTrusteeshipHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_trusteeship_activity_recyclerview, parent, false);

        return new MyTrusteeshipHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyTrusteeshipHolder holder, final int position) {
        if (list.size() == num){
            holder.delete.setVisibility(View.VISIBLE);
            Glide.with(holder.img.getContext())
                    .load(list.get(position))
                    .into(holder.img);
        }else {
            if (position == list.size()){
                holder.img.setImageResource(R.drawable.tupiantianjia);
                holder.delete.setVisibility(View.GONE);
            }else {
                holder.delete.setVisibility(View.VISIBLE);
                Glide.with(holder.img.getContext())
                        .load(list.get(position))
                        .into(holder.img);
            }
        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                boolean b = false;
                for (int i = 0 ; i<list.size(); i++){
                    if (list.get(i).equals("")){
                        b = true;
                    }
                }
//                if (!b){
//                    list.add("");
//                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size()  == num){
            return num;
        }else {
            return list.size() +1;
        }
    }

    public class MyTrusteeshipHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final ImageView delete;

        public MyTrusteeshipHolder(View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            delete = (ImageView)itemView.findViewById(R.id.delete);
        }
    }

//    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }
}
