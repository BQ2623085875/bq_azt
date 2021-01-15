package com.azhyun.massxj.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.LandInfoResult;
import com.azhyun.massxj.bean.ManagerInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/23.
 */

public class AgentInfoActivity extends BaseActivity {
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
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_personal_profile)
    TextView tvPersonalProfile;
    @BindView(R.id.line_note)
    AutoLinearLayout lineNote;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.btn_pass)
    Button btnPass;
    @BindView(R.id.btn_reject)
    Button btnReject;
    @BindView(R.id.line_bottom_btn)
    AutoLinearLayout lineBottomBtn;
    private int id;

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        //获取详情
        getManagerInfo(id);
    }

    private void getManagerInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerInfoResult> managerInfo = service.getManagerInfo(id, User.INSTANCE.getToken());
        managerInfo.enqueue(new Callback<ManagerInfoResult>() {
            @Override
            public void onResponse(Call<ManagerInfoResult> call, Response<ManagerInfoResult> response) {
                if (response.isSuccessful()) {
                    ManagerInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        setInfo(body.getData().getInfo());
                    } else {
                        ToastUtils.showToast(AgentInfoActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerInfoResult> call, Throwable t) {
                Log.e("----->>",t.getMessage());
            }
        });
    }

    private void setInfo(ManagerInfoResult.Data.Info info) {
        if (info.getStatus() == 1){
            lineBottomBtn.setVisibility(View.VISIBLE);
            lineNote.setVisibility(View.GONE);
        }else if (info.getStatus() == 2){
            lineBottomBtn.setVisibility(View.GONE);
            lineNote.setVisibility(View.GONE);
        } else if (info.getStatus() == -1){
            lineBottomBtn.setVisibility(View.GONE);
            lineNote.setVisibility(View.VISIBLE);
        }
        title.setText("详情");
        tvRole.setText(getRole(info.getApplyRole()));
        tvStatus.setText(getStatus(info.getStatus()));
        tvName.setText(info.getName());
        tvSex.setText(info.getSex());
        tvPhone.setText(info.getMobilePhone());
        tvRegion.setText(info.getFullName());
        if (info.getAddress() != null){
            tvAddress.setText(info.getAddress());
        }else {
            tvAddress.setText("暂无详细地址");
        }
        tvArea.setText(StringUtils.stringDouble(info.getArea())+"亩");
        tvPersonalProfile.setText(info.getIntroduce());
        tvNote.setText(info.getNote());
    }

    private String getStatus(int status) {
        //1.未审核 2.审核通过 -1.审核拒绝
        switch (status){
            case 1:
                return "待审核";
            case 2:
                return "同意";
            case -1:
                return "拒绝";

        }
        return "";
    }

    private String getRole(int applyRole) {
        //1.村级理事长 2.乡级联络员 3.服务商
        switch (applyRole){
            case 1:
                return "村级理事长";
              case 2:
                return "乡级理事长";
              case 3:
                return "服务商";

        }
        return "";
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btnPass.setOnClickListener(this);
        btnReject.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agent_info;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.btn_pass:
                operate(1,null);
                break;
            case R.id.btn_reject:
                //拒绝理由
                rejectDialog();
                break;
        }
    }
    /**
     * 拒绝弹窗
     */
    private void rejectDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dailog_jeject_reason, null);
        // dialog.setView(view);// 将自定义的布局文件设置给dialog
        dialog.setView(view, 0, 0, 0, 0);
        //找到控件
        final EditText editText = (EditText) view.findViewById(R.id.edt);
        Button button = (Button) view.findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确认拒绝
                String string = editText.getText().toString().trim();
                if (!string.isEmpty()) {

                    operate(-1,string);
                    dialog.dismiss();
                } else {
                    ToastUtils.showToast(AgentInfoActivity.this, "请填写拒绝理由!");
                }
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

//        Window win = dialog.getWindow();
//        win.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        win.setAttributes(lp);
//        dialog.getWindow().setAttributes(lp); //设置生效


        WindowManager.LayoutParams params = window.getAttributes();
        //设置窗口宽度为充满全屏
        params.width = WindowManager.LayoutParams.MATCH_PARENT;//如果不设置,可能部分机型出现左右有空隙,也就是产生margin的感觉
        //设置窗口高度为包裹内容
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;//显示dialog的时候,就显示软键盘
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;//就是这个属性导致window后所有的东西都成暗淡
        params.dimAmount = 0.5f;//设置对话框的透明程度背景(非布局的透明度)
        //将设置好的属性set回去
        window.setAttributes(params);

    }


    private void operate(final int type, String note){
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<LandInfoResult> landInfoResultCall = service.managerOperate(id,note,type,User.INSTANCE.getToken());
        landInfoResultCall.enqueue(new Callback<LandInfoResult>() {
            @Override
            public void onResponse(Call<LandInfoResult> call, Response<LandInfoResult> response) {
                if (response.isSuccessful()){
                    LandInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        ToastUtils.showToast(AgentInfoActivity.this,body.getResult().getMessage());
                        if (type == 1){
                            Intent intent = new Intent(AgentInfoActivity.this, ApplyForResultActivity.class);
                            intent.putExtra("type", 1);//type = 1 为通过
                            intent.putExtra("id", id);
                            intent.putExtra("classfy", 1);
                            startActivityForResult(intent,132);
                        }else if (type == -1){
                            Intent intent = new Intent(AgentInfoActivity.this, ApplyForResultActivity.class);
                            intent.putExtra("type", 2);//type = 2 为拒绝
                            intent.putExtra("id", id);
                            intent.putExtra("classfy", 1);
                            startActivityForResult(intent,132);
                        }
                    }else {
                        ToastUtils.showToast(AgentInfoActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LandInfoResult> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 132){
            fund();
        }
    }
}
