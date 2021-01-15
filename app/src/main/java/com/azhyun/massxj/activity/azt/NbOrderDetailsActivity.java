package com.azhyun.massxj.activity.azt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.NbOrderXQBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 农保订单详情
* */
public class NbOrderDetailsActivity extends BaseActivity {
    protected final String TAG = "NbOrderDetailsActivity";

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.nb_order_tu_tv)
    TextView nb_order_tu_tv;
    @BindView(R.id.nb_order_address)
    TextView nb_order_address;
    @BindView(R.id.nb_order_user_name)
    TextView nb_order_user_name;
    @BindView(R.id.nb_order_user_phone)
    TextView nb_order_user_phone;
    @BindView(R.id.nb_order_number)
    TextView nb_order_number;
    @BindView(R.id.nb_order_canpay_name)
    TextView nb_order_canpay_name;
    @BindView(R.id.nb_order_name)
    TextView nb_order_name;
    @BindView(R.id.nb_order_jine)
    TextView nb_order_jine;
    @BindView(R.id.nb_order_zeren)
    TextView nb_order_zeren;
    @BindView(R.id.nb_order_img)
    ImageView nb_order_img;
    @BindView(R.id.nb_order_tu)
    ImageView nb_order_tu;
    @BindView(R.id.nb_order_dingbainhao)
    TextView nb_order_dingbainhao;
    @BindView(R.id.nb_order_xiadan_tiem)
    TextView nb_order_xiadan_tiem;
    @BindView(R.id.nb_order_toubao_tiem)
    TextView nb_order_toubao_tiem;
    @BindView(R.id.nb_order_price)
    TextView nb_order_price;
    @BindView(R.id.nb_order_mushu)
    TextView nb_order_mushu;
    @BindView(R.id.nb_order_nongyuzhi)
    TextView nb_order_nongyuzhi;
    @BindView(R.id.nb_order_heji)
    TextView nb_order_heji;
    @BindView(R.id.nb_order_shengxiao)
    TextView nb_order_shengxiao;
    @BindView(R.id.nb_order_toubao_tiem_ll)
    LinearLayout nb_order_toubao_tiem_ll;
    @BindView(R.id.nb_order_shengxiao_ll)
    LinearLayout nb_order_shengxiao_ll;

    private int orderId;
    private int type;
    private String phone;
    private String fullName;
    private String name;
    private String userId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_nb_order_details;
    }

    @Override
    protected void initView() {
        title.setText("订单详情");
        title.setTextColor(getResources().getColor(R.color.black));
        title.setTextSize(18);

        orderId = getIntent().getIntExtra("orderId", 0);
        type = getIntent().getIntExtra("type", 0);

        if (type == 1) {
            nb_order_tu_tv.setText("待处理");
            nb_order_tu.setImageResource(R.drawable.nb_daichuli_tu);
        } else if (type == 2) {
            nb_order_tu_tv.setText("已生效");
            nb_order_tu.setImageResource(R.drawable.shengxiao);
        } else if (type == 3) {
            nb_order_tu_tv.setText("已过期");
            nb_order_tu.setImageResource(R.drawable.guoqi);
        } else if (type == 4) {
            nb_order_tu_tv.setText("已超时");
            nb_order_tu.setImageResource(R.drawable.chaoshi);
        } else if (type == -1) {
            nb_order_tu_tv.setText("已取消");
            nb_order_tu.setImageResource(R.drawable.order_quxiao);
        }

        phone = (String) SpUtils.get("phone", "");
        fullName = (String) SpUtils.get("fullName", "");
        userId = (String) SpUtils.get("userId", "");
        name = (String) SpUtils.get("name", "");
    }


    @Override
    protected void initData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NbOrderXQBean> nbOrderXQBeanCall = service.nbOrderXQ(orderId);
        nbOrderXQBeanCall.enqueue(new Callback<NbOrderXQBean>() {
            @Override
            public void onResponse(Call<NbOrderXQBean> call, Response<NbOrderXQBean> response) {
                if (response.isSuccessful()) {
                    NbOrderXQBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        NbOrderXQBean.DataBean data = body.getData();
                        Glide.with(NbOrderDetailsActivity.this).load(Constant.IMG_URL + data.getInsuranceItem().getImg())
                                .placeholder(R.drawable.me_hard_danwei)
                                .error(R.drawable.me_hard_danwei)
                                .into(nb_order_img);
                        nb_order_address.setText(data.getInsuranceBookingVo().getAddress());
                        nb_order_user_name.setText(data.getInsuranceBookingVo().getName());
                        nb_order_user_phone.setText(phone.substring(0, 3) + "****" + phone.substring(7, phone.length()));
                        nb_order_number.setText(hideId(data.getInsuranceBookingVo().getIdcard(), 1, data.getInsuranceBookingVo().getIdcard().length() - 1));
                        nb_order_canpay_name.setText(data.getCompany().getName());
                        nb_order_name.setText(data.getInsuranceItem().getName());
                        nb_order_jine.setText("保险金额:每亩" + data.getInsuranceItem().getAmount() + "元");
                        nb_order_zeren.setText("保险责任:" + data.getInsuranceItem().getResponsibility());
                        nb_order_dingbainhao.setText(data.getId() + "");
                        nb_order_xiadan_tiem.setText(data.getAddedTime());
                        nb_order_price.setText("¥" + new DecimalFormat("#0.00").format(data.getInsuranceItem().getPrice()));
                        nb_order_mushu.setText(data.getInsuranceBookingVo().getMu() + "亩");
                        nb_order_heji.setText("¥" + new DecimalFormat("#0.00").format(data.getAmount()));
                        if (data.getInsuranceBookingVo().getCurrency() == 0) {
                            nb_order_nongyuzhi.setText("- ¥0.00");
                        } else {
                            int currency = data.getInsuranceBookingVo().getCurrency() / 10;
                            nb_order_nongyuzhi.setText("- ¥" + currency + ".00");
                        }

                        if (type == 1) {//待处理
                            nb_order_toubao_tiem_ll.setVisibility(ViewGroup.GONE);
                            nb_order_shengxiao_ll.setVisibility(ViewGroup.GONE);
                        } else if (type == 2) {//已生效
                            nb_order_toubao_tiem_ll.setVisibility(ViewGroup.VISIBLE);
                            nb_order_shengxiao_ll.setVisibility(ViewGroup.VISIBLE);
                            nb_order_toubao_tiem.setText(data.getConfirmTime());
                            nb_order_shengxiao.setText(data.getApproveTime() + " 至 " + data.getDeliveryTime());
                        } else if (type == 3) {//已过期
                            nb_order_toubao_tiem_ll.setVisibility(ViewGroup.VISIBLE);
                            nb_order_shengxiao_ll.setVisibility(ViewGroup.VISIBLE);
                            nb_order_toubao_tiem.setText(data.getConfirmTime());
                            nb_order_shengxiao.setText(data.getApproveTime() + " 至 " + data.getDeliveryTime());
                        } else if (type == 4) {//已超时
                            nb_order_toubao_tiem_ll.setVisibility(ViewGroup.GONE);
                            nb_order_shengxiao_ll.setVisibility(ViewGroup.GONE);
                        } else if (type == -1) {//已取消
                            nb_order_toubao_tiem_ll.setVisibility(ViewGroup.GONE);
                            nb_order_shengxiao_ll.setVisibility(ViewGroup.GONE);
                        }
                        ToastUtils.showToast(NbOrderDetailsActivity.this, body.getResult().getMessage());
                    } else {
                        ToastUtils.showToast(NbOrderDetailsActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<NbOrderXQBean> call, Throwable t) {
                ToastUtils.showToast(NbOrderDetailsActivity.this, "加载失败");
            }
        });
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
        }
    }

    public static String hideId(String idmode, int ks, int over) {
        StringBuilder stringBuilder = new StringBuilder(idmode);
        stringBuilder.replace(ks, over, "****************");
        return stringBuilder.toString();
    }
}
