package com.azhyun.massxj.view.shishi;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by ljh on 2016/11/30.
 * 垂直滚动scrollview
 */

public class VerticalRollScrollView extends ScrollView {
    private float xDistance, yDistance, xLast, yLast;
    // 是否在触摸状态
    private boolean inTouch = false;
    // 检查ScrollView的最终状态
    private static final int CHECK_STATE = 0;
    private ScrollViewListener mScrollViewListener = null;

    //主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
    private int lastScrollY;

    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = VerticalRollScrollView.this.getScrollY();
            handler.removeCallbacksAndMessages(null);
            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 1);
            }
            if (mScrollViewListener != null) {
                mScrollViewListener.onScroll(scrollY);
            }
        }
    };

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }

    public VerticalRollScrollView(Context context) {
        super(context);
    }

    public VerticalRollScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalRollScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                if (Math.abs(xDistance - 4) > Math.abs(yDistance)) {
                    return false;
                } else {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mScrollViewListener != null) {
            mScrollViewListener.onScroll(lastScrollY = this.getScrollY());
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                inTouch = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                inTouch = false;
                lastScrollY = getScrollY();

                handler.removeMessages(CHECK_STATE);
                handler.sendEmptyMessageDelayed(CHECK_STATE, 5);
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener == null) {
            return;
        }

        if (inTouch) {
            if (t != oldt) {
                // 有手指触摸，并且位置有滚动
                mScrollViewListener.onScroll(t);
            }
        } else {
            if (t != oldt) {
                // 没有手指触摸，并且位置有滚动，就可以简单的认为是在fling
                mScrollViewListener.onScroll(t);
                // 记住上次滑动的最后位置
                lastScrollY = t;
                handler.removeMessages(CHECK_STATE);
                handler.sendEmptyMessageDelayed(CHECK_STATE, 5);// 5毫秒检查一下
            }
        }
    }

}
