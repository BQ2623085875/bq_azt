package com.azhyun.massxj.adapter.azt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;

/**
 * Created by dell on 2020/7/30.
 */

public class NewNbAdapter extends RecyclerView.Adapter<NewNbAdapter.Holder> {
    protected final String TAG = "NewNbAdapter";

    private Context context;


    @Override
    public NewNbAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.new_nb_adapter_item, null));
    }

    @Override
    public void onBindViewHolder(NewNbAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final ImageView new_nb_img;
        private final TextView new_nb_name;
        private final TextView new_nb_price;
        private final TextView new_nb_mushu;
        private final TextView new_nb_jine;
        private final TextView new_nb_zeren;

        public Holder(View itemView) {
            super(itemView);
            new_nb_img = itemView.findViewById(R.id.new_nb_img);
            new_nb_name = itemView.findViewById(R.id.new_nb_name);
            new_nb_price = itemView.findViewById(R.id.new_nb_price);
            new_nb_mushu = itemView.findViewById(R.id.new_nb_mushu);
            new_nb_jine = itemView.findViewById(R.id.new_nb_jine);
            new_nb_zeren = itemView.findViewById(R.id.new_nb_zeren);
        }
    }
}
