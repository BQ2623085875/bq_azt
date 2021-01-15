package com.azhyun.massxj.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.PublishedActivity;
import com.azhyun.massxj.activity.azt.AgriculturalMaterialsMallActivity;
import com.azhyun.massxj.activity.MianMessageActivity;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.activity.azt.CaiPurchaseActivity;
import com.azhyun.massxj.activity.azt.CommodityDetailsActivity;
import com.azhyun.massxj.activity.azt.NewFieldActivity;
import com.azhyun.massxj.activity.azt.OrderActivity;
import com.azhyun.massxj.activity.azt.farmingInsurance.FarmingInsuranceDetailsActivity;
import com.azhyun.massxj.activity.azt.locailtyactivity.LocalityLiveActivity;
import com.azhyun.massxj.bean.HomeArticleShowResult;
import com.azhyun.massxj.bean.HomeDataResult;
import com.azhyun.massxj.bean.PostRegiste1rResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.APKVersionCodeUtils;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MyLoader;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.JSONTool;
import com.azhyun.massxj.utils.azt.ShijianChuo;
import com.azhyun.massxj.utils.baidulbs.BDLocationUtils;
import com.azhyun.massxj.utils.baidulbs.Const;
import com.azhyun.massxj.utils.update.UpdateAppUtils;
import com.azhyun.massxj.view.BadgeView;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.MarqueeView;
import com.azhyun.massxj.x5webview.TencentBrowserActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.azhyun.massxj.utils.LoadingDialog.cancel;

public class HomeFragment2 extends LazyLoadFragment implements View.OnClickListener, OnBannerListener {

    protected final String TAG = "HomeFragment2";

    @BindView(R.id.title_address)
    TextView titleAddress;
    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.home_trusteeship)
    LinearLayout homeTrusteeship;
    @BindView(R.id.home_farming)
    LinearLayout homeFarming;
    @BindView(R.id.home_lobby)
    LinearLayout homeLobby;
    @BindView(R.id.home_message)
    LinearLayout homeMessage;
    @BindView(R.id.marquee_view)
    MarqueeView marqueeView;
    @BindView(R.id.home_combo)
    LinearLayout homeCombo;
    @BindView(R.id.home_trusteeship2)
    LinearLayout homeTrusteeship2;
    @BindView(R.id.home_sow)
    LinearLayout homeSow;
    @BindView(R.id.home_pesticide)
    LinearLayout homePesticide;
    @BindView(R.id.home_harvest)
    LinearLayout homeHarvest;
    @BindView(R.id.home_my_trusteeship)
    LinearLayout homeMyTrusteeship;
    @BindView(R.id.home_release)
    LinearLayout homeRelease;
    @BindView(R.id.img_sow)
    ImageView imgSow;
    @BindView(R.id.tv_sow_name)
    TextView tvSowName;
    @BindView(R.id.tv_sow_fullname)
    TextView tvSowFullname;
    @BindView(R.id.img_pesticide)
    ImageView imgPesticide;
    @BindView(R.id.tv_pesticide_name)
    TextView tvPesticideName;
    @BindView(R.id.tv_pesticide_fullname)
    TextView tvPesticideFullname;
    @BindView(R.id.img_harvest)
    ImageView imgHarvest;
    @BindView(R.id.tv_harvest_name)
    TextView tvHarvestName;
    @BindView(R.id.tv_harvest_fullname)
    TextView tvHarvestFullname;
    @BindView(R.id.tv_combo_name)
    TextView tvComboName;
    @BindView(R.id.tv_combo_fullname)
    TextView tvComboFullname;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.title_message)
    ImageView titleMessage;
    @BindView(R.id.mswipeRefreshLayout)
    SwipeRefreshLayout mswipeRefreshLayout;
    @BindView(R.id.home_nb_zx)
    LinearLayout home_nb_zx;
    @BindView(R.id.tuijianll)
    LinearLayout tuijianll;

    private BDLocationUtils bdLocationUtils;
    private BadgeView badgeView;
    private PostRegiste1rResult.Data UpDate;
    private LocationManager lm;
    private HomeDataResult.DataBeanX data;

    private final int LOCATION_CODE = 1;
    private int page = 1;
    private int pagerow = 3;
    private int CommId1;
    private int CommId2;
    private int CommId3;
    private String district;
    private String cityCode;
    private String region = "";
    private String deviceSoftwareVersion;

    private List<HomeDataResult.DataBeanX.BannersBean> banners = new ArrayList<>();
    private List<HomeArticleShowResult.Data.Rows> rows = new ArrayList<>();
    private boolean isLogin;
    private boolean isGX = false;

    private boolean tuijian1 = false;
    private boolean tuijian2 = false;
    private boolean tuijian3 = false;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        Log.d("区域id", "====: " + User.INSTANCE.getRegion());
        Log.d("token", "====: " + User.INSTANCE.getToken());
        Log.d("userId", "====: " + SpUtils.get("userId", ""));
        Log.d("区域地址", "====: " + SpUtils.get("fullName", ""));

        this.region = User.INSTANCE.getRegion();
        isLogin = (boolean) SpUtils.get("isLogin", false);

        homeFarming.setOnClickListener(this);
        homeLobby.setOnClickListener(this);
        homeCombo.setOnClickListener(this);
        homeSow.setOnClickListener(this);
        homeRelease.setOnClickListener(this);
        homeHarvest.setOnClickListener(this);
        homePesticide.setOnClickListener(this);
        homeMyTrusteeship.setOnClickListener(this);
        homeTrusteeship.setOnClickListener(this);
        homeMessage.setOnClickListener(this);
        homeTrusteeship2.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        titleMessage.setOnClickListener(this);
        mswipeRefreshLayout.setOnClickListener(this);
        home_nb_zx.setOnClickListener(this);

        /*
        * 获取版本号
        * */
        String ANDROID_ID = Settings.System.getString(getActivity().getContentResolver(), Settings.System.ANDROID_ID);
