package com.azhyun.massxj.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.azhyun.massxj.R;
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

public class BuyInfoActivity extends BaseActivity {
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
    @BindView(R.id.btn_name)
    Button btnName;
      @BindView(R.id.btn_contact)
    Button btnContact;

    private SupplyInfoResult.Data data;

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
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
                        ToastUtils.showToast(BuyInfoActivity.this, body.getResult().getMessage());
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
                .load(Constant.IMG_URL + data.getInfo().getHeadPortrait())
                .error(R.drawable.err)
                .into(img);
        String phone = data.getInfo().getPhone();
        String s = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        if (isLogin){
            tvPhone.setText(phone);
        }else {
            tvPhone.setText(s);
        }
        tvTime.setText(DateUtils.convertTimeToFormat(Long.parseLong(data.getInfo().getAddTime())));
        tvTitle.setText(data.getInfo().getTitle());
        tvClassify.setText(data.getInfo().getCategoryName());
        tvNumber.setText(data.getInfo().getNum()+"公斤");
        tvArea.setText(data.getInfo().getFullName());
        tvDescribe.setText(data.getInfo().getDescriptionContent());
        tvAddTime.setText(DateTimeUtil.getDateToString(Long.parseLong(data.getInfo().getAddTime()),"yyyy-MM-dd HH:mm:ss"));
        btnName.setText("联系人:"+data.getInfo().getContacts());
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btnContact.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_info;
    }

    @Override
    protected void otherViewClick(View view) {
        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.btn_contact:
                if (!isLogin){
                    Intent intent = new Intent(BuyInfoActivity.this,UserLogingActivity.class);
                    intent.putExtra("ActivityName",11);
                    startActivity(intent);
                }else {

                    final View layout = LayoutInflater.from(this).inflate(R.layout.alert_dialog_red, null);

                    final TextView tm = (TextView) layout.findViewById(R.id.dialog_red_message);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    tm.setText(data.getInfo().getPhone());

                    builder.setView(layout);
                    builder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {

                        @SuppressLint("MissingPermission")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //判断是否同意此权限
                            if (ContextCompat.checkSelfPermission(BuyInfoActivity.this,
                                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true
                                if (ActivityCompat.shouldShowRequestPermissionRationale(BuyInfoActivity.this,
                                        Manifest.permission.CALL_PHONE)) {
                                    Toast.makeText(BuyInfoActivity.this, "你之前拒绝过此权限", Toast.LENGTH_SHORT).show();
                                } else {
                                    //申请权限
                                    ActivityCompat.requestPermissions(BuyInfoActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                                }
                            } else {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+data.getInfo().getPhone()));
                                startActivity(intent);
                            }
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
                }

                break;
        }

    }

}
