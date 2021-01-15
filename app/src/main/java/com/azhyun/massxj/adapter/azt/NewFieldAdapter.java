package com.azhyun.massxj.adapter.azt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.aizhongtian.NewFieldBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.azt.ShijianChuo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by dell on 2020/7/2.
 */

public class NewFieldAdapter extends RecyclerView.Adapter<NewFieldAdapter.Holder> {
    private Context mContext;
    private ArrayList<NewFieldBean.DataBeanX.RowsBean> list;

    public NewFieldAdapter(Context context, ArrayList<NewFieldBean.DataBeanX.RowsBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.newfieldadapter_item, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        final NewFieldBean.DataBeanX.RowsBean rowsBean = list.get(position);

        if (rowsBean.getDefaultImg() == null) {
            holder.newfield_item_img.setVisibility(ViewGroup.GONE);
        } else {
            holder.newfield_item_img.setVisibility(ViewGroup.VISIBLE);
            Glide.with(mContext).load(Constant.IMG_URL + rowsBean.getDefaultImg())
                    .placeholder(R.drawable.me_hard_danwei)
                    .error(R.drawable.me_hard_danwei)
                    .into(holder.newfield_item_img);
        }

        holder.newfield_item_unit.setText(rowsBean.getSource());

        holder.newfield_item_tiem.setText(ShijianChuo.getYMDTiem(rowsBean.getPublishTime()));

        holder.newfield_item_cotent.setText(rowsBean.getIntroduce());
        holder.newfield_item_title.setText(rowsBean.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterface.OnCilkeface(rowsBean, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView newfield_item_title;
        private final TextView newfield_item_cotent;
        private final ImageView newfield_item_img;
        private final TextView newfield_item_unit;
        private final TextView newfield_item_tiem;
        private final LinearLayout newfield_item_img_yes_ll;
        private final TextView newfield_item_cotent_no;
        private final TextView newfield_item_unit_no;
        private final TextView newfield_item_tiem_no;
        private final LinearLayout newfield_item_img_no_ll;

        public Holder(View itemView) {
            super(itemView);
            //有图片
            newfield_item_title = itemView.findViewById(R.id.newfield_item_title);
            newfield_item_cotent = itemView.findViewById(R.id.newfield_item_cotent);
            newfield_item_img = itemView.findViewById(R.id.newfield_item_img);
            newfield_item_unit = itemView.findViewById(R.id.newfield_item_unit);
            newfield_item_tiem = itemView.findViewById(R.id.newfield_item_tiem);
            newfield_item_img_yes_ll = itemView.findViewById(R.id.newfield_item_img_yes_ll);
            //无图片
            newfield_item_cotent_no = itemView.findViewById(R.id.newfield_item_cotent_no);
            newfield_item_unit_no = itemView.findViewById(R.id.newfield_item_unit_no);
            newfield_item_tiem_no = itemView.findViewById(R.id.newfield_item_tiem_no);
            newfield_item_img_no_ll = itemView.findViewById(R.id.newfield_item_img_no_ll);
        }
    }

    public interface OnInterface {
        void OnCilkeface(NewFieldBean.DataBeanX.RowsBean rowsBean, int position);
    }

    private OnInterface onInterface;

    public void setOnInterface(OnInterface onInterface) {
        this.onInterface = onInterface;
    }
}
