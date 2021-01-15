package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.FarmingServiceActivity;
import com.azhyun.massxj.activity.MyTrusteeshipActivity;
import com.azhyun.massxj.activity.PublishedActivity;
import com.azhyun.massxj.activity.SupplyLobbyActivity;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.bean.HomeDataResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.MyLoader;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.baidulbs.Const;
import com.azhyun.massxj.view.MarqueeView;
import com.azhyun.massxj.x5webview.TencentBrowserActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, OnBannerListener {
    @BindView(R.id.title_address)
    TextView titleAddress;
    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.home_trusteeship)
    AutoLinearLayout homeTrusteeship;
    @BindView(R.id.home_farming)
    AutoLinearLayout homeFarming;
    @BindView(R.id.home_lobby)
    AutoLinearLayout homeLobby;
    @BindView(R.id.home_message)
    AutoLinearLayout homeMessage;
    @BindView(R.id.marquee_view)
    MarqueeView marqueeView;
    @BindView(R.id.home_combo)
    AutoRelativeLayout homeCombo;
    @BindView(R.id.home_trusteeship2)
    AutoRelativeLayout homeTrusteeship2;
    @BindView(R.id.home_sow)
    AutoRelativeLayout homeSow;
    @BindView(R.id.home_pesticide)
    AutoRelativeLayout homePesticide;
    @BindView(R.id.home_harvest)
    AutoRelativeLayout homeHarvest;
    @BindView(R.id.home_my_trusteeship)
    AutoLinearLayout homeMyTrusteeship;
    @BindView(R.id.home_release)
    AutoLinearLayout homeRelease;
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
//    @BindView(R.id.img_combo)
//    ImageView imgCombo;
    private HomeDataResult.DataBeanX data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            getData();
        } else {
            //相当于Fragment的onPause
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (User.INSTANCE.getJSESSIONID().equals("") || User.INSTANCE.getJSESSIONID() == null){
            Log.e("---定位---->",Const.ADDRESS+"");
            Log.e("---定位----2>",Const.LATITUDE+"");
            Log.e("---定位----3>",Const.LONGITUDE+"");
            titleAddress.setText(Const.ADDRESS);
        }else {
            titleAddress.setText(User.INSTANCE.getRegionName());
        }
    }

    private void initView() {


//        if (User.token.isEmpty() || User.token.equals(null)){
//            Intent intent = new Intent(getContext(), UserLogingActivity.class);
//            startActivity(intent);
//        }

        //获取首页数据
//        getData();

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


    }

    private void getData() {
//        HttpService service = ServiceGenerator.createService(HttpService.class);
//        Call<HomeDataResult> home = service.getHome(User.INSTANCE.getToken());
//        home.enqueue(new Callback<HomeDataResult>() {
//            @Override
//            public void onResponse(Call<HomeDataResult> call, Response<HomeDataResult> response) {
//                if (response.isSuccessful()) {
//                    HomeDataResult body = response.body();
//                    if (body.getResult().getCode().equals("200")) {
//                        data = body.getData();
////                        setVIew();
//                    } else {
//                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
//                    }
//                }else {
//                Log.e("   ------", response.errorBody().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HomeDataResult> call, Throwable t) {
//                ToastUtils.showToast(getContext(), t.getMessage());
//            }
//        });
    }

    /**
     * 设置数据
     */
