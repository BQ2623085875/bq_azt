package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.EvaluationsResult;
import com.azhyun.massxj.bean.ReviewType;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by tl on 2018/8/23.
 */

public class ReViewTabAdapter extends RecyclerView.Adapter<ReViewTabAdapter.ReViewTabHolder> {
    private final List<ReviewType> list;
    private final EvaluationsResult.Data data;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private int id = 0;

    public ReViewTabAdapter(List<ReviewType> tabList, EvaluationsResult.Data data) {
        this.list = tabList;
        this.data = data;
    }

    public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public ReViewTabHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_fragment_tab_recyclerview, null, false);
        return new ReViewTabHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReViewTabHolder holder, final int position) {

        if (list.get(position).getName().equals("全部")){
            holder.tvTab.setText(list.get(position).getName()+"("+(data.getLevel1()+data.getLevel2()+data.getLevel3())+")");
        }else  if (list.get(position).getName().equals("好评")){
            holder.tvTab.setText(list.get(position).getName()+"("+data.getLevel1()+")");
        }else  if (list.get(position).getName().equals("中评")){
            holder.tvTab.setText(list.get(position).getName()+"("+data.getLevel2()+")");
        }else  if (list.get(position).getName().equals("差评")){
            holder.tvTab.setText(list.get(position).getName()+"("+data.getLevel3()+")");
        }else  if (list.get(position).getName().equals("有图")){
            holder.tvTab.setText(list.get(position).getName()+"("+data.getHaveImg()+")");
        }

            if (list.get(position).getSelected()){
                holder.tvTab.setBackgroundResource(R.drawable.recview_item_backgroud_selected);
            }else {
                holder.tvTab.setBackgroundResource(R.drawable.recview_item_backgroud);
            }


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i =0; i < list.size();i++){
                    if (i == position){
                        list.get(position).setSelected(true);
                    }else {
                        list.get(i).setSelected(false);
                    }
                }
                onRecyclerViewItemClickListener.onItemClick(v,position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReViewTabHolder extends RecyclerView.ViewHolder{

        private final TextView tvTab;

        public ReViewTabHolder(View itemView) {
          super(itemView);
          tvTab = (TextView) itemView.findViewById(R.id.tv_tab);
      }
  }
}
