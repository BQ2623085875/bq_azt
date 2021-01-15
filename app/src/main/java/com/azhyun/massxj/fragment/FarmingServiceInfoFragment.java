package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.FarmingServiceInfoActivity;
import com.azhyun.massxj.activity.FarmingServiceOrderActivity;
import com.azhyun.massxj.bean.FarmingInfo;
import com.azhyun.massxj.bean.ServiceInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.MyLoader;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.SpaceItemDecoration;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/23.
 */

public class FarmingServiceInfoFragment extends LazyLoadFragment implements View.OnClickListener ,OnBannerListener{
    @BindView(R.id.tv_all_review)
    TextView tvAllReview;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.tv_title)
    TextView tvTitle;
     @BindView(R.id.tv_introduction)
    TextView tvIntroduction;

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_norms)
    TextView tvNorms;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_evaluations_number)
    TextView tvEvaluationsNumber;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private ServiceInfoResult.Data.Info info;


    public static FarmingServiceInfoFragment newIntent(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        FarmingServiceInfoFragment fragment = new FarmingServiceInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        tvAllReview.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.item_fragment_farming_service_info;
    }

    @Override
    protected void lazyLoad() {
        Bundle arguments = getArguments();
        int id = arguments.getInt("id");
        getFramingServiceInfo(id);
    }

    /**
     * 获取详情
     *
     * @param id
     */
    private void getFramingServiceInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ServiceInfoResult> serviceInfo = service.getServiceInfo(id, User.INSTANCE.getToken());
        serviceInfo.enqueue(new Callback<ServiceInfoResult>() {
            @Override
            public void onResponse(Call<ServiceInfoResult> call, Response<ServiceInfoResult> response) {
                if (response.isSuccessful()) {
                    ServiceInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        setServiceInfo(body.getData().getInfo());
                    }
                }
            }

            @Override
            public void onFailure(Call<ServiceInfoResult> call, Throwable t) {

            }
        });
    }

    /**
     * 设置详情
     *
     * @param info
     */
    private void setServiceInfo(ServiceInfoResult.Data.Info info) {
        this.info = info;
        FarmingInfo.info = info;
        List<ServiceInfoResult.Data.Info.Imgs> imgs = info.getImgs();
        //设置banner
        //设置轮播
        List<String> list = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            list.add(Constant.IMG_URL + imgs.get(i).getUrl());
        }

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


        tvTitle.setText(info.getCategoryName());
        tvIntroduction.setText(info.getIntroduce());
        tvPrice.setText("¥"+StringUtils.stringDouble(info.getPrice()));
        tvNorms.setText("元/亩");
        tvNumber.setText(info.getMinimumQty()+"亩");
        tvArea.setText(info.getRegionName()+"");
        tvDescribe.setText(info.getDescriptionContent());
        tvEvaluationsNumber.setText("评价("+info.getEvalNum()+")");

        //设置评论
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), DividerItemDecoration.HORIZONTAL, 2, Color.parseColor("#eeeeee")));

        recyclerView.setAdapter(new MyEvaluationAdapter(info.getEvaluations()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all_review:
                FarmingServiceInfoActivity activity = (FarmingServiceInfoActivity) getActivity();
                activity.setnext();

                break;
            case R.id.btn_order:
                Intent intent = new Intent(getContext(), FarmingServiceOrderActivity.class);

                intent.putExtra("info",(Serializable) info);
                startActivity(intent);
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnBannerClick(int position) {//banner 点击

    }

    private class MyEvaluationAdapter extends RecyclerView.Adapter<MyEvaluationAdapter.MyEvaluationHolder> {
        private final List<ServiceInfoResult.Data.Info.Evaluations> list;

        public MyEvaluationAdapter(List<ServiceInfoResult.Data.Info.Evaluations> evaluations) {
            this.list = evaluations;
        }

        @Override
        public MyEvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_evaluation, parent, false);
            return new MyEvaluationHolder(view);
        }

        @Override
        public void onBindViewHolder(MyEvaluationHolder holder, int position) {
            Glide.with(MyApplication.getContext())
                    .load(Constant.IMG_URL + list.get(position).getHeadPortrait())
                    .error(R.drawable.err)
                    .into(holder.img);
            holder.tvPhone.setText(list.get(position).getUserName());
            holder.tvContent.setText(list.get(position).getContent());
            if (list.get(position).getIsImg() == 1){//有图
                holder.imgRecyclerView.setVisibility(View.VISIBLE);
                holder.imgRecyclerView.setLayoutManager(new GridLayoutManager(holder.imgRecyclerView.getContext(),4));
                holder.imgRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(getContext(), 5), 4));
                holder.imgRecyclerView.setAdapter(new MyImgAdapter(list.get(position).getImgs()));
            }else {
                holder.imgRecyclerView.setVisibility(View.GONE);
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyEvaluationHolder extends RecyclerView.ViewHolder{

            private final ImageView img;
            private final TextView tvPhone;
            private final TextView tvContent;
            private final RecyclerView imgRecyclerView;

            public MyEvaluationHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.img);
                tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
                tvContent = (TextView) itemView.findViewById(R.id.tv_content);
                imgRecyclerView = (RecyclerView) itemView.findViewById(R.id.img_recycle_view);


            }
        }

        private class MyImgAdapter extends RecyclerView.Adapter<MyImgAdapter.MyImgHolder> {
            private final List<ServiceInfoResult.Data.Info.Imgs> list;

            public MyImgAdapter(List<ServiceInfoResult.Data.Info.Imgs> imgs) {
                this.list= imgs;

            }

            @Override
            public MyImgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_holder, parent, false);
                return new MyImgHolder(view);
            }

            @Override
            public void onBindViewHolder(MyImgHolder holder, int position) {
                Glide.with(holder.img.getContext())
                        .load(Constant.IMG_URL + list.get(position).getUrl())
                        .error(R.drawable.err)
                        .into(holder.img);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }

            public class MyImgHolder extends RecyclerView.ViewHolder{

                private final ImageView img;

                public MyImgHolder(View itemView) {
                    super(itemView);
                    img = (ImageView)itemView.findViewById(R.id.img);
                }
            }
        }
    }
}
