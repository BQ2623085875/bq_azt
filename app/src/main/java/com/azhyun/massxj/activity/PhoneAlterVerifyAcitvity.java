package com.azhyun.massxj.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2019/1/14.
 */

public class PhoneAlterVerifyAcitvity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.relation_title)
    AutoRelativeLayout relationTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.line_phone)
    AutoLinearLayout linePhone;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private String hpone;

    @Override
    protected void initData() {
         hpone = getIntent().getStringExtra("hpone");
        tvPhone.setText(hpone);
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_alter_verofy;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.tv_submit:
                String trim = tvCode.getText().toString().trim();
                if (trim.isEmpty()){
                    ToastUtils.showToast(PhoneAlterVerifyAcitvity.this,"请输入验证码");
                }else {
                    submit(hpone,trim);
                }

                break;

        }
    }

    private void submit(String hpone, String trim) {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.phoneUpdate(hpone, trim);
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    LoadingDialog.cancel();
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        ToastUtils.showToast(PhoneAlterVerifyAcitvity.this,"修改成功");
                        fund();
                    }else {
                        ToastUtils.showToast(PhoneAlterVerifyAcitvity.this,body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }


}
