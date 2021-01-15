package com.azhyun.massxj.adapter.azt;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.aizhongtian.NbLieBiaoBean;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateTimeUtil;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dell on 2020/7/2.
 */

public class NbOrderAdapter extends RecyclerView.Adapter<NbOrderAdapter.Holder> {

    protected final String TAG = "OrderAdapter";
    private Context mContext;
    private ArrayList<NbLieBiaoBean.DataBeanX.RowsBean> list;

    public NbOrderAdapter(Context context, ArrayList<NbLieBiaoBean.DataBeanX.RowsBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.nb_order_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final NbLieBiaoBean.DataBeanX.RowsBean rowsBean = list.get(position);
        NbLieBiaoBean.DataBeanX.RowsBean.InsuranceItemBean insuranceItem = rowsBean.getInsuranceItem();

        //判断状态
        if (rowsBean.getStatus() == 1) {
            holder.shengxiao.setVisibility(ViewGroup.GONE);
            holder.orsder_item_zhuangtai.setText("待处理");
        } else if (rowsBean.getStatus() == 2) {
            holder.orsder_item_zhuangtai.setText("已生效");
            holder.shengxiao.setVisibility(ViewGroup.VISIBLE);
//            holder.shengxiao.setText("生效日期:" + rowsBean.getApproveTime() + "至" + rowsBean.getDeliveryTime());
            holder.shengxiao.setText("生效日期: " + DateTimeUtil.getDateToString(Long.parseLong(rowsBean.getApproveTime()), "yyyy-MM-dd") + " 至 " + DateTimeUtil.getDateToString(Long.parseLong(rowsBean.getDeliveryTime()), "yyyy-MM-dd"));
        } else if (rowsBean.getStatus() == 3) {
            holder.orsder_item_zhuangtai.setText("已过期");
            holder.shengxiao.setVisibility(ViewGroup.VISIBLE);
            holder.shengxiao.setText("生效日期: " + DateTimeUtil.getDateToString(Long.parseLong(rowsBean.getApproveTime()), "yyyy-MM-dd") + " 至 " + DateTimeUtil.getDateToString(Long.parseLong(rowsBean.getDeliveryTime()), "yyyy-MM-dd"));
        } else if (rowsBean.getStatus() == 4) {
            holder.shengxiao.setVisibility(ViewGroup.GONE);
            holder.orsder_item_zhuangtai.setText("已超时");
        } else if (rowsBean.getStatus() == -1) {
            holder.shengxiao.setVisibility(ViewGroup.GONE);
            holder.orsder_item_zhuangtai.setText("已取消");
        } else {
            holder.shengxiao.setVisibility(ViewGroup.GONE);
            holder.orsder_item_zhuangtai.setText("状态不合法");
        }
        //服务商
        holder.orsder_item_nuitname.setText(rowsBean.getSaleName());
        //合计
        holder.orsder_item_heji.setText("¥" + new DecimalFormat("#0.00").format(rowsBean.getAmount()));

        Glide.with(mContext).load(Constant.IMG_URL + insuranceItem.getImg())
                .placeholder(R.drawable.me_hard_danwei)
                .error(R.drawable.me_hard_danwei)
                .into(holder.new_nb_img);
        holder.new_nb_name.setText(insuranceItem.getName());
        holder.new_nb_price.setText("¥" + new DecimalFormat("#0.00").format(insuranceItem.getPrice()));
        holder.new_nb_jine.setText(insuranceItem.getAmount());
        holder.new_nb_zeren.setText(insuranceItem.getResponsibility());
        holder.new_nb_mushu.setText(rowsBean.getItemQty() + "");

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

        private final TextView orsder_item_nuitname;
        private final TextView orsder_item_zhuangtai;
        private final TextView orsder_item_heji;
        private final ImageView new_nb_img;
        private final TextView new_nb_name;
        private final TextView new_nb_price;
        private final TextView new_nb_jine;
        private final TextView new_nb_zeren;
        private final TextView new_nb_mushu;
        private final TextView shengxiao;

        public Holder(View itemView) {
            super(itemView);
            orsder_item_nuitname = itemView.findViewById(R.id.orsder_item_nuitname);
            orsder_item_zhuangtai = itemView.findViewById(R.id.orsder_item_zhuangtai);
            orsder_item_heji = itemView.findViewById(R.id.orsder_item_heji);
            new_nb_img = itemView.findViewById(R.id.new_nb_img);
            new_nb_name = itemView.findViewById(R.id.new_nb_name);
            new_nb_price = itemView.findViewById(R.id.new_nb_price);
            new_nb_jine = itemView.findViewById(R.id.new_nb_jine);
            new_nb_zeren = itemView.findViewById(R.id.new_nb_zeren);
            new_nb_mushu = itemView.findViewById(R.id.new_nb_mushu);
            shengxiao = itemView.findViewById(R.id.shengxiao);
        }
    }

    public interface OnInterface {
        void OnCilkInterface(NbLieBiaoBean.DataBeanX.RowsBean rowsBean, int position);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
