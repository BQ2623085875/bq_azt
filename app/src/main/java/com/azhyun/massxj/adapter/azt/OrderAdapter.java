package com.azhyun.massxj.adapter.azt;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2020/7/2.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Holder> {

    protected final String TAG = "OrderAdapter";
    private Context mContext;
    private ArrayList<OrdserBean.DataBeanX> list;

    public OrderAdapter(Context context, ArrayList<OrdserBean.DataBeanX> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.order_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final OrdserBean.DataBeanX dataBeanX = list.get(position);

        //判断状态
        if (dataBeanX.getStatus() == 1) {
            holder.orsder_item_zhuangtai.setText("待付款");
            holder.cloer_tv.setVisibility(View.VISIBLE);
            holder.zhifu_tv.setVisibility(View.VISIBLE);
            holder.yes_shou_tv.setVisibility(View.GONE);
        } else if (dataBeanX.getStatus() == 2) {
            holder.orsder_item_zhuangtai.setText("待发货");
            holder.cloer_tv.setVisibility(View.GONE);
            holder.zhifu_tv.setVisibility(View.GONE);
            holder.yes_shou_tv.setVisibility(View.GONE);
        } else if (dataBeanX.getStatus() == 3) {
            holder.orsder_item_zhuangtai.setText("待收货");
            holder.cloer_tv.setVisibility(View.GONE);
            holder.zhifu_tv.setVisibility(View.GONE);
            holder.yes_shou_tv.setVisibility(View.VISIBLE);
        } else if (dataBeanX.getStatus() == 4) {
            holder.orsder_item_zhuangtai.setText("已完成");
            holder.cloer_tv.setVisibility(View.GONE);
            holder.zhifu_tv.setVisibility(View.GONE);
            holder.yes_shou_tv.setVisibility(View.GONE);
        } else if (dataBeanX.getStatus() == -1) {
            holder.orsder_item_zhuangtai.setText("已取消");
            holder.cloer_tv.setVisibility(View.GONE);
            holder.zhifu_tv.setVisibility(View.GONE);
            holder.yes_shou_tv.setVisibility(View.GONE);
        }else {
            holder.orsder_item_zhuangtai.setText("状态不合法");
            holder.cloer_tv.setVisibility(View.GONE);
            holder.zhifu_tv.setVisibility(View.GONE);
            holder.yes_shou_tv.setVisibility(View.GONE);
        }
        //服务商
        holder.orsder_item_nuitname.setText(dataBeanX.getSaleName());
        //总件数
        holder.orsder_item_jianshu.setText("共计" + dataBeanX.getItemQty() + "件商品");
        //合计
        holder.orsder_item_heji.setText("¥" + new DecimalFormat("#0.00").format(dataBeanX.getRetailPayAmount()));

        List<OrdserBean.DataBeanX.ItemsBean> items = dataBeanX.getItems();
        if (items!=null && items.size()!=0) {
            NewOrsderItemAdapter newOrsderItemAdapter = new NewOrsderItemAdapter(mContext, items);
            holder.orsder_item_rv.setAdapter(newOrsderItemAdapter);
            holder.orsder_item_rv.setLayoutManager(new LinearLayoutManager(mContext));
            //0整个条目  1取消订单  2去支付  3确认收货
            newOrsderItemAdapter.setOnInterface(new NewOrsderItemAdapter.OnInterface() {
                @Override
                public void OnCilkInterface() {
                    onInterface.OnCilkInterface(dataBeanX, position,0);
                }
            });
        }


        //0整个条目  1取消订单  2去支付  3确认收货
        holder.cloer_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkInterface(dataBeanX,position,1);
            }
        });
        //0整个条目  1取消订单  2去支付  3确认收货
        holder.zhifu_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkInterface(dataBeanX,position,2);
            }
        });
        //0整个条目  1取消订单  2去支付  3确认收货
        holder.yes_shou_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkInterface(dataBeanX,position,3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView orsder_item_nuitname;
        private final TextView orsder_item_jianshu;
        private final TextView orsder_item_zhuangtai;
        private final RecyclerView orsder_item_rv;
        private final TextView orsder_item_heji;
        private final TextView cloer_tv;
        private final TextView yes_shou_tv;
        private final TextView zhifu_tv;

        public Holder(View itemView) {
            super(itemView);
            orsder_item_nuitname = itemView.findViewById(R.id.orsder_item_nuitname);
            orsder_item_jianshu = itemView.findViewById(R.id.orsder_item_jianshu);
            orsder_item_zhuangtai = itemView.findViewById(R.id.orsder_item_zhuangtai);
            orsder_item_rv = itemView.findViewById(R.id.orsder_item_rv);
            orsder_item_heji = itemView.findViewById(R.id.orsder_item_heji);
            cloer_tv = itemView.findViewById(R.id.cloer_tv);
            yes_shou_tv = itemView.findViewById(R.id.yes_shou_tv);
            zhifu_tv = itemView.findViewById(R.id.zhifu_tv);
        }
    }

    public interface OnInterface {
        void OnCilkInterface(OrdserBean.DataBeanX dataBeanX, int position,int type);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
