package com.azhyun.massxj.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.MainActivity2;
import com.azhyun.massxj.activity.azt.CommodityDetailsActivity;
import com.azhyun.massxj.activity.azt.caigou.CaiGouCommodityDetailsActivity;
import com.azhyun.massxj.bean.NoDataResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.UserResult;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.DownButton;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/14.
 */


@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.edt_hone)
    EditText edtHone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.get_code)
    DownButton getCode;
    private String phone;
    private String code;
    private int mReturnCode = 0;

    @SuppressLint("ValidFragment")
    public LoginFragment(int returnCode) {
        mReturnCode = returnCode;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
//        SpUtils.put("region","140203002003");
        getCode.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        phone = edtHone.getText().toString().trim();
        code = edtCode.getText().toString().trim();
        switch (v.getId()) {
            case R.id.get_code://获取验证码
                if (phone.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入手机号");
                } else if (!MobileUtils.isMobile(phone)) {
                    ToastUtils.showToast(getContext(), "请输入正确的手机号");
                } else {
                    //获取验证码
                    if (getCode.isFinish()) {
                        //发送验证码请求成功后调用
                        getSMSCode(phone);
                        getCode.start();
                    }
                }
                break;

            case R.id.btn_login:
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                startActivity(intent);
                if (phone.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入手机号");
                    break;
                } else if (!MobileUtils.isMobile(phone)) {
                    ToastUtils.showToast(getContext(), "请输入正确的手机号");
                    break;
                }
                if (code.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请先获取或输入验证码");
                    break;
                }
                SpUtils.put("JSESSIONID", "");
                SpUtils.put("token", "");
                SpUtils.put("managerRole", 0);
                SpUtils.put("region", "");
                SpUtils.put("isLogin", false);
                SpUtils.put("userId", "");
                SpUtils.put("regionName", "");
                SpUtils.put("phone", "");

                //登录
                userLogin(phone, code);

                Log.d("commodityid", "otherViewClick: SP Login " + SpUtils.get("commodityid", ""));

                break;
        }
    }

    /**
     * 登录
     *
     * @param phone
     * @param code
     */
    private void userLogin(final String phone, String code) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<UserResult> login = service.Login(phone, code);
        login.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.isSuccessful()) {
                    UserResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        UserResult.Data data = body.getData();
                        String status = body.getData().getStatus();
                        if (status != null) {
                            if (status.equals("-1")) {//无效    审核未通过
                                getGion(-1);
                            } else if (status.equals("3")) {//审核中
                                getGion(3);
                            } else {//审核通过
                                SpUtils.put("isLogin", true);
                                SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                                SpUtils.put("token", body.getData().getToken());
                                SpUtils.put("managerRole", body.getData().getManagerRole());
                                SpUtils.put("region", body.getData().getRegion());
                                SpUtils.put("regionName", body.getData().getRegionName());
                                SpUtils.put("fullName", body.getData().getFullName());
                                SpUtils.put("userId", body.getData().getUserId());
                                SpUtils.put("phone", body.getData().getMob());
                                SpUtils.put("name", body.getData().getName());
                                if (mReturnCode == 250) {//采购商品，返回页面
                                    getActivity().finish();
                                } else if (mReturnCode == 350) {//农资商品，返回页面
                                    getActivity().finish();
                                } else {
                                    Intent intent = new Intent(getContext(), MainActivity2.class);
                                    intent.putExtra("type", 0);
                                    startActivity(intent);
                                }
                            }
                        } else {
                            getGion(8);
                        }
//                        setJpush(body.getData().getUserId());//极光推送
                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                ToastUtils.showToast(getContext(), t.getMessage());

            }
        });
    }


    //提示
    private void getGion(int i) {
        String tishitext = "";
        if (i == -1) {
            tishitext = "当前账号审核被拒绝，请重新注册。";
        } else if (i==3){
            tishitext = "当前账号正在审核中，请耐心等待。";
        }else{
            tishitext = "暂无账号";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage(tishitext);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtHone.setText("");
                edtCode.setText("");
            }
        });
        builder.show();
    }

    private void setJpush(String userId) {
        JPushInterface.init(getActivity());             // 初始化 JPush
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
     * 获取短信验证码
     *
     * @param phone
     */
    private void getSMSCode(String phone) {
        int type = 2;//type = 2  登录
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
