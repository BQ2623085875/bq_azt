package com.azhyun.massxj.adapter;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.HomeArticleShowResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateTimeUtil;
import com.azhyun.massxj.utils.RoundBackgroundColorSpan;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by tl on 2019/1/8.
 */

public class HomeHeadLineAdapter extends RecyclerView.Adapter<HomeHeadLineAdapter.HomeHeadLineHolder>{

    private List<HomeArticleShowResult.Data.Rows> list;
    private final FragmentActivity content;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public HomeHeadLineAdapter(List<HomeArticleShowResult.Data.Rows> list, FragmentActivity activity) {
        this.list = list;
        this.content = activity;
    }

    public void setNewData(List<HomeArticleShowResult.Data.Rows> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public HomeHeadLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item__home_headline_recyclerview, parent, false);

        return new HomeHeadLineHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeHeadLineHolder holder, final int position) {
        String string = "";
        if (list.get(position).getIsTop() == 1){
            string = "推荐"+list.get(position).getTitle();
            SpannableStringBuilder style=new SpannableStringBuilder(string);

            style.setSpan(new RoundBackgroundColorSpan(Color.parseColor("#6CA323"),Color.parseColor("#6CA323")), 0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvTitle.setText(style);
        }else {
            string = list.get(position).getTitle();
            holder.tvTitle.setText(string);
        }


        holder.tvContent.setText(list.get(position).getIntroduce());
        holder.tvType.setText(list.get(position).getCategoryName());
        holder.tvTime.setText(DateTimeUtil.getDateToString(list.get(position).getPublishTime(),"yyyy-MM-dd"));

        if (list.get(position).getDefaultImg() == null || list.get(position).getDefaultImg().isEmpty() ){
            holder.image.setVisibility(View.GONE);
        }else {
            holder.image.setVisibility(View.VISIBLE);
            Glide.with(content).load(Constant.IMG_URL + list.get(position).getDefaultImg()).into(holder.image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerViewItemClickListener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeHeadLineHolder extends RecyclerView.ViewHolder{

        private final TextView tvTitle;
        private final TextView tvContent;
        private final TextView tvType;
        private final TextView tvTime;
        private final ImageView image;

        public HomeHeadLineHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
            tvType = (TextView)itemView.findViewById(R.id.tv_type);
            tvTime = (TextView)itemView.findViewById(R.id.tv_time);
            image = (ImageView)itemView.findViewById(R.id.image);

        }
    }
}
