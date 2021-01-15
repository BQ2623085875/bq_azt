package com.azhyun.massxj.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.ScoreTeamAdapter;
import com.azhyun.massxj.bean.CategoryResult;
import com.azhyun.massxj.bean.aizhongtian.GongXuFenLeiBean;
import com.azhyun.massxj.listener.OnItemClickListener;

import java.util.List;

public class SelectPicPopupWindow extends PopupWindow {


    private final OnItemClickListener onItemClickListener;
    private Activity mContent;
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private View mMenuView;
  
    public SelectPicPopupWindow(Activity context, final List<GongXuFenLeiBean.DataBeanX.ChildBean> list, final OnItemClickListener onItemClickListener) {
        super(context);
        mContent = context;
        this.onItemClickListener = onItemClickListener;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.alert_aolger, null);
        RecyclerView recyclerView = (RecyclerView) mMenuView.findViewById(R.id.select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ScoreTeamAdapter scoreTeamAdapter = new ScoreTeamAdapter(list);
        recyclerView.addItemDecoration(new RecycleViewDivider(mContent, DividerItemDecoration.HORIZONTAL,2, Color.parseColor("#eeeeee")));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContent, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                onItemClickListener.onItemClick(list.get(position));
                dismiss();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(scoreTeamAdapter);

        //设置SelectPicPopupWindow的View  
        this.setContentView(mMenuView);  
        //设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
        this.setAnimationStyle(R.style.AnimBottom);  
        //实例化一个ColorDrawable颜色为半透明  
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        //设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
        backgroundAlpha(0.5f);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框  
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
              
            public boolean onTouch(View v, MotionEvent event) {
                  
                int height = mMenuView.findViewById(R.id.select).getTop();
                int y=(int) event.getY();  
                if(event.getAction()==MotionEvent.ACTION_UP){  
                    if(y<height){  
                        dismiss();  
                    }  
                }                 
                return true;  
            }  
        });
        setOnDismissListener(new popupDismissListener());
    }
    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mContent.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContent.getWindow().setAttributes(lp);
    }
}  
 