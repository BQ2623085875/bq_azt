package com.azhyun.massxj.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.MyTrusteeshipAdapter;
import com.azhyun.massxj.bean.CategoryResult;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.GongXuFenLeiBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBeans;
import com.azhyun.massxj.listener.OnItemClickListener;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ImgUtils;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.SpaceItemDecoration;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;
import com.azhyun.massxj.view.RecyclerItemClickListener;
import com.azhyun.massxj.view.SelectPicPopupWindow;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.azhyun.massxj.utils.LoadingDialog.cancel;

/*
发布求购
 */
public class ReleaseForActivity extends BaseActivity {

    @BindView(R.id.bank)//返回键
            ImageView bank;
    @BindView(R.id.title)//标题
            TextView title;
    @BindView(R.id.edt_title)
    TextInputEditText edtTitle;
    @BindView(R.id.line_classify)
    AutoLinearLayout lineClassify;
    @BindView(R.id.edt_num)
    TextInputEditText edtNum;
    @BindView(R.id.edt_norms)
    TextInputEditText edtNorms;
    @BindView(R.id.btn_publish)
    Button btnPublish;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.line_area)
    AutoLinearLayout line_area;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_before_uploading)
    ImageView layout_before_uploading;

    private BottomStyleDialog bottomStyleDialog;
    private String areaID = "";
    private int classifyId;
    private int num = 6;
    private String regionId;
    private HashMap<String, RequestBody> FileImgs = new HashMap<>();
    private ArrayList<GongXuFenLeiBean.DataBeanX.ChildBean> strings = new ArrayList<>();
    ArrayList<String> photos = new ArrayList<>();//添加图片地址

    @Override
    protected void initData() {
        //获取分类
        getCategory();
    }

    private void getCategory() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<GongXuFenLeiBean> category = service.getGongXuFenLei();
        category.enqueue(new Callback<GongXuFenLeiBean>() {
            @Override
            public void onResponse(Call<GongXuFenLeiBean> call, Response<GongXuFenLeiBean> response) {
                if (response.isSuccessful()) {
                    GongXuFenLeiBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<GongXuFenLeiBean.DataBeanX> data = body.getData();
                        if (data.size() != 0) {
                            for (int i = 0; i < data.size(); i++) {
                                List<GongXuFenLeiBean.DataBeanX.ChildBean> child = data.get(i).getChild();
                                if (child.size() != 0) {
                                    for (int j = 0; j < child.size(); j++) {
                                        GongXuFenLeiBean.DataBeanX.ChildBean childBean = child.get(j);
                                        strings.add(childBean);
                                    }

                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GongXuFenLeiBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        lineClassify.setOnClickListener(this);
        btnPublish.setOnClickListener(this);
        layout_before_uploading.setOnClickListener(this);
        line_area.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("发布求购");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

        bottomStyleDialog = new BottomStyleDialog(ReleaseForActivity.this, new BottomStyleDialog.OnItemListener() {
            @Override
            public void getArea(String area, String id) {
                areaID = id;
                tvArea.setText(area);
            }
        }, 0);//创建地址选择器

        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 10), 3));
        recyclerView.setNestedScrollingEnabled(false);
        MyTrusteeshipAdapter myTrusteeshipAdapter = new MyTrusteeshipAdapter(photos, num);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == photos.size() && photos.size() < 6) {
                    camera();
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
    protected int getLayoutId() {
        return R.layout.activity_release_for;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.line_area:
                bottomStyleDialog.show();
                break;
            case R.id.bank:
                finish();
                break;
            case R.id.line_classify://选择分类
                SelectPicPopupWindow selectPicPopupWindow = new SelectPicPopupWindow(this, strings, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object object) {
                        GongXuFenLeiBean.DataBeanX.ChildBean item = (GongXuFenLeiBean.DataBeanX.ChildBean) object;
                        tvClassify.setText(item.getName());
                        classifyId = item.getId();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                selectPicPopupWindow.showAtLocation(super.view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.layout_before_uploading:
                camera();
                break;
            case R.id.btn_publish://发布
                String edtTitle = this.edtTitle.getText().toString().trim();
                String classify = tvClassify.getText().toString().trim();
                String num = edtNum.getText().toString().trim();
                String area = tvArea.getText().toString().trim();
                String norms = edtNorms.getText().toString().trim();

                if (edtTitle.isEmpty()) {
                    ToastUtils.showToast(this, "请输入求购标题");
                    break;
                }
                if (classify.isEmpty()) {
                    ToastUtils.showToast(this, "请选择求购货品分类");
                    break;
                }
                if (num.isEmpty()) {
                    ToastUtils.showToast(this, "请输入求购数量");
                    break;
                }
                if (area.isEmpty()) {
                    ToastUtils.showToast(this, "请选择期望货源地");
                    break;
                }
                if (norms.isEmpty()) {
                    ToastUtils.showToast(this, "请输入补充求购商品规格、品质等要求");
                    break;
                }
                if (photos.size() == 0) {
                    ToastUtils.showToast(ReleaseForActivity.this, "请至少添加一张商品展示图片");
                    break;
                }
                //发布
                int type = 2;//求购
                publish(edtTitle, num, area, norms, type);
                break;
        }

    }

    private void publish(String edtTitle, String num, String area, String norms, int type) {
        //将参数转换
        RequestBody BodyEdtTitle = RequestBody.create(MediaType.parse("text/plain"), edtTitle);
        RequestBody BodyNum = RequestBody.create(MediaType.parse("text/plain"), num);
        RequestBody BodyRegion = RequestBody.create(MediaType.parse("text/plain"), areaID);//发布求购时的发源地
        RequestBody BodyDescribe = RequestBody.create(MediaType.parse("text/plain"), norms);

        //商品展示图
        for (int i = 0; i < photos.size(); i++) {
            String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos.get(i) + ".jpg";//压缩后图片的路径
            final String compressImage = ImgUtils.compressImage(photos.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
            final File file = new File(compressImage); //压缩后的图片
            RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
            FileImgs.put("banners\"; filename=\"icon" + i + ".png", photo);
        }

        LoadingDialog.show(ReleaseForActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBeans> faBuGongXu = service.getFaBuQiuGou(FileImgs, classifyId, BodyDescribe, BodyNum, BodyRegion, BodyEdtTitle, 2);
        faBuGongXu.enqueue(new Callback<ResultBeans>() {
            @Override
            public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                if (response.isSuccessful()) {
                    cancel();
                    ResultBeans body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        finish();
                    }
                    ToastUtils.showToast(ReleaseForActivity.this, body.getResult().getMessage());
                } else {
                    cancel();
                }
            }

            @Override
            public void onFailure(Call<ResultBeans> call, Throwable t) {
                cancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片返回
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                photos.addAll(stringArrayListExtra);
                layout_before_uploading.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public void camera() {//相机调用方法
        RxPermissions rxPermissions = new RxPermissions(ReleaseForActivity.this);
        rxPermissions
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            PhotoPicker.builder()
                                    .setShowCamera(true)
                                    .setPhotoCount(6 - photos.size())
                                    .setPreviewEnabled(false)
                                    .start(ReleaseForActivity.this, PhotoPicker.REQUEST_CODE);
                        } else {
                            Toast.makeText(ReleaseForActivity.this, "请在权限设置中打开相机权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
