package com.azhyun.massxj.activity.azt;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.fragment.azt.CommodityDetailsFragment;

import butterknife.BindView;

/*
* 商品详情
* */
public class CommodityDetailsActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.bank)
    ImageView mBank;
    @BindView(R.id.info_frame_layout)
    FrameLayout mInfo_frame_layout;
    @BindView(R.id.promptly_ll)
    LinearLayout mPromptly_ll;

    private FragmentManager mSupportFragmentManager;
    private CommodityDetailsFragment mCommodityDetailsFragment;

    private int commodityid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    protected void initView() {

        //商品id
        commodityid = getIntent().getIntExtra("commodityid", 0);

        mSupportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        mCommodityDetailsFragment = CommodityDetailsFragment.newInstance(commodityid);
        fragmentTransaction.add(R.id.info_frame_layout, mCommodityDetailsFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void initListener() {
        mBank.setOnClickListener(this);
        mPromptly_ll.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        switch (view.getId()) {
            case R.id.bank://返回
                onBackPressed();
                break;
            case R.id.promptly_ll://立即购买
                if (!isLogin) {
                    intent = new Intent(CommodityDetailsActivity.this, UserLogingActivity.class);
                    intent.putExtra("ReturnCode", 350);
                    startActivity(intent);
                } else {
                    mCommodityDetailsFragment.placeBuy();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
