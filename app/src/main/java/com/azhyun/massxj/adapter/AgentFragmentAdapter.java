package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerListResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by tl on 2018/8/22.
 */

public class AgentFragmentAdapter extends RecyclerView.Adapter<AgentFragmentAdapter.AgentFragmentHolder> {

    private final List<ManagerListResult.Data.Rows> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public AgentFragmentAdapter(List<ManagerListResult.Data.Rows> rowsList) {
        this.list = rowsList;
    }
    public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public AgentFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agent_fragment_recyclerview, parent, false);

        return new AgentFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(AgentFragmentHolder holder, final int position) {

        holder.tvRole.setText(getRole(list.get(position).getApplyRole()));
        holder.tvPhone.setText("联系方式:"+list.get(position).getMobilePhone());
        holder.tvName.setText("姓名:"+ list.get(position).getName());
        holder.tvArea.setText("土地面积:"+ list.get(position).getArea()+"亩");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(v,position);
            }
        });
    }

    private String getRole(int applyRole) {
        //1.村级理事长 2.乡级联络员 3.服务商
        switch (applyRole){
            case 1:
                return "村级理事长";
             case 2:
                return "乡级理事长";
             case 3:
                return "服务商";
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AgentFragmentHolder extends RecyclerView.ViewHolder{

        private final TextView tvRole;
        private final TextView tvPhone;
        private final TextView tvName;
        private final TextView tvArea;

        public AgentFragmentHolder(View itemView) {
            super(itemView);
            tvRole = (TextView) itemView.findViewById(R.id.tv_role);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvArea = (TextView) itemView.findViewById(R.id.tv_area);
        }
    }
}
