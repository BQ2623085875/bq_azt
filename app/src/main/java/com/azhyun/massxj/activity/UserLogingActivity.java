package com.azhyun.massxj.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.fragment.LoginFragment;
import com.azhyun.massxj.fragment.RegisterFragment;
import com.azhyun.massxj.view.NoSrcollViewPage;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by tl on 2018/8/13.
 */

public class UserLogingActivity extends BaseActivity {
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.img_login)
    ImageView imgLogin;
    @BindView(R.id.line_login)
    AutoLinearLayout lineLogin;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.img_register)
    ImageView imgRegister;
    @BindView(R.id.line_register)
    AutoLinearLayout lineRegister;
    @BindView(R.id.view_pager)
    NoSrcollViewPage viewPager;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initData() {
        int i = 1;
        SpUtils.put("isLogin", false);
//        Log.e("2222--->>",User.JSESSIONID+"----");
//        if (!User.JSESSIONID.isEmpty()&& !User.JSESSIONID.equals("")){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }

    }

    @Override
    protected void initListener() {
        lineLogin.setOnClickListener(this);
        lineRegister.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        int returnCode = getIntent().getIntExtra("ReturnCode", 0);
        fragmentList.add(new LoginFragment(returnCode));
        fragmentList.add(new RegisterFragment());
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_loging;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.line_login:
                viewPager.setCurrentItem(0);
                imgLogin.setVisibility(View.VISIBLE);
                imgRegister.setVisibility(View.INVISIBLE);
                break;
            case R.id.line_register:
                viewPager.setCurrentItem(1);
                imgLogin.setVisibility(View.INVISIBLE);
                imgRegister.setVisibility(View.VISIBLE);
                break;
        }


    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter {


        private final ArrayList<Fragment> list;

        public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
            super(fm);
            this.list = fragmentList;
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

    long preTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getIntent().getIntExtra("ActivityName", 0) == 11) {
                fund();
            } else {
                Intent intent = new Intent(UserLogingActivity.this, MainActivity2.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, 128);
            }

        }
//        System.exit(0);
        return false;
    }
}