//    private void setVIew() {
//
//
//        //轮播
//        List<HomeDataResult.Data.Banners> banners = data.getBanners();
//
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < banners.size(); i++) {
//            list.add(Constant.IMG_URL + banners.get(i).getUrl());
//        }
//
//        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        //设置图片加载器
//        homeBanner.setImageLoader(new MyLoader());
//        //设置图片网址或地址的集合
//        homeBanner.setImages(list);
//        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
//        homeBanner.setBannerAnimation(Transformer.Default);
//        //设置轮播图的标题集合
////        homeBanner.setBannerTitles(list_title);
//        //设置轮播间隔时间
//        homeBanner.setDelayTime(3000);
//        //设置是否为自动轮播，默认是“是”。
//        homeBanner.isAutoPlay(true);
//        //设置指示器的位置，小点点，左中右。
//        homeBanner.setIndicatorGravity(BannerConfig.RIGHT)
//                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
//                .setOnBannerListener(this)
//                //必须最后调用的方法，启动轮播图。
//                .start();
//
//
//        List<HomeDataResult.Data.ServiceCategory> serviceCategory = data.getServiceCategory();
//
//        if (serviceCategory.size() >= 4){
//            //设置分类列表
//            tvComboName.setText(serviceCategory.get(0).getName());
//            tvComboFullname.setText(serviceCategory.get(0).getFullName());
//
//            tvSowName.setText(serviceCategory.get(1).getName());
//            tvSowFullname.setText(serviceCategory.get(1).getFullName());
//
//            tvPesticideName.setText(serviceCategory.get(2).getName());
//            tvPesticideFullname.setText(serviceCategory.get(2).getFullName());
//
//            tvHarvestName.setText(serviceCategory.get(3).getName());
//            tvHarvestFullname.setText(serviceCategory.get(3).getFullName());
//        }
//
//
//        //设置通知
//        List<HomeDataResult.Data.Articles> articles = data.getArticles();
//        List<String> articlesList = new ArrayList<>();
//        for (HomeDataResult.Data.Articles article:articles){
//            articlesList.add(article.getIntroduce());
//        }
//        marqueeView.startWithList(articlesList);
//    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.home_farming://农事服务
                if (User.INSTANCE.getToken().isEmpty()){
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(), FarmingServiceActivity.class);
                    intent.putExtra("type", 0);
                    startActivity(intent);
                }

                break;
            case R.id.home_lobby://供应大厅
                intent = new Intent(getContext(), SupplyLobbyActivity.class);
                intent.putExtra("num", 0);
                startActivity(intent);
                break;
            case R.id.home_combo://种肥药
                if (User.INSTANCE.getToken().isEmpty()){
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(), FarmingServiceActivity.class);
                    intent.putExtra("type", 0);
                    startActivity(intent);
                }

                break;
            case R.id.home_sow://耕种
                if (User.INSTANCE.getToken().isEmpty() ){
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(), FarmingServiceActivity.class);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                }

                break;
            case R.id.home_pesticide://3-2
                if (User.INSTANCE.getToken().isEmpty()){
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(), FarmingServiceActivity.class);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                }

                break;
            case R.id.home_harvest://3-3

                if (User.INSTANCE.getToken().isEmpty()){
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(), FarmingServiceActivity.class);
                    intent.putExtra("type", 3);
                    startActivity(intent);
                }
                break;


            case R.id.home_release://发布供需
                if (User.INSTANCE.getToken().isEmpty() ){
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getActivity(), PublishedActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.home_my_trusteeship: // 我要托管
                if (User.INSTANCE.getToken().isEmpty() ){
                    intent = new Intent(getContext(), UserLogingActivity.class);
                    startActivity(intent);
               }else {
                    intent = new Intent(getContext(), MyTrusteeshipActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.home_trusteeship:
                intent = new Intent(getContext(), TencentBrowserActivity.class);//region=&token=
                intent.putExtra("url","http://test-hmq.51zhongzi.com:8070/tudi/tudi.html?"+"token="+User.INSTANCE.getToken()+"&region="+User.INSTANCE.getRegion());
                intent.putExtra("title","土地托管");
                startActivity(intent);
                break;

            case R.id.home_trusteeship2:
                intent = new Intent(getContext(), TencentBrowserActivity.class);//region=&token=
                intent.putExtra("url","http://test-hmq.51zhongzi.com:8070/tudi/tudi.html?"+"token="+User.INSTANCE.getToken()+"&region="+User.INSTANCE.getRegion());
                intent.putExtra("title","土地托管");
                startActivity(intent);
                break;

            case R.id.home_message://""
                intent = new Intent(getContext(), TencentBrowserActivity.class);//region=&token=
                intent.putExtra("url","http://test-hmq.51zhongzi.com:8070/tudi/xinxi.html?"+"token="+User.INSTANCE.getToken()+"&region="+User.INSTANCE.getRegion());
                intent.putExtra("title","信息通知");
                startActivity(intent);

                break;
        }
    }


    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }


//    public void getRegion(String city) {
//        titleAddress.setText(city);
//    }
}
