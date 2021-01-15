package com.azhyun.massxj.view.shishi;

import android.widget.ScrollView;

public interface ScrollViewListener {

    void onScrollChanged(ScrollView observableScrollView, int x, int y, int oldx, int oldy);
    void onScroll(int y);
}