package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.SupplyListResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.DateUtils;

import java.util.List;

/**
 * Created by tl on 2018/8/16.
 */

public class BuyFragmentAdapter extends RecyclerView.Adapter<BuyFragmentAdapter.BuyFragmentHolder> {
    private final List<SupplyListResult.Data.Rows> list;

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public BuyFragmentAdapter(List<SupplyListResult.Data.Rows> list) {
        this.list = list;
    }

    public void getOnClick(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public BuyFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy_fragment_recyclerview, parent, false);

        return new BuyFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(BuyFragmentHolder holder, final int position) {

        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDescribe.setText(list.get(position).getDescriptionContent());
        holder.tvNumber.setText(list.get(position).getNum()+"公斤");
        holder.tvTime.setText(DateUtils.convertTimeToFormat(Long.parseLong(list.get(position).getAddTime())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BuyFragmentHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvDescribe;
        private final TextView tvNumber;
        private final TextView tvTime;

        public BuyFragmentHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);


        }
    }
}
