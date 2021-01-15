package com.azhyun.massxj.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.MyTrusteeshipAdapter;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.AlignTextView;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ImgUtils;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.SpaceItemDecoration;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;
import com.azhyun.massxj.view.RecyclerItemClickListener;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

/**
 * Created by tl on 2018/8/17.
 */

public class MyTrusteeshipActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edt_title)
    EditText edtTitle;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.selected_area)
    AutoLinearLayout selectedArea;
    @BindView(R.id.text_area)
    TextView textArea;
    @BindView(R.id.edt_address)
    EditText edtAddress;
    @BindView(R.id.edt_acreage)
    EditText edtAcreage;
    @BindView(R.id.describe)
    AlignTextView describe;
    @BindView(R.id.edt_describe)
    EditText edtDescribe;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private ArrayList<String> photos = new ArrayList<>();
    private int num = 3;
    private BottomStyleDialog bottomStyleDialog;
    private String region;
    private HashMap<String, RequestBody> FileImgs = new HashMap<>();
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        selectedArea.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("添加托管");

        bottomStyleDialog = new BottomStyleDialog(this, new BottomStyleDialog.OnItemListener() {
            @Override
            public void getArea(String area, String id) {
                textArea.setText(area);
                region = id;
            }
        },1);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        final MyTrusteeshipAdapter myTrusteeshipAdapter = new MyTrusteeshipAdapter(photos, num);
        recyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 20), 3));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == photos.size()  && photos.size()<num){
                    //去相册
                    RxPermissions rxPermissions = new RxPermissions(MyTrusteeshipActivity.this);
                    rxPermissions
                            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    if (aBoolean) {
                                        PhotoPicker.builder()
                                                .setShowCamera(true)
                                                .setPhotoCount(3-photos.size())
                                                .setPreviewEnabled(false)
                                                .start(MyTrusteeshipActivity.this, PhotoPicker.REQUEST_CODE);
                                    } else {
                                        ToastUtils.showToast(MyTrusteeshipActivity.this, "请在权限设置中打开相机权限");
                                    }
                                }
                            });

                }


            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));
        recyclerView.setAdapter(myTrusteeshipAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片返回
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {

            if (data != null) {

                photos.addAll(data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS));

                recyclerView.getAdapter().notifyDataSetChanged();

            }

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_trusteeship;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;

            case R.id.selected_area:
                //选择区域
                bottomStyleDialog.show();
                break;
            case R.id.btn_submit://提交
                String edtTitle = this.edtTitle.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String area = textArea.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String acreage = edtAcreage.getText().toString().trim();
                String describe = edtDescribe.getText().toString().trim();
                if (edtTitle.isEmpty()){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请输入标题信息");
                    break;
                }
                if (name.isEmpty()){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请输入姓名");
                    break;
                }
                 if (phone.isEmpty()){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请输入联系方式");
                     break;
                 }else if (!MobileUtils.isMobile(phone)){
                     ToastUtils.showToast(MyTrusteeshipActivity.this,"请输入正确的联系方式");
                     break;
                 }

                if (area.isEmpty()){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请选择区域地址");
                    break;
                }
                if (address.isEmpty()){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请输入详细地址");
                    break;
                }
                 if (acreage.isEmpty()){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请输入土地面积");
                    break;
                }
                 if (describe.isEmpty()){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请输入土地描述");
                    break;
                }
                if (photos.size() == 0){
                    ToastUtils.showToast(MyTrusteeshipActivity.this,"请添加一至三张图片");
                    break;
                }
                //提交
                submit(address, Double.parseDouble(acreage),photos,describe,name,phone,region,edtTitle, User.INSTANCE.getToken());
                break;
        }
    }

    private void submit(String address, double acreage, ArrayList<String> photos, String describe, String name, String phone,
                        String region, String edtTitle, String token) {
        //将参数转换
        RequestBody BodyAddress = RequestBody.create(MediaType.parse("text/plain"), address);

        RequestBody BodyDescribe = RequestBody.create(MediaType.parse("text/plain"), describe);
        RequestBody BodyName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody BodyPhone = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody BodyRegion = RequestBody.create(MediaType.parse("text/plain"), region);
        RequestBody BodyEdtTitle = RequestBody.create(MediaType.parse("text/plain"), edtTitle);
        RequestBody BodyToken = RequestBody.create(MediaType.parse("text/plain"), token);

        for (int i = 0; i < photos.size(); i++) {
            String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos.get(i) + ".jpg";//压缩后图片的路径
            final String compressImage = ImgUtils.compressImage(photos.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
            final File file = new File(compressImage); //压缩后的图片

            RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
            FileImgs.put("file\"; filename=\"icon" + i + ".png", photo);

        }

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.lanSave(BodyAddress, acreage, FileImgs, BodyDescribe, BodyName, BodyPhone, BodyRegion, BodyEdtTitle, BodyToken);
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        Intent intent = new Intent(MyTrusteeshipActivity.this,SubmitSucceedActivity.class);
                        intent.putExtra("id",body.getData().getId());
                        intent.putExtra("type",1);//1 代表土地托管
                        startActivity(intent);
                        fund();
                    }else {
                        ToastUtils.showToast(MyTrusteeshipActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bottomStyleDialog.cancel();
    }
}
