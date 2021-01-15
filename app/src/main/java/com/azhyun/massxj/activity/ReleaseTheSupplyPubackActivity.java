package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ImgUtils;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReleaseTheSupplyPubackActivity extends BaseActivity {
    @BindView(R.id.text_site)//地址
            TextView textSite;
    @BindView(R.id.aupply_ship_address)//发货地址选择按钮
            AutoRelativeLayout aupplyShipAddress;
    @BindView(R.id.text_confirm_the_delivery)//确认发货
            TextView textNextStep;
    @BindView(R.id.bank)//返回键
            ImageView bank;
    @BindView(R.id.title)//标题
            TextView title;
    @BindView(R.id.edt_name)
    TextInputEditText edtName;
    @BindView(R.id.edt_phone)
    TextInputEditText edtPhone;
    @BindView(R.id.edt_address)
    TextInputEditText edtAddress;
    private BottomStyleDialog bottomStyleDialog;
    private String regionID;
    private HashMap<String, RequestBody> FileImgs = new HashMap<>();

    @Override
    protected void initData() {
        title.setText("发布供应");
        title.setTextColor(Color.rgb(108, 163, 35));
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        aupplyShipAddress.setOnClickListener(this);
        textNextStep.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        bottomStyleDialog = new BottomStyleDialog(this,1 ,new BottomStyleDialog.OnItemListener() {

            @Override
            public void getArea(String area, String id) {
            textSite.setText(area);
                regionID = id;
            }
        },1);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_the_supply_puback;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
            case R.id.aupply_ship_address:

                bottomStyleDialog.show();
                break;
            case R.id.text_confirm_the_delivery://发布供应
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String area = textSite.getText().toString().trim();
                if (name.isEmpty()){
                    ToastUtils.showToast(ReleaseTheSupplyPubackActivity.this,"请输入姓名");
                    break;
                }
                if (phone.isEmpty()){
                    ToastUtils.showToast(ReleaseTheSupplyPubackActivity.this,"请输入联系方式");
                    break;
                }else if (!MobileUtils.isMobile(phone)){
                    ToastUtils.showToast(ReleaseTheSupplyPubackActivity.this,"请输入正确的联系方式");
                    break;
                }
                if (area.isEmpty()){
                    ToastUtils.showToast(ReleaseTheSupplyPubackActivity.this,"请选择区域地址");
                    break;
                }
                //发布


                publish(name,phone);



                break;
        }
    }

    private void publish(String name, String phone) {
        int type = 1;
        String edtTitle = getIntent().getStringExtra("edtTitle");
        int categoryID = getIntent().getIntExtra("categoryID", 0);
        String norms = getIntent().getStringExtra("norms");
        String price = getIntent().getStringExtra("price");
        String num = getIntent().getStringExtra("num");
        String describe = getIntent().getStringExtra("describe");
        ArrayList<String> photos = getIntent().getStringArrayListExtra("imgList");

        //将参数转换
        RequestBody BodyName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody BodyDescribe = RequestBody.create(MediaType.parse("text/plain"), describe);
        RequestBody BodyNorms = RequestBody.create(MediaType.parse("text/plain"), norms);
        RequestBody BodyPhone = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody BodyReice = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody BodyRegionID = RequestBody.create(MediaType.parse("text/plain"), regionID);
        RequestBody BodyEdtTitle = RequestBody.create(MediaType.parse("text/plain"), edtTitle);
        RequestBody BodyToken = RequestBody.create(MediaType.parse("text/plain"), User.INSTANCE.getToken());
        RequestBody BodyNum = RequestBody.create(MediaType.parse("text/plain"),num);

        for (int i = 0; i < photos.size(); i++) {
            String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos.get(i) + ".jpg";//压缩后图片的路径
            final String compressImage = ImgUtils.compressImage(photos.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
            final File file = new File(compressImage); //压缩后的图片

            RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
            FileImgs.put("file\"; filename=\"icon" + i + ".png", photo);

        }

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.supplySave(categoryID,
                BodyName, BodyDescribe,
                FileImgs,
                BodyNorms,
             BodyNum,
                BodyPhone,BodyReice,
                BodyRegionID, BodyEdtTitle, BodyToken, type);

        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        ToastUtils.showToast(getApplicationContext(),body.getResult().getMessage());

                        //去选择界面
                        Intent intent = new Intent(ReleaseTheSupplyPubackActivity.this, PublishedActivity.class);
                        startActivity(intent);
                    }else {
                        ToastUtils.showToast(getApplicationContext(),body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {
                ToastUtils.showToast(getApplicationContext(),t.getMessage());
            }
        });
    }

}
