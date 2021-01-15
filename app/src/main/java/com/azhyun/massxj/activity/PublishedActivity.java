package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;

/*
发布供需
 */
public class PublishedActivity extends BaseActivity {


    @BindView(R.id.linearlayout_release_the_supply)//发布供应
            RelativeLayout linearlayoutReleaseTheSupply;
    @BindView(R.id.linearlayout_release_for)//发布求购
            RelativeLayout linearlayoutReleaseFor;
    @BindView(R.id.bank)//返回键
            ImageView bank;
    @BindView(R.id.title)//字体(题目)
            TextView title;


    @Override
    protected void initData() {
        title.setText("发布供应");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    protected void initListener() {
        linearlayoutReleaseTheSupply.setOnClickListener(this);//发布供应点击
        linearlayoutReleaseFor.setOnClickListener(this);//发布求购点击
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_published_for;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout_release_the_supply://发布供应点击
                Intent intent_the = new Intent(PublishedActivity.this, ReleaseTheSupplyActivity.class);
                startActivity(intent_the);
                break;
            case R.id.linearlayout_release_for://发布求购点击
                Intent intent_for = new Intent(PublishedActivity.this, ReleaseForActivity.class);
                startActivity(intent_for);
                break;
            case R.id.bank:
                fund();
                break;
        }
    }
}
