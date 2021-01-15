package com.azhyun.massxj.activity;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.MySupplyInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.SupplyInfoResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateUtils;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.MyLoader;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/23.
 */

public class MineSupplyInfoActivity extends BaseActivity implements OnBannerListener {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_norms)
    TextView tvNorms;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.user_img)
    CircleImageView userImg;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_offline)
    TextView tvOffline;
    private int id;
    private SupplyInfoResult.Data supplyInfo;
    private AlertDialog mDialog;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        tvOffline.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("供应详情");
        id = getIntent().getIntExtra("id", 0);

        //获取详情
        getSupplyInfo(id);
    }

    private void getSupplyInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        final Call<MySupplyInfoResult> supplyInfo = service.getMySupplyInfo(id, User.INSTANCE.getToken());
        supplyInfo.enqueue(new Callback<MySupplyInfoResult>() {
            @Override
            public void onResponse(Call<MySupplyInfoResult> call, Response<MySupplyInfoResult> response) {
                if (response.isSuccessful()) {
                    MySupplyInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        MySupplyInfoResult.Data data = body.getData();
                        //设置详情
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        setSupplyInfo(body.getData().getInfo());
                    } else {
                        ToastUtils.showToast(MineSupplyInfoActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MySupplyInfoResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_supply_info;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.tv_offline:
                //下线
                MySupplyOperate();
                break;
        }
    }

    private void MySupplyOperate() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.MySupplyOperate(id, User.INSTANCE.getToken(), -1);
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        ToastUtils.showToast(MineSupplyInfoActivity.this,body.getResult().getMessage());
                        fund();
                    }else {
                        ToastUtils.showToast(MineSupplyInfoActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }

    public void setSupplyInfo(MySupplyInfoResult.Data.Info supplyInfo) {

        List<MySupplyInfoResult.Data.Info.Imgs> imgs = supplyInfo.getImgs();
        List<String> urlList = new ArrayList<>();
        for (MySupplyInfoResult.Data.Info.Imgs imgs1:imgs){
            urlList.add(Constant.IMG_URL+imgs1.getUrl());
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

        tvTitle.setText(supplyInfo.getCategoryName()+"   "+supplyInfo.getTitle());
        tvPrice.setText("¥"+StringUtils.stringDouble(supplyInfo.getPrice())+"");
        tvNorms.setText(supplyInfo.getNorms());
        tvNumber.setText("数量:"+(int)(supplyInfo.getNum()));
        tvDescribe.setText(supplyInfo.getDescriptionContent());
        Glide.with(this)
                .load(Constant.IMG_URL + supplyInfo.getHeadPortrait())
                .error(R.drawable.err)
                .into(userImg);
        String a=supplyInfo.getPhone();
        tvPhone.setText(a.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
        tvStatus.setText(getStatus(supplyInfo.getManagerRole()));
        tvTime.setText(DateUtils.convertTimeToFormat(Long.parseLong(supplyInfo.getAddTime())));
        tvArea.setText(supplyInfo.getFullName());

    }

    private String getStatus(long managerRole) {
        switch ((int) managerRole){
            case 0:
                return "普通用户";
            case 1:
                return "村级理事长";
            case 2:
                return "乡级理事长";
            case 3:
                return "服务商";
            case 4:
                return "县级理事长";
            case 5:
                return "省级理事长";

        }
        return "";
    }

    @Override
    public void OnBannerClick(int position) {
//        ToastUtils.showToast(this, "你点击了第" + position + "条轮播");
    }


}
