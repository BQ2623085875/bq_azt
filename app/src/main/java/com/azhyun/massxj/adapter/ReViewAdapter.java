package com.azhyun.massxj.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.EvaluationsResult;
import com.azhyun.massxj.utils.Constant;
import com.bumptech.glide.Glide;
import com.example.xlhratingbar_lib.XLHRatingBar;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tl on 2018/8/23.
 */

public class ReViewAdapter extends RecyclerView.Adapter<ReViewAdapter.ReViewHolder> {

    private final List<EvaluationsResult.Data.Rows> list;

    public ReViewAdapter(List<EvaluationsResult.Data.Rows> rowsList) {
        this.list = rowsList;
    }

    @Override
    public ReViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_recyclerview, parent, false);
        return new ReViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReViewHolder holder, int position) {


        Glide.with(MyApplication.getContext())
                .load(Constant.IMG_URL + list.get(position).getHeadPortrait())
                .error(R.drawable.err)
                .into(holder.headPortrait);

        holder.tvDeacrbe.setText(list.get(position).getContent());
        holder.ratingBar.setCountNum(5);
        holder.ratingBar.setCountSelected(list.get(position).getServiceLevel());
        holder.tvPhone.setText(list.get(position).getUserName());
        holder.imgRecyclerView.setLayoutManager(new GridLayoutManager(holder.imgRecyclerView.getContext(),4));
        holder.imgRecyclerView.setAdapter(new MyImgAdapter(list.get(position).getImgs()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReViewHolder extends RecyclerView.ViewHolder{

        private final RecyclerView imgRecyclerView;
        private final CircleImageView headPortrait;
        private final TextView tvPhone;
        private final XLHRatingBar ratingBar;
        private final TextView tvDeacrbe;

        public ReViewHolder(View itemView) {
            super(itemView);
            imgRecyclerView = (RecyclerView) itemView.findViewById(R.id.img_recycle_view);
            headPortrait = (CircleImageView) itemView.findViewById(R.id.head_portrait);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            tvDeacrbe = (TextView) itemView.findViewById(R.id.tv_describe);
            ratingBar = (XLHRatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }

    private class MyImgAdapter extends RecyclerView.Adapter<MyImgAdapter.MyImgHolder> {
        private final List<EvaluationsResult.Data.Rows.Imgs> imgList;

        public MyImgAdapter(List<EvaluationsResult.Data.Rows.Imgs> imgs) {
            this.imgList = imgs;
        }

        @Override
        public MyImgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_holder, parent, false);
            return new MyImgHolder(view);
        }

        @Override
        public void onBindViewHolder(MyImgHolder holder, int position) {
            Glide.with(MyApplication.getContext())
                    .load(Constant.IMG_URL + imgList.get(position).getUrl())
                    .error(R.drawable.err)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return imgList.size();
        }

        public class MyImgHolder extends RecyclerView.ViewHolder{

            private final ImageView imageView;

            public MyImgHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.img);
            }
        }
    }
}
