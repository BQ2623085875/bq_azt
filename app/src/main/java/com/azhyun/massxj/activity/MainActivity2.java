package com.azhyun.massxj.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.fragment.HomeFragment2;
import com.azhyun.massxj.fragment.LeagueFragment;
import com.azhyun.massxj.fragment.MineFragment;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.NoSrcollViewPage;
import com.azhyun.massxj.x5webview.TencentBrowserActivity;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by tl on 2018/8/7.
 */

public class MainActivity2 extends BaseActivity {

    @BindView(R.id.view_pager)
    NoSrcollViewPage viewPager;
    @BindView(R.id.nihao)
    View nihao;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.rd_home)
    RadioButton rdHome;
    @BindView(R.id.rd_daily)
    RadioButton rdDaily;
    @BindView(R.id.rd_my)
    RadioButton rdMy;
    @BindView(R.id.rg_oper)
    LinearLayout rgOper;
    @BindView(R.id.img_league)
    ImageView imgLeague;
    private Fragment[] mFragments;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTransaction ft;
    private int mIndex = 0;
    private HomeFragment2 homeFragment;
    private LeagueFragment leagueFragment;
    private MineFragment mineFragment;

    public static MainActivity2 instance;

    @Override
    protected void initData() {
        int type = getIntent().getIntExtra("type", 0);
        //首页
        homeFragment = new HomeFragment2();

        //加盟
        leagueFragment = new LeagueFragment();

        //个人中心
        mineFragment = new MineFragment();

        //添加到数组
        mFragments = new Fragment[]{homeFragment, leagueFragment, mineFragment};
        fragmentList.add(homeFragment);
        fragmentList.add(leagueFragment);
        fragmentList.add(mineFragment);

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));

        // ViewPager页面切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rd_home);
                        break;
                    case 1:
                        radioGroup.check(R.id.rd_daily);
                        break;
                    case 2:
                        radioGroup.check(R.id.rd_my);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(type);
        rdHome.setChecked(true);
    }


    @Override
    protected void initListener() {
        rdHome.setOnClickListener(this);
        rdMy.setOnClickListener(this);
        rdDaily.setOnClickListener(this);
        imgLeague.setOnClickListener(this);
    }

    @Override
    protected void initView() {

//        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
//
//        if (isLogin) {
//            imgLeague.setVisibility(ViewGroup.VISIBLE);
//            rdDaily.setVisibility(ViewGroup.VISIBLE);
//        } else {
//            imgLeague.setVisibility(ViewGroup.GONE);
//            rdDaily.setVisibility(ViewGroup.GONE);
//        }

        instance = this;
        new RxPermissions(this)
                .requestEach(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.granted) {

                            //AppUtil.showShortToast(activity,"您已经授权该权限");
                        } else {

                            //未获得授权
                            ToastUtils.showToast(MainActivity2.this, "您没有授权该权限，请在设置中打开授权");

                        }

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        switch (view.getId()) {
            case R.id.rd_home://首页
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.rd_daily:
            case R.id.img_league://定制
                /*if (isLogin) {
                    rdDaily.setChecked(true);
                    viewPager.setCurrentItem(1, false);
                } else {
                    intent = new Intent(MainActivity2.this, UserLogingActivity.class);
                    startActivity(intent);
                }*/
                ToastUtils.showToast(this, "暂未开通");
                break;
            case R.id.rd_my://我的
//                rdDaily.setClickable(false);
//                if (isLogin) {
                viewPager.setCurrentItem(2, false);
//                } else {
//                    intent = new Intent(MainActivity2.this, UserLogingActivity.class);
//                    startActivity(intent);
//                }

                break;
        }
    }

    long preTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();
            // 如果时间间隔大于2秒，不处理
            if ((currentTime - preTime) > 2000) {
                // 显示消息
                Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
                //更新时间
                preTime = currentTime;
                //截获事件，不再处理
                return false;
            } else {
                application.exit();
            }
        }
//        System.exit(0);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Uri uri = intent.getData();
        if (uri != null) {
            String data = uri.getQueryParameter("data");
            Log.e("---data-->", data);
            intent = new Intent(this, TencentBrowserActivity.class);//region=&token=
            intent.putExtra("url", data);
            intent.putExtra("title", "土地托管");
            startActivityForResult(intent, 130);
        }

        int type = intent.getIntExtra("type", 0);
//        fragmentList.clear();
//        fragmentList.add(1,new LeagueFragment());
        viewPager.setCurrentItem(type);
        HomeFragment2 homeFragment2 = (HomeFragment2) fragmentList.get(0);
        homeFragment2.isCanLoadData();
        rdHome.setChecked(true);
    }

}
