package com.azhyun.massxj.activity.azt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.aizhongtian.UnpayZfBean;
import com.azhyun.massxj.bean.aizhongtian.WeChatZfBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;


import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * 订单支付
 * */
public class OrderPayActivity extends BaseActivity {
    protected final String TAG = "OrderPayActivity";


    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pay_price)
    TextView pay_price;
    @BindView(R.id.weixin_anniu)
    ImageView weixin_anniu;
    @BindView(R.id.yinlian_anniu)
    ImageView yinlian_anniu;
    @BindView(R.id.rl_liji_pay)
    RelativeLayout rl_liji_pay;

    private int mPay = 0;

    private String payNumber;
    private IWXAPI msgApi;
    public static final String MESSAGE = "PayResult.message";
    private String message;
    private String heji;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    public static Intent newIntent(Context context, String message) {
        Intent intent = new Intent(context, OrderPayActivity.class);
        intent.putExtra(MESSAGE, message);
        return intent;
    }


    @Override
    protected void initView() {

        message = getIntent().getStringExtra(MESSAGE);//支付结果消息

        title.setText("订单支付");
        title.setTextColor(getResources().getColor(R.color.black));
        title.setTextSize(18);


        payNumber = getIntent().getStringExtra("payNumber");
        heji = getIntent().getStringExtra("heji");

        pay_price.setText(heji);

        weixin_anniu.setBackground(getResources().getDrawable(R.drawable.zhifu_yes));
        yinlian_anniu.setBackground(getResources().getDrawable(R.drawable.zhifu_no));


    }


    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        weixin_anniu.setOnClickListener(this);
        yinlian_anniu.setOnClickListener(this);
        rl_liji_pay.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank://返回
                fund();
                break;
            case R.id.weixin_anniu://微信
                mPay = 0;
                weixin_anniu.setBackground(getResources().getDrawable(R.drawable.zhifu_yes));
                yinlian_anniu.setBackground(getResources().getDrawable(R.drawable.zhifu_no));
                break;
            case R.id.yinlian_anniu://银联
                mPay = 1;
                weixin_anniu.setBackground(getResources().getDrawable(R.drawable.zhifu_no));
                yinlian_anniu.setBackground(getResources().getDrawable(R.drawable.zhifu_yes));
                break;
            case R.id.rl_liji_pay://立即支付
                if (mPay == 0) {
                    //微信支付
                    payWeChat();
                } else {
                    //银联支付
                    payUnionpay();
                }
                break;
        }

    }

    private void payWeChat() {
        sendWeChatPay();
    }

    private void sendWeChatPay() {
//        boolean wxAppInstalledAndSupported = isWXAppInstalledAndSupported();
//        if (wxAppInstalledAndSupported) {
//            PayReq req = new PayReq();
//            req.appId = Constant.APP_ID;
//            req.partnerId = data.getAppid();
//            req.prepayId = data.getPrepayId();
//            req.packageValue = "Sign=WXPay";
//            req.nonceStr = data.getNonceStr();
//            req.timeStamp = data.getTimeStamp();
//            req.sign = data.getWxSign();
//            msgApi.registerApp(Constant.APP_ID);
//            boolean b = msgApi.sendReq(req);
//            finish();
//        } else {
//            ToastUtils.showToast(OrderPayActivity.this, "您当前没有安装微信！");
//        }


        //微信支付
        boolean wxAppInstalledAndSupported = isWXAppInstalledAndSupported();
        if (wxAppInstalledAndSupported) {
            buyservice();
        } else {
            ToastUtils.showToast(OrderPayActivity.this, "您当前没有安装微信！");
        }
    }

    private void buyservice() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<WeChatZfBean> weChat = service.getWeChat(payNumber);
        weChat.enqueue(new Callback<WeChatZfBean>() {
            @Override
            public void onResponse(Call<WeChatZfBean> call, Response<WeChatZfBean> response) {
                WeChatZfBean body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    WeChatZfBean.ResultData data = body.getData();
                    sendPayRequest(data);
                }
            }

            @Override
            public void onFailure(Call<WeChatZfBean> call, Throwable t) {

            }
        });
    }

    //微信相关------>>--
    public void sendPayRequest(WeChatZfBean.ResultData data) {
        PayReq req = new PayReq();
        req.appId = Constant.APP_ID;
        req.partnerId = data.getPartnerId();
        req.prepayId = data.getPrepayId();
        req.nonceStr = data.getNonceStr();
        req.timeStamp = data.getTimeStamp();
        req.packageValue = "Sign=WXPay";
        req.sign = data.getWxSign();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        //3.调用微信支付sdk支付方法
        boolean b = msgApi.sendReq(req);
        finish();
    }

    private boolean isWXAppInstalledAndSupported() {
        msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(Constant.APP_ID);

        boolean wxAppInstalled = msgApi.isWXAppInstalled();
        //boolean wxAppSupportAPI = msgApi.isWXAppSupportAPI();
        boolean sIsWXAppInstalledAndSupported = wxAppInstalled
                /*&& wxAppSupportAPI*/;

        return sIsWXAppInstalledAndSupported;
    }

    private void payUnionpay() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<UnpayZfBean> unionpay = service.getUnionpay(payNumber);
        unionpay.enqueue(new Callback<UnpayZfBean>() {
            @Override
            public void onResponse(Call<UnpayZfBean> call, Response<UnpayZfBean> response) {
                UnpayZfBean body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    UnpayZfBean.ResultData data = body.getData();
                    // “00” – 银联正式环境
                    // “01” – 银联测试环境，该环境中不发生真实交易
                    String tn = data.getTn();
                    String serverMode = "00";
                    UPPayAssistEx.startPay(OrderPayActivity.this, null, null, tn, serverMode);
                }
            }

            @Override
            public void onFailure(Call<UnpayZfBean> call, Throwable t) {

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            // 支付成功后，extra中如果存在result_data，取出校验
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                msg = "支付成功";
                String result = data.getExtras().getString("result_data");
                /*try {
                    JSONObject resultJson = new JSONObject(result);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    // 验签证书同后台验签证书
                    // 此处的verify，商户需送去商户后台做验签
                    boolean ret = verify(dataOrg, sign, mMode);
                    if (ret) {
                        // 验证通过后，显示支付结果
                        msg = "支付成功！";
                    } else {
                        // 验证不通过后的处理
                        // 建议通过商户后台查询支付结果
                        msg = "支付失败！";
                    }
                } catch (JSONException e) {
                }*/
            } else {
                // 未收到签名信息
                // 建议通过商户后台查询支付结果
                msg = "支付成功";
            }
            Intent intent = new Intent(OrderPayActivity.this, OrderActivity.class);
            intent.putExtra("status", 2);
            intent.putExtra("distinguish", "order");
            startActivity(intent);
            finish();

        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败,请重试";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
        }
        ToastUtils.showToast(OrderPayActivity.this, msg);
    }

}
