package com.azhyun.massxj.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.MyManageInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementDetailsActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_applyRole)
    TextView tvApplyRole;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_area)
    TextView tvArea;

    @Override
    protected void initData() {
        title.setText("详情");
        //获取详情
        int id = getIntent().getIntExtra("id", 0);
        getInfo(id);
    }

    private void getInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyManageInfoResult> myManageInfoResultCall = service.MyManageInfo(User.INSTANCE.getToken(), id);
        myManageInfoResultCall.enqueue(new Callback<MyManageInfoResult>() {
            @Override
            public void onResponse(Call<MyManageInfoResult> call, Response<MyManageInfoResult> response) {
                if (response.isSuccessful()) {
                    MyManageInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        setInfo(body.getData().getInfo());
                    } else {
                        ToastUtils.showToast(ManagementDetailsActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyManageInfoResult> call, Throwable t) {

            }
        });
    }

    private void setInfo(MyManageInfoResult.Data.Info info) {
        tvApplyRole.setText(getRole(info.getApplyRole()));
        tvName.setText(info.getName());
        tvSex.setText(info.getSex());
        tvPhone.setText(info.getMobilePhone());
        tvRegion.setText(info.getFullName());
        tvArea.setText(info.getArea()+"亩");
    }

    private String getRole(long applyRole) {
        //0.普通用户 1.村级理事长 2.乡级理事长 3.服务商 4.县级理事长 5.省级理事长
        switch ((int) applyRole){
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

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_management_details;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
        }
    }

}
