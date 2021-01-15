package com.azhyun.massxj.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;

import butterknife.BindView;

/**
 * Created by tl on 2018/8/20.
 */

public class SubmitSucceedActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_check)
    Button btnCheck;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    bank.setOnClickListener(this);
    btnBack.setOnClickListener(this);
    btnCheck.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        int type = getIntent().getIntExtra("type", 0);
        if (type == 3){
            title.setText("预约成功");
            btnCheck.setText("查看预约");
        }

        title.setText("提交成功");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_succeed;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                Intent intent = new Intent(this,MainActivity2.class);
                startActivity(intent);
                break;
            case R.id.btn_back://返回首页
                Intent intent1 = new Intent(this,MainActivity2.class);
                startActivity(intent1);
                break;
            case R.id.btn_check://查看申请
                int id = getIntent().getIntExtra("id", 0);
                int type = getIntent().getIntExtra("type", 0);
                if (type == 1){
                    Intent intent3 = new Intent(this, ApplyForInfoActivity.class);
                    intent3.putExtra("id", id);
                    intent3.putExtra("sub",1);
                    startActivity(intent3);
                }else if (type == 2){
                    Intent intent2 = new Intent(this,MyAgentInfoActivity.class);
//                    intent2.putExtra("id",id);
                    startActivity(intent2);
                }else if (type == 3){
//                    Intent intent4 = new Intent(this,OrderInfoActivity.class);
                    Intent intent4 = new Intent(this,OrderInfoActivity2.class);
                    intent4.putExtra("id",id);
                    intent4.putExtra("sub",1);
                    startActivity(intent4);
                }

                break;
        }
    }

    long preTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this,MainActivity2.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
