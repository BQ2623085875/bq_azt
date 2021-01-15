package com.azhyun.massxj.adapter.azt.localityadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.ProductInfoImagesActivity;
import com.azhyun.massxj.bean.aizhongtian.GongQiuBean;
import com.azhyun.massxj.bean.aizhongtian.MallShangPinBean;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.utils.Constant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2020/7/28.
 */

public class LocalityAdapter extends RecyclerView.Adapter<LocalityAdapter.Holder> {
    protected final String TAG = "LocalityAdapter";

    private List<GongQiuBean.DataBeanX.RowsBean> list;
    private Context context;

    public LocalityAdapter(Context context, List<GongQiuBean.DataBeanX.RowsBean> list) {
        this.list = list;
        this.context = context;
    }

    public void addMoreData(List<GongQiuBean.DataBeanX.RowsBean> data) {
        if (data != null) {
            list.addAll(list.size(), data);
            notifyDataSetChanged();
        }
    }


    @Override
    public LocalityAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.locality_item, null));
    }

    @Override
    public void onBindViewHolder(LocalityAdapter.Holder holder, final int position) {
        final GongQiuBean.DataBeanX.RowsBean rowsBean = list.get(position);

        if (rowsBean.getType() == 1) {
            holder.loca_item_gong_img.setVisibility(ViewGroup.VISIBLE);
            holder.loca_item_qiu_img.setVisibility(ViewGroup.GONE);
        } else if (rowsBean.getType() == 2) {
            holder.loca_item_gong_img.setVisibility(ViewGroup.GONE);
            holder.loca_item_qiu_img.setVisibility(ViewGroup.VISIBLE);
        }
        Glide.with(context).load(Constant.IMG_URL + rowsBean.getDefaultImg()).crossFade().placeholder(R.drawable.me_hard_danwei).error(R.drawable.me_hard_danwei).into(holder.loca_item_img);
        holder.loca_item_name.setText(rowsBean.getTitle());
        holder.loca_item_title.setText(rowsBean.getDescriptionContent());
        holder.loca_item_price.setText(rowsBean.getPrice() + "");
        holder.loca_item_danwei.setText(rowsBean.getNorms());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkInterface(rowsBean, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final ImageView loca_item_gong_img;
        private final ImageView loca_item_qiu_img;
        private final TextView loca_item_name;
        private final TextView loca_item_title;
        private final TextView loca_item_price;
        private final TextView loca_item_danwei;
        private final TextView loca_item_tiem;
        private final ImageView loca_item_img;

        public Holder(View itemView) {
            super(itemView);
            loca_item_gong_img = itemView.findViewById(R.id.loca_item_gong_img);
            loca_item_qiu_img = itemView.findViewById(R.id.loca_item_qiu_img);
            loca_item_name = itemView.findViewById(R.id.loca_item_name);
            loca_item_title = itemView.findViewById(R.id.loca_item_title);
            loca_item_price = itemView.findViewById(R.id.loca_item_price);
            loca_item_danwei = itemView.findViewById(R.id.loca_item_danwei);
            loca_item_tiem = itemView.findViewById(R.id.loca_item_tiem);
            loca_item_img = itemView.findViewById(R.id.loca_item_img);
        }
    }

    public interface OnInterface {
        void OnCilkInterface(GongQiuBean.DataBeanX.RowsBean rowsBean, int position);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
