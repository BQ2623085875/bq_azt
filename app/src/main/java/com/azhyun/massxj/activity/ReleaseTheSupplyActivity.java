package com.azhyun.massxj.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.MyTrusteeshipAdapter;
import com.azhyun.massxj.bean.CategoryResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.GongXuFenLeiBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBeans;
import com.azhyun.massxj.listener.OnItemClickListener;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ImgUtils;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.SpaceItemDecoration;
import com.azhyun.massxj.utils.ToastUtils;
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
import rx.functions.Action1;

import static com.azhyun.massxj.utils.LoadingDialog.cancel;

/*
发布供应
 */
public class ReleaseTheSupplyActivity extends BaseActivity {
    @BindView(R.id.text_next_step)//下一步
            TextView textNextStep;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.recycler_view2)
    RecyclerView recycler_view2;
    @BindView(R.id.layout_before_uploading)//发布之前
            ImageView layoutBeforeUploading;
    @BindView(R.id.layout_before_uploading2)//发布之前
            ImageView layout_before_uploading2;
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edt_title)
    TextInputEditText edtTitle;
    @BindView(R.id.text_selected)
    TextView textSelected;
    @BindView(R.id.line_selected)
    AutoLinearLayout lineSelected;
    @BindView(R.id.edt_norms)
    TextInputEditText edtNorms;
    @BindView(R.id.edt_price)
    TextInputEditText edtPrice;
    @BindView(R.id.edt_num)
    TextInputEditText edtNum;
    @BindView(R.id.edt_describe)
    TextInputEditText edtDescribe;
    private int categoryID;


    private int num = 6;
    private int xijienum = 9;
    private int type = 0;
    ArrayList<String> photos = new ArrayList<>();//添加图片地址
    ArrayList<String> xijiephotos = new ArrayList<>();//细节图片地址
    private PopupWindow popupWindow;
    private ArrayList<GongXuFenLeiBean.DataBeanX.ChildBean> strings = new ArrayList<>();
    private HashMap<String, RequestBody> FileImgs = new HashMap<>();//展示图
    private HashMap<String, RequestBody> FileImgsMiao = new HashMap<>();//描述图

    @Override
    protected void initData() {
        title.setText("发布供应");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

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
        layoutBeforeUploading.setOnClickListener(this);
        layout_before_uploading2.setOnClickListener(this);
        textNextStep.setOnClickListener(this);
        bank.setOnClickListener(this);
        lineSelected.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 10), 3));
        recyclerView.setNestedScrollingEnabled(false);
        MyTrusteeshipAdapter myTrusteeshipAdapter = new MyTrusteeshipAdapter(photos, num);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == photos.size() && photos.size() < 6) {
                    type = 0;
                    camera();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));
        recyclerView.setAdapter(myTrusteeshipAdapter);

        //细节图
        recycler_view2.setVisibility(View.INVISIBLE);
        recycler_view2.setLayoutManager(new GridLayoutManager(this, 3));
        recycler_view2.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 10), 3));
        recycler_view2.setNestedScrollingEnabled(false);
        MyTrusteeshipAdapter myTrusteeshipAdapter2 = new MyTrusteeshipAdapter(xijiephotos, xijienum);
        recycler_view2.addOnItemTouchListener(new RecyclerItemClickListener(this, recycler_view2, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (position == xijiephotos.size() && xijiephotos.size() < 9) {
                    type = 1;
                    camera();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));
        recycler_view2.setAdapter(myTrusteeshipAdapter2);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_the_supply;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.line_selected:
                //分类
                final SelectPicPopupWindow selectPicPopupWindow = new SelectPicPopupWindow(this, strings, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object object) {
                        GongXuFenLeiBean.DataBeanX.ChildBean item = (GongXuFenLeiBean.DataBeanX.ChildBean) object;
                        textSelected.setText(item.getName());
                        categoryID = item.getId();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                selectPicPopupWindow.showAtLocation(super.view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.layout_before_uploading://展示图
                type = 0;
                camera();
                break;
            case R.id.layout_before_uploading2://细节图
                type = 1;
                camera();
                break;
            case R.id.text_next_step:
                String edtTitle = this.edtTitle.getText().toString().trim();//标题
                String category = textSelected.getText().toString().trim();//分类
                String norms = edtNorms.getText().toString().trim();//规格
                String price = edtPrice.getText().toString().trim();//价格
                String num = edtNum.getText().toString().trim();//数量
                String describe = edtDescribe.getText().toString().trim();//描述

                if (edtTitle.isEmpty()) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请输入信息标题");
                    break;
                }
                if (category.isEmpty()) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请选择货品分类");
                    break;
                }
                if (norms.isEmpty()) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请输入货品规格");
                    break;
                }
                if (price.isEmpty()) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请输入货品单价");
                    break;
                }

                if (num.isEmpty()) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请输入货品数量");
                    break;
                }
                if (describe.isEmpty()) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请输入商品描述");
                    break;
                }

                if (photos.size() == 0) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请至少添加一张商品展示图片");
                    break;
                }
                if (xijiephotos.size() == 0) {
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, "请至少添加一张商品细节描述图片");
                    break;
                }
