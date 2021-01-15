package com.azhyun.massxj.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.NoDataResult;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.hb.dialog.myDialog.MyAlertDialog;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2019/1/10.
 */

public class UserInfoPhoneAlterActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_next)
    TextView titleNext;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.edit_new_phone)
    EditText editNewPhone;

    @Override
    protected void initData() {
        String phone = getIntent().getStringExtra("phone");
        tvContent.setText("更换手机号后,下次登录可使用新手机号登录.当前手机号:"+phone);
    }

    @Override
    protected void initListener() {
        titleNext.setOnClickListener(this);
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info_phone_alter;
    }

    @Override
    protected void otherViewClick(View view) {
        String newPhone = editNewPhone.getText().toString().trim();
        switch (view.getId()){
            case R.id.title_next:
                //确认手机号
                if (newPhone.isEmpty()){
                    ToastUtils.showToast(UserInfoPhoneAlterActivity.this,"请输入手机号");
                }else if (!MobileUtils.isMobile(newPhone)){
                    ToastUtils.showToast(UserInfoPhoneAlterActivity.this,"请输入正确的手机号");
                }else {
                    getDialog(newPhone);
                }

                break;
            case R.id.bank:
                finish();
                break;
        }
    }

    private void getDialog(final String newPhone) {
        final MyAlertDialog myAlertDialog = new MyAlertDialog(this);
        myAlertDialog.builder();
        myAlertDialog.setTitle("确认手机号");
        myAlertDialog.setMsg("我们将发送验证码短信到这个号码:"+newPhone);
        myAlertDialog.setPositiveButton("好", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取验证码
//                myAlertDialog.dismiss();
                getCode(newPhone);
            }
        });
        myAlertDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myAlertDialog.dismiss();
            }
        });
        myAlertDialog.show();

    }

    private void getCode(final String newPhone) {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NoDataResult> code = service.getCode(newPhone, 1);
        code.enqueue(new Callback<NoDataResult>() {
            @Override
            public void onResponse(Call<NoDataResult> call, Response<NoDataResult> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    NoDataResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        ToastUtils.showToast(UserInfoPhoneAlterActivity.this, body.getResult().getMessage());
                        Intent intent = new Intent(UserInfoPhoneAlterActivity.this,PhoneAlterVerifyAcitvity.class);
                        intent.putExtra("hpone",newPhone);
                        startActivityForResult(intent,200);

                    } else {
                        ToastUtils.showToast(UserInfoPhoneAlterActivity.this, body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<NoDataResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200){
            fund();
        }
    }
}
