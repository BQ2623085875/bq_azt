package com.azhyun.massxj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.DiaLogItemResult;

import java.util.List;

public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.MyViewHoulder>{
    public List<DiaLogItemResult.Data> list;
    public Context context;
    public OnItemClickListener onItemClickListener;
    public StyleAdapter(List<DiaLogItemResult.Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHoulder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.iten_text,viewGroup,false);
        MyViewHoulder myViewHoulder=new MyViewHoulder(view,onItemClickListener);
        return myViewHoulder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoulder myViewHoulder, int i) {
        myViewHoulder.textView.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MyViewHoulder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        OnItemClickListener onItemClickListener;
        public MyViewHoulder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textView);
            this.onItemClickListener=onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view,getPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }
}
