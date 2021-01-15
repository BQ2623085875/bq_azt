package com.azhyun.massxj.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yh on 2016/8/14.
 */
public class IndexSideBar extends View {
    public interface OnLetterTouchedListener {
        public void onLetterTouched(String letter);
    }

    public OnLetterTouchedListener mOnLetterTouchedListener;

    public void setOnLetterTouchedListener (OnLetterTouchedListener onLetterTouchedListener) {
        mOnLetterTouchedListener = onLetterTouchedListener;
    }

    public static String[] letterIndex = {  "#","A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    private int choose = -1; // 选中状态
    private Paint paint = new Paint();

    private TextView mSelectLetterTextView;

    public IndexSideBar(Context context) {
        super(context);
    }

    public IndexSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndexSideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSelectLetterTextView(TextView selectLetterTextView) {
        this.mSelectLetterTextView = selectLetterTextView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getHeight();
        int width = getWidth();
        int singleLetterHeight = height / letterIndex.length;

        for (int i = 0; i < letterIndex.length; i++) {
            paint.setColor(Color.rgb(158, 158, 158));// 设置字体颜色
            paint.setTypeface(Typeface.DEFAULT_BOLD);// 设置字体
            paint.setAntiAlias(true);// 设置抗锯齿
            paint.setTextSize(30);// 设置字母字体大小

            if (i == choose) {
                paint.setColor(Color.parseColor("#3399ff"));// 选中的字母改变颜色
                paint.setFakeBoldText(true);// 设置字体为粗体
            }

            float xCoord = width / 2 - paint.measureText(letterIndex[i]) / 2;// x坐标 = 中间 - 字符串宽度的一半
            float yCoord = singleLetterHeight * (i + 1);

            canvas.drawText(letterIndex[i], xCoord, yCoord, paint);// 绘制所有的字母
            paint.reset();// 重置画笔
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final OnLetterTouchedListener listener = mOnLetterTouchedListener;

        final float yCoord = event.getY();
        final int prevChoose = choose;
        final int index = (int) (yCoord / getHeight() * letterIndex.length);

        final int action = event.getAction();

        switch(action) {

            case MotionEvent.ACTION_UP:
                choose = -1;

                invalidate();

                if (mSelectLetterTextView != null) mSelectLetterTextView.setVisibility(View.INVISIBLE);

                break;

            default:
                if (prevChoose != index) {
                    if (index >= 0 && index < letterIndex.length) {
                        if (listener != null) listener.onLetterTouched(letterIndex[index]);

                        if (mSelectLetterTextView != null) {
                            mSelectLetterTextView.setText(letterIndex[index]);
                            mSelectLetterTextView.setVisibility(VISIBLE);
                        }

                        choose = index;
                        invalidate();
                    }
                }

                break;
        }

        return true;
    }
}
