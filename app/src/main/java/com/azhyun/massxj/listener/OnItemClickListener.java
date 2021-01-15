package com.azhyun.massxj.listener;

import android.view.View;

public interface OnItemClickListener {
    void onItemClick(Object object);

    void onItemLongClick(View view, int position);
}