package com.azhyun.massxj.activity.azt.farmingInsurance;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;

import butterknife.BindView;

/*
* 农保中心-详情
* */
public class FarmingInsuranceDetailsActivity extends BaseActivity {
    protected final String TAG = "FarmingInsuranceDetailsActivity";

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.prico)
    TextView prico;
    @BindView(R.id.yuding)
    TextView yuding;


    private int id;
    private String price;
    private String title1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_farming_insurance_details;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra("id", 0);
        price = getIntent().getStringExtra("price");
        title1 = getIntent().getStringExtra("title");

        prico.setText(price);

    }

    @Override
    protected void initListener() {
        title.setText(title1);
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

        bank.setOnClickListener(this);
        yuding.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        loadUrl("https://yun.51zhongzi.com/azt_insurance/index.html?id=" + id);
    }

    private void loadUrl(String url) {

        if (Build.VERSION.SDK_INT > 21) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(url);//显示远程网页

    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.yuding:
                intent = new Intent(FarmingInsuranceDetailsActivity.this, NxReserveActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
    }
}