//                Intent intent = new Intent(ReleaseTheSupplyActivity.this, ReleaseTheSupplyPubackActivity.class);
                submitgongqiu(edtTitle, category, norms, price, num, describe);

                break;
            case R.id.bank:
                finish();
                break;
        }
    }

    private void submitgongqiu(String edtTitle, String category, String norms, String price, String num, String describe) {
        //将参数转换
        RequestBody BodyEdtTitle = RequestBody.create(MediaType.parse("text/plain"), edtTitle);
        RequestBody BodyNorms = RequestBody.create(MediaType.parse("text/plain"), norms);
        RequestBody BodyPrice = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody BodyNum = RequestBody.create(MediaType.parse("text/plain"), num);
        RequestBody BodyDescribe = RequestBody.create(MediaType.parse("text/plain"), describe);
        RequestBody BodyRegion = RequestBody.create(MediaType.parse("text/plain"), "");//发布求购时的发源地

        //商品展示图
        for (int i = 0; i < photos.size(); i++) {
            String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos.get(i) + ".jpg";//压缩后图片的路径
            final String compressImage = ImgUtils.compressImage(photos.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
            final File file = new File(compressImage); //压缩后的图片

            RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
            FileImgs.put("banners\"; filename=\"icon" + i + ".png", photo);

        }

        //商品描述图
        for (int i = 0; i < xijiephotos.size(); i++) {
            String targetPath = Environment.getExternalStorageDirectory() + "/download/" + xijiephotos.get(i) + ".jpg";//压缩后图片的路径
            final String compressImage = ImgUtils.compressImage(xijiephotos.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
            final File file = new File(compressImage); //压缩后的图片

            RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
            FileImgsMiao.put("infoImg\"; filename=\"icon" + i + ".png", photo);

        }

        LoadingDialog.show(ReleaseTheSupplyActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBeans> faBuGongXu = service.getFaBuGongXu(FileImgs, categoryID, BodyDescribe, FileImgsMiao, BodyNorms, BodyNum, BodyPrice, BodyRegion, BodyEdtTitle, 1);
        faBuGongXu.enqueue(new Callback<ResultBeans>() {
            @Override
            public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                if (response.isSuccessful()) {
                    cancel();
                    ResultBeans body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        finish();
                    }
                    ToastUtils.showToast(ReleaseTheSupplyActivity.this, body.getResult().getMessage());

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
                if (type == 0) {
                    photos.addAll(stringArrayListExtra);
                    layoutBeforeUploading.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    xijiephotos.addAll(stringArrayListExtra);
                    layout_before_uploading2.setVisibility(View.GONE);
                    recycler_view2.setVisibility(View.VISIBLE);
                    recycler_view2.getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    public void camera() {//相机调用方法
        RxPermissions rxPermissions = new RxPermissions(ReleaseTheSupplyActivity.this);
        if (type == 0) {
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
                                        .start(ReleaseTheSupplyActivity.this, PhotoPicker.REQUEST_CODE);
                            } else {
                                Toast.makeText(ReleaseTheSupplyActivity.this, "请在权限设置中打开相机权限", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean) {
                                PhotoPicker.builder()
                                        .setShowCamera(true)
                                        .setPhotoCount(9 - xijiephotos.size())
                                        .setPreviewEnabled(false)
                                        .start(ReleaseTheSupplyActivity.this, PhotoPicker.REQUEST_CODE);
                            } else {
                                Toast.makeText(ReleaseTheSupplyActivity.this, "请在权限设置中打开相机权限", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}
