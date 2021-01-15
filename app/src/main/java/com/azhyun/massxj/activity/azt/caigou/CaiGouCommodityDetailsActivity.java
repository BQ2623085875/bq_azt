package com.azhyun.massxj.activity.azt.caigou;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.CaiGouXQBean;
import com.azhyun.massxj.fragment.azt.CommodityDetailsFragment;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 采购商品详情
* */
public class CaiGouCommodityDetailsActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.bank)
    ImageView mBank;
    @BindView(R.id.info_frame_layout)
    FrameLayout mInfo_frame_layout;
    @BindView(R.id.promptly_ll)
    LinearLayout mPromptly_ll;

    private FragmentManager mSupportFragmentManager;
    private CaiGouCommodityDetailsFragment mCommodityDetailsFragment;

    private int commodityid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_caigou_commodity_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();


        //商品id
        commodityid = getIntent().getIntExtra("commodityid", 0);
        int type = getIntent().getIntExtra("type", 0);

        mSupportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        mCommodityDetailsFragment = CaiGouCommodityDetailsFragment.newInstance(commodityid, type);
        fragmentTransaction.add(R.id.info_frame_layout, mCommodityDetailsFragment);
        fragmentTransaction.commit();

        Log.d("commodityid", "onResume: ");
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<CaiGouXQBean> commodity = service.getCaiGouXQ(commodityid, (String) SpUtils.get("userId", ""));
        commodity.enqueue(new Callback<CaiGouXQBean>() {
            @Override
            public void onResponse(Call<CaiGouXQBean> call, Response<CaiGouXQBean> response) {
                if (response.isSuccessful()) {
                    CaiGouXQBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        if (body.getData().getIsHarvestItem() == 0) {

                        } else {
                            mPromptly_ll.setBackgroundResource(R.color.gray_press);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CaiGouXQBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("commodityid", "onRestart: ");
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
                    intent = new Intent(CaiGouCommodityDetailsActivity.this, UserLogingActivity.class);
                    intent.putExtra("ReturnCode", 250);
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
