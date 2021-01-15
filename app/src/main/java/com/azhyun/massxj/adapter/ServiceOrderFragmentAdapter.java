package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.MyServiceListResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.StringUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by tl on 2018/8/16.
 */

public class ServiceOrderFragmentAdapter extends RecyclerView.Adapter<ServiceOrderFragmentAdapter.SupplyFragmentHolder> {
    private final List<MyServiceListResult.Data.Rows> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public ServiceOrderFragmentAdapter(List<MyServiceListResult.Data.Rows> list) {
        this.list = list;
    }
    public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public SupplyFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supply_fragment_recyclerview, parent, false);
        return new SupplyFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(SupplyFragmentHolder holder, final int position) {
        Glide.with(MyApplication.getContext())
                .load(Constant.IMG_URL+list.get(position).getDefaultImg())
                .error(R.drawable.err)
                .into(holder.img);
    holder.title.setText(list.get(position).getServiceName());
    holder.tvIntroduction.setText(list.get(position).getIntroduce());
    holder.price.setText("¥"+StringUtils.stringDouble(list.get(position).getServicePrice()));
   //holder.time.setText(DateUtils.convertTimeToFormat(Long.parseLong(list.get(position).getServiceTime())));
        if (list.get(position).getCode() != null){

            holder.tvCodeId.setText("预约单号:"+list.get(position).getCode());
        }else {
            holder.tvCodeId.setText("预约单号:");
        }
   holder.tvNum.setText("数量"+list.get(position).getNum());
   holder.tvOrderPrice.setText("金额:  ¥"+StringUtils.stringDouble(list.get(position).getServicePrice()*list.get(position).getNum()));





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

    public class SupplyFragmentHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView title;
        private final TextView price;
        private final TextView time;
        private final TextView tvIntroduction;
        private final TextView tvCodeId;
        private final TextView tvNum;
        private final TextView tvOrderPrice;

        public SupplyFragmentHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            time = (TextView) itemView.findViewById(R.id.time);
            tvIntroduction = (TextView) itemView.findViewById(R.id.tv_introduction);
            tvCodeId = (TextView) itemView.findViewById(R.id.tv_code_id);
            tvNum = (TextView) itemView.findViewById(R.id.tv_num);
            tvOrderPrice = (TextView) itemView.findViewById(R.id.tv_order_price);
        }
    }
}
