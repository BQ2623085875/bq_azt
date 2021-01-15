package com.azhyun.massxj.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/9/4.
 */

public class UserInfoAlterActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edt)
    EditText edt;
    @BindView(R.id.img_delete)
    ImageView imgDelete;
    @BindView(R.id.btn_alter)
    Button btnAlter;
    private int type = 0;

    @Override
    protected void initData() {
        title.setText("修改个人信息");
        type = getIntent().getIntExtra("type", 0);
        String name = getIntent().getStringExtra("name");
        String nickName = getIntent().getStringExtra("nickName");
        String phone = getIntent().getStringExtra("phone");
        if (type == 1){
            if (name != null){
                edt.setText(name);
                }else {
                edt.setHint("请输入新姓名");
                }
        } else if (type == 2){
            if (nickName != null){
                edt.setText(nickName);
              }else {
                    edt.setHint("请输入新昵称");
                }
        }else if (type == 3){
            if (phone != null){
                edt.setText(phone);
            }else {
                edt.setHint("请输入新手机号");
                }
        }
        if (edt.getText().toString().trim().isEmpty()){

            imgDelete.setVisibility(View.GONE);
        }else {
            imgDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        btnAlter.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edt.getText().toString().trim().isEmpty()){
                    imgDelete.setVisibility(View.GONE);
                }else {
                    imgDelete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info_alter;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.img_delete:
                edt.setText(null);
                break;
            case R.id.btn_alter:
                String edt = this.edt.getText().toString().trim();
                if (edt.isEmpty()){
                    if (type == 1){
                        ToastUtils.showToast(UserInfoAlterActivity.this,"请输入新的姓名");
                    }else  if (type == 2){
                        ToastUtils.showToast(UserInfoAlterActivity.this,"请输入新的昵称");
                    }else  if (type == 3){
                        ToastUtils.showToast(UserInfoAlterActivity.this,"请输入新的手机号");
                    }
                    break;
                }
                    userInfoAlter(edt);
                break;

        }
    }

    /**
     * 修改
     * @param edt
     */
    private void userInfoAlter(final String edts) {
        String name = null;
        String nickName = null;
        String phone = null;
        if (type == 1){
            name = edts;
        }else if (type == 2){
            nickName = edts;
        }else if (type == 3){
            phone = edts;
        }

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> personInfoResultCall = service.personUpdate(phone, name, nickName, User.INSTANCE.getToken());
        personInfoResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        ToastUtils.showToast(UserInfoAlterActivity.this,body.getResult().getMessage());
                        Intent intent = new Intent();
                        intent.putExtra("String",edts);
                        setResult(11,intent);
                        fund();
                    }else {
                        ToastUtils.showToast(UserInfoAlterActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }

}
