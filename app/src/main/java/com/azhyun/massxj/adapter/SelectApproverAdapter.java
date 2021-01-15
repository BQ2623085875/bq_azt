package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.AuditUsersResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by tl on 2019/1/17.
 */

public class SelectApproverAdapter extends RecyclerView.Adapter<SelectApproverAdapter.SelectApproverHolder>{

    private final List<AuditUsersResult.Data> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public SelectApproverAdapter(List<AuditUsersResult.Data> data) {
        this.list = data;
    }

    public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public SelectApproverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_approver_recyclerview, parent, false);
        return new SelectApproverHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectApproverHolder holder, int position) {

        holder.tvName.setText(list.get(position).getName());
        holder.tvArea.setText(list.get(position).getFullName());
        onRecyclerViewItemClickListener.onItemClick(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  SelectApproverHolder extends RecyclerView.ViewHolder{

        private final CheckBox checkbox;
        private final TextView tvName;
        private final TextView tvArea;

        public SelectApproverHolder(View itemView) {
            super(itemView);

            checkbox = (CheckBox)itemView.findViewById(R.id.checkbox);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvArea = (TextView)itemView.findViewById(R.id.tv_area);
        }
    }
}
