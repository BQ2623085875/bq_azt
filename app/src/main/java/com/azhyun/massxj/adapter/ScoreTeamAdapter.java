package com.azhyun.massxj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.CategoryResult;
import com.azhyun.massxj.bean.aizhongtian.GongXuFenLeiBean;

import java.util.List;

/**
 * Created by tl on 2018/8/27.
 */

public class ScoreTeamAdapter extends RecyclerView.Adapter<ScoreTeamAdapter.ScoreTramHolder>{

    private final List<GongXuFenLeiBean.DataBeanX.ChildBean> list;

    public ScoreTeamAdapter(List<GongXuFenLeiBean.DataBeanX.ChildBean> rowsList) {
        this.list = rowsList;
    }

    @Override
    public ScoreTramHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_tram, parent, false);
        return new ScoreTramHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreTramHolder holder, int position) {
        holder.text.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScoreTramHolder extends RecyclerView.ViewHolder{

        private final TextView text;

        public ScoreTramHolder(View itemView) {
            super(itemView);
             text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