//        getVersion(ANDROID_ID);

        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
            public void onRefresh() {
                getData();//首页信息
                mswipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onClick(View v) {
        String token = (String) SpUtils.get("token", "");
        Intent intent = null;
        String region = "";
        switch (v.getId()) {
            case R.id.home_farming://农资商城
                intent = new Intent(getContext(), AgriculturalMaterialsMallActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, 130);
                break;
            case R.id.home_lobby://本地生活
//                ToastUtils.showToast(getContext(), "暂未开通");
                intent = new Intent(getContext(), LocalityLiveActivity.class);
                startActivityForResult(intent, 130);
                break;
            case R.id.home_nb_zx://农保中心
                if (isLogin) {
                    intent = new Intent(getContext(), AgriculturalMaterialsMallActivity.class);
                    intent.putExtra("type", 1);
                    startActivityForResult(intent, 130);
                } else {
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
                }                break;
            case R.id.home_combo://我的售后服务
                ToastUtils.showToast(getContext(), "暂未开通");
                break;
            case R.id.home_sow://推荐商品（1）
                if (tuijian1) {
                    intent = new Intent(getContext(), CommodityDetailsActivity.class);
                    intent.putExtra("commodityid", CommId1);
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(getContext(), "暂无数据!");
                }
                break;
            case R.id.home_pesticide://推荐商品（2）
                if (tuijian2) {
                    intent = new Intent(getContext(), CommodityDetailsActivity.class);
                    intent.putExtra("commodityid", CommId2);
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(getContext(), "暂无数据!");
                }
                break;
            case R.id.home_harvest://推荐商品（3）
                if (tuijian3) {
                    intent = new Intent(getContext(), CommodityDetailsActivity.class);
                    intent.putExtra("commodityid", CommId3);
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(getContext(), "暂无数据!");
                }
                break;
            case R.id.home_release://发布供需
//                ToastUtils.showToast(getContext(), "暂未开通");
                intent = new Intent(getContext(), PublishedActivity.class);
                startActivityForResult(intent, 130);
                break;
            case R.id.home_my_trusteeship: //定制方案
                ToastUtils.showToast(getContext(), "暂未开通");
                break;
            case R.id.home_trusteeship://新品田
                intent = new Intent(getContext(), NewFieldActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.home_trusteeship2://采收服务
                intent = new Intent(getContext(), CaiPurchaseActivity.class);
                startActivityForResult(intent, 130);
                break;
            case R.id.home_message://信息通知
                ToastUtils.showToast(getContext(), "暂未开通");
                break;
            case R.id.tv_more://更多
                intent = new Intent(getContext(), AgriculturalMaterialsMallActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, 130);
                break;
            case R.id.title_message://消息
                if (isLogin) {
                    intent = new Intent(getContext(), MianMessageActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivityForResult(intent, 130);
                }
                break;
        }
    }

    private void updateDialog(final PostRegiste1rResult.Data data) {
        this.UpDate = data;
        final Dialog dialog = new Dialog(getActivity(), R.style.myNewsDialogStyle);

        // 自定义对话框布局
        View layout = View.inflate(getActivity(), R.layout.home_undate_dialog, null);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new UpDateAdapter(data.getUpdateContent()));

        TextView tvVersion = (TextView) layout.findViewById(R.id.tv_version);
        ImageView imageView = (ImageView) layout.findViewById(R.id.img_upgrade);
        ImageView imgX = (ImageView) layout.findViewById(R.id.img_x);

        tvVersion.setText(data.getShowVersion());

        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                String url = "";
                if (data.getPackageName().contains("http")) {
                    url = data.getPackageName();
                } else {
                    url = Constant.AI_APK + data.getPackageName();
                }

                Log.e("----->>", url);
                String Version_name = "1.1";//版本名称
                String info = "";  //更新说明
                int Forced = 1;// 1：强制更新   0：不是
                int Version_no = Integer.parseInt(data.getVersion());//版本号
                UpdateAppUtils.UpdateApp(getActivity(), HomeFragment2.this, Version_no, Version_name, info, url, Forced == 1 ? true : false, true);

            }
        });
        dialog.setContentView(layout);
        dialog.show();

    }

    @Override
    protected int setContentView() {
        bdLocationUtils = new BDLocationUtils(getContext(), new MyLocationListener());
        quanxian();
        return R.layout.fragment_main;
    }

    public void quanxian() {
        lm = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.e("BRG", "没有权限");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);

            } else {

                bdLocationUtils.doLocation();//开启定位

            }
        } else {
            Log.e("BRG", "系统检测到未开启GPS定位服务");
            Toast.makeText(getActivity(), "系统检测到未开启GPS定位服务", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 1315);
        }
    }

    @Override
    protected void lazyLoad() {
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        if (isLogin) {
            titleAddress.setText((String) SpUtils.get("regionName", ""));//地址
        } else {
            if (bdLocationUtils == null) {
                bdLocationUtils = new BDLocationUtils(getContext(), new MyLocationListener());
            }
            bdLocationUtils.doLocation();//开启定位
            bdLocationUtils.mLocationClient.start();
        }

        marqueeView.removeAllViews();

        getData();//首页信息

    }

    @Override
    public void OnBannerClick(int position) {

    }

    //首页信息
    private void getData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<HomeDataResult> home = service.getHome(User.INSTANCE.getToken());
        home.enqueue(new Callback<HomeDataResult>() {
            @Override
            public void onResponse(Call<HomeDataResult> call, Response<HomeDataResult> response) {
                if (response.isSuccessful()) {
                    HomeDataResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        data = body.getData();
                        setVIew();
                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<HomeDataResult> call, Throwable t) {
                cancel();
            }
        });
    }

    /**
     * 设置数据
     */
    private void setVIew() {
        //轮播
        badgeView = new BadgeView(getContext());
        badgeView.setBadgeCount(data.getArticleNum());
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);
        badgeView.setTargetView(imgMessage);

        banners = data.getBanners();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < this.banners.size(); i++) {
            list.add(Constant.IMG_URL + this.banners.get(i).getUrl());
        }


        if (homeBanner != null) {
            homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片加载器
            homeBanner.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            homeBanner.setImages(list);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            homeBanner.setBannerAnimation(Transformer.Default);
            //设置轮播图的标题集合
            //homeBanner.setBannerTitles(list_title);
            //设置轮播间隔时间
            homeBanner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            homeBanner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            homeBanner.setIndicatorGravity(BannerConfig.RIGHT)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(this)
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }

        //设置通知
        List<HomeDataResult.DataBeanX.ArticlesBean> articles = data.getArticles();
        List<String> articlesList = new ArrayList<>();
        articlesList.clear();
        if (articles != null) {
            for (int i = 0; i < data.getArticles().size(); i++) {
                articlesList.add(data.getArticles().get(i).getTitle());
            }
            marqueeView.startWithList(articlesList);
            marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                @Override
                public void onItemClick(int position, LinearLayout linearLayout) {
                    Intent intent = null;
                    if (isLogin) {
                        if (data.getArticles().size() != 0) {
                            intent = new Intent(getContext(), TencentBrowserActivity.class);
                            intent.putExtra("url", Constant.IMG_URL + data.getArticles().get(position).getContent());
                            intent.putExtra("title", "文章详情");
                            intent.putExtra("shi", ShijianChuo.getYMDTiem(data.getArticles().get(position).getAddTime()));
                            intent.putExtra("name", data.getArticles().get(position).getTitle());
                            intent.putExtra("lai", data.getArticles().get(position).getSource());
                            startActivity(intent);
                        }
                    } else {
                        intent = new Intent(getContext(), UserLogingActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } else {
            articlesList.add("暂无数据");
        }

        /*
         * 推荐商品
         * */
        List<HomeDataResult.DataBeanX.ItemBean> item = data.getItem();
        if (item.size() == 0) {
            tuijianll.setVisibility(ViewGroup.GONE);
        } else {
            tuijianll.setVisibility(ViewGroup.VISIBLE);
            //推荐商品（1）
            if (item.size() > 1) {
                HomeDataResult.DataBeanX.ItemBean itemBean0 = item.get(0);
                Glide.with(getContext()).load(Constant.IMG_URL + itemBean0.getDefaultImg()).into(imgSow);
                tvSowName.setText("【" + itemBean0.getBrand() + "】" + itemBean0.getOnlineTitle());
                CommId1 = itemBean0.getId();
                if (itemBean0.getPrice() > 0) {
                    tvSowFullname.setText("¥" + new DecimalFormat("#0.00").format(itemBean0.getPrice()));
                    tuijian1 = true;
                } else {
                    tvSowFullname.setText("¥0.00");
                    tuijian1 = false;
                }
            }
            //推荐商品（2）
            if (item.size() >= 2) {
                HomeDataResult.DataBeanX.ItemBean itemBean1 = item.get(1);
                Glide.with(getContext()).load(Constant.IMG_URL + itemBean1.getDefaultImg()).into(imgPesticide);
                tvPesticideName.setText("【" + itemBean1.getBrand() + "】" + itemBean1.getOnlineTitle());
                CommId2 = itemBean1.getId();
                if (itemBean1.getPrice() > 0) {
                    tvPesticideFullname.setText("¥" + new DecimalFormat("#0.00").format(itemBean1.getPrice()));
                    tuijian2 = true;
                } else {
                    tvPesticideFullname.setText("¥0.00");
                    tuijian2 = false;
                }
            }
            //推荐商品（3）
            if (item.size() == 3) {
                HomeDataResult.DataBeanX.ItemBean itemBean2 = item.get(2);
                Glide.with(getContext()).load(Constant.IMG_URL + itemBean2.getDefaultImg()).into(imgHarvest);
                tvHarvestName.setText("【" + itemBean2.getBrand() + "】" + itemBean2.getOnlineTitle());
                CommId3 = itemBean2.getId();
                if (itemBean2.getPrice() > 0) {
                    tvHarvestFullname.setText("¥" + new DecimalFormat("#0.00").format(itemBean2.getPrice()));
                    tuijian3 = true;
                } else {
                    tvHarvestFullname.setText("¥0.00");
                    tuijian3 = false;
                }
            }

        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            getData();

            isCanLoadData();
//            boolean isLogin = (boolean)SpUtils.get("isLogin", false);
//            if (isLogin){
//                titleAddress.setText(User.INSTANCE.getRegionName());
//            }else {
////                BDLocationUtils bdLocationUtils = new BDLocationUtils(getContext(),new MyLocationListener());
////                bdLocationUtils.doLocation();//开启定位
//                bdLocationUtils.mLocationClient.start();//开始定位
//            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        isCanLoadData();

        if (requestCode == 130) {
            getData();
        }
        //如果是从设置界面返回,就继续判断权限
        if (requestCode == UpdateAppUtils.REQUEST_PERMISSION_SDCARD_SETTING) {
//                UpdateAppUtils.checkPermission();
            updateDialog(UpDate);
        }
    }

    //=--------更新---

    private void getVersion(String deviceSoftwareVersion) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<PostRegiste1rResult> postRegiste1rResultCall = service.PostRegiste1r(deviceSoftwareVersion, 1);//1.安卓，2.ios
        postRegiste1rResultCall.enqueue(new Callback<PostRegiste1rResult>() {
            @Override
            public void onResponse(Call<PostRegiste1rResult> call, Response<PostRegiste1rResult> response) {
                if (response.isSuccessful()) {
                    PostRegiste1rResult body = response.body();
                    Log.d("gengxin", "onResponse: " + JSONTool.format(new Gson().toJson(body)));
                    if (body.getResult().getCode().equals("200")) {
                        //判断是否需要更新
                        isUpdate(body.getData());
                    } else if (!body.getResult().getCode().equals("-1")) {

                    }
                }
            }

            @Override
            public void onFailure(Call<PostRegiste1rResult> call, Throwable t) {

            }
        });
    }

    //判断是否需要更新
    private void isUpdate(PostRegiste1rResult.Data data) {
        int versionCode = APKVersionCodeUtils.getVersionCode(getContext());
        if (versionCode < Integer.parseInt(data.getVersion())) {
//            EventBus.getDefault().register(this);
            updateDialog(data);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class UpDateAdapter extends RecyclerView.Adapter<UpDateAdapter.UpAdateHolder> {
        private final String string;

        public UpDateAdapter(String updateContent) {
            this.string = updateContent;
        }

        @Override
        public UpAdateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item__update_content_recyclerview, parent, false);
            return new UpAdateHolder(view);
        }

        @Override
        public void onBindViewHolder(UpAdateHolder holder, int position) {
            holder.tvContent.setText(string);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        public class UpAdateHolder extends RecyclerView.ViewHolder {

            private final TextView tvContent;

            public UpAdateHolder(View itemView) {
                super(itemView);
                tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // 权限被用户同意。

                } else {
                    // 权限被用户拒绝了。
                    Toast.makeText(getActivity(), "定位权限被禁止，相关地图功能无法使用！", Toast.LENGTH_LONG).show();
                }

            }
        }
        UpdateAppUtils.onActRequestPermissionsResult(requestCode, permissions, grantResults, getActivity(), HomeFragment2.this);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取定位结果
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());    // 单位：公里每小时

                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());    //获取卫星数

                sb.append("\nheight : ");
                sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                sb.append("\ndirection : ");
                sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");


            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息

            List<Poi> list = location.getPoiList();    // POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }

            //现在已经定位成功，可以将定位的数据保存下来，这里我新建的一个Const类，保存全局静态变量
            Const.LONGITUDE = location.getLongitude();
            Const.LATITUDE = location.getLatitude();
            district = location.getDistrict();
            if (district!=null) {
                titleAddress.setText(district);
            }else {
                titleAddress.setText("暂无定位");
            }
//        Const.ADDRESS = location.getAddrStr();
            Const.ADDRESS = location.getDistrict();
            region = cityCode = location.getAdCode();


        }
    }

}
