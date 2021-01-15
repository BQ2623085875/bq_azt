package com.azhyun.massxj.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;

import butterknife.BindView;

/**
 * Created by tl on 2018/8/22.
 */

public class ApplyForResultActivity extends BaseActivity {


    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.line_pass)
    LinearLayout linePass;
    @BindView(R.id.line_reject)
    LinearLayout lineReject;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_info)
    Button btnInfo;
    @BindView(R.id.pass)
    TextView pass;
    @BindView(R.id.reject)
    TextView reject;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        int type = getIntent().getIntExtra("type", 0);
        if (getIntent().getIntExtra("classfy", 0) != 1){
            pass.setText("您已同意该土地托管申请");
            reject.setText("您已拒绝该土地托管申请");
        }else {
            pass.setText("您已同意该经纪人申请");
            reject.setText("您已拒绝该经纪人申请");
        }
        if (type == 1) {
            title.setText("审核成功");
            linePass.setVisibility(View.VISIBLE);
            lineReject.setVisibility(View.GONE);
        } else {
            title.setText("拒绝成功");
            linePass.setVisibility(View.GONE);
            lineReject.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_result;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                fund();
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                Intent intent2 = new Intent(this, MainActivity2.class);
                startActivity(intent2);
                break;
            case R.id.btn_info:
                if (getIntent().getIntExtra("classfy", 0) != 1) {
                    Intent intent1 = new Intent(this, ApplyForInfoActivity.class);
                    intent1.putExtra("id", getIntent().getIntExtra("id", 0));
                    startActivity(intent1);
                } else {
                    intent = new Intent(this, AgentInfoActivity.class);
                    intent.putExtra("id", getIntent().getIntExtra("id", 0));
                    startActivity(intent);
                }

                fund();
                break;
        }
    }

}
