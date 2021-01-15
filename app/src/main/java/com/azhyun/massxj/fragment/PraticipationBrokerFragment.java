package com.azhyun.massxj.fragment;

import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//经纪人
public class PraticipationBrokerFragment extends LazyLoadFragment {
    @BindView(R.id.recycler_brokerview)
    XRecyclerView recyclerView;
    List<String> list = new ArrayList<>();

    @Override
    protected int setContentView() {
        return R.layout.my_participation_broker_activity;
    }

    @Override
    protected void lazyLoad() {
        for (int i = 20; i >= 0; i--) {
            list.add(" " + i);
        }
        MyAdapter myAdapter = new MyAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), DividerItemDecoration.HORIZONTAL, 5, Color.parseColor("#eeeeee")));
        recyclerView.setAdapter(myAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoulder> {
        List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public MyViewHoulder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.praticipation_broker_item, null);
            MyViewHoulder myViewHolder = new MyViewHoulder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHoulder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHoulder extends RecyclerView.ViewHolder {
            TextView textGrade;
            TextView textName;
            TextView textMobile;
            TextView textArea;

            public MyViewHoulder(View itemView) {
                super(itemView);
                textGrade = (TextView) itemView.findViewById(R.id.text_grade);
                textName = (TextView) itemView.findViewById(R.id.text_name);
                textMobile = (TextView) itemView.findViewById(R.id.text_mobile);
                textArea = (TextView) itemView.findViewById(R.id.text_area);
            }
        }
    }
}
