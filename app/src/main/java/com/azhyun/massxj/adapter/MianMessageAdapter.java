package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.MyinfoListResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tl on 2018/9/10.
 */

public class MianMessageAdapter extends RecyclerView.Adapter<MianMessageAdapter.MyHolder>{
    private List<MyinfoListResult.Data.Rows> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public MianMessageAdapter(List<MyinfoListResult.Data.Rows> rowsList) {
        this.list = rowsList;
    }

    public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mian_message, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        if (list.get(position).getStatus() == 1){//未读
            holder.circleImg.setVisibility(View.VISIBLE);
        }else {
            holder.circleImg.setVisibility(View.GONE);
        }
        if (list.get(position).getType() == 1){
            holder.img.setImageResource(R.drawable.my_yuyuedan);
        }else if (list.get(position).getType() == 2){
            holder.img.setImageResource(R.drawable.my_tudituoguan);
        }else if (list.get(position).getType() == 3){
            holder.img.setImageResource(R.drawable.my_jingjiren);
        }
        holder.tvCodeName.setText(getType(list.get(position).getType()));
        holder.tvDescribe.setText(list.get(position).getContent());
        String addPerson = list.get(position).getAddPerson();
        if (!TextUtils.isEmpty(addPerson) && addPerson != ""){
            if (list.get(position).getType() == 1){
                holder.tvName.setText(list.get(position).getAddPerson());
            }else {
                holder.tvName.setText(list.get(position).getAddPerson());
            }
        }else {

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(v,position);
            }
        });
    }
    public void addData(List<MyinfoListResult.Data.Rows> rowsList){

        notifyDataSetChanged();
    }

    private String getType(int type) {
        //1.农事服务预约单 2.土地托管申请 3.经纪人申请
        switch (type){
            case 1:
                return "农事服务预约单";
             case 2:
                return "土地托管申请";
             case 3:
                return "经纪人申请";

        }
        return "";
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        private final TextView tvCodeName;
        private final TextView tvDescribe;
        private final TextView tvName;
        private final ImageView img;
        private final CircleImageView circleImg;

        public MyHolder(View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            circleImg = (CircleImageView)itemView.findViewById(R.id.circle_img);
            tvCodeName = (TextView)itemView.findViewById(R.id.tv_code_name);
            tvDescribe = (TextView)itemView.findViewById(R.id.tv_describe);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
        }
    }
}
