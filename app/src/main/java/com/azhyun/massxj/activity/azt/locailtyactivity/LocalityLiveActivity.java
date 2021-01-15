package com.azhyun.massxj.activity.azt.locailtyactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.activity.azt.MainActivity;
import com.azhyun.massxj.adapter.azt.FenLeiNewAdapter;
import com.azhyun.massxj.bean.aizhongtian.CommodityDetailsBean;
import com.azhyun.massxj.bean.aizhongtian.GongQiuBean;
import com.azhyun.massxj.bean.aizhongtian.GongXuFenLeiBean;
import com.azhyun.massxj.fragment.HomeFragment2;
import com.azhyun.massxj.fragment.azt.licaityfragment.AllFragment;
import com.azhyun.massxj.fragment.azt.licaityfragment.AskFragment;
import com.azhyun.massxj.fragment.azt.licaityfragment.SupplyFragment;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.FlexRadioGroup;
import com.azhyun.massxj.view.NoSrcollViewPage;
import com.google.android.flexbox.FlexboxLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 本地生活
* */
public class LocalityLiveActivity extends BaseActivity implements DrawerLayout.DrawerListener {
    protected final String TAG = "LocalityLiveActivity";

    @BindView(R.id.bank)
    ImageView mBank;
    @BindView(R.id.locality_sou)
    ImageView mLocality_sou;
    @BindView(R.id.locality_pager)
    NoSrcollViewPage mLocality_pager;
    @BindView(R.id.licality_sou_rl)
    RelativeLayout mLicality_sou_rl;
    @BindView(R.id.licality_biaoti_rl)
    RelativeLayout mLicality_biaoti_rl;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.quan)
    RadioButton quan;
    @BindView(R.id.gongying)
    RadioButton gongying;
    @BindView(R.id.qiugou)
    RadioButton qiugou;
    @BindView(R.id.fenlie)
    TextView fenlie;
    @BindView(R.id.fen_rl)
    RelativeLayout fen_rl;
    @BindView(R.id.sou_dele)
    ImageView sou_dele;
    @BindView(R.id.sou_ed)
    EditText sou_ed;
    @BindView(R.id.sou_rl)
    RelativeLayout sou_rl;
    @BindView(R.id.loca_rv)
    RecyclerView loca_rv;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.fenlei_chongzhi)
    TextView fenlei_chongzhi;
    @BindView(R.id.fenlei_yes)
    TextView fenlei_yes;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;

    private AllFragment allFragment;
    private SupplyFragment supplyFragment;
    private AskFragment askFragment;

    private List<Fragment> fragmentList = new ArrayList<>();

    private boolean boolSou = false;
    private int position = 0;
    private FenLeiAdapter fenLeiAdapter;
    private FenLeiNewAdapter fenLeiNewAdapter;
    private GongXuFenLeiBean.DataBeanX dataBeanX;

    private String mFenLeiString;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_locality_live;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initListener() {
        quan.setOnClickListener(this);
        gongying.setOnClickListener(this);
        qiugou.setOnClickListener(this);
        fenlie.setOnClickListener(this);
        fen_rl.setOnClickListener(this);
        mBank.setOnClickListener(this);
        mLocality_sou.setOnClickListener(this);
        sou_dele.setOnClickListener(this);
        mBank.setOnClickListener(this);
        drawerlayout.setDrawerListener(this);//侧滑栏监听
        fenlei_chongzhi.setOnClickListener(this);
        fenlei_yes.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        allFragment = new AllFragment();
        supplyFragment = new SupplyFragment();
        askFragment = new AskFragment();


        fragmentList.add(allFragment);
        fragmentList.add(supplyFragment);
        fragmentList.add(askFragment);

        mLocality_pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));

        // ViewPager页面切换监听
        mLocality_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.quan);
                        fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                        fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                        break;
                    case 1:
                        radioGroup.check(R.id.gongying);
                        quan.setChecked(false);
                        fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                        fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                        break;
                    case 2:
                        radioGroup.check(R.id.qiugou);
                        quan.setChecked(false);
                        fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                        fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        quan.setChecked(true);

    }

    private void gainFlData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<GongXuFenLeiBean> gongXuFenLei = service.getGongXuFenLei();
        gongXuFenLei.enqueue(new Callback<GongXuFenLeiBean>() {
            @Override
            public void onResponse(Call<GongXuFenLeiBean> call, Response<GongXuFenLeiBean> response) {
                if (response.isSuccessful()) {
                    GongXuFenLeiBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        fenLeiAdapter = new FenLeiAdapter(body.getData());
                        loca_rv.setAdapter(fenLeiAdapter);
                        loca_rv.setLayoutManager(new LinearLayoutManager(LocalityLiveActivity.this));
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<GongXuFenLeiBean> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.quan:
                mLocality_pager.setCurrentItem(0, false);
                fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                position = 0;
                if (boolSou) {
                    boolSou = false;
                    sou_rl.setVisibility(ViewGroup.GONE);
                    sou_ed.setText("");
                }
                break;
            case R.id.gongying:
                mLocality_pager.setCurrentItem(1, false);
                fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                position = 1;
                if (boolSou) {
                    boolSou = false;
                    sou_rl.setVisibility(ViewGroup.GONE);
                    sou_ed.setText("");
                }
                break;
            case R.id.qiugou:
                mLocality_pager.setCurrentItem(2, false);
                fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                position = 2;
                if (boolSou) {
                    boolSou = false;
                    sou_rl.setVisibility(ViewGroup.GONE);
                    sou_ed.setText("");
                }
                break;
            case R.id.fenlie:
                //获取分类
                gainFlData();
                fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_yes));
                fenlie.setTextColor(getResources().getColor(R.color.white));
                quan.setChecked(false);
                gongying.setChecked(false);
                qiugou.setChecked(false);
                ToastUtils.showToast(LocalityLiveActivity.this, "分类");
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
                drawerlayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.locality_sou:
                if (boolSou) {
                    if (TextUtils.isEmpty(sou_ed.getText())) {
                        if (position == 0) {
                            allFragment.localitysou(sou_ed.getText().toString());
                        } else if (position == 1) {
                            supplyFragment.localitysou(sou_ed.getText().toString());
                        } else if (position == 2) {
                            askFragment.localitysou(sou_ed.getText().toString());
                        }
                        boolSou = false;
                        sou_rl.setVisibility(ViewGroup.GONE);
                    } else {
                        if (position == 0) {
                            allFragment.localitysou(sou_ed.getText().toString());
                        } else if (position == 1) {
                            supplyFragment.localitysou(sou_ed.getText().toString());
                        } else if (position == 2) {
                            askFragment.localitysou(sou_ed.getText().toString());
                        }
                    }
                } else {
                    boolSou = true;
                    sou_rl.setVisibility(ViewGroup.VISIBLE);
                }
                break;
            case R.id.sou_dele:
                sou_ed.setText("");
                break;
            case R.id.fenlei_chongzhi:
                //获取分类
                gainFlData();
                break;
            case R.id.fenlei_yes:
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerlayout.closeDrawer(linterHistoryConfirm);
                }
                mLocality_pager.setCurrentItem(0, false);
                fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                position = 0;
                if (boolSou) {
                    boolSou = false;
                    sou_rl.setVisibility(ViewGroup.GONE);
                    sou_ed.setText("");
                }
                allFragment.localitysou(mFenLeiString);
                break;
        }
    }


    /*
   * adapter
   * */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {

            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    //分类adapter
    public class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.Holder> {
        private List<GongXuFenLeiBean.DataBeanX> dataBeanXES;

        public FenLeiAdapter(List<GongXuFenLeiBean.DataBeanX> data) {
            this.dataBeanXES = data;
        }

        @Override
        public FenLeiAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(LocalityLiveActivity.this).inflate(R.layout.fenlei_item, null));
        }

        @Override
        public void onBindViewHolder(FenLeiAdapter.Holder holder, int position) {
            dataBeanX = dataBeanXES.get(position);

            holder.title_tv.setText(dataBeanX.getName());

            if (dataBeanX.getChild().size() > 0) {
                holder.fen_rv.setVisibility(ViewGroup.VISIBLE);
                holder.wsj.setVisibility(ViewGroup.GONE);
                fenLeiNewAdapter = new FenLeiNewAdapter(dataBeanX.getChild(), LocalityLiveActivity.this);
                holder.fen_rv.setAdapter(fenLeiNewAdapter);
                holder.fen_rv.setLayoutManager(new GridLayoutManager(LocalityLiveActivity.this, 3));
                fenLeiNewAdapter.setOnInterface(new FenLeiNewAdapter.OnInterface() {
                    @Override
                    public void OnCilkInterface(GongXuFenLeiBean.DataBeanX.ChildBean childBean, int position) {
                        mFenLeiString = childBean.getName();
                        fenLeiNewAdapter.notifyDataSetChanged();
                        fenLeiAdapter.notifyDataSetChanged();
                    }
                });

            } else {
                holder.fen_rv.setVisibility(ViewGroup.GONE);
                holder.wsj.setVisibility(ViewGroup.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return dataBeanXES.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private final TextView title_tv;
            private final RecyclerView fen_rv;
            private final TextView wsj;

            public Holder(View itemView) {
                super(itemView);
                title_tv = itemView.findViewById(R.id.title_tv);
                fen_rv = itemView.findViewById(R.id.fen_rv);
                wsj = itemView.findViewById(R.id.wsj);

            }
        }
    }


    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        drawerView.setClickable(true);
    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    //手机返回键监听事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                //当菜单栏是可见的，则关闭
                drawerlayout.closeDrawer(linterHistoryConfirm);

                mLocality_pager.setCurrentItem(0, false);
                fen_rl.setBackground(getResources().getDrawable(R.drawable.ben_ch_no));
                fenlie.setTextColor(getResources().getColor(R.color.ben_text));
                position = 0;
                if (boolSou) {
                    boolSou = false;
                    sou_rl.setVisibility(ViewGroup.GONE);
                    sou_ed.setText("");
                }
                return true;
            } else {
                //反之退出
                finish();
            }
//            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
