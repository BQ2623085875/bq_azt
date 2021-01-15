package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.MyServiceInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateTimeUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MyLoader;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/20.
 */

public class OrderInfoActivity extends BaseActivity implements OnBannerListener {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image_submit)
    ImageView imgSubit;
    @BindView(R.id.img_confirm)
    ImageView imgConfirm;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.mbanner)
    Banner mBanner;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_norms)
    TextView tvNorms;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.img_fukuan)
    ImageView imgFukuan;
    @BindView(R.id.tv_fukuan)
    TextView tvFukuan;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.img_service)
    ImageView imgService;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.img_review)
    ImageView imgReview;
    @BindView(R.id.tv_review)
    TextView tvReview;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.line_order_name)
    AutoLinearLayout lineOrderName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.line_phone)
    AutoLinearLayout linePhone;
    private MyServiceInfoResult.Data.Info info;

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        //获取详情
        getOrderInfo(id);
    }

    private void getOrderInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyServiceInfoResult> myservInfo = service.getMyservInfo(id, User.INSTANCE.getToken());
        myservInfo.enqueue(new Callback<MyServiceInfoResult>() {
            @Override
            public void onResponse(Call<MyServiceInfoResult> call, Response<MyServiceInfoResult> response) {
                if (response.isSuccessful()) {
                    MyServiceInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        setInfo(body.getData().getInfo());
                    } else {
                        ToastUtils.showToast(OrderInfoActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyServiceInfoResult> call, Throwable t) {

            }
        });
    }

    /**
     * 设置详情
     *
     * @param info
     */
    private void setInfo(MyServiceInfoResult.Data.Info info) {
        this.info = info;
        title.setText("预约详情");
        int managerRole = (int) SpUtils.get("managerRole", 0);
        if (managerRole == 1) {
            btn.setVisibility(View.VISIBLE);
//            lineOrderName.setVisibility(View.VISIBLE);
            linePhone.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);
            linePhone.setVisibility(View.GONE);
        }
        lineOrderName.setVisibility(View.GONE);

        List<String> urlList = new ArrayList<>();
        List<MyServiceInfoResult.Data.Info.ServImgs> servImgs = info.getServImgs();
        for (MyServiceInfoResult.Data.Info.ServImgs img : servImgs) {
            urlList.add(Constant.IMG_URL + img.getUrl());
        }
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        mBanner.setImages(urlList);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        mBanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        homeBanner.setBannerTitles(list_title);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        mBanner.setIndicatorGravity(BannerConfig.RIGHT)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

        String introduce = info.getIntroduce();
        if (TextUtils.isEmpty(introduce) || introduce.equals("")) {
            tvName.setText(info.getServiceCategory());
        } else {
            tvName.setText(info.getServiceCategory() + "  " + info.getIntroduce());
        }

        tvPrice.setText("¥" + StringUtils.stringDouble(info.getServicePrice()) + "元/亩");
        tvNorms.setText("");
        //状态
        setStatus(info.getStatus());

        tvNumber.setText(info.getNum() + "亩");
        tvArea.setText(info.getFullName());
        tvDescribe.setText(info.getDescriptionContent());

        tvTime.setText(DateTimeUtil.getDateToString(Long.parseLong(info.getServiceTime()),"yyyy-MM-dd HH:mm:ss"));
        tvOrderPrice.setText("¥"+StringUtils.stringDouble(info.getServicePrice()*info.getNum())+"元");
        tvOrderName.setText(info.getUserName());
        tvPhone.setText(info.getUserMob());
    }

    private void setStatus(int status) {
        switch (status) {
            case 1:
                btn.setText("确认预约");

                imgSubit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_0));
                text2.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_0));
                text3.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_0));
                text4.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));

                break;
            case 2:
                btn.setText("确认已付款");

                imgSubit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_1));
                text2.setBackgroundColor(Color.parseColor("#6ca323"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_0));
                text3.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_0));
                text4.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));

                break;

            case 3:
                btn.setText("确认已服务");

                imgSubit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_1));
                text2.setBackgroundColor(Color.parseColor("#6ca323"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_1));
                text3.setBackgroundColor(Color.parseColor("#6ca323"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_0));
                text4.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));
                break;

            case 4:
                btn.setVisibility(View.VISIBLE);
                btn.setText("立即评价");

                imgSubit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_1));
                text2.setBackgroundColor(Color.parseColor("#6ca323"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_1));
                text3.setBackgroundColor(Color.parseColor("#6ca323"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_1));
                text4.setBackgroundColor(Color.parseColor("#6ca323"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));
                break;

        }
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    protected void initView() {
//        int tabId = getIntent().getIntExtra("tabId", 0);
//        if (tabId == 3) {
//            btn.setText("立即评价");
//        }
//        imgConfirm.setImageResource(R.drawable.queren_1);
////        text1.setText("");
//        text1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_info;
//        return R.layout.activity_order_info2;//改版
    }

    @Override
    protected void otherViewClick(View view) {
        int type = 0;
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;

            case R.id.btn:
                if (info.getStatus() == 4) {//发表评论
                    Intent intent = new Intent(this, AddCommentActivity.class);
                    intent.putExtra("orderInfo", (Serializable) info);
                    startActivityForResult(intent, 127);
                } else if (info.getStatus() == 1) {//确认
                    type = 1;
                    MyservOperate(info.getId(), type);
                } else if (info.getStatus() == 2) {//确认付款
                    type = 2;
                    MyservOperate(info.getId(), type);
                } else if (info.getStatus() == 3) {//确认已服务
                    type = 3;
                    MyservOperate(info.getId(), type);
                }
                break;
        }
    }

    /**
     * 操作预约单
     *
     * @param id
     * @param type
     */
    private void MyservOperate(int id, int type) {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyServiceInfoResult> myServiceInfoResultCall = service.MyservOperate(id, type);
        myServiceInfoResultCall.enqueue(new Callback<MyServiceInfoResult>() {
            @Override
            public void onResponse(Call<MyServiceInfoResult> call, Response<MyServiceInfoResult> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    MyServiceInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        fund();
                        ToastUtils.showToast(OrderInfoActivity.this, body.getResult().getMessage());
                    } else {
                        ToastUtils.showToast(OrderInfoActivity.this, body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<MyServiceInfoResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    //轮播图点击监听
    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 127) {
            fund();
        }
    }

}
