package com.azhyun.massxj.activity.azt;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.OrdserDetailsBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 采购订单详情
* */
public class CaiGouOrderDetailsActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.details_address)
    TextView details_address;
    @BindView(R.id.details_name)
    TextView details_name;
    @BindView(R.id.details_phone)
    TextView details_phone;
    @BindView(R.id.details_canpay_name)
    TextView details_canpay_name;
    @BindView(R.id.details_comm_name)
    TextView details_comm_name;
    @BindView(R.id.details_comm_price)
    TextView details_comm_price;
    @BindView(R.id.details_comm_shuliang)
    TextView details_comm_shuliang;
    @BindView(R.id.details_comm_guige)
    TextView details_comm_guige;
    @BindView(R.id.details_order_code)
    TextView details_order_code;
    @BindView(R.id.details_order_tiem)
    TextView details_order_tiem;


    @BindView(R.id.details_qian_tiem)
    TextView details_qian_tiem;
    @BindView(R.id.details_comm_but_price)
    TextView details_comm_but_price;
    @BindView(R.id.details_yunfei)
    TextView details_yunfei;
    @BindView(R.id.details_img)
    ImageView details_img;
    @BindView(R.id.details_heji)
    TextView details_heji;
    @BindView(R.id.qiantiem_ll)
    LinearLayout qiantiem_ll;
    @BindView(R.id.order_img)
    ImageView order_img;
    @BindView(R.id.order_name)
    TextView order_name;
    @BindView(R.id.orsder_item_nuitname)
    TextView orsder_item_nuitname;

    private int orderId;
    private String phone;
    private String fullName;
    private String name;
    private String userId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_caigou_order_details;
    }

    @Override
    protected void initView() {
        title.setText("订单详情");
        title.setTextColor(getResources().getColor(R.color.black));
        title.setTextSize(18);

        orderId = getIntent().getIntExtra("orderId", 0);

        phone = (String) SpUtils.get("phone", "");
        fullName = (String) SpUtils.get("fullName", "");
        userId = (String) SpUtils.get("userId", "");
        name = (String) SpUtils.get("name", "");
    }


    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        gainData();
    }

    /*
    * 请求数据
    * */
    private void gainData() {
        LoadingDialog.show(CaiGouOrderDetailsActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<OrdserDetailsBean> orderxiang = service.orderxiang(orderId);
        orderxiang.enqueue(new Callback<OrdserDetailsBean>() {
            @Override
            public void onResponse(Call<OrdserDetailsBean> call, Response<OrdserDetailsBean> response) {
                if (response.isSuccessful()) {
                    OrdserDetailsBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        LoadingDialog.cancel();
                        OrdserDetailsBean.DataBean data = body.getData();

                        Glide.with(CaiGouOrderDetailsActivity.this)
                                .load(Constant.IMG_URL + data.getItems()
                                        .get(0).getImg())
                                .placeholder(R.drawable.me_hard_danwei).error(R.drawable.me_hard_danwei)
                                .into(details_img);

                        orsder_item_nuitname.setText(data.getCompany().getName());
                        details_address.setText(fullName);
                        details_name.setText(name);
                        details_phone.setText(phone);
                        details_order_code.setText(data.getId() + "");
                        details_canpay_name.setText(data.getCompany().getName());
                        details_comm_name.setText(data.getItems().get(0).getBrand() + data.getItems().get(0).getItemName());
                        details_comm_price.setText("¥" + new DecimalFormat("#0.00").format(data.getItems().get(0).getPrice()));
                        details_comm_shuliang.setText("x" + data.getItems().get(0).getQty());
                        details_comm_guige.setText(data.getItems().get(0).getNorm() + "/" + data.getItems().get(0).getUnits());
                        details_order_tiem.setText(data.getAddedTime());
                        details_comm_but_price.setText("¥" + new DecimalFormat("#0.00").format(data.getAmount()));
                        details_yunfei.setText("¥" + new DecimalFormat("#0.00").format(data.getFeeAmount()));
                        details_heji.setText("¥" + new DecimalFormat("#0.00").format(data.getRetailPayAmount()));

                        if (data.getStatus() == 1) {
                            order_name.setText("待处理");
                            order_img.setImageResource(R.drawable.order_daishoukuan);
                        } else if (data.getStatus() == 2) {
                            order_name.setText("已完成");
                            order_img.setImageResource(R.drawable.order_wancheng);
                            qiantiem_ll.setVisibility(ViewGroup.VISIBLE);
                            details_qian_tiem.setText(data.getSignTime());
                        } else {
                            order_name.setText(" 已取消");
                            order_img.setImageResource(R.drawable.order_quxiao);
                        }


                    } else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(CaiGouOrderDetailsActivity.this, body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<OrdserDetailsBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
