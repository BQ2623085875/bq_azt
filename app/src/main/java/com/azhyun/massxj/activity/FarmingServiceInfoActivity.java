package com.azhyun.massxj.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.fragment.FarmingServiceInfoFragment;
import com.azhyun.massxj.fragment.ReviewFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by tl on 2018/8/23.
 */

public class FarmingServiceInfoActivity extends BaseActivity {
    @BindView(R.id.layout_tab)
    TabLayout layoutTab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    private ArrayList<String> tablayouts = new ArrayList<>();

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        tablayouts.add("详情");
        tablayouts.add("评价");

        int num=getIntent().getIntExtra("num",0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layoutTab));
        viewPager.setOffscreenPageLimit(tablayouts.size());
        layoutTab.setupWithViewPager(viewPager);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),tablayouts));
        viewPager.setCurrentItem(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_farming_service_info;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.back:
                fund();
                break;
        }
    }

    //去评论fragment
    public void setnext() {
        viewPager.setCurrentItem(1);
        viewPager.getAdapter().notifyDataSetChanged();
    }


    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager supportFragmentManager, ArrayList<String> tablayouts) {
            super(supportFragmentManager);

        }


        @Override
        public Fragment getItem(int position) {
            int id = getIntent().getIntExtra("id", 0);
            Fragment fragment = null;
            if (position == 0){
                fragment = FarmingServiceInfoFragment.newIntent(id);//农事服务详情fragment
            }else if (position == 1){
                fragment = ReviewFragment.newIntent(id);//评论详情
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tablayouts.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tablayouts.get(position);
        }
    }

}
