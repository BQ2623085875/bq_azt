package com.azhyun.massxj.activity.azt;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.activity.azt.caigou.CaiGouCommodityDetailsActivity;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.CaiBean;
import com.azhyun.massxj.fragment.azt.AgriculturalMaterialsMallFragment;
import com.azhyun.massxj.fragment.azt.caigou.CaiGouFragment;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
* 采收服务页面
* */
public class CaiPurchaseActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;


    @BindView(R.id.layout_tab)
    TabLayout mLayout_tab;
    @BindView(R.id.view_pager)
    ViewPager mView_pager;


    private String region;
    private ArrayList<CaiBean.DataBeanX.RowsBean> list = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> tablist = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cai_purchase;
    }

    @Override
    protected void initView() {
        title.setText("采收服务");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.toolBarBackground));
        region = User.INSTANCE.getRegion();

        tablist.add("采收服务");
        tablist.add("企业名录");

        integers.add(0);
        integers.add(1);
    }


    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initViewDAata();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        initViewDAata();
    }

    private void initViewDAata() {
        for (int i = 0; i < integers.size(); i++) {
            fragmentList.add(new CaiGouFragment(integers.get(i)));
            mLayout_tab.addTab(mLayout_tab.newTab().setText(tablist.get(i)));
        }
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
        mView_pager.setAdapter(adapter);
        mLayout_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mView_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mView_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mLayout_tab));


    }

    /*
  * adapter
  * */
    public class VPAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;

        public VPAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
        }
    }


}
