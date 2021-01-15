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
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.MyLoader;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/22.
 */

public class ApplyForInfoActivity extends BaseActivity implements OnBannerListener{
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.btn_pass)
    Button btnPass;
    @BindView(R.id.btn_reject)
    Button btnReject;
    @BindView(R.id.btn_upload_img)
    Button btnUploadImg;
    @BindView(R.id.line_bottom_btn)
    AutoLinearLayout lineBottomBtn;
    @BindView(R.id.line_img)
    AutoLinearLayout lineImg;
    @BindView(R.id.line_customer)
    AutoLinearLayout lineCustomer;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.line_refuse)
    AutoLinearLayout lineRefuse;
    @BindView(R.id.img_agreement)
    ImageView imgAgreement;
    @BindView(R.id.img_certificate)
    ImageView imgCertificate;
    @BindView(R.id.img_id_card)
    ImageView imgIdCard;
    private LandInfoResult.Data.Info info;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        btnReject.setOnClickListener(this);
        btnPass.setOnClickListener(this);
        btnUploadImg.setOnClickListener(this);
        imgAgreement.setOnClickListener(this);
        imgCertificate.setOnClickListener(this);
        imgIdCard.setOnClickListener(this);
    }

    @Override
    protected void initView() {

        int type = getIntent().getIntExtra("type", 0);
        int id = getIntent().getIntExtra("id", 0);
        int isImg = getIntent().getIntExtra("isImg", 0);
        int sub = getIntent().getIntExtra("sub", 0);

        if (sub == 1){
            lineBottomBtn.setVisibility(View.GONE);

        }else {
            if (type == 1) {//待审核详情
                btnUploadImg.setVisibility(View.GONE);
                lineBottomBtn.setVisibility(View.VISIBLE);
                lineImg.setVisibility(View.GONE);
                lineRefuse.setVisibility(View.GONE);
                int managerRole = (int) SpUtils.get("managerRole", 0);
                if (managerRole != 1){
                    lineBottomBtn.setVisibility(View.GONE);
                }
            } else if (type == 2) {//审核通过详情
                lineBottomBtn.setVisibility(View.GONE);

                if (isImg == 2){
                    btnUploadImg.setVisibility(View.VISIBLE);//审核通过,未上传证件
                    lineImg.setVisibility(View.GONE);
                    lineRefuse.setVisibility(View.GONE);

                }else if (isImg == 1){
                    btnUploadImg.setVisibility(View.GONE);//审核通过,以上传证件
                    lineImg.setVisibility(View.VISIBLE);
                    lineRefuse.setVisibility(View.GONE);
                }


            } else if (type == -1) {//审核拒绝申请
                btnUploadImg.setVisibility(View.GONE);
                lineBottomBtn.setVisibility(View.GONE);
                lineImg.setVisibility(View.GONE);
                lineRefuse.setVisibility(View.VISIBLE);
            }
        }



        //获取详情
        getApplyForInfo(id);
    }

    /**
     * \
     * h获取详情
     *
     * @param id
     */
    private void getApplyForInfo(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<LandInfoResult> landInfo = service.getLandInfo(id, User.INSTANCE.getToken());
        landInfo.enqueue(new Callback<LandInfoResult>() {
            @Override
            public void onResponse(Call<LandInfoResult> call, Response<LandInfoResult> response) {

                if (response.isSuccessful()) {
                    LandInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        //设置详情
                        setLandInfo(body.getData().getInfo());
                    } else {
                        ToastUtils.showToast(ApplyForInfoActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LandInfoResult> call, Throwable t) {
                Log.e("----->>",t.getMessage());
            }
        });
    }

    /**
     * \
     * 设置详情
     *
     * @param info
     */
    private void setLandInfo(LandInfoResult.Data.Info info) {
        title.setText("托管详情");

        this.info = info;
        int status = info.getStatus();

        int sub = getIntent().getIntExtra("sub", 0);

        if (sub == 1){
            lineBottomBtn.setVisibility(View.GONE);

        }else {
            if (status == 1) {//待审核详情
                btnUploadImg.setVisibility(View.GONE);
                lineBottomBtn.setVisibility(View.VISIBLE);
                lineImg.setVisibility(View.GONE);
                lineRefuse.setVisibility(View.GONE);
                int managerRole = (int) SpUtils.get("managerRole", 0);
                if (managerRole != 1){
                    lineBottomBtn.setVisibility(View.GONE);
                }
            } else if (status == 2) {//审核通过详情
                lineBottomBtn.setVisibility(View.GONE);

                List<LandInfoResult.Data.Info.AttaImgs4> attaImgs4 = info.getAttaImgs4();
                if (attaImgs4.size() == 0){
                    btnUploadImg.setVisibility(View.VISIBLE);//审核通过,未上传证件
                    lineImg.setVisibility(View.GONE);
                    lineRefuse.setVisibility(View.GONE);

                }else if (attaImgs4.size() != 0){
                    btnUploadImg.setVisibility(View.GONE);//审核通过,以上传证件
                    lineImg.setVisibility(View.VISIBLE);
                    lineRefuse.setVisibility(View.GONE);
                }


            } else if (status == -1) {//审核拒绝申请
                btnUploadImg.setVisibility(View.GONE);
                lineBottomBtn.setVisibility(View.GONE);
                lineImg.setVisibility(View.GONE);
                lineRefuse.setVisibility(View.VISIBLE);
            }

        }




        List<LandInfoResult.Data.Info.AttaImgs1> attaImgs1 = info.getAttaImgs1();
        List<String> urlList = new ArrayList<>();
        for (LandInfoResult.Data.Info.AttaImgs1 attaImgs:attaImgs1) {
            urlList.add(Constant.IMG_URL+attaImgs.getUrl());
        }
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        mBanner.setImages(urlList);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        mBanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        homeBanner.setBannerTitles(list_title);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        mBanner.setIndicatorGravity(BannerConfig.RIGHT)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

        tvTitle.setText(info.getTitle());
        tvAddress.setText(info.getFullName());
        tvArea.setText(info.getArea()+"亩");
        if (info.getRemark() == null || info.getRemark() == ""){
            tvInfo.setText("暂无备注");
        }else {
            tvInfo.setText(info.getRemark());
        }
        tvName.setText(info.getName());
        tvPhone.setText(info.getPhone());
        tvReason.setText(info.getNote());
        tvType.setText(getStatus(info.getStatus()));

        //协议
        if (info.getAttaImgs4().size() != 0){
            Glide.with(ApplyForInfoActivity.this)
                    .load(Constant.IMG_URL + info.getAttaImgs4().get(0).getUrl())
                    .error(R.drawable.err)
                    .into(imgAgreement);
        }
        //证书
        if (info.getAttaImgs3().size() != 0){
            Glide.with(ApplyForInfoActivity.this)
                    .load(Constant.IMG_URL + info.getAttaImgs3().get(0).getUrl())
                    .error(R.drawable.err)
                    .into(imgCertificate);
        }

        //身份证
        if (info.getAttaImgs2().size() != 0){
            Glide.with(ApplyForInfoActivity.this)
                    .load(Constant.IMG_URL + info.getAttaImgs2().get(0).getUrl())
                    .error(R.drawable.err)
                    .into(imgIdCard);
        }

 }

    private String getStatus(int status) {
        switch (status ){
            case -1:
                return "下线 ";
            case 1:
                return "待审核 ";
            case 2:
                return "同意 ";
            case -2:
                return "拒绝 ";

        }
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_info;
    }

    @Override
    protected void otherViewClick(View view) {
        List<String> urlList = new ArrayList<>();
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.btn_pass://通过
                operate(1,null);
                break;
            case R.id.btn_reject://拒绝
                //拒绝弹窗
                rejectDialog();
                break;
            case R.id.btn_upload_img:
                //获取图片
                Intent intent = new Intent(this, UploadImageActivity.class);
                intent.putExtra("id",info.getId());
                startActivityForResult(intent,122);
                break;
            case R.id.img_agreement://协议
                urlList.clear();
                for (int i = 0 ; i <info.getAttaImgs4().size();i++){
                    urlList.add(Constant.IMG_URL + info.getAttaImgs4().get(i).getUrl());
                }
                intent = new Intent(ApplyForInfoActivity.this,MyImagesActivity.class);
                intent.putExtra("data", (Serializable) urlList);
                intent.putExtra("position",0);
                startActivity(intent);
                break;
             case R.id.img_certificate://证书
                 urlList.clear();
                 for (int i = 0 ; i <info.getAttaImgs3().size();i++){
                     urlList.add(Constant.IMG_URL + info.getAttaImgs3().get(i).getUrl());
                 }
                 intent = new Intent(ApplyForInfoActivity.this,MyImagesActivity.class);
                intent.putExtra("data", (Serializable)urlList);
                intent.putExtra("position",0);
                startActivity(intent);
                break;

               case R.id.img_id_card://身份证
                   urlList.clear();
                   for (int i = 0 ; i <info.getAttaImgs2().size();i++){
                       urlList.add(Constant.IMG_URL + info.getAttaImgs2().get(i).getUrl());
                   }

                 intent = new Intent(ApplyForInfoActivity.this,MyImagesActivity.class);
                intent.putExtra("data", (Serializable) urlList);
                intent.putExtra("position",0);
                startActivity(intent);
                break;


        }

    }

    /**
     * 拒绝弹窗
     */
    private void rejectDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.quick_option_dialog);
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
                    //跳转拒绝界面
                    operate(-1,string);
                    dialog.dismiss();
                } else {
                    ToastUtils.showToast(ApplyForInfoActivity.this, "请填写拒绝理由!");
                }
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
//
//        Window win = dialog.getWindow();
//        win.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        win.setAttributes(lp);
//        dialog.getWindow().setAttributes(lp); //设置生效


