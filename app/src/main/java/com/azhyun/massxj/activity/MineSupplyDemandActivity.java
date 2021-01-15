package com.azhyun.massxj.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.fragment.MineBuyFragment;
import com.azhyun.massxj.fragment.MineSupplyFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by tl on 2018/9/7.
 */

public class MineSupplyDemandActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_tab)
    TabLayout layoutTab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<String> tablayouts = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("我的供求");
        tablayouts.add("供应");
        tablayouts.add("求购");

        int num=getIntent().getIntExtra("num",0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layoutTab));
        viewPager.setOffscreenPageLimit(tablayouts.size());
        layoutTab.setupWithViewPager(viewPager);
        layoutTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
//                viewPager.setCurrentItem(tab.getPosition(), false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),tablayouts));
//        viewPager.setCurrentItem(0);
        viewPager.setCurrentItem(num);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_supply_demand;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
        }
    }
    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager supportFragmentManager, ArrayList<String> tablayouts) {
            super(supportFragmentManager);

        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0){//我的供应
                fragment = MineSupplyFragment.newIntent();
            }else if (position == 1){//我的求购
                fragment = new MineBuyFragment();
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
