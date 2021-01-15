package com.azhyun.massxj.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.azhyun.massxj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tl on 2018/6/26.
 */

public class RightPopuWindow implements PopupWindow.OnDismissListener {

    public static PopupWindow popupWindow = null;
    private static int from = 0;
    private static Activity mcontext;
    private static RecyclerView recyclerView;

    public static void initPopupWindow(View view, Activity context) {
        List<String> nameList = new ArrayList<>();
        nameList.add("首页");
        nameList.add("添加台账");
        nameList.add("客户管理");
        nameList.add("应收台账");
        nameList.add("历史台账");
        nameList.add("商品管理");
        nameList.add("服务");

        mcontext = context;
        View popupWindowView = context.getLayoutInflater().inflate(R.layout.pop, null);
        recyclerView = (RecyclerView) popupWindowView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.setAdapter(new MyAdapter(nameList));

        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT, true);
        //动画效果
        popupWindow.setAnimationStyle(R.style.AnimationRightFade);

        //菜单背景色
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);
        //宽度
        //popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
        //高度
        //popupWindow.setHeight(LayoutParams.FILL_PARENT);
        //显示位置
//        popupWindow.showAtLocation(context.getLayoutInflater().inflate(mcontext, null), Gravity.RIGHT, 0, 500);
//        popupWindow.showAsDropDown(view, 0, 0, Gravity.RIGHT);
        popupWindow.showAtLocation(view,Gravity.RIGHT,0,0);
        //设置背景半透明
        backgroundAlpha(0.5f);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());

        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*if( popupWindow!=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }*/
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });

    }

    public static void dismiss(){
        popupWindow.dismiss();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mcontext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mcontext.getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {

    }

    /**
     * 菜单弹出方向
     */
    public enum Location {
        RIGHT,
    }

    static class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHodler> {
        private final List<String> list;


        public MyAdapter(List<String> data) {
            this.list = data;

        }

        @Override
        public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_popu, null, false);
            return new MyHodler(view);
        }

        @Override
        public void onBindViewHolder(final MyHodler holder, final int position) {

            holder.text.setText(list.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
//                   if ( list.get(position).equals("首页")){
//                       intent = new Intent(mcontext, MainActivity.class);
//                       mcontext.startActivity(intent);
//
//                   }else if ( list.get(position).equals("添加台账")){
//                       intent = new Intent(mcontext, AddStandingBookActivity.class);
//                       mcontext.startActivity(intent);
//
//                   }else if ( list.get(position).equals("客户管理")){
//                       intent = new Intent(mcontext, CustomerManagementActivity.class);
//                       mcontext.startActivity(intent);
//
//                   }else if (list.get(position).equals("应收台账")){
//                       intent=new Intent(mcontext, AccountReceivableActivity.class);
//                       mcontext.startActivity(intent);
//                   }else if (list.get(position).equals("历史台账")){
//                       intent=new Intent(mcontext, HistoryParameterActivity.class);
//                       mcontext.startActivity(intent);
//
//                   }else if (list.get(position).equals("商品管理")){
//                       intent=new Intent(mcontext, CommodityManagementActivity.class);
//                       mcontext.startActivity(intent);
//
//                   }else if (list.get(position).equals("服务")){
//                       intent=new Intent(mcontext, ServiceActicity.class);
//                       mcontext.startActivity(intent);
//
//                   }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyHodler extends RecyclerView.ViewHolder {

            private final TextView text;

            public MyHodler(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}

