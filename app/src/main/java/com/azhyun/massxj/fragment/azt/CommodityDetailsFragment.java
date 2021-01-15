package com.azhyun.massxj.fragment.azt;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.activity.azt.ProductInfoImagesActivity;
import com.azhyun.massxj.activity.azt.SubmitOrderActivity;
import com.azhyun.massxj.adapter.azt.caigou.CaiGouCommitImgAdapter;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.CommodityDetailsBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.FlexRadioGroup;
import com.azhyun.massxj.utils.azt.JSONTool;
import com.azhyun.massxj.utils.azt.PhotoView;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * 商品详情 Fragment
 */
public class CommodityDetailsFragment extends LazyLoadFragment implements View.OnClickListener {

    @BindView(R.id.commodity_jianhao)
    LinearLayout mCommodity_jianhao;
    @BindView(R.id.commodity_jiahao)
    LinearLayout mCommodity_jiahao;
    @BindView(R.id.commodity_shuliang)
    EditText mCommodity_shuliang;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.indicator_layout)
    LinearLayout mIndicatorLayout;
    @BindView(R.id.product_name_text_view)
    TextView product_name_text_view;
    @BindView(R.id.adapt_field_text_view)
    TextView adapt_field_text_view;
    @BindView(R.id.product_price_text_view)
    TextView product_price_text_view;
    @BindView(R.id.mall_commodity_fuwushang)
    TextView mall_commodity_fuwushang;
    @BindView(R.id.product_guige_text)
    TextView product_guige_text;
    @BindView(R.id.product_guige_rl)
    RelativeLayout product_guige_rl;
    @BindView(R.id.danwei)
    TextView danwei;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_rv)
    RecyclerView img_rv;

    private int maxNumber = 9999;//最大购买数量
    private static final int ITEM_ID = 0;
    private static int commodityid;
    private int guigeid = 0;
    private int zuixiaoshuliang = 0;
    private int equal = 1;
    private int type = 0;//type=0  没选  type=1 选了

    private Dialog mCameraDialog;
    private FlexRadioGroup mDiaRadioGroup;
    private TextView mDiaPriceTextView;
    private TextView mDiaMinCountTextView;
    private ImageView mDiaProductImageView;

    private String supplierId;//服务商id
    private String SupplierName;//服务商

    private CommodityDetailsBean.DataBeanX data;
    private TextView mall_commodity_fuwushang1;
    private LinearLayout commodity_jiahao_tan;
    private LinearLayout commodity_jianhao_tan;
    private EditText commodity_shuliang_tan;
    private TextView danwei_tan;
    private String zuihou;
    private List<CommodityDetailsBean.DataBeanX.SkusBean.PricesBean> prices;
    private ArrayList<String> imglist = new ArrayList<>();


    public CommodityDetailsFragment() {

    }

    public static CommodityDetailsFragment newInstance(int itemId) {
        commodityid = itemId;
        CommodityDetailsFragment fragment = new CommodityDetailsFragment();
//        Bundle args = new Bundle();
//        args.putInt(ITEM_ID, itemId);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findContentViews2(view);
        initView();
    }


    private void initView() {
        mCommodity_jianhao.setOnClickListener(this);
        mCommodity_jiahao.setOnClickListener(this);
        product_guige_rl.setOnClickListener(this);
        commodity_shuliang_tan.setOnClickListener(this);

        mCommodity_shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                zuihou = s.toString();
                if (s.toString().startsWith("0") || s.toString().equals("")) {
                    mCommodity_shuliang.setText(zuixiaoshuliang + "");
                    mCommodity_shuliang.setSelection(zuixiaoshuliang);
                }
            }
        });

        commodity_shuliang_tan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                zuihou = s.toString();
                if (s.toString().startsWith("0") || s.toString().equals("")) {
                    commodity_shuliang_tan.setText(zuixiaoshuliang + "");
                    commodity_shuliang_tan.setSelection(zuixiaoshuliang);
                }
            }
        });
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_commodity_details;
    }

    @Override
    protected void lazyLoad() {
        initData();
    }


    private void initData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<CommodityDetailsBean> commodity = service.commodity(commodityid);
        commodity.enqueue(new Callback<CommodityDetailsBean>() {
            @Override
            public void onResponse(Call<CommodityDetailsBean> call, Response<CommodityDetailsBean> response) {
                if (response.isSuccessful()) {
                    CommodityDetailsBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        data = body.getData();
                        List<CommodityDetailsBean.DataBeanX.SkusBean> skus = data.getSkus();

                        String[] parms = data.getDetail().split("\"");
                        for (int i = 0; i < parms.length; i++) {
                            boolean https = parms[i].contains("http");
                            if (https) {
                                imglist.add(parms[i]);
                            }
                        }
                        if (imglist.size() != 0) {
                            CaiGouCommitImgAdapter caiGouCommitImgAdapter = new CaiGouCommitImgAdapter(imglist, getContext());
                            img_rv.setAdapter(caiGouCommitImgAdapter);
                            img_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        type = 0;
                        product_name_text_view.setText("【" + data.getBrand() + "】" + data.getOnlineTitle());

//                        if (data.getAdapRegion().length() > 0) {
                        adapt_field_text_view.setText(data.getAdapRegion());
//                        } else {
//                            adapt_field_text_view.setText(" 暂无商品描述");
//                        }

                        //服务商
                        mall_commodity_fuwushang.setText(data.getSkus().get(0).getSupplierName());
                        supplierId = data.getSkus().get(0).getSupplierId() + "";
                        SupplierName = data.getSkus().get(0).getSupplierName();

                        if (data.getSkus().get(0).getMoq() == 0) {
                            zuixiaoshuliang = 1;
                            mCommodity_shuliang.setText(1 + "");
                            zuihou = 1 + "";
                            mCommodity_shuliang.setSelection(mCommodity_shuliang.getText().toString().length());
                        } else {
                            zuixiaoshuliang = data.getSkus().get(0).getMoq();
                            mCommodity_shuliang.setText(data.getSkus().get(0).getMoq() + "");
                            mCommodity_shuliang.setSelection(mCommodity_shuliang.getText().toString().length());
                            zuihou = data.getSkus().get(0).getMoq() + "";
                        }

                        List<CommodityDetailsBean.DataBeanX.SkusBean> skus1 = data.getSkus();
                        for (int i = 0; i < skus1.size(); i++) {
                            product_guige_text.setText(skus1.get(0).getNorm());
                            danwei.setText("/" + skus1.get(0).getUnits());
                            prices = skus1.get(0).getPrices();
                            if (prices.get(0).getRetailPrice() > 0) {
                                product_price_text_view.setText("¥" + new DecimalFormat("#0.00").format(prices.get(0).getRetailPrice()));
                            } else {
                                product_price_text_view.setText("¥0.00");
                            }
                        }

                        //弹框
                        int childCount = mDiaRadioGroup.getChildCount();
                        if (childCount <= 0) {
                            createRadioButton(skus1, mDiaRadioGroup);
                        }
                        Glide.with(getContext()).load(Constant.IMG_URL + data.getImgs().get(0).getUrl()).into(mDiaProductImageView);
                        mall_commodity_fuwushang1.setText(data.getSkus().get(0).getSupplierName());
                        if (data.getSkus().get(0).getPrices().get(0).getRetailPrice() > 0) {
                            mDiaPriceTextView.setText("¥" + new DecimalFormat("#0.00").format(data.getSkus().get(0).getPrices().get(0).getRetailPrice()));
                        } else {
                            mDiaPriceTextView.setText("¥0.00");
                        }
                        danwei_tan.setText("/" + data.getSkus().get(0).getUnits());
                        guigeid = data.getSkus().get(0).getId();
                        if (data.getSkus().get(0).getMoq() == 0) {
                            zuihou = 1 + "";
                            commodity_shuliang_tan.setText(1 + "");
                            commodity_shuliang_tan.setSelection(commodity_shuliang_tan.getText().toString().length());
                            mDiaMinCountTextView.setText("最小起订量：" + 1 + "");
                        } else {
                            commodity_shuliang_tan.setText(data.getSkus().get(0).getMoq() + "");
                            commodity_shuliang_tan.setSelection(commodity_shuliang_tan.getText().toString().length());
                            zuihou = data.getSkus().get(0).getMoq() + "";
                            mDiaMinCountTextView.setText("最小起订量：" + data.getSkus().get(0).getMoq() + "");
                        }

                        setTopViewPager();
                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<CommodityDetailsBean> call, Throwable t) {

            }
        });


    }

    /*
    * 立即购买
    * */

    public void placeBuy() {
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        if (!isLogin) {
            Intent intent = new Intent(getContext(), UserLogingActivity.class);
            startActivityForResult(intent, 130);
        } else {
            int s = Integer.parseInt(mCommodity_shuliang.getText().toString());
            if (zuixiaoshuliang <= s) {
                String s1 = product_price_text_view.getText().toString();
                if (s1.equals("¥0.00")) {
                    ToastUtils.showToast(getContext(), "此商品暂不支持购买！");
                } else {
                    Intent intent = new Intent(getContext(), SubmitOrderActivity.class);
                    intent.putExtra("data", data);//商品对象
                    intent.putExtra("qty", zuihou);//商品数量
                    intent.putExtra("guigeid", guigeid);
                    intent.putExtra("type", type);
                    intent.putExtra("zuixiaoshuliang", zuixiaoshuliang);
                    intent.putExtra("supplierId", supplierId);
                    intent.putExtra("SupplierName", SupplierName);

                    getContext().startActivity(intent);
                }

            } else {
                ToastUtils.showToast(getContext(), "最小起订量为" + zuixiaoshuliang);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commodity_jiahao:
                int amount = Integer.parseInt(mCommodity_shuliang.getText().toString());
                if (amount > 0) {
                    if (equal < 999) {
                        equal = amount + 1;
                        mCommodity_shuliang.setText(equal + "");
                        zuihou = equal + "";
                    } else {
                        ToastUtils.showToast(getContext(), "最大起订量为999！");
                    }
                }

                break;
            case R.id.commodity_jianhao:
                int amount2 = Integer.parseInt(mCommodity_shuliang.getText().toString());
                if (amount2 > 1) {
                    if (amount2 > zuixiaoshuliang) {
                        equal = amount2 - 1;
                        mCommodity_shuliang.setText(equal + "");
                        zuihou = equal + "";
                    } else {
                        ToastUtils.showToast(getContext(), "最小起订量为" + zuixiaoshuliang);
                    }
                }
                break;
            case R.id.product_guige_rl:
                type = 1;
                mCameraDialog.show();
                break;
        }
    }

    private void setTopViewPager() {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < data.getImgs().size(); i++) {
            list.add(data.getImgs().get(i).getUrl());
        }
        ViewGroup.LayoutParams layoutParams = mViewPager.getLayoutParams();
        layoutParams.height = (int) DensityUtil.getWidth(getActivity());
        mViewPager.setLayoutParams(layoutParams);
        mViewPager.setAdapter(new TopProductInfoAdapter(list));
        mIndicatorLayout.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(R.drawable.indicator_shape);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(DensityUtil.dip2px(getActivity(), 5), 0, DensityUtil.dip2px(getActivity(), 5), 0);
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
                View view = LayoutInflater.from(getContext()).inflate(R.layout.left_scroll_see_details, null);
                container.addView(view);
                return view;
            }

            ImageView imageView = new ImageView(getContext());
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

            Glide.with(getContext())
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
                    Intent intent = ProductInfoImagesActivity.newIntent(getActivity(), list, position);
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

    /*
    * 底部跳框
    * */
    private void findContentViews2(View view) {
        mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_prodect_details_standard, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(true);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
//        mCameraDialog.show();

        ImageView mDiaCancenImageView = (ImageView) root.findViewById(R.id.cancen_image_view);//隐藏弹框 图片
        mDiaCancenImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCameraDialog != null) {
                    mCameraDialog.dismiss();
                }
            }
        });


        mDiaPriceTextView = (TextView) root.findViewById(R.id.price_text_view);//价格
        mDiaMinCountTextView = (TextView) root.findViewById(R.id.min_count_text_view);//最小起订量
        mDiaProductImageView = (ImageView) root.findViewById(R.id.product_image_view);//商品图片
        mDiaRadioGroup = (FlexRadioGroup) root.findViewById(R.id.radio_group);//规格列表
        mall_commodity_fuwushang1 = root.findViewById(R.id.mall_commodity_fuwushang);//服务商
        commodity_jiahao_tan = root.findViewById(R.id.commodity_jiahao_tan);//加号
        commodity_jianhao_tan = root.findViewById(R.id.commodity_jianhao_tan); //减号
        commodity_shuliang_tan = root.findViewById(R.id.commodity_shuliang_tan);//数量
        danwei_tan = root.findViewById(R.id.danwei_tan);
        LinearLayout promptly_ll = root.findViewById(R.id.promptly_ll); //购买


        //监听
        commodity_jiahao_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int amount = Integer.parseInt(commodity_shuliang_tan.getText().toString());
                if (amount > 0) {
                    if (equal < 999) {
                        equal = amount + 1;
                        mCommodity_shuliang.setText(equal + "");
                        commodity_shuliang_tan.setText(equal + "");
                        zuihou = equal + "";
                    } else {
                        ToastUtils.showToast(getContext(), "最大起订量为999！");
                    }
                }
            }
        });
        commodity_jianhao_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int amount = Integer.parseInt(commodity_shuliang_tan.getText().toString());
                if (amount > 1) {
                    if (amount > zuixiaoshuliang) {
                        equal = amount - 1;
                        commodity_shuliang_tan.setText(equal + "");
                        mCommodity_shuliang.setText(equal + "");
                        zuihou = equal + "";
                    } else {
                        ToastUtils.showToast(getContext(), "最小起订量为" + zuixiaoshuliang);
                    }
                }
            }
        });

        promptly_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeBuy();
            }
        });


        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }
        });

    }


    private void createRadioButton(final List<CommodityDetailsBean.DataBeanX.SkusBean> skusBeans, final FlexRadioGroup group) {
        /**
         *  64dp菜单的边距{@link DrawerLayout#MIN_DRAWER_MARGIN}+10dp*2为菜单内部的padding=84dp
         */
        float margin = DensityUtil.dip2px(getActivity(), 84);
        float width = DensityUtil.getWidth(getActivity());

        group.setOnCheckedChangeListener(new FlexRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@IdRes int checkedId) {
                updateStandard(checkedId);
            }
        });

        for (int i = 0; i < skusBeans.size(); i++) {
            CommodityDetailsBean.DataBeanX.SkusBean item = skusBeans.get(i);
            RadioButton rb = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.item_label_radio_button, null);
            rb.setText(item.getNorm());
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rb.setLayoutParams(lp);
            rb.setId(i);

            group.addView(rb);

            if (i == 0) {
                group.check(i);
            }

        }
    }

    private void updateStandard(int position) {
        showSku(position);
    }

    private void showSku(int position) {

        //价格
        if (data.getSkus().get(position).getPrices().get(0).getRetailPrice() > 0) {
            mDiaPriceTextView.setText("¥" + new DecimalFormat("#0.00").format(data.getSkus().get(position).getPrices().get(0).getRetailPrice()));
            product_price_text_view.setText("¥" + new DecimalFormat("#0.00").format(data.getSkus().get(position).getPrices().get(0).getRetailPrice()));
        } else {
            mDiaPriceTextView.setText("¥0.00");
            product_price_text_view.setText("¥0.00");
        }
        danwei_tan.setText("/" + data.getSkus().get(position).getUnits());

        //最小数量
        if (data.getSkus().get(position).getMoq() == 0) {
            mDiaMinCountTextView.setText("最小起订量：" + 1 + "");
            commodity_shuliang_tan.setText(1 + "");
            mCommodity_shuliang.setText(1 + "");
            zuixiaoshuliang = 1;
            zuihou = 1 + "";
        } else {
            mDiaMinCountTextView.setText("最小起订量：" + data.getSkus().get(position).getMoq() + "");
            commodity_shuliang_tan.setText(data.getSkus().get(position).getMoq() + "");
            mCommodity_shuliang.setText(data.getSkus().get(position).getMoq() + "");
            zuihou = data.getSkus().get(position).getMoq() + "";
            zuixiaoshuliang = data.getSkus().get(position).getMoq();
        }

        //规格
        product_guige_text.setText(data.getSkus().get(position).getNorm());
        //规格id
        guigeid = data.getSkus().get(position).getId();
        //服务商id
        supplierId = data.getSkus().get(position).getSupplierId() + "";
        //服务商
        SupplierName = data.getSkus().get(position).getSupplierName();
        mall_commodity_fuwushang.setText(data.getSkus().get(position).getSupplierName());
        mall_commodity_fuwushang1.setText(data.getSkus().get(position).getSupplierName());

    }


    public String getInsideString(String str, String strStart, String strEnd) {
        if (str.indexOf(strStart) < 0) {
            return "";
        }
        if (str.indexOf(strEnd) < 0) {
            return "";
        }
        return str.substring(str.indexOf(strStart) + strStart.length(), str.indexOf(strEnd));
    }


}
