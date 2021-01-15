package com.azhyun.massxj.utils;

import android.content.Context;
import android.widget.ImageView;

import com.azhyun.massxj.R;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

//自定义的图片加载器
    public class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).fitCenter().error(R.drawable.err).into(imageView);
        }

}