package com.azhyun.massxj.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.fragment.MyLandHostingFragment;
import com.azhyun.massxj.view.NoSrcollViewPage;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by tl on 2018/9/7.
 */

public class MyLandHostingActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_tab)
    TabLayout layoutTab;
    @BindView(R.id.view_pager)
    NoSrcollViewPage viewPager;
    private ArrayList<String> tabTitles = new ArrayList<>();

    @Override
    protected void initData() {
        title.setText("我的土地托管");
        tabTitles.add("待审核");
        tabTitles.add("审核通过");
        tabTitles.add("审核拒绝");

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),tabTitles));
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layoutTab));
        viewPager.setOffscreenPageLimit(tabTitles.size());
        layoutTab.setupWithViewPager(viewPager);
        layoutTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
                viewPager.setCurrentItem(tab.getPosition(), false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_land_hosting;
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

        private final ArrayList<String> lsit ;

        public MyAdapter(FragmentManager fm, ArrayList<String> tablayouts) {
            super(fm);
            this.lsit = tablayouts;
        }

        @Override
        public Fragment getItem(int position) {

            return MyLandHostingFragment.newIntent(tabTitles.get(position));
        }

        @Override
        public int getCount() {
            return lsit.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }
    }

}
