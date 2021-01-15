package com.azhyun.massxj.adapter.azt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.locailtyactivity.LocalityLiveActivity;
import com.azhyun.massxj.bean.aizhongtian.GongQiuBean;
import com.azhyun.massxj.bean.aizhongtian.GongXuFenLeiBean;
import com.azhyun.massxj.utils.ToastUtils;

import java.util.List;

/**
 * Created by wkk on 2020/8/13.
 */

public class FenLeiNewAdapter extends RecyclerView.Adapter<FenLeiNewAdapter.Holder> {
    private List<GongXuFenLeiBean.DataBeanX.ChildBean> list;
    private Context context;

    public FenLeiNewAdapter(List<GongXuFenLeiBean.DataBeanX.ChildBean> child, Context context) {
        this.list = child;
        this.context = context;
    }

    @Override
    public FenLeiNewAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.fenlei_new_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final FenLeiNewAdapter.Holder holder, final int position) {
        final GongXuFenLeiBean.DataBeanX.ChildBean childBean = list.get(position);

        holder.name_tv.setText(childBean.getName());


        if (childBean.getP() == position) {
            holder.name_tv.setBackgroundColor(context.getResources().getColor(R.color.fenlei_yes));
            childBean.setP(-1);
        } else {
            holder.name_tv.setBackgroundColor(context.getResources().getColor(R.color.fenlei_no));
        }

        holder.name_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childBean.setP(position);
                onInterface.OnCilkInterface(childBean, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView name_tv;

        public Holder(View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.name_tv);

        }
    }

    public static interface OnInterface {
        void OnCilkInterface(GongXuFenLeiBean.DataBeanX.ChildBean childBean, int position);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
