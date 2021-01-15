package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.LandListResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.StringUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by tl on 2018/8/22.
 */

public class LandHostingAdapter extends RecyclerView.Adapter<LandHostingAdapter.LandHostingHolder> {

    private final List<LandListResult.Data.Rows> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public LandHostingAdapter(List<LandListResult.Data.Rows> rowsList) {
        this.list = rowsList;
    }

    public void getOnClick(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
    @Override
    public LandHostingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_land_hosting_recyclerview, parent, false);
        return new LandHostingHolder(view);
    }

    @Override
    public void onBindViewHolder(LandHostingHolder holder, final int position) {

        Glide.with(MyApplication.getContext())
                .load(Constant.IMG_URL + list.get(position).getDefaultImg())
                .error(R.drawable.err)
                .into(holder.img);

        holder.tvTitle.setText(list.get(position).getTitle()+StringUtils.stringDouble(list.get(position).getArea())+"亩");
        holder.tvIntroduce.setText("托管人:"+list.get(position).getName());
        holder.tvArea.setText("联系方式:"+ list.get(position).getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LandHostingHolder extends RecyclerView.ViewHolder{

        private final TextView tvTitle;
        private final TextView tvIntroduce;
        private final TextView tvArea;
        private final ImageView img;

        public LandHostingHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvIntroduce = (TextView) itemView.findViewById(R.id.tv_introduce);
            tvArea = (TextView) itemView.findViewById(R.id.tv_area);
        }
    }
}
