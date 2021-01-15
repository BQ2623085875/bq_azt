package com.azhyun.massxj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ServiceListResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.StringUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by tl on 2018/8/15.
 */

public class FarmingServiceFragmentAdapter extends RecyclerView.Adapter<FarmingServiceFragmentAdapter.FarmingServiceFragmentHolder> {
    private final List<ServiceListResult.Data.Rows> list;
    private final Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public FarmingServiceFragmentAdapter(Context context, List<ServiceListResult.Data.Rows> list) {
        this.context = context;
        this.list = list;
    }
public void getClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
}
    @Override
    public FarmingServiceFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_farming_service_recyclerview, parent, false);

        return new FarmingServiceFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(FarmingServiceFragmentHolder holder, final int position) {
        Glide.with(context)
                .load(Constant.IMG_URL+list.get(position).getDefaultImg())
                .error(R.drawable.err)
                .into(holder.img);

        holder.tvName.setText(list.get(position).getName());
//        holder.tvIntroduction.setText();
        holder.tvDesvribe.setText(list.get(position).getIntroduce());
        holder.tvprice.setText("¥ "+StringUtils.stringDouble(list.get(position).getPrice())+"元/亩");

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

    class  FarmingServiceFragmentHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView tvName;
        private final TextView tvDesvribe;
        private final TextView tvprice;
        private final TextView tvIntroduction;

        public FarmingServiceFragmentHolder(View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvDesvribe = (TextView)itemView.findViewById(R.id.tv_describe);
            tvprice = (TextView)itemView.findViewById(R.id.tv_price);
            tvIntroduction = (TextView)itemView.findViewById(R.id.tv_introduction);

        }
    }
}
