package com.azhyun.massxj.activity.azt.farmingInsurance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.baoxian.BaoYuDingBean;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.IdNumberUtils;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 农险预定
* */
public class NxReserveActivity extends BaseActivity {
    protected final String TAG = "NxReserveActivity";

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_identity)
    EditText edIdentity;
    @BindView(R.id.ed_mushu)
    EditText edMushu;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.next)
    TextView Next;

    private BottomStyleDialog bottomStyleDialog;
    private String areaID = "";
    private int insuranceItemId = 0;//商品id
    private String userId;
    private int id;
    private String mMushu;
    private String mMIdentity;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_nx_reserve;
    }

    @Override
    protected void initView() {
        title.setText("农险预定");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

        userId = String.valueOf(SpUtils.get("userId", ""));

        insuranceItemId = getIntent().getIntExtra("id", 0);

        bottomStyleDialog = new BottomStyleDialog(NxReserveActivity.this, new BottomStyleDialog.OnItemListener() {
            @Override
            public void getArea(String area, String id) {
                areaID = id;
                tvAddress.setText(area);
            }
        }, 0);//创建地址选择器

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        Next.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.tv_address:
                //获取区域
                bottomStyleDialog.show();
                break;
            case R.id.next:
                String mUserName = edName.getText().toString().trim();
                mMIdentity = edIdentity.getText().toString().trim();
                mMushu = edMushu.getText().toString().trim();
                String mAddress = tvAddress.getText().toString().trim();

                if (mUserName.isEmpty()) {
                    ToastUtils.showToast(NxReserveActivity.this, "请输入姓名!");
                    break;
                }
                if (mMIdentity.isEmpty()) {
                    ToastUtils.showToast(NxReserveActivity.this, "请输入身份证号!");
                    break;
                } else {
                    boolean isIdentity = IdNumberUtils.personIdValidation(mMIdentity);
                    if (!isIdentity) {
                        ToastUtils.showToast(NxReserveActivity.this, "请输入正确的身份证号!");
                        break;
                    }
                }
                if (mMushu.isEmpty()) {
                    ToastUtils.showToast(NxReserveActivity.this, "请输入投保亩数!");
                    break;
                }
                if (mAddress.isEmpty()) {
                    ToastUtils.showToast(NxReserveActivity.this, "请选择投保所在地址!");
                    break;
                }

                NextData(mUserName, mMIdentity, mMushu, mAddress);

                break;
        }
    }

    private void NextData(String mUserName, String mIdentity, final String mMushu, String mAddress) {
        LoadingDialog.show(NxReserveActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<BaoYuDingBean> baoYuDing = service.getBaoYuDing(mAddress, userId, mIdentity, insuranceItemId, mMushu, mUserName, areaID);
        baoYuDing.enqueue(new Callback<BaoYuDingBean>() {
            @Override
            public void onResponse(Call<BaoYuDingBean> call, Response<BaoYuDingBean> response) {
                if (response.isSuccessful()) {
                    bottomStyleDialog.cancel();
                    BaoYuDingBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        BaoYuDingBean.DataBeanX data = body.getData();
                        Intent intent = new Intent(NxReserveActivity.this, NxTxOrderActivity.class);
                        intent.putExtra("yudingid", data.getId());
                        intent.putExtra("companyId", data.getCompanyId());
                        intent.putExtra("insuranceItemId", insuranceItemId);
                        intent.putExtra("mushu",mMushu);
                        intent.putExtra("Identity",mMIdentity);
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(NxReserveActivity.this, body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<BaoYuDingBean> call, Throwable t) {

            }
        });
    }
}
