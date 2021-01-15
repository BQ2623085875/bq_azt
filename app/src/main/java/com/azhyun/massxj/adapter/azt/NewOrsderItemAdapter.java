package com.azhyun.massxj.adapter.azt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.utils.Constant;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by dell on 2020/7/2.
 */

public class NewOrsderItemAdapter extends RecyclerView.Adapter<NewOrsderItemAdapter.Holder> {
    private Context mContext;
    private List<OrdserBean.DataBeanX.ItemsBean> list;

    public NewOrsderItemAdapter(Context context, List<OrdserBean.DataBeanX.ItemsBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public NewOrsderItemAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.newordseritem_item, null));
    }

    @Override
    public void onBindViewHolder(NewOrsderItemAdapter.Holder holder, int position) {
        OrdserBean.DataBeanX.ItemsBean itemsBean = list.get(position);

        //图片
        Glide.with(mContext).load(Constant.IMG_URL + itemsBean.getImg())
                .placeholder(R.drawable.me_hard_danwei)
                .error(R.drawable.me_hard_danwei)
                .into(holder.orderitem_img);
        //商品名字
        holder.orderitem_titles.setText(itemsBean.getBrand() + itemsBean.getItemName());
        //商品价格
        holder.orderitem_jiage.setText("¥" + new DecimalFormat("#0.00").format(itemsBean.getPrice()));
        //商品数量
        holder.orderitem_shuliang.setText("x" + itemsBean.getQty() + "");
        //商品规格
        holder.orderitem_guige.setText(itemsBean.getNorm() + "/" + itemsBean.getUnits());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkInterface();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final ImageView orderitem_img;
        private final TextView orderitem_jiage;
        private final TextView orderitem_shuliang;
        private final TextView orderitem_guige;
        private final TextView orderitem_titles;
        private final LinearLayout newordseritem_item_ll;

        public Holder(View itemView) {
            super(itemView);
            orderitem_img = itemView.findViewById(R.id.orderitem_img);
            orderitem_jiage = itemView.findViewById(R.id.orderitem_jiage);
            orderitem_shuliang = itemView.findViewById(R.id.orderitem_shuliang);
            orderitem_guige = itemView.findViewById(R.id.orderitem_guige);
            orderitem_titles = itemView.findViewById(R.id.orderitem_titles);
            newordseritem_item_ll = itemView.findViewById(R.id.newordseritem_item_ll);
        }
    }


    public interface OnInterface {
        void OnCilkInterface();
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
