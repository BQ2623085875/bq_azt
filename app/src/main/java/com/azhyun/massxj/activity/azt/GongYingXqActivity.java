package com.azhyun.massxj.activity.azt;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.adapter.SupplyInfoAdapter;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.GyXqBean;
import com.azhyun.massxj.bean.aizhongtian.QgBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.autolayout.utils.ScreenUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

import static com.azhyun.massxj.utils.LoadingDialog.cancel;

/*
* 供应详情
* */
public class GongYingXqActivity extends BaseActivity {
    protected final String TAG = "GongYingXqActivity";

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.indicator_layout)
    LinearLayout mIndicatorLayout;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.danwei)
    TextView danwei;
    @BindView(R.id.ghdz)
    TextView ghdz;
    @BindView(R.id.miaoshu)
    TextView miaoshu;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.tiem)
    TextView tiem;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.bendi_gy_lianxi_ll)
    LinearLayout bendi_gy_lianxi_ll;
    @BindView(R.id.bd_lianxiren)
    TextView bd_lianxiren;
    @BindView(R.id.bd_lianxi)
    TextView bd_lianxi;
    @BindView(R.id.shuliang)
    TextView shuliang;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.xiaxian_anniu_tv)
    TextView xiaxian_anniu_tv;

    @BindView(R.id.qg_name)
    TextView qg_name;
    @BindView(R.id.qg_pinzhong)
    TextView qg_pinzhong;
    @BindView(R.id.qg_shuliang)
    TextView qg_shuliang;
    @BindView(R.id.qg_huoyuandi)
    TextView qg_huoyuandi;
    @BindView(R.id.qg_pinzhi)
    TextView qg_pinzhi;
    @BindView(R.id.qg_img)
    ImageView qg_img;
    @BindView(R.id.qg_phone)
    TextView qg_phone;
    @BindView(R.id.qg_tiem)
    TextView qg_tiem;

    @BindView(R.id.qg_xq_ll)
    LinearLayout qg_xq_ll;
    @BindView(R.id.gy_xq_ll)
    LinearLayout gy_xq_ll;

    @BindView(R.id.gy_xiaxian_ll)
    LinearLayout gy_xiaxian_ll;
    @BindView(R.id.gy_xiaxian_tiem)
    TextView gy_xiaxian_tiem;
    @BindView(R.id.qg_xiaxian_ll)
    LinearLayout qg_xiaxian_ll;
    @BindView(R.id.qg_xiaxian_tiem)
    TextView qg_xiaxian_tiem;
    @BindView(R.id.gy_zhanshitu_rv)
    XRecyclerView gy_zhanshitu_rv;


    private int id;
    private String userid;
    private int type;//1,供应   0,求购
    private int judge;//1,从本地生活   0,从我发布的
    private GyXqBean.SupplyInfoBean supplyInfo;
    private List<GyXqBean.SupplyInfoBean.ImgsBean> gyImgs;
    private List<QgBean.DemandInfoBean.ImgsBean> qgImgs;
    private QgBean.DemandInfoBean demandInfo;
    private String userId;
    private SupplyInfoAdapter supplyInfoAdapter;
    private ArrayList<String> list;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_gong_ying_xq;
    }

    @Override
    protected void initView() {
        title.setText("供应详情");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        judge = getIntent().getIntExtra("judge", 0);
        userid = getIntent().getStringExtra("userid");
        userId = (String) SpUtils.get("userId", "");

        if (type == 1) {
            gy_xq_ll.setVisibility(ViewGroup.VISIBLE);
            qg_xq_ll.setVisibility(ViewGroup.GONE);
            if (judge == 1) {
                bendi_gy_lianxi_ll.setVisibility(ViewGroup.VISIBLE);
                xiaxian_anniu_tv.setVisibility(ViewGroup.GONE);
                if (userid.equals(userId)) {
                    bd_lianxi.setBackgroundResource(R.drawable.xiaxian_shape);
                }
            } else {
                bendi_gy_lianxi_ll.setVisibility(ViewGroup.GONE);
                xiaxian_anniu_tv.setVisibility(ViewGroup.VISIBLE);
            }
        } else {
            gy_xq_ll.setVisibility(ViewGroup.GONE);
            qg_xq_ll.setVisibility(ViewGroup.VISIBLE);
            if (judge == 1) {
                bendi_gy_lianxi_ll.setVisibility(ViewGroup.VISIBLE);
                xiaxian_anniu_tv.setVisibility(ViewGroup.GONE);
                if (userid.equals(userId)) {
                    bd_lianxi.setBackgroundResource(R.drawable.xiaxian_shape);
                }
            } else {
                bendi_gy_lianxi_ll.setVisibility(ViewGroup.GONE);
                xiaxian_anniu_tv.setVisibility(ViewGroup.VISIBLE);
            }
        }

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        bd_lianxi.setOnClickListener(this);
        xiaxian_anniu_tv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (type == 1) {
            gongyingData();
        } else {
            qiugouData();
        }
    }

    private void qiugouData() {
        LoadingDialog.show(GongYingXqActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<QgBean> qgXq = service.getQgXq(id);
        qgXq.enqueue(new Callback<QgBean>() {
            @Override
            public void onResponse(Call<QgBean> call, Response<QgBean> response) {
                if (response.isSuccessful()) {
                    QgBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        demandInfo = body.getDemandInfo();
                        qgImgs = demandInfo.getImgs();
                        setTopViewPager();

                        qg_name.setText(demandInfo.getTitle());
                        qg_pinzhong.setText(demandInfo.getCategoryName());
                        qg_shuliang.setText(demandInfo.getNum() + "");
                        qg_huoyuandi.setText(demandInfo.getFullName());
                        qg_pinzhi.setText(demandInfo.getDescriptionContent());
                        Glide.with(GongYingXqActivity.this).load(Constant.IMG_URL + demandInfo.getHeadImg()).crossFade().placeholder(R.drawable.me_hard_danwei).error(R.drawable.me_hard_danwei).into(qg_img);
                        qg_phone.setText(demandInfo.getPhone());
                        qg_tiem.setText(demandInfo.getAddtimes());

                        tiem.setText(demandInfo.getAddtimes() + "");
                        bd_lianxiren.setText("联系人：" + demandInfo.getContacts());

                        if (demandInfo.getStatus() != 1) {
                            qg_xiaxian_ll.setVisibility(ViewGroup.VISIBLE);
                            xiaxian_anniu_tv.setVisibility(ViewGroup.GONE);
                            qg_xiaxian_tiem.setText(demandInfo.getDownlineTime());
                        }

                    }
                    cancel();
                    ToastUtils.showToast(GongYingXqActivity.this, body.getResult().getMessage());
                } else {
                    cancel();
                }
            }

            @Override
            public void onFailure(Call<QgBean> call, Throwable t) {
                cancel();
            }
        });
    }

    private void gongyingData() {
        LoadingDialog.show(GongYingXqActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<GyXqBean> gyXq = service.getGyXq(id);
        gyXq.enqueue(new Callback<GyXqBean>() {
            @Override
            public void onResponse(Call<GyXqBean> call, Response<GyXqBean> response) {
                if (response.isSuccessful()) {
                    GyXqBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        supplyInfo = body.getSupplyInfo();
                        gyImgs = supplyInfo.getImgs();
                        setTopViewPager();

                        name.setText(supplyInfo.getTitle());
                        if (supplyInfo.getPrice() > 0) {
                            price.setText("¥" + new DecimalFormat("#0.00").format(supplyInfo.getPrice()));
                        } else {
                            price.setText("价格待定");
                        }
                        danwei.setText(supplyInfo.getNorms());
                        shuliang.setText("数量：   " + supplyInfo.getNum() + "");
                        ghdz.setText(supplyInfo.getFullName());
                        miaoshu.setText(supplyInfo.getDescriptionContent());
                        Glide.with(GongYingXqActivity.this).load(Constant.IMG_URL + supplyInfo.getHeadImg()).crossFade().placeholder(R.drawable.me_hard_danwei).error(R.drawable.me_hard_danwei).into(img);
                        if (!TextUtils.isEmpty(supplyInfo.getPhone())) {
                            String maskNumber = supplyInfo.getPhone().substring(0, 3) + "****" + supplyInfo.getPhone().substring(7, supplyInfo.getPhone().length());
                            phone.setText(maskNumber);
                        }
                        tiem.setText(supplyInfo.getAddtimes() + "");
                        bd_lianxiren.setText("联系人：" + supplyInfo.getContacts());
                        if (supplyInfo.getStatus() != 1) {
                            gy_xiaxian_ll.setVisibility(ViewGroup.VISIBLE);
                            xiaxian_anniu_tv.setVisibility(ViewGroup.GONE);
                            gy_xiaxian_tiem.setText(supplyInfo.getDownlineTime());
                        }

                        if (supplyInfo.getInfoimgs().size() > 0) {
                            list = new ArrayList<>();
                            for (int i = 0; i < supplyInfo.getInfoimgs().size(); i++) {
                                Log.d(TAG, "你好tu" + supplyInfo.getInfoimgs().get(i).getUrl());
                                list.add(supplyInfo.getInfoimgs().get(i).getUrl());
                            }
                            gy_zhanshitu_rv.setLayoutManager(new LinearLayoutManager(GongYingXqActivity.this));
                            View view = LayoutInflater.from(GongYingXqActivity.this).inflate(R.layout.recyclerview_empty_layout, null, false);
                            gy_zhanshitu_rv.setEmptyView(view);
                            gy_zhanshitu_rv.setNestedScrollingEnabled(false);
                            gy_zhanshitu_rv.setLoadingMoreEnabled(false);
                            gy_zhanshitu_rv.setPullRefreshEnabled(false);
                            gy_zhanshitu_rv.addItemDecoration(new RecycleViewDivider(GongYingXqActivity.this, DividerItemDecoration.HORIZONTAL, 2, Color.parseColor("#eeeeee")));

                            supplyInfoAdapter = new SupplyInfoAdapter(list, GongYingXqActivity.this);

                            gy_zhanshitu_rv.setAdapter(supplyInfoAdapter);
                        }
                    }
                    cancel();
                    ToastUtils.showToast(GongYingXqActivity.this, body.getResult().getMessage());
                } else {
                    cancel();
                }
            }


            @Override
            public void onFailure(Call<GyXqBean> call, Throwable t) {
                cancel();
            }
        });
    }


    private void setTopViewPager() {
        final List<String> list = new ArrayList<>();
        if (type == 1) {
            for (int i = 0; i < gyImgs.size(); i++) {
                list.add(gyImgs.get(i).getUrl());
            }
        } else {
            for (int i = 0; i < qgImgs.size(); i++) {
                list.add(qgImgs.get(i).getUrl());
            }
        }

        ViewGroup.LayoutParams layoutParams = mViewPager.getLayoutParams();
        layoutParams.height = (int) DensityUtil.getWidth(GongYingXqActivity.this);
        mViewPager.setLayoutParams(layoutParams);
        mViewPager.setAdapter(new TopProductInfoAdapter(list));
        mIndicatorLayout.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(GongYingXqActivity.this);
            imageView.setBackgroundResource(R.drawable.indicator_shape);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(DensityUtil.dip2px(GongYingXqActivity.this, 5), 0, DensityUtil.dip2px(GongYingXqActivity.this, 5), 0);
            imageView.setLayoutParams(lp);
            mIndicatorLayout.addView(imageView);
        }

        if (list.size() > 1) {
            mIndicatorLayout.getChildAt(0).setSelected(true);
        } else {
            mIndicatorLayout.setVisibility(View.GONE);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean ifSelect = false;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == list.size() - 1 || position == list.size()) {
                    if (positionOffset > 0.2) {
                        ifSelect = true;
                    } else {
                        ifSelect = false;
                    }
                } else {
                    ifSelect = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == list.size()) {
                    mViewPager.setCurrentItem(list.size() - 1);
                } else {
                    int childCount = mIndicatorLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childView = mIndicatorLayout.getChildAt(i);
                        childView.setSelected(false);
                    }
                    View childView = mIndicatorLayout.getChildAt(position);
                    childView.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if (state == 2) {
//                    if (ifSelect) {
//                        //执行切换逻辑
//                        ProductInfoActivtiy activity = (ProductInfoActivtiy) mContext;
//                        activity.addGraphicDetailsFragment();
//                    }
//                }
            }
        });

    }

    @Override
    protected void otherViewClick(View view) {
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
            case R.id.bd_lianxi:
                if (!isLogin) {
                    Intent intent = new Intent(GongYingXqActivity.this, UserLogingActivity.class);
                    intent.putExtra("ActivityName", 11);
                    startActivity(intent);
                } else {
                    if (!userid.equals(userId)) {
                        showLodgo();
                    }
                }
                break;
            case R.id.xiaxian_anniu_tv:

                break;
        }
    }

    private void showLodgo() {
        final View layout = LayoutInflater.from(this).inflate(R.layout.alert_dialog_red, null);
        final TextView tm = (TextView) layout.findViewById(R.id.dialog_red_message);
        ImageView img = layout.findViewById(R.id.img);
        TextView no_tv = layout.findViewById(R.id.no_tv);
        TextView yes_tv = layout.findViewById(R.id.yes_tv);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (type == 1) {
            tm.setText(supplyInfo.getPhone());
        } else {
            tm.setText(demandInfo.getPhone());
        }
        Glide.with(GongYingXqActivity.this).load(Constant.IMG_URL + supplyInfo.getHeadImg()).crossFade().placeholder(R.drawable.me_hard_danwei).error(R.drawable.me_hard_danwei).into(img);
        builder.setView(layout);

        final AlertDialog dialog = builder.create();

        no_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RxPermissions(GongYingXqActivity.this)
                        .requestEach(Manifest.permission.CALL_PHONE)
                        .subscribe(new Action1<Permission>() {
                            @Override
                            public void call(Permission permission) {
                                if (permission.granted) {
                                    Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + supplyInfo.getPhone()));
                                    startActivity(intent2);
                                    //AppUtil.showShortToast(activity,"您已经授权该权限");

                                } else {

                                    //未获得授权
                                    ToastUtils.showToast(GongYingXqActivity.this, "您没有授权该权限，请在设置中打开授权");

                                }

                            }
                        });
            }
        });

        dialog.show();

    }

    class TopProductInfoAdapter extends PagerAdapter {
        private List<String> itemImgs;
        private ImageView mImageView;

        public TopProductInfoAdapter(List<String> itemImgs) {
            this.itemImgs = itemImgs;
        }

        public Bitmap getOneImage() {
            Drawable drawable = mImageView.getDrawable();
            Bitmap bitmap = Bitmap.createBitmap(

                    drawable.getIntrinsicWidth(),

                    drawable.getIntrinsicHeight(),

                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                            : Bitmap.Config.RGB_565);

            Canvas canvas = new Canvas(bitmap);

            //canvas.setBitmap(bitmap);

            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

            drawable.draw(canvas);
            return bitmap;
        }

        @Override
        public int getCount() {
            return itemImgs.size() + 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (position == itemImgs.size()) {
                View view = LayoutInflater.from(GongYingXqActivity.this).inflate(R.layout.left_scroll_see_details, null);
                container.addView(view);
                return view;
            }

            ImageView imageView = new ImageView(GongYingXqActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            if (position == 0) {
                mImageView = imageView;
            }

            String img = itemImgs.get(position);
            String imgUrl;
            if (!TextUtils.isEmpty(img)) {
                String[] split = img.split("\\.");
                imgUrl = Constant.IMG_URL + img;//+ ".400x400." + split[1]
            } else {
                imgUrl = Constant.IMG_URL + img;
            }

            Glide.with(GongYingXqActivity.this)
                    .load(imgUrl)
                    .crossFade()
                    .into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> list = new ArrayList<String>();
                    for (int i = 0; i < itemImgs.size(); i++) {
                        list.add(Constant.IMG_URL + itemImgs.get(i));
                    }
                    Intent intent = ProductInfoImagesActivity.newIntent(GongYingXqActivity.this, list, position);
                    startActivity(intent);
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }


}
