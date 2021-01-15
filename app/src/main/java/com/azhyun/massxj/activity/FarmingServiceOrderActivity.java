package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.PersonInfoResult;
import com.azhyun.massxj.bean.ServiceInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateTimeUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;
import com.bumptech.glide.Glide;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.DecimalFormat;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/23.
 */

public class FarmingServiceOrderActivity extends BaseActivity implements OnDateSetListener {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.edt_number)
    EditText edtNumber;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_area)
    TextView tvArea;

    @BindView(R.id.tv_new_price)
    TextView tvNewPrice;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edt_remarks)
    EditText editRemarks;
    private ServiceInfoResult.Data.Info info;
    private TimePickerDialog mDialogAll;
    private BottomStyleDialog bottomStyleDialog;
    private String region;

    private String mTime;

    private PersonInfoResult.Data userInfo;

    @Override
    protected void initData() {
        title.setText("预约");
        info = (ServiceInfoResult.Data.Info) getIntent().getSerializableExtra("info");

        Glide.with(this)
                .load(Constant.IMG_URL + info.getDefaultImg())
                .error(R.drawable.err)
                .into(img);
        tvClassify.setText(info.getCategoryName());
        tvName.setText(info.getIntroduce());
        tvPrice.setText("¥" + StringUtils.stringDouble(info.getPrice()) + "元/亩");

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    if (s.toString().equals("0")) {
                        ToastUtils.showToast(FarmingServiceOrderActivity.this, "数量不能为0");
                        return;
                    } else {
                        //计算价格.
                        double price = info.getPrice();
                        String string = s.toString();
                        Double integer = Double.valueOf(string);

                        tvNewPrice.setText("¥" + formatFloatNumber(price * integer));
                    }
                }
            }
        });
        setInfo();
//        getInfo();
    }


    private void getInfo() {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<PersonInfoResult> personInfo = service.getPersonInfo(User.INSTANCE.getToken());
        personInfo.enqueue(new Callback<PersonInfoResult>() {
            @Override
            public void onResponse(Call<PersonInfoResult> call, Response<PersonInfoResult> response) {
                if (response.isSuccessful()){
                    LoadingDialog.cancel();
                    PersonInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        setInfo();

                    }else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(FarmingServiceOrderActivity.this,body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }


            @Override
            public void onFailure(Call<PersonInfoResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    private void setInfo() {
    /*    this.userInfo = userInfo;
        String address = userInfo.getAddress();
        if (address == null) {
            tvArea.setText(userInfo.getFullName());
        } else {
            tvArea.setText(userInfo.getFullName() + userInfo.getAddress());
        }*/

        String fullName = (String) SpUtils.get("fullName", "");
        String region = (String) SpUtils.get("region", "");
        if(region.length() == 12){
            this.region = region;
            tvArea.setText(fullName);
        }else {
            this.region = "";
            tvArea.setText("");
        }

    }

    public static String formatFloatNumber(double value) {
        if (value != 0.00) {
            DecimalFormat df = new DecimalFormat("########.00");
            return df.format(value);
        } else {
            return "0.00";
        }

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        tvArea.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        bottomStyleDialog = new BottomStyleDialog(this, new BottomStyleDialog.OnItemListener() {
            @Override
            public void getArea(String area, String id) {
                tvArea.setText(area);
                region = id;
            }
        }, 1);
        tvArea.setText("");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_farming_service_order;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.tv_time:
                //选择时间
                getTime("date");
                break;
            case R.id.tv_area:
                bottomStyleDialog.show();
                break;
            case R.id.btn_submit://提交
                String num = edtNumber.getText().toString().trim();
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String time = tvTime.getText().toString().trim();
                String area = tvArea.getText().toString().trim();
//                String address = edtAddress.getText().toString().trim();
                String remark = editRemarks.getText().toString().trim();


                if (num.isEmpty()) {
                    ToastUtils.showToast(FarmingServiceOrderActivity.this, "请输入数量");
                    break;
                }
                if (name.isEmpty()){
                    ToastUtils.showToast(FarmingServiceOrderActivity.this, "请输预约人姓名");
                    break;
                }
                if (phone.isEmpty()){
                    ToastUtils.showToast(FarmingServiceOrderActivity.this, "请输入预约人联系方式");
                    break;
                }else if (!MobileUtils.isMobile(phone)){
                    ToastUtils.showToast(FarmingServiceOrderActivity.this,"请输入正确的手机号");
                    break;
                }
                if (Double.parseDouble(num) < info.getMinimumQty()) {
                    ToastUtils.showToast(FarmingServiceOrderActivity.this, "服务数量不能小于最小起订量");
                    break;
                }
//                if (time.isEmpty()) {
//                    ToastUtils.showToast(FarmingServiceOrderActivity.this, "请选择时间");
//                    break;
//                }
                if (area.isEmpty()) {
                    ToastUtils.showToast(FarmingServiceOrderActivity.this, "请选择区域");
                    break;
                }
//                  if (address.isEmpty()){
//                    ToastUtils.showToast(FarmingServiceOrderActivity.this,"请输入服务地址");
//                    break;
//                }
                submit(name,phone,num, remark,"");

                break;
        }
    }

    private void submit(String name, String phone, String num,String remark, String address) {
        LoadingDialog.show(FarmingServiceOrderActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> serviceListResultCall = service.myServiceSave(null,name,phone, num, region,remark, info.getId(), mTime, User.INSTANCE.getToken());
        serviceListResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                LoadingDialog.cancel();
                if (response.isSuccessful()) {
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        ToastUtils.showToast(FarmingServiceOrderActivity.this, body.getResult().getMessage());
                        Intent intent = new Intent(FarmingServiceOrderActivity.this, SubmitSucceedActivity.class);
                        intent.putExtra("id", body.getData().getId());
                        intent.putExtra("type", 3);//3 代表预约单
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(FarmingServiceOrderActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    //获取时间
    private void getTime(String date) {
        long tSaterYears = 10L * 365 * 1000 * 60 * 60 * 24L;   //L 就如float类型  0.5f 一个意思。
        long tenYears = 200L * 365 * 1000 * 60 * 60 * 24L;   //L 就如float类型  0.5f 一个意思。
        mDialogAll = new TimePickerDialog.Builder()
                .setType(Type.ALL)
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(Color.parseColor("#FFCB57"))
                .setWheelItemTextNormalColor(Color.parseColor("#333333"))
                .setWheelItemTextSelectorColor(Color.parseColor("#898989"))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getSupportFragmentManager(), date);

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        if (timePickerView.getTag().equals("date")) {
            mTime = millseconds + "";
            tvTime.setText(DateTimeUtil.getDateToString(millseconds, "yyyy-MM-dd HH:mm"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bottomStyleDialog.cancel();
    }

}
