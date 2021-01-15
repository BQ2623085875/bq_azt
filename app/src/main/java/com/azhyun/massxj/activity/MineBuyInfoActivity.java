package com.azhyun.massxj.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.SupplyInfoResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DateTimeUtil;
import com.azhyun.massxj.utils.DateUtils;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/24.
 */

public class MineBuyInfoActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img)
    CircleImageView img;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_add_time)
    TextView tvAddTime;
     @BindView(R.id.btn_off)
    TextView btnOff;

    private SupplyInfoResult.Data data;
    private int id;

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        //获取详情
        getBuyInfo(id);
    }

    /**
     * 获取详情
     * @param id
     */
    private void getBuyInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        final Call<SupplyInfoResult> supplyInfo = service.getSupplyInfo(id, User.INSTANCE.getToken());
        supplyInfo.enqueue(new Callback<SupplyInfoResult>() {
            @Override
            public void onResponse(Call<SupplyInfoResult> call, Response<SupplyInfoResult> response) {
                if (response.isSuccessful()) {
                    SupplyInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SupplyInfoResult.Data data = body.getData();
                        //设置详情
                        setSupplyInfo(data);
                    } else {
                        ToastUtils.showToast(MineBuyInfoActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<SupplyInfoResult> call, Throwable t) {

            }
        });
    }

    //设置详情
    private void setSupplyInfo(SupplyInfoResult.Data data) {
        this.data = data;
        title.setText("求购详情");
        Glide.with(this)
                .load(Constant.IMG_URL+data.getInfo().getHeadPortrait())
                .error(R.drawable.err)
                .into(img);
        tvPhone.setText(data.getInfo().getPhone());
        tvTime.setText(DateUtils.convertTimeToFormat(Long.parseLong(data.getInfo().getAddTime())) );
        tvTitle.setText(data.getInfo().getTitle());
        tvClassify.setText(data.getInfo().getCategoryName());
        tvNumber.setText(data.getInfo().getNum()+"公斤");
        tvArea.setText(data.getInfo().getFullName());
        tvDescribe.setText(data.getInfo().getDescriptionContent());
        tvAddTime.setText(DateTimeUtil.getDateToString(Long.parseLong(data.getInfo().getAddTime()),"yyyy-MM-dd HH:mm:ss"));

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btnOff.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_buy_info;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.btn_off:
                MySupplyOperate();
                break;
        }

    }

    private void MySupplyOperate() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.MySupplyOperate(id, User.INSTANCE.getToken(), -1);
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        ToastUtils.showToast(MineBuyInfoActivity.this,body.getResult().getMessage());
                        fund();
                    }else {
                        ToastUtils.showToast(MineBuyInfoActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }
}
