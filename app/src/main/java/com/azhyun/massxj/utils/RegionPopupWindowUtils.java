package com.azhyun.massxj.utils;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.ManagementActivity;
import com.azhyun.massxj.bean.RegionResult;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.example.zhouwei.library.CustomPopWindow;

import java.util.List;

/**
 * Created by tl on 2018/9/8.
 */

public class RegionPopupWindowUtils {
    private static CustomPopWindow mListPopWindow;
    private static PopupWindow popupWindow;
    private static onClikListener myOnClikListener;
    private static View thisView;

    public static void show(final ManagementActivity context, View lineCounty, List<RegionResult.Data> dataList){
        thisView = lineCounty;
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_grid_list, null);
        handleListView(contentView,dataList);
        //创建并显示popWindow
//        mListPopWindow= new CustomPopWindow.PopupWindowBuilder(context)
//                .setView(contentView)
//                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
//                .enableOutsideTouchableDissmiss(true)//是否PopupWindow 以外触摸dissmiss
//                .setBgDarkAlpha(0.5f) // 控制亮度
//                .create();
////                .showAsDropDown(lineCounty,0,0);


        popupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        ColorDrawable cd = new ColorDrawable(0x00ffffff);// 背景颜色全透明
        popupWindow.setBackgroundDrawable(cd);
        int[] location = new int[2];
        lineCounty.getLocationOnScreen(location);
//        popupWindow.setAnimationStyle(R.style.style_pop_animation);// 动画效果必须放在showAsDropDown()方法上边，否则无效
        backgroundAlpha(context,0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                popupWindow.dismiss();
                popupWindow = null;// 当点击屏幕时，使popupWindow消失
                backgroundAlpha(context,1.0f);// 当点击屏幕时，使半透明效果取消
            }
        });

        showAsDropDown( popupWindow,lineCounty,0,0);

    }
    // 设置popupWindow背景半透明
    public static void backgroundAlpha(ManagementActivity context,float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;// 0.0-1.0
        context.getWindow().setAttributes(lp);
    }


    public static void showAsDropDown(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }

    public static void onClik(onClikListener onClikListener){
        myOnClikListener = onClikListener;
    }

    private static void handleListView(View contentView, List<RegionResult.Data> dataList) {
        RecyclerView recyclerView1 = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        recyclerView1.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),5));
//        recyclerView1.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 10), 5));
        MyRegionAdapter myRegionAdapter = new MyRegionAdapter(dataList);
        myRegionAdapter.OnClik(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                popupWindow.dismiss();
                myOnClikListener.onClick(thisView,postion);

            }
        });
        recyclerView1.setAdapter(myRegionAdapter);
    }


    private static class MyRegionAdapter extends RecyclerView.Adapter<MyRegionAdapter.MyRegionholder> {
        private final List<RegionResult.Data> list;
        private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

        public MyRegionAdapter(List<RegionResult.Data> dataList) {
            this.list = dataList;
        }

        public void OnClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
            this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
        }
        @Override
        public MyRegionholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_region, parent, false);
            return new MyRegionholder(view);
        }

        @Override
        public void onBindViewHolder(MyRegionholder holder, final int position) {

            holder.tvName.setText(list.get(position).getName());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v,position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyRegionholder extends RecyclerView.ViewHolder{

            private final TextView tvName;

            public MyRegionholder(View itemView) {
                super(itemView);
                tvName = (TextView)itemView.findViewById(R.id.tv_name);
            }
        }
    }
    public interface onClikListener{
        void onClick(View view, int position);
    }
}

