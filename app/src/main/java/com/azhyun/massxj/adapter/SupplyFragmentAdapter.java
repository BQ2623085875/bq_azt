package com.azhyun.massxj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.SupplyListResult;
import com.azhyun.massxj.bean.aizhongtian.GongQiuBean;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateUtils;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by tl on 2018/8/16.
 */

public class SupplyFragmentAdapter extends RecyclerView.Adapter<SupplyFragmentAdapter.SupplyFragmentHolder> {
    private final List<GongQiuBean.DataBeanX.RowsBean> list;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public SupplyFragmentAdapter(Context context, List<GongQiuBean.DataBeanX.RowsBean> list) {
        this.list = list;
        this.context = context;
//        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void addMoreData(List<GongQiuBean.DataBeanX.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }

    public void getOnClick(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public SupplyFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supply_fragment_recyclerview, parent, false);
        return new SupplyFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(final SupplyFragmentHolder holder, final int position) {
        final GongQiuBean.DataBeanX.RowsBean rowsBean = list.get(position);

        if (rowsBean.getType() == 1) {
            holder.item_gong_img.setVisibility(ViewGroup.VISIBLE);
            holder.item_qiu_img.setVisibility(ViewGroup.GONE);
        } else {
            holder.item_gong_img.setVisibility(ViewGroup.GONE);
            holder.item_qiu_img.setVisibility(ViewGroup.VISIBLE);
        }
        if (rowsBean.getPrice() > 0) {
            holder.item_price.setText(new DecimalFormat("#0.00").format(rowsBean.getPrice()));
        } else {
            holder.item_price.setText("价格待定");
        }
        if (rowsBean.getStatus() == 1) {
            holder.item_xiaxian.setBackgroundResource(R.drawable.shangpin_xiangqing_mai);
        } else {
            holder.item_xiaxian.setBackgroundResource(R.drawable.xiaxian_shape);

        }
        Glide.with(context).load(Constant.IMG_URL + rowsBean.getDefaultImg()).crossFade().placeholder(R.drawable.me_hard_danwei).error(R.drawable.me_hard_danwei).into(holder.item_img);
        holder.item_name.setText(rowsBean.getTitle());
        holder.item_title.setText(rowsBean.getDescriptionContent());
        holder.item_danwei.setText(rowsBean.getNorms());
        holder.item_tiem.setText(rowsBean.getAddtimes());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkInterface(rowsBean, position, 0);
            }
        });

        holder.item_xiaxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkInterface(rowsBean, position, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SupplyFragmentHolder extends RecyclerView.ViewHolder {

        private final ImageView item_img;
        private final ImageView item_gong_img;
        private final ImageView item_qiu_img;
        private final TextView item_name;
        private final TextView item_title;
        private final TextView item_price;
        private final TextView item_tiem;
        private final TextView item_xiaxian;
        private final TextView item_yixiaxian;
        private final TextView item_danwei;

        public SupplyFragmentHolder(View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_gong_img = itemView.findViewById(R.id.item_gong_img);
            item_qiu_img = itemView.findViewById(R.id.item_qiu_img);
            item_name = itemView.findViewById(R.id.item_name);
            item_title = itemView.findViewById(R.id.item_title);
            item_price = itemView.findViewById(R.id.item_price);
            item_tiem = itemView.findViewById(R.id.item_tiem);
            item_xiaxian = itemView.findViewById(R.id.item_xiaxian);
            item_yixiaxian = itemView.findViewById(R.id.item_yixiaxian);
            item_danwei = itemView.findViewById(R.id.item_danwei);

        }
    }

    public interface OnInterface {
        void OnCilkInterface(GongQiuBean.DataBeanX.RowsBean rowsBean, int position, int type);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }


}
