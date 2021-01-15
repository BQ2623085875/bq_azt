package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.MyManageListResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.StringUtils;

import java.util.List;

/**
 * Created by tl on 2018/9/8.
 */

public class ManagementAdapter extends RecyclerView.Adapter<ManagementAdapter.Managementholder>{

    private final List<MyManageListResult.Data.Rows> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public ManagementAdapter(List<MyManageListResult.Data.Rows> rowsList) {
        this.list = rowsList;
    }

    public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public Managementholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_management, parent, false);
        return new Managementholder(view);
    }

    @Override
    public void onBindViewHolder(Managementholder holder, final int position) {
        holder.tvRole.setText(list.get(position).getRegionName()+"理事长");
        holder.tvName.setText("姓名:"+list.get(position).getName());
        holder.tvPhone.setText("联系方式:"+list.get(position).getMobilePhone());
        holder.tvArea.setText("土地面积:"+ StringUtils.stringDouble(list.get(position).getArea())+"亩");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(v,position);
            }
        });
    }

    private String getRole(int applyRole) {
        //0.普通用户 1.村级理事长 2.乡级理事长 3.服务商 4.县级理事长 5.省级理事长
        switch (applyRole){
            case 0:
                return "普通用户";
              case 1:
                return "村级理事长";
              case 2:
                return "乡级理事长";
              case 3:
                return "服务商";
              case 4:
                return "县级理事长";
              case 5:
                return "省级理事长";


        }
        return "";
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Managementholder extends RecyclerView.ViewHolder{

        private final TextView tvRole;
        private final TextView tvName;
        private final TextView tvPhone;
        private final TextView tvArea;

        public Managementholder(View itemView) {
            super(itemView);
            tvRole = (TextView) itemView.findViewById(R.id.tv_role);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            tvArea = (TextView) itemView.findViewById(R.id.tv_area);
        }
    }
}
