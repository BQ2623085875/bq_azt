package com.azhyun.massxj.activity.azt;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.fragment.azt.NbOrderFragment;
import com.azhyun.massxj.fragment.azt.OrderFragment;

import butterknife.BindView;

/*
* 订单列表
* */
public class OrderActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.layout_tab)
    TabLayout mLayout_tab;
    @BindView(R.id.view_pager)
    ViewPager mView_pager;

    private int mStatus = 0;
    private String distinguish;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initData() {
        if (distinguish.equals("order")) {//商品订单
            mTitle.setText("我的订单");
            gainData();
        } else {//农保订单
            mTitle.setText("农保订单");
            nbgainData();
        }
    }

    private void nbgainData() {
        mView_pager.setAdapter(new NbVPAdapter(getSupportFragmentManager()));
        mView_pager.setPageMargin(20);
        mLayout_tab.setupWithViewPager(mView_pager);


        int currentItem = 0;
        switch (mStatus) {
            case 0:
                currentItem = 0;
                break;
            case 1:
                currentItem = 1;
                break;
            case 2:
                currentItem = 2;
                break;
            case 3:
                currentItem = 3;
                break;
        }
        mView_pager.setCurrentItem(currentItem, false);
    }

    private void gainData() {
        mView_pager.setAdapter(new VPAdapter(getSupportFragmentManager()));
        mView_pager.setPageMargin(20);
        mLayout_tab.setupWithViewPager(mView_pager);

        int currentItem = 0;
        switch (mStatus) {
            case 1:
                currentItem = 1;
                break;
            case 2:
                currentItem = 2;
                break;
            case 3:
                currentItem = 3;
                break;
            case 4:
                currentItem = 4;
                break;
            case 0:
                currentItem = 0;
                break;
        }
        mView_pager.setCurrentItem(currentItem, false);
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mTitle.setTextColor(getResources().getColor(R.color.black));
        mTitle.setTextSize(18);

        mStatus = getIntent().getIntExtra("status", 0);
        distinguish = getIntent().getStringExtra("distinguish");

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                    finish();
                break;
        }
    }

    /*
    * 商品订单   adapter
    * */
    public class VPAdapter extends FragmentStatePagerAdapter {
        public final String[] TITLES = {"全部", "待付款", "待发货", "待收货", "已完成"};

        public final int[] STATUS = {0, 1, 2, 3, 4,};

        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            int status = STATUS[position];
            return OrderFragment.newInstance(status);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }

    /*
   * 农保订单   adapter
   * */
    public class NbVPAdapter extends FragmentStatePagerAdapter {
        public final String[] TITLES = {"全部", "待处理", "已生效", "已过期"};

        public final int[] STATUS = {0, 1, 2, 3,};

        public NbVPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            int status = STATUS[position];
            return NbOrderFragment.newInstance(status);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
