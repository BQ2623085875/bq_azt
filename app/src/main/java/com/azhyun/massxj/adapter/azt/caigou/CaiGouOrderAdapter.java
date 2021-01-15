package com.azhyun.massxj.adapter.azt.caigou;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.azt.NewOrsderItemAdapter;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by dell on 2020/7/2.
 */

public class CaiGouOrderAdapter extends RecyclerView.Adapter<CaiGouOrderAdapter.Holder> {

    protected final String TAG = "OrderAdapter";
    private Context mContext;
    private ArrayList<OrdserBean.DataBeanX> list;

    public CaiGouOrderAdapter(Context context, ArrayList<OrdserBean.DataBeanX> list) {
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
            holder.orsder_item_zhuangtai.setText("待处理");
        } else if (dataBeanX.getStatus() == 2) {
            holder.orsder_item_zhuangtai.setText("已完成");
        } else if (dataBeanX.getStatus() == -1) {
            holder.orsder_item_zhuangtai.setText("已取消");
        }
        //服务商
        holder.orsder_item_nuitname.setText(dataBeanX.getSaleName());
        //总件数
        holder.orsder_item_jianshu.setText("共计" + dataBeanX.getItemQty() + "件商品");
        //合计
        holder.orsder_item_heji.setText("¥" + new DecimalFormat("#0.00").format(dataBeanX.getRetailPayAmount()));

        NewOrsderItemAdapter newOrsderItemAdapter = new NewOrsderItemAdapter(mContext, dataBeanX.getItems());
        holder.orsder_item_rv.setAdapter(newOrsderItemAdapter);
        holder.orsder_item_rv.setLayoutManager(new LinearLayoutManager(mContext));
        newOrsderItemAdapter.setOnInterface(new NewOrsderItemAdapter.OnInterface() {
            @Override
            public void OnCilkInterface() {
                onInterface.OnCilkInterface(dataBeanX, position);
            }
        });


        holder.rrrr.setVisibility(View.GONE);

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
        private final RelativeLayout rrrr;

        public Holder(View itemView) {
            super(itemView);
            orsder_item_nuitname = itemView.findViewById(R.id.orsder_item_nuitname);
            orsder_item_jianshu = itemView.findViewById(R.id.orsder_item_jianshu);
            orsder_item_zhuangtai = itemView.findViewById(R.id.orsder_item_zhuangtai);
            orsder_item_rv = itemView.findViewById(R.id.orsder_item_rv);
            orsder_item_heji = itemView.findViewById(R.id.orsder_item_heji);
            rrrr = itemView.findViewById(R.id.rrrr);
        }
    }

    public interface OnInterface {
        void OnCilkInterface(OrdserBean.DataBeanX dataBeanX, int position);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
