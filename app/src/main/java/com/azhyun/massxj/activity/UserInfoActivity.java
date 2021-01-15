package com.azhyun.massxj.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.PersonInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ImgUtils;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.JSONTool;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

/**
 * Created by tl on 2018/8/20.
 */

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.head_portrait)
    CircleImageView headPortrait;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_nickname)
    EditText tvNickname;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.save)
    Button save;


    private PersonInfoResult.Data userInfo;
    private HashMap<String, RequestBody> FileImgs = new HashMap<>();
    private int type = 0;

    private boolean NICK = false;


    @Override
    protected void initData() {
        title.setText("个人信息");
        title.setTextColor(getResources().getColor(R.color.black));
        title.setTextSize(18);
//        userInfo = (PersonInfoResult.Data) getIntent().getSerializableExtra("userInfo");

//        if (userInfo != null) {
//
//        }

        getInfo();

    }

    private String getStatus(int managerRole) {
        //0.普通用户 1.村级理事长 2.乡级联络员 3.服务商
        switch (managerRole) {
            case 0:
                return "普通用户";
            case 1:
                return "村级理事长";
            case 2:
                return "乡级理事长";
            case 3:
                return "服务商";
            case 4:
                return "县级理事长";
            case 5:
                return "省级理事长";

        }
        return null;
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        headPortrait.setOnClickListener(this);
        tvNickname.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvPhone.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        NICK = false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.head_portrait://修改图片
                //去相册
                RxPermissions rxPermissions = new RxPermissions(UserInfoActivity.this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    PhotoPicker.builder()
                                            .setShowCamera(true)
                                            .setPhotoCount(1)
                                            .setPreviewEnabled(false)
                                            .start(UserInfoActivity.this, PhotoPicker.REQUEST_CODE);
                                } else {
                                    ToastUtils.showToast(UserInfoActivity.this, "请在权限设置中打开相机权限");
                                }
                            }
                        });

                break;
            case R.id.tv_name://修改姓名
                break;
            case R.id.tv_nickname:
                if (tvNickname.isClickable()) {
                    tvNickname.setCursorVisible(true);//显示光标
                    NICK = true;
                    save.setBackground(getResources().getDrawable(R.drawable.login_shape));
                } else {
                    tvNickname.setCursorVisible(false);
                    NICK = false;
                    save.setBackground(getResources().getDrawable(R.drawable.login_shape_no));
                }
                break;
            case R.id.tv_phone:
                break;
            case R.id.save:
                if (NICK = false) {

                } else {
                    //保存
                    String nickname = tvNickname.getText().toString();
                    if (nickname.isEmpty()) {
                        ToastUtils.showToast(UserInfoActivity.this, "请输入新的昵称");
                        break;
                    }
                    Save(nickname);
                }
                break;
            case R.id.bank:
                fund();
                NICK = false;
                break;

        }
    }

    //保存
    private void Save(String nickName) {
        LoadingDialog.show(UserInfoActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> personInfoResultCall = service.personUpdate(tvPhone.getText().toString(), tvName.getText().toString(), nickName, User.INSTANCE.getToken());
        personInfoResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()) {
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        ToastUtils.showToast(UserInfoActivity.this, "修改成功!");
                        save.setBackground(getResources().getDrawable(R.drawable.login_shape_no));
                        getInfo();
                    } else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(UserInfoActivity.this, body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = new ArrayList<>();
                photos.addAll(data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS));
                if (photos.size() > 0) {
                    Glide.with(UserInfoActivity.this)
                            .load(photos.get(0))
                            .error(R.drawable.fabu_tupian2)
                            .into(headPortrait);
                    //上传新图片
                    upImg(photos.get(0));
                }
            }
        }

        if (requestCode == 11) {
            getInfo();

//            String string = data.getStringExtra("String");
//            if (type == 1) {
//                tvName.setText(string);
//                userInfo.setName(string);
//            } else if (type == 2) {
//                tvNickname.setText(string);
//                userInfo.setNickname(string);
//            } else if (type == 3) {
//                tvPhone.setText(string);
//                userInfo.setMobilePhone(string);
//            }
        }
    }

    private void getInfo() {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<PersonInfoResult> personInfo = service.getPersonInfo(User.INSTANCE.getToken());
        personInfo.enqueue(new Callback<PersonInfoResult>() {
            @Override
            public void onResponse(Call<PersonInfoResult> call, Response<PersonInfoResult> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    PersonInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        Log.d("bq0025", "onResponse: " + JSONTool.format(new Gson().toJson(body.getData())));
                        setInfo(body.getData());
                    } else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(UserInfoActivity.this, body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }


            @Override
            public void onFailure(Call<PersonInfoResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    private void setInfo(PersonInfoResult.Data userInfo) {
        this.userInfo = userInfo;
        //展示数据
        Glide.with(this)
                .load(Constant.IMG_URL + userInfo.getHeadPortrait())
                .error(R.drawable.fabu_tupian2)
                .into(headPortrait);
        tvName.setText(userInfo.getName());
        tvNickname.setText(userInfo.getNickname());
        tvPhone.setText(userInfo.getMobilePhone());
        tvArea.setText(userInfo.getFullName());

//        tvAddress.setText(userInfo.);
        tvStatus.setText(getStatus(userInfo.getManagerRole()));
    }

    private void upImg(String imgString) {
        String targetPath = Environment.getExternalStorageDirectory() + "/download/" + imgString + ".jpg";//压缩后图片的路径
        final String compressImage = ImgUtils.compressImage(imgString, targetPath, 30);//进行图片压缩，返回压缩后图片的路径
        final File file = new File(compressImage); //压缩后的图片

        RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
        FileImgs.put("file\"; filename=\"icon" + 0 + ".png", photo);


        Log.d("爱种田", "upImg: " + imgString + "================" + compressImage + "--------------" + file + "*************" + FileImgs);

        RequestBody BodyToken = RequestBody.create(MediaType.parse("text/plain"), User.INSTANCE.getToken());

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.personImg(FileImgs, BodyToken);
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()) {
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        ToastUtils.showToast(UserInfoActivity.this, body.getResult().getMessage());
                    } else {
                        ToastUtils.showToast(UserInfoActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });

    }


}