//        window.setContentView(view);

        //获得window窗口的属性
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
        Call<LandInfoResult> landInfoResultCall = service.landOperate(info.getId(), note, type,User.INSTANCE.getToken());
        landInfoResultCall.enqueue(new Callback<LandInfoResult>() {
            @Override
            public void onResponse(Call<LandInfoResult> call, Response<LandInfoResult> response) {
                if (response.isSuccessful()){
                    LandInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        ToastUtils.showToast(ApplyForInfoActivity.this,body.getResult().getMessage());
                        if (type == 1){
                            Intent intent = new Intent(ApplyForInfoActivity.this, ApplyForResultActivity.class);
                            intent.putExtra("id",getIntent().getIntExtra("id", 0));
                            intent.putExtra("type", 1);//type = 1 为通过
                            startActivityForResult(intent,133);
                        }else if (type == -1){
                            Intent intent = new Intent(ApplyForInfoActivity.this, ApplyForResultActivity.class);
                            intent.putExtra("id",getIntent().getIntExtra("id", 0));
                            intent.putExtra("type", 2);//type = 2 为拒绝
                            startActivityForResult(intent,133);
                        }
                    }else {
                        ToastUtils.showToast(ApplyForInfoActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LandInfoResult> call, Throwable t) {

            }
        });

    }

    /**
     * banner 点击
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 122 && resultCode == 123){
           fund();
        }
        if (requestCode == 133){
            fund();
        }
    }
}
