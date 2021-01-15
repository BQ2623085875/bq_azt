package com.azhyun.massxj.bean;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class GridViewResult extends GridView {
    public GridViewResult(Context context) {
        super(context);
    }

    public GridViewResult(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewResult(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
