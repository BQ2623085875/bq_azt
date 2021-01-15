package com.azhyun.massxj.activity;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.fragment.BuyFragment;
import com.azhyun.massxj.fragment.SupplyFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by tl on 2018/8/16.
 * 我发布的
 */

public class SupplyLobbyActivity extends BaseActivity {
    @BindView(R.id.layout_tab)
    TabLayout layoutTab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;

    private ArrayList<String> tablayouts = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {//0:供应   1:求购
        title.setText("我发布的");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

        tablayouts.add("供应");
        tablayouts.add("求购");


        layoutTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(layoutTab, 70, 70);
            }
        });

        int num = getIntent().getIntExtra("num", 0);
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
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), tablayouts));
//        viewPager.setCurrentItem(0);
        viewPager.setCurrentItem(num);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_supply_lobby;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
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
            if (position == 0) {
                fragment = SupplyFragment.newIntent();//供应
            } else if (position == 1) {
                fragment = new BuyFragment();//求购
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


    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
