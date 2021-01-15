package com.azhyun.massxj.activity.azt;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.aizhongtian.MallLiebiaoBean;
import com.azhyun.massxj.bean.aizhongtian.baoxian.BaoFenLeiBean;
import com.azhyun.massxj.fragment.azt.AgriculturalMaterialsMallFragment;
import com.azhyun.massxj.fragment.azt.baoxian.BaoAgriculturaFragment;
import com.azhyun.massxj.fragment.azt.farmingInsurance.FarmingInsuranceFragment;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 农资商城
* 农保中心
* */
public class AgriculturalMaterialsMallActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.layout_tab)
    TabLayout mLayout_tab;
    @BindView(R.id.view_pager)
    ViewPager mView_pager;
    @BindView(R.id.jiazai)
    TextView jiazai;

    private List<MallLiebiaoBean.DataBeanX> data;
    //id
    ArrayList<Integer> idlist = new ArrayList<>();
    //名字
    ArrayList<String> namelist = new ArrayList<>();
    //Fragment 集合
    ArrayList list = new ArrayList<>();


    private int mType = 0;
    private List<BaoFenLeiBean.DataBeanX> nongbaodata;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_agricultural_materials_mall;
    }

    @Override
    protected void initView() {
        mType = getIntent().getIntExtra("type", 0);

        if (mType == 0) {
            mTitle.setText("农资商城");
        } else {
            mTitle.setText("农保中心");
        }
        mTitle.setTextSize(18);
        mTitle.setTextColor(getResources().getColor(R.color.toolBarBackground));
        jiazai.setVisibility(ViewGroup.VISIBLE);
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (mType == 0) {
            gainiData();//商品商城
        } else {
            gainiNongData();//农保中心
        }
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank://返回
                finish();
                break;
        }
    }

    private void gainiData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MallLiebiaoBean> malllb = service.malllb(0);
        malllb.enqueue(new Callback<MallLiebiaoBean>() {
            @Override
            public void onResponse(Call<MallLiebiaoBean> call, Response<MallLiebiaoBean> response) {
                if (response.isSuccessful()) {
                    MallLiebiaoBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        jiazai.setVisibility(ViewGroup.GONE);
                        data = body.getData();

                        idlist.add(0);
                        namelist.add("全部");

                        for (int i = 0; i < data.size(); i++) {
                            idlist.add(data.get(i).getId());
                            namelist.add(data.get(i).getName());
                        }

                        for (int i = 0; i < idlist.size(); i++) {
                            list.add(new AgriculturalMaterialsMallFragment(idlist.get(i)));
                            mLayout_tab.addTab(mLayout_tab.newTab().setText(namelist.get(i)));
                        }
                        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), list);
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
                    } else {
                        ToastUtils.showToast(AgriculturalMaterialsMallActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MallLiebiaoBean> call, Throwable t) {

            }
        });

    }

    private void gainiNongData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<BaoFenLeiBean> baoFenLei = service.getBaoFenLei();
        baoFenLei.enqueue(new Callback<BaoFenLeiBean>() {
            @Override
            public void onResponse(Call<BaoFenLeiBean> call, Response<BaoFenLeiBean> response) {
                if (response.isSuccessful()) {
                    BaoFenLeiBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        jiazai.setVisibility(ViewGroup.GONE);
                        nongbaodata = body.getData();

                        idlist.add(0);
                        namelist.add("全部");

                        for (int i = 0; i < nongbaodata.size(); i++) {
                            idlist.add(nongbaodata.get(i).getId());
                            namelist.add(nongbaodata.get(i).getName());
                        }
                        for (int i = 0; i < idlist.size(); i++) {
                            list.add(new FarmingInsuranceFragment(idlist.get(i)));
                            mLayout_tab.addTab(mLayout_tab.newTab().setText(namelist.get(i)));
                        }
                        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), list);
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


                    } else {
                        ToastUtils.showToast(AgriculturalMaterialsMallActivity.this, body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<BaoFenLeiBean> call, Throwable t) {

            }
        });
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

}
