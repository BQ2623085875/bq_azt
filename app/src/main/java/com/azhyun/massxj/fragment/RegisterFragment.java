package com.azhyun.massxj.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.MainActivity2;
import com.azhyun.massxj.bean.NoDataResult;
import com.azhyun.massxj.bean.RegisterResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.utils.AlignTextView;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;
import com.azhyun.massxj.view.DownButton;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.MyBottomDialog;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Set;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/14.
 */

public class RegisterFragment extends LazyLoadFragment implements View.OnClickListener {

    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.selected_area)
    LinearLayout selectedArea;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.edt_linkman)
    EditText edtLinkman;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.get_code)
    DownButton getCode;
    @BindView(R.id.tuijianren)
    EditText tuijianren;
    Unbinder unbinder;
    private String phone;
    private String linkman;
    private String area;
    private String code;
    private MyBottomDialog dialog;
    private BottomStyleDialog bottomStyleDialog;
    private String areaID = "";
    private String mTuijianren;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        selectedArea.setOnClickListener(this);//获取区域;
        getCode.setOnClickListener(this);//获取验证码
        btnRegister.setOnClickListener(this);//注册

        bottomStyleDialog = new BottomStyleDialog(getContext(), new BottomStyleDialog.OnItemListener() {
            @Override
            public void getArea(String area, String id) {
                areaID = id;
                tvArea.setText(area);
            }
        }, 0);//创建地址选择器
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_register;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View v) {
        phone = edtPhone.getText().toString().trim();//手机号
        area = tvArea.getText().toString().trim();//地址
        linkman = edtLinkman.getText().toString().trim();//联系人
        code = edtCode.getText().toString().trim();//验证码
        mTuijianren = tuijianren.getText().toString().trim();//推荐人编号

        switch (v.getId()) {
            case R.id.selected_area:
                bottomStyleDialog.show();
                break;
            case R.id.get_code://获取验证码
                if (phone.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入手机号");
                    break;
                } else if (!MobileUtils.isMobile(phone)) {
                    ToastUtils.showToast(getContext(), "请输入正确的手机号");
                    break;
                }
                if (getCode.isFinish()) {
                    //发送验证码请求成功后调用
                    getCode();
                    getCode.start();
                }
                break;
            case R.id.btn_register://注册
                if (linkman.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入联系人");
                    break;
                }
                if (area.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请选择区域地址");
                    break;
                }
               /* if (mTuijianren.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请选输入推荐人编号");
                    break;
                }*/
                if (phone.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入手机号");
                    break;
                } else if (!MobileUtils.isMobile(phone)) {
                    ToastUtils.showToast(getContext(), "请输入正确的手机号");
                    break;
                }
                if (code.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请先获取并输入验证码");
                    break;
                }
                //注册
                isRegister();
                break;
        }
    }

    /**
     * 注册
     */
    private void isRegister() {
        LoadingDialog.show(getContext());
        HttpService httpService = ServiceGenerator.createService(HttpService.class);
        Call<RegisterResult> registerResultCall = httpService.PostRegister(area, phone, linkman, mTuijianren, areaID, code);
        registerResultCall.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                LoadingDialog.cancel();
                if (response.isSuccessful()) {
                    RegisterResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());

                        getArlogin();

//                        setJpush(body.getData().getUserId());//极光推送
                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                ToastUtils.showToast(getContext(), t.getMessage());
                LoadingDialog.cancel();
            }
        });

    }

    //提示框
    private void getArlogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage("您的信息已提交后台审核，审核结果将以短信通知，请您耐心等待。");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtLinkman.setText("");
                tvArea.setText("");
                tuijianren.setText("");
                edtPhone.setText("");
                edtCode.setText("");
            }
        });
        builder.show();
    }

    private void setJpush(String userId) {
        JPushInterface.init(getContext());             // 初始化 JPush
        Log.i("JPush", "isPushStopped:" + JPushInterface.isPushStopped(getContext()));
        if (JPushInterface.isPushStopped(getContext())) {
            JPushInterface.resumePush(getContext());//恢复极光推送
        }
        Log.i("JPush", "JPushAlias:" + userId);

        JPushInterface.setAlias(getContext(), userId, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.i("JPush", "Login:");
            }
        });
    }

    /**
     * 获取验证吗
     */
    public void getCode() {
        int type = 1;//type = 1  注册
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NoDataResult> code = service.getCode(phone, type);
        code.enqueue(new Callback<NoDataResult>() {
            @Override
            public void onResponse(Call<NoDataResult> call, Response<NoDataResult> response) {
                LoadingDialog.cancel();
                if (response.isSuccessful()) {
                    NoDataResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    } else {
                        getCode.cancel();
                        getCode.normalBackground();
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<NoDataResult> call, Throwable t) {
                ToastUtils.showToast(getContext(), t.getMessage());
                LoadingDialog.cancel();
            }
        });
    }
}