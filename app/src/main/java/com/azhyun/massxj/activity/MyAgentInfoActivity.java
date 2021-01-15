package com.azhyun.massxj.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.MyApplyInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.PopUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/9/7.
 */

public class MyAgentInfoActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_role)
    TextView tvRole;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_personal_profile)
    TextView tvPersonalProfile;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.line_note)
    AutoLinearLayout lineNote;
    private int applyRole = 0;

    private MyApplyInfoResult.Data.Info info;

    @Override
    protected void initData() {
        //获取经纪人申请详情
        getMyApplyInfo();
    }

    private void getMyApplyInfo() {
        LoadingDialog.show(MyAgentInfoActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyApplyInfoResult> managerResultCall = service.MyApplyInfo(User.INSTANCE.getToken());
        managerResultCall.enqueue(new Callback<MyApplyInfoResult>() {
            @Override
            public void onResponse(Call<MyApplyInfoResult> call, Response<MyApplyInfoResult> response) {
                if (response.isSuccessful()) {
                    MyApplyInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        setInfo(body.getData().getInfo());
                    } else {
                        fund();
                        LoadingDialog.cancel();
                        ToastUtils.showToast(MyApplication.getContext(), body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<MyApplyInfoResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    private void setInfo(MyApplyInfoResult.Data.Info info) {
        LoadingDialog.cancel();
        this.info = info;
        title.setText("申请详情");
        tvRole.setText(getRole(info.getApplyRole()));
        tvStatus.setText(getstatus(info.getStatus()));
        tvName.setText(info.getName());
        tvSex.setText(info.getSex());
        tvPhone.setText(info.getMobilePhone());
        tvRegion.setText(info.getFullName());
        if (info.getAddress() !=  null){
            tvAddress.setText(info.getAddress());
        }else {
            tvAddress.setText("暂无地址");
        }
        tvArea.setText(StringUtils.stringDouble(info.getArea()) + "亩");
        tvPersonalProfile.setText(info.getIntroduce());
        tvNote.setText(info.getNote());
    }

    private String getRole(int applyRole) {
        switch (applyRole) {
            case 0:
                return "普通用户";
            case 1:
                return "村级理事长";
            case 2:
                return "乡级理事长";
            case 3:
                return "服务商";
            case 4:
                return "县级理事长";
            case 5:
                return "省级理事长";

        }
        return "";
    }

    private String getstatus(int status) {
        switch (status) {
            case 1:
                lineNote.setVisibility(View.GONE);
                return "未审核";
            case 2:
                lineNote.setVisibility(View.GONE);
                return "通过";
            case -1:
                lineNote.setVisibility(View.VISIBLE);
                return "拒绝";
        }
        return "";
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        tvStatus.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_agent_info;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;

            case R.id.tv_status:
                if (info.getStatus() == -1) {
//                    radioGroup

                PopUtils utils = new PopUtils(MyAgentInfoActivity.this, R.layout.popu_role_select, AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, tvStatus, Gravity.BOTTOM, 0, 0,
                        new PopUtils.ClickListener() {
                            @Override
                            public void setUplistener(final PopUtils.PopBuilder builder) {
                                builder.setText(R.id.radio0, "普通用户");
                                builder.getView(R.id.radio0).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        applyRole = 0;
                                    }
                                });

                                builder.setText(R.id.radio1, "村级理事长");
                                builder.getView(R.id.radio1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        applyRole = 1;
                                    }
                                });

                                builder.setText(R.id.radio2, "乡级理事长");
                                builder.getView(R.id.radio2).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        applyRole = 2;
                                    }
                                });

                                builder.setText(R.id.radio3, "服务商");
                                builder.getView(R.id.radio3).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        applyRole = 3;
                                    }
                                });

                                builder.setText(R.id.tv_cancel, "取消");
                                builder.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        builder.dismiss();
                                    }
                                });

                                builder.setText(R.id.tv_submit, "确定");
                                builder.getView(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        builder.dismiss();
                                       //确认修改
                                        myapplyUpdate();
                                    }
                                });
                            }
                        });
                }
                break;
        }
    }

    private void myapplyUpdate() {
        if (applyRole == 3){
            HttpService service = ServiceGenerator.createService(HttpService.class);
            String token = (String) SpUtils.get("token", "");
            Call<ManagerResult> managerResultCall = service.MyapplyUpdate(info.getAddress(), applyRole, info.getArea(),0 ,info.getIntroduce(), info.getMobilePhone(), info.getName(), info.getRegion(), info.getSex(), token);
            managerResultCall.enqueue(new Callback<ManagerResult>() {
                @Override
                public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                    if (response.isSuccessful()){
                        ManagerResult body = response.body();
                        if (body.getResult().getCode().equals("200")){
                            ToastUtils.showToast(MyAgentInfoActivity.this,body.getResult().getMessage());
                            //获取经纪人申请详情
                            getMyApplyInfo();
                        }else {
                            ToastUtils.showToast(MyAgentInfoActivity.this,body.getResult().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ManagerResult> call, Throwable t) {

                }
            });

        }else {
            Intent intent = new Intent(MyAgentInfoActivity.this,SelectApproverAcitivity.class);
            intent.putExtra("type",1);
            intent.putExtra("status",2);
            intent.putExtra("applyRole",applyRole);
            intent.putExtra("name",info.getName());
            intent.putExtra("companyName",info.getCompanyName());
            intent.putExtra("gender",info.getSex());
            intent.putExtra("phone",info.getMobilePhone());
            intent.putExtra("area",info.getRegion());
            intent.putExtra("address",info.getAddress());
            startActivityForResult(intent,200);
        }

    }


}
