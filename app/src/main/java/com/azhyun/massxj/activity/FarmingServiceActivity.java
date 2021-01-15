package com.azhyun.massxj.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ServiceCategorysResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.fragment.FarmingServiceFragment;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.NoSrcollViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/15.
 */

public class FarmingServiceActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_tab)
    TabLayout layoutTab;
    @BindView(R.id.view_pager)
    NoSrcollViewPage viewPager;
    private int type = 0;

    private List<FarmingServiceFragment> fragmentList = new ArrayList<>();
    private List<ServiceCategorysResult.DataBean.Categorys> categorysList = new ArrayList<>();

    @Override
    protected void initData() {
        //获取分类
        getCategorys();

        //设置viewpager
        type = getIntent().getIntExtra("type", 0);
    }

    private void getCategorys() {
        LoadingDialog.show(FarmingServiceActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ServiceCategorysResult> categorys = service.getCategorys(0, User.INSTANCE.getToken());
        categorys.enqueue(new Callback<ServiceCategorysResult>() {
            @Override
            public void onResponse(Call<ServiceCategorysResult> call, Response<ServiceCategorysResult> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    ServiceCategorysResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getDataBean().getJSESSIONID());
                        categorysList = body.getDataBean().getCategorys();

                        for (int i = 0; i < categorysList.size(); i++) {
                            fragmentList.add(new FarmingServiceFragment(categorysList.get(i).getId()));
                        }

                        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), categorysList, fragmentList));
                        viewPager.setCurrentItem(type);
                    } else {
                        ToastUtils.showToast(FarmingServiceActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ServiceCategorysResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("农事服务");
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layoutTab));
        viewPager.setOffscreenPageLimit(10);
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
        return R.layout.activity_farming_service;
    }

    @Override
    protected void otherViewClick(View view) {

        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {

        private final List<ServiceCategorysResult.DataBean.Categorys> categorysList;
        private final List<FarmingServiceFragment> fragmentlist;

        public MyAdapter(FragmentManager fm, List<ServiceCategorysResult.DataBean.Categorys> tablayouts, List<FarmingServiceFragment> fragmentList) {
            super(fm);

            this.categorysList = tablayouts;
            this.fragmentlist = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return this.categorysList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categorysList.get(position).getName();
        }
    }

}
