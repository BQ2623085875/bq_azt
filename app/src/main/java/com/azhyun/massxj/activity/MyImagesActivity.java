package com.azhyun.massxj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.azhyun.massxj.R;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;


/**
 * Created by tl on 2018/6/22.
 */

public class MyImagesActivity extends AppCompatActivity {
    private ViewPager viewPagerImage;
    private List<String> data;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        viewPagerImage = (ViewPager)findViewById(R.id.view_pager_image);
        initData();
    }

    private void initData() {
       data = getIntent().getStringArrayListExtra("data");
       position = getIntent().getIntExtra("position",0);

        viewPagerImage.setAdapter(new NewTopBannerAdapter(viewPagerImage,data));
        viewPagerImage.setCurrentItem(position);
    }
    class NewTopBannerAdapter extends StaticPagerAdapter {

        private final List<String> data;

        public NewTopBannerAdapter(ViewPager viewPager, List<String> data) {
            this.data = MyImagesActivity.this.data;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            PhotoView view = new PhotoView(MyImagesActivity.this);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER  );
            view.enable();
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(MyImagesActivity.this)
                    .load(data.get(position))
                    .dontAnimate()
                    .crossFade()
                    .fitCenter()
                    .into(view);
            return view;
        }


        @Override
        public int getCount() {
            return data.size();
        }
    }
}

