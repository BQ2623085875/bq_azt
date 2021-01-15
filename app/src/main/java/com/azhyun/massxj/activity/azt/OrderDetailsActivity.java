package com.azhyun.massxj.activity.azt;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.bean.aizhongtian.OrdserDetailsBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBeans;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * 订单详情
 * */
public class OrderDetailsActivity extends BaseActivity {

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
    @BindView(R.id.details_fukuan_fangshi)
    TextView details_fukuan_fangshi;
    @BindView(R.id.details_zhifu_tiem)
    TextView details_zhifu_tiem;
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
    @BindView(R.id.details_gongjian)
    TextView details_gongjian;
    @BindView(R.id.zhifutiem_ll)
    LinearLayout zhifutiem_ll;
    @BindView(R.id.qiantiem_ll)
    LinearLayout qiantiem_ll;
    @BindView(R.id.order_img)
    ImageView order_img;
    @BindView(R.id.order_name)
    TextView order_name;
    @BindView(R.id.orsder_item_nuitname)
    TextView orsder_item_nuitname;

    @BindView(R.id.cloer_tv)
    TextView cloer_tv;
    @BindView(R.id.zhifu_tv)
    TextView zhifu_tv;
    @BindView(R.id.yes_shou_tv)
    TextView yes_shou_tv;

    private int orderId;
    private String phone;
    private String fullName;
    private String name;
    private String userId;
    private OrdserDetailsBean.DataBean data;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
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
    protected void initData() {
        gainData();
    }

    /*
     * 请求数据
     * */
    private void gainData() {

        LoadingDialog.show(OrderDetailsActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<OrdserDetailsBean> orderxiang = service.orderxiang(orderId);
        orderxiang.enqueue(new Callback<OrdserDetailsBean>() {
            @Override
            public void onResponse(Call<OrdserDetailsBean> call, Response<OrdserDetailsBean> response) {
                if (response.isSuccessful()) {
                    OrdserDetailsBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        LoadingDialog.cancel();
                        data = body.getData();

                        Glide.with(OrderDetailsActivity.this)
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
                        details_fukuan_fangshi.setText("货到付款");
                        details_comm_but_price.setText("¥" + new DecimalFormat("#0.00").format(data.getAmount()));
                        details_yunfei.setText("¥" + new DecimalFormat("#0.00").format(data.getFeeAmount()));
                        details_heji.setText("¥" + new DecimalFormat("#0.00").format(data.getRetailPayAmount()));
                        details_gongjian.setText("共计" + data.getItems().get(0).getQty() + "件商品");

                        if (data.getStatus() == 1) {
                            order_name.setText("待付款");
                            order_img.setImageResource(R.drawable.order_daishoukuan);
                            cloer_tv.setVisibility(View.VISIBLE);
                            zhifu_tv.setVisibility(View.VISIBLE);
                            yes_shou_tv.setVisibility(View.GONE);
                        } else if (data.getStatus() == 2) {
                            order_name.setText("待发货");
                            order_img.setImageResource(R.drawable.order_daifahuo);
                            cloer_tv.setVisibility(View.GONE);
                            zhifu_tv.setVisibility(View.GONE);
                            yes_shou_tv.setVisibility(View.GONE);
                        } else if (data.getStatus() == 3) {
                            order_name.setText("待收货");
                            order_img.setImageResource(R.drawable.order_daishouhuo);
                            cloer_tv.setVisibility(View.GONE);
                            zhifu_tv.setVisibility(View.GONE);
                            yes_shou_tv.setVisibility(View.VISIBLE);
                        } else if (data.getStatus() == 4) {
                            order_name.setText("已完成");
                            order_img.setImageResource(R.drawable.order_wancheng);
                            zhifutiem_ll.setVisibility(ViewGroup.VISIBLE);
                            qiantiem_ll.setVisibility(ViewGroup.VISIBLE);
                            details_zhifu_tiem.setText(data.getSignTime());
                            details_qian_tiem.setText(data.getSignTime());
                            cloer_tv.setVisibility(View.GONE);
                            zhifu_tv.setVisibility(View.GONE);
                            yes_shou_tv.setVisibility(View.GONE);
                        } else {
                            order_name.setText("已取消");
                            order_img.setImageResource(R.drawable.order_quxiao);
                            cloer_tv.setVisibility(View.GONE);
                            zhifu_tv.setVisibility(View.GONE);
                            yes_shou_tv.setVisibility(View.GONE);
                        }

                    } else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(OrderDetailsActivity.this, body.getResult().getMessage());
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
    protected void initListener() {
        bank.setOnClickListener(this);
        cloer_tv.setOnClickListener(this);
        zhifu_tv.setOnClickListener(this);
        yes_shou_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                onBackPressed();
                break;
            case R.id.cloer_tv:
                shouPoew(1, data.getId());
                break;
            case R.id.zhifu_tv:
                payData();
                break;
            case R.id.yes_shou_tv:
                shouPoew(2, data.getId());
                break;
        }
    }

    //跳支付页面
    private void payData() {
        Intent intent = new Intent(OrderDetailsActivity.this, OrderPayActivity.class);
        intent.putExtra("payNumber", data.getPayOrderId());
        intent.putExtra("heji", data.getRetailPayAmount() + "");
        startActivity(intent);
        finish();
    }

    //1取消订单  2收货
    private void shouPoew(final int i, final int id) {
        final AlertDialog.Builder alertDialog7 = new AlertDialog.Builder(OrderDetailsActivity.this);
        View view1 = View.inflate(OrderDetailsActivity.this, R.layout.dialog_setview, null);
        final TextView titles = view1.findViewById(R.id.titles);
        final TextView cler = view1.findViewById(R.id.cler);
        final TextView yes = view1.findViewById(R.id.yes);
        final AlertDialog alertDialog = alertDialog7.setView(view1)
                .create();

        alertDialog.show();

        if (i == 1) {
            titles.setText("订单一旦取消不可恢复，您确定要取消吗？");
        } else if (i == 3) {
            titles.setText("您确定要收货吗？");
        }

        cler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {
                    detletMetohd(id);
                    alertDialog.dismiss();
                } else if (i == 2) {
                    shouMethod(id);
                    alertDialog.dismiss();
                }
            }
        });

    }

    //确认收货
    private void shouMethod(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBeans> quxiaoorder = service.getYesData(id);
        quxiaoorder.enqueue(new Callback<ResultBeans>() {
            @Override
            public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                ResultBeans body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    gainData();
                } else {
                    ToastUtils.showToast(OrderDetailsActivity.this, "收货失败");
                }
            }

            @Override
            public void onFailure(Call<ResultBeans> call, Throwable t) {

            }
        });
    }

    //取消订单
    private void detletMetohd(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBeans> quxiaoorder = service.quxiaoorder(id);
        quxiaoorder.enqueue(new Callback<ResultBeans>() {
            @Override
            public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                ResultBeans body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    gainData();
                } else {
                    ToastUtils.showToast(OrderDetailsActivity.this, "取消失败");
                }
            }

            @Override
            public void onFailure(Call<ResultBeans> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
