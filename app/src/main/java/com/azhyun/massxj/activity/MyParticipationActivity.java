package com.azhyun.massxj.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.fragment.PraticipationBrokerFragment;
import com.azhyun.massxj.fragment.PraticipationCollocationFragment;
import com.azhyun.massxj.view.NoSrcollViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
我的加入
 */
public class MyParticipationActivity extends BaseActivity {

    public List<Fragment> list = new ArrayList<>();//fragment
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    private ArrayList<String> tablayouts = new ArrayList<>();//TabLayout
    @BindView(R.id.view_pager)//viewpager切换Fragment;
            NoSrcollViewPage viewPager;
    @BindView(R.id.tab_participation)
    TabLayout tabParticipation;

    @Override
    protected void initData() {
        viewPager.setScanScroll(false);//禁止viewpager滑动
//        viewPager.setCurrentItem(0);
        title.setText("我的加入");
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        tablayouts.add("土地管理");
        tablayouts.add("经纪人");
        int num = getIntent().getIntExtra("join", 0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabParticipation));
        viewPager.setOffscreenPageLimit(tablayouts.size());
        tabParticipation.setupWithViewPager(viewPager);
        tabParticipation.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), tablayouts));
        viewPager.setCurrentItem(num);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_participation_adapter;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
        }
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {
        private List<String> list;

        public MyPagerAdapter(FragmentManager fm, List<String> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            Fragment fragment = null;
            if (arg0 == 0) {
                fragment = new PraticipationCollocationFragment();//土地管理
            } else if (arg0 == 1) {
                fragment = new PraticipationBrokerFragment();//经纪人
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
}
