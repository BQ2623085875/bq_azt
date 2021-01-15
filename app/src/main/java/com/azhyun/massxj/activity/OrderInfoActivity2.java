package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.MyServiceInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateTimeUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.Serializable;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2019/1/16.
 */

public class OrderInfoActivity2 extends BaseActivity {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image_submit)
    ImageView imageSubmit;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.img_confirm)
    ImageView imgConfirm;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.img_fukuan)
    ImageView imgFukuan;
    @BindView(R.id.tv_fukuan)
    TextView tvFukuan;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.img_service)
    ImageView imgService;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.img_review)
    ImageView imgReview;
    @BindView(R.id.tv_review)
    TextView tvReview;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_booking_name)
    TextView tvBookingName;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.tv_code_id)
    TextView tvCodeId;
    @BindView(R.id.line_code_id)
    AutoLinearLayout lineCodeId;
    @BindView(R.id.tv_submit_time)
    TextView tvSubmitTime;
    @BindView(R.id.line_submit_time)
    AutoLinearLayout lineSubmitTime;
    @BindView(R.id.tv_confirm_time)
    TextView tvConfirmTime;
    @BindView(R.id.line_confirm_time)
    AutoLinearLayout lineConfirmTime;
    @BindView(R.id.tv_confirm_service)
    TextView tvConfirmService;
    @BindView(R.id.line_confirm_service)
    AutoLinearLayout lineConfirmService;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
     @BindView(R.id.tv_remark)
    TextView tvRemark;
     @BindView(R.id.line_bottm)
    AutoLinearLayout lineBottm;
    @BindView(R.id.line_pay_time)
    AutoLinearLayout linePayTime;
    @BindView(R.id.btn)
    Button btn;
      @BindView(R.id.btn1)
    Button btn1;

    private MyServiceInfoResult.Data.Info info;
    String introduce;

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        introduce=getIntent().getStringExtra("introduce");
        //获取详情
        getOrderInfo(id);
    }

    private void getOrderInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyServiceInfoResult> myservInfo = service.getMyservInfo(id, User.INSTANCE.getToken());
        myservInfo.enqueue(new Callback<MyServiceInfoResult>() {
            @Override
            public void onResponse(Call<MyServiceInfoResult> call, Response<MyServiceInfoResult> response) {
                if (response.isSuccessful()) {
                    MyServiceInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        setInfo(body.getData().getInfo());
                    } else {
                        ToastUtils.showToast(OrderInfoActivity2.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyServiceInfoResult> call, Throwable t) {

            }
        });
    }

    /**
     * 设置详情
     *
     * @param info
     */
    private void setInfo(MyServiceInfoResult.Data.Info info) {
        this.info = info;
        title.setText("预约详情");
        int managerRole = (int) SpUtils.get("managerRole", 0);
        int sub = getIntent().getIntExtra("sub", 0);
        if (sub == 1){
            lineBottm.setVisibility(View.GONE);
        }else {
            lineBottm.setVisibility(View.VISIBLE);
            if (managerRole == 1) {
                btn.setVisibility(View.VISIBLE);
            } else {
                btn.setVisibility(View.GONE);

            }
        }


        //设置状态
        setStatus(info.getStatus());

        //图片
        Glide.with(this).load(Constant.IMG_URL + info.getDefaultImg()).placeholder(R.drawable.err).into(img);

        tvName.setText(info.getServiceCategory());
        if (sub == 1){
            tvDescribe.setText(info.getIntroduce());
        }else {
            tvDescribe.setText(introduce);
        }
        tvRemark.setText(info.getRemark());
        tvPrice.setText("¥" + StringUtils.stringDouble(info.getServicePrice()) + "元/亩");
        tvIntroduction.setText(info.getUserName());
        tvBookingName.setText(info.getUserName());
        String a=info.getUserMob();
        if (a != null){
            String b=a.substring(3,7);
            String c=a.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
            tvUserPhone.setText(c);
        }
        tvArea.setText(info.getFullName());
        tvNum.setText(info.getNum()+" 亩");
        tvAllPrice.setText("¥"+StringUtils.stringDouble(info.getServicePrice()*info.getNum()));

    }

    private void setStatus(int status) {
        switch (status) {
            case 1:
                btn.setText("确认预约");
                btn1.setVisibility(View.VISIBLE);
                imageSubmit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_0));
                text2.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_0));
                text3.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_0));
                text4.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));

                tvCodeId.setText(info.getCode());
                tvSubmitTime.setText(DateTimeUtil.getDateToString(info.getAddTime(),"yyyy-MM-dd HH:mm"));

                lineConfirmTime.setVisibility(View.GONE);//确认时间
                linePayTime.setVisibility(View.GONE);//付款时间
                lineConfirmService.setVisibility(View.GONE);//服务时间
                break;
            case 2:
                btn.setText("确认已付款");
                btn1.setVisibility(View.GONE);
                imageSubmit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_1));
                text2.setBackgroundColor(Color.parseColor("#6ca323"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_0));
                text3.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_0));
                text4.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));


                tvCodeId.setText(info.getCode());
                tvSubmitTime.setText(DateTimeUtil.getDateToString(info.getAddTime(),"yyyy-MM-dd HH:mm"));//提交时间
                tvConfirmTime.setText(DateTimeUtil.getDateToString(info.getAffirmTime(),"yyyy-MM-dd HH:mm"));//确认时间

                linePayTime.setVisibility(View.GONE);//付款时间
                lineConfirmService.setVisibility(View.GONE);//服务时间
                break;

            case 3:
                btn.setText("确认已服务");
                btn1.setVisibility(View.GONE);
                imageSubmit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_1));
                text2.setBackgroundColor(Color.parseColor("#6ca323"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_1));
                text3.setBackgroundColor(Color.parseColor("#6ca323"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_0));
                text4.setBackgroundColor(Color.parseColor("#D2E6B9"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));

                tvCodeId.setText(info.getCode());
                tvSubmitTime.setText(DateTimeUtil.getDateToString(info.getAddTime(),"yyyy-MM-dd HH:mm"));//提交时间
                tvConfirmTime.setText(DateTimeUtil.getDateToString(info.getAffirmTime(),"yyyy-MM-dd HH:mm"));//确认时间
                tvPayTime.setText(DateTimeUtil.getDateToString(info.getAffirmPayTime(),"yyyy-MM-dd HH:mm"));//付款时间

                lineConfirmService.setVisibility(View.GONE);//服务时间
                break;

            case 4:
                btn.setVisibility(View.VISIBLE);
                btn.setText("立即评价");
                btn1.setVisibility(View.GONE);
                imageSubmit.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tijiao_1));
                text1.setBackgroundColor(Color.parseColor("#6ca323"));

                imgConfirm.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.queren_1));
                text2.setBackgroundColor(Color.parseColor("#6ca323"));

                imgFukuan.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fukuan_1));
                text3.setBackgroundColor(Color.parseColor("#6ca323"));

                imgService.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fuwu_1));
                text4.setBackgroundColor(Color.parseColor("#6ca323"));

                imgReview.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pingjia_0));

                tvCodeId.setText(info.getCode());
                tvSubmitTime.setText(DateTimeUtil.getDateToString(info.getAddTime(),"yyyy-MM-dd HH:mm"));//提交时间
                tvConfirmTime.setText(DateTimeUtil.getDateToString(info.getAffirmTime(),"yyyy-MM-dd HH:mm"));//确认时间
                tvPayTime.setText(DateTimeUtil.getDateToString(info.getAffirmPayTime(),"yyyy-MM-dd HH:mm"));//付款时间
                tvConfirmService.setText(DateTimeUtil.getDateToString(info.getAffirmServiceTime(),"yyyy-MM-dd HH:mm"));//服务时间
                break;

        }
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_info2;//改版
    }

    @Override
    protected void otherViewClick(View view) {
        int type = 0;
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;

            case R.id.btn:
                if (info.getStatus() == 4) {//发表评论
                    Intent intent = new Intent(this, AddCommentActivity.class);
                    intent.putExtra("orderInfo", (Serializable) info);
                    startActivityForResult(intent, 127);
                } else if (info.getStatus() == 1) {//确认
                    type = 1;
                    MyservOperate(info.getId(), type);
                } else if (info.getStatus() == 2) {//确认付款
                    type = 2;
                    MyservOperate(info.getId(), type);
                } else if (info.getStatus() == 3) {//确认已服务
                    type = 3;
                    MyservOperate(info.getId(), type);
                }
                break;
            case R.id.btn1:
                type = -1;
                MyservOperate(info.getId(), type);
                break;
        }
    }

    /**
     * 操作预约单
     *
     * @param id
     * @param type
     */
    private void MyservOperate(int id, int type) {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyServiceInfoResult> myServiceInfoResultCall = service.MyservOperate(id, type);
        myServiceInfoResultCall.enqueue(new Callback<MyServiceInfoResult>() {
            @Override
            public void onResponse(Call<MyServiceInfoResult> call, Response<MyServiceInfoResult> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    MyServiceInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        fund();
                        ToastUtils.showToast(OrderInfoActivity2.this, body.getResult().getMessage());
                    } else {
                        ToastUtils.showToast(OrderInfoActivity2.this, body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<MyServiceInfoResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 127) {
            fund();
        }
    }
}
