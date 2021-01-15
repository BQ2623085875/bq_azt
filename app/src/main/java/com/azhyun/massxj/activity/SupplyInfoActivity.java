package com.azhyun.massxj.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.SupplyInfoAdapter;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.SupplyInfoResult;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateUtils;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MyLoader;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
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
import rx.functions.Action1;

/**
 * Created by tl on 2018/8/23.
 */

public class SupplyInfoActivity extends BaseActivity implements OnBannerListener{
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
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.btn_name)
    Button btnName;
    @BindView(R.id.btn_contact)
    Button btnContact;
    private int id;
    private SupplyInfoResult.Data supplyInfo;
    private AlertDialog mDialog;
    private SupplyInfoAdapter supplyInfoAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btnContact.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("供应详情");
        id = getIntent().getIntExtra("id", 0);
        String token = (String)SpUtils.get("token", "");
        //获取详情
        getSupplyInfo(id,token);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        View view = LayoutInflater.from(this).inflate(R.layout.recyclerview_empty_layout, null, false);
//        recyclerView.setEmptyView(view);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setLoadingMoreEnabled(false);
//        recyclerView.setPullRefreshEnabled(false);
//        recyclerView.addItemDecoration(new RecycleViewDivider(this, DividerItemDecoration.HORIZONTAL, 2, Color.parseColor("#eeeeee")));
//
//        supplyInfoAdapter = new SupplyInfoAdapter(list, this);
//
//        recyclerView.setAdapter(supplyInfoAdapter);

    }

    private void getSupplyInfo(int id, String token) {
        LoadingDialog.show(SupplyInfoActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        final Call<SupplyInfoResult> supplyInfo = service.getSupplyInfo(id, token);
        supplyInfo.enqueue(new Callback<SupplyInfoResult>() {
            @Override
            public void onResponse(Call<SupplyInfoResult> call, Response<SupplyInfoResult> response) {
                if (response.isSuccessful()) {
                    SupplyInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SupplyInfoResult.Data data = body.getData();
                        //设置详情
                        setSupplyInfo(data);
                    } else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(SupplyInfoActivity.this, body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<SupplyInfoResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_supply_info;
    }

    @Override
    protected void otherViewClick(View view) {
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.btn_contact:

              if (!isLogin){
                  Intent intent = new Intent(SupplyInfoActivity.this,UserLogingActivity.class);
                  intent.putExtra("ActivityName",11);
                  startActivity(intent);
              }else {
                  final View layout = LayoutInflater.from(this).inflate(R.layout.alert_dialog_red, null);

                  final TextView tm = (TextView) layout.findViewById(R.id.dialog_red_message);

                  final AlertDialog.Builder builder = new AlertDialog.Builder(this);

                  tm.setText(supplyInfo.getInfo().getPhone());

                  builder.setView(layout);
                  builder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {

                      @SuppressLint("MissingPermission")
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                          new RxPermissions(SupplyInfoActivity.this)
                                  .requestEach(Manifest.permission.CALL_PHONE)
                                  .subscribe(new Action1<Permission>() {
                                      @Override
                                      public void call(Permission permission) {
                                          if(permission.granted){

                                              Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+supplyInfo.getInfo().getPhone()));
                                              startActivity(intent);
                                              //AppUtil.showShortToast(activity,"您已经授权该权限");

                                          }else{

                                              //未获得授权
                                              ToastUtils.showToast(SupplyInfoActivity.this,"您没有授权该权限，请在设置中打开授权");

                                          }

                                      }
                                  });

                      }
                  }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          dialog.cancel();
                      }
                  }).show();
              }
                break;
        }

    }

    public void setSupplyInfo(SupplyInfoResult.Data supplyInfo) {
        LoadingDialog.cancel();
        this.supplyInfo = supplyInfo;
        //替换session
        SpUtils.put("JSESSIONID",supplyInfo.getJSESSIONID());

        List<SupplyInfoResult.Data.Info.Imgs> imgs = supplyInfo.getInfo().getImgs();

        //设置轮播
        List<String> list = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            list.add(Constant.IMG_URL + imgs.get(i).getUrl());
            this.list.add(imgs.get(i).getUrl());
        }
        supplyInfoAdapter.notifyDataSetChanged();

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        mBanner.setImages(list);
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

        tvTitle.setText(supplyInfo.getInfo().getCategoryName() +"   "+supplyInfo.getInfo().getTitle());//设置标题信息
        tvPrice.setText("¥"+StringUtils.stringDouble(supplyInfo.getInfo().getPrice()));//价格
        tvNorms.setText(supplyInfo.getInfo().getNorms());//规格
        tvNumber.setText("数量:"+((int)supplyInfo.getInfo().getNum())+"");//数量
        tvDescribe.setText(supplyInfo.getInfo().getDescriptionContent());//描述

        Glide.with(this)//头像
                .load(Constant.IMG_URL+supplyInfo.getInfo().getHeadPortrait())
                .into(userImg);
        String phone = supplyInfo.getInfo().getPhone();
        String s = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        if (isLogin){
            tvPhone.setText(phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
        }else {
            tvPhone.setText(s);
        }
        tvTime.setText(DateUtils.convertTimeToFormat(Long.parseLong(supplyInfo.getInfo().getAddTime())) );
        tvStatus.setText(getgetManagerRoleString(supplyInfo.getInfo().getManagerRole()));
        tvArea.setText(supplyInfo.getInfo().getFullName());

        btnName.setText("联系人:"+supplyInfo.getInfo().getContacts());
    }

    @Override
    public void OnBannerClick(int position) {
//        ToastUtils.showToast(this,"你点击了第"+ position+"条轮播");
    }
    //用户角色
    public String getgetManagerRoleString(int managerRole){
        switch (managerRole){
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
}
