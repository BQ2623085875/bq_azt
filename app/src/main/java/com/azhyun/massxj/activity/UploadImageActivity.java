package com.azhyun.massxj.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.MyTrusteeshipAdapter;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ImgUtils;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.SpaceItemDecoration;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.RecyclerItemClickListener;
import com.tbruyelle.rxpermissions.RxPermissions;

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

/**
 * Created by tl on 2018/8/22.
 */

public class UploadImageActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.card_recycler_view)
    RecyclerView cardRecyclerView;
    @BindView(R.id.certificate_recycler_view)
    RecyclerView certificateRecyclerView;
    @BindView(R.id.pact_recycler_view)
    RecyclerView pactRecyclerView;
    @BindView(R.id.btn_add)
    Button btnAdd;
    private List<String> photos = new ArrayList<>();
    private ArrayList<String> photos2 = new ArrayList<>();
    private ArrayList<String> photos3 = new ArrayList<>();

    private HashMap<String, RequestBody> FileImgs = new HashMap<>();
    private HashMap<String, RequestBody> FileImgs2 = new HashMap<>();
    private HashMap<String, RequestBody> FileImgs3 = new HashMap<>();
    private RequestBody BodyToken;
    private int id = 0;
    private int type = 0;

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);

    }

    @Override
    protected void initListener() {
        btnAdd.setOnClickListener(this);
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("添加资料");
        //添加身份证
        addCard();
        //添加土地权证书
        addCertificate();

        //添加合同
        addPact();

    }

    /**
     * 添加合同
     */
    private void addPact() {
        pactRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        final MyTrusteeshipAdapter myTrusteeshipAdapter = new MyTrusteeshipAdapter(photos3, 9);
        pactRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 20), 3));
        pactRecyclerView.setNestedScrollingEnabled(false);
        pactRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, pactRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == photos3.size()  && photos3.size()<9){
                    //去相册
                    RxPermissions rxPermissions = new RxPermissions(UploadImageActivity.this);
                    rxPermissions
                            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    if (aBoolean) {
                                        PhotoPicker.builder()
                                                .setShowCamera(true)
                                                .setPhotoCount(9-photos3.size())
                                                .setPreviewEnabled(false)
                                                .start(UploadImageActivity.this,125);
                                    } else {
                                        ToastUtils.showToast(UploadImageActivity.this, "请在权限设置中打开相机权限");
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
        pactRecyclerView.setAdapter(myTrusteeshipAdapter);
    }

    /**
     * 添加土地权证书
     */
    private void addCertificate() {
        certificateRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        final MyTrusteeshipAdapter myTrusteeshipAdapter = new MyTrusteeshipAdapter(photos2, 3);
        certificateRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 20), 3));
        certificateRecyclerView.setNestedScrollingEnabled(false);
        certificateRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, certificateRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == photos2.size()  && photos2.size()<3){
                    //去相册
                    RxPermissions rxPermissions = new RxPermissions(UploadImageActivity.this);
                    rxPermissions
                            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    if (aBoolean) {
                                        PhotoPicker.builder()
                                                .setShowCamera(true)
                                                .setPhotoCount(3-photos2.size())
                                                .setPreviewEnabled(false)
                                                .start(UploadImageActivity.this,124);
                                    } else {
                                        ToastUtils.showToast(UploadImageActivity.this, "请在权限设置中打开相机权限");
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
        certificateRecyclerView.setAdapter(myTrusteeshipAdapter);
    }

    /**
     * 添加身份证
     */
    private void addCard() {

        cardRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        final MyTrusteeshipAdapter myTrusteeshipAdapter = new MyTrusteeshipAdapter(photos, 2);
        cardRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 20), 3));
        cardRecyclerView.setNestedScrollingEnabled(false);
        cardRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, cardRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == photos.size()  && photos.size()<2){
                    //去相册
                    RxPermissions rxPermissions = new RxPermissions(UploadImageActivity.this);
                    rxPermissions
                            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    if (aBoolean) {
                                        PhotoPicker.builder()
                                                .setShowCamera(true)
                                                .setPhotoCount(2-photos.size())
                                                .setPreviewEnabled(false)
                                                .start(UploadImageActivity.this,123);
                                    } else {
                                        ToastUtils.showToast(UploadImageActivity.this, "请在权限设置中打开相机权限");
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
        cardRecyclerView.setAdapter(myTrusteeshipAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_image;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.btn_add:
                if (photos.size() == 0){
                    ToastUtils.showToast(UploadImageActivity.this,"请添加身份证正反面照片");
                    break;
                }else if (photos.size() != 2){
                    ToastUtils.showToast(UploadImageActivity.this,"请添加身份证正反面照片");
                    break;
                }
                if (photos2.size() == 0){
                    ToastUtils.showToast(UploadImageActivity.this,"请添加至少一张土地权证书照片");
                    break;
                }
                if (photos3.size() == 0){
                    ToastUtils.showToast(UploadImageActivity.this,"请添加至少一张土地托管协议合同照片");
                    break;
                }
                for (int i = 0; i < photos.size(); i++) {
                    String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos.get(i) + ".jpg";//压缩后图片的路径
                    final String compressImage = ImgUtils.compressImage(photos.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
                    final File file = new File(compressImage); //压缩后的图片

                    RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
                    FileImgs.put("file\"; filename=\"icon" + i + ".png", photo);

                }
                for (int i = 0; i < photos2.size(); i++) {
                    String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos2.get(i) + ".jpg";//压缩后图片的路径
                    final String compressImage = ImgUtils.compressImage(photos2.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
                    final File file = new File(compressImage); //压缩后的图片

                    RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
                    FileImgs2.put("file\"; filename=\"icon" + i + ".png", photo);

                }
                for (int i = 0; i < photos3.size(); i++) {
                    String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos3.get(i) + ".jpg";//压缩后图片的路径
                    final String compressImage = ImgUtils.compressImage(photos3.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
                    final File file = new File(compressImage); //压缩后的图片

                    RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
                    FileImgs3.put("file\"; filename=\"icon" + i + ".png", photo);

                }
               BodyToken = RequestBody.create(MediaType.parse("text/plain"), User.INSTANCE.getToken());

                addImgs(FileImgs,id,2,BodyToken);
                break;
        }
    }

    private void addImgs(HashMap<String, RequestBody> fileImgs, final int id, final int type, RequestBody bodyToken) {
        LoadingDialog.show(UploadImageActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.landAttaSave(fileImgs,id, type, bodyToken);
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        if (type == 2){
                            addImgs(FileImgs2, id, 3, BodyToken);
                        }else if (type == 3){
                            addImgs(FileImgs3, id, 4, BodyToken);
                        }else if (type == 4){
                            LoadingDialog.cancel();
                            ToastUtils.showToast(UploadImageActivity.this,body.getResult().getMessage());
                            setResult(123);
                            fund();
                        }
                    }else {
                        ToastUtils.showToast(UploadImageActivity.this,body.getResult().getMessage());
                    }
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
        if (resultCode == RESULT_OK && requestCode == 123) {
            if (data != null) {
                photos.addAll(data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS));
                cardRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }else if (resultCode == RESULT_OK && requestCode == 124){
            if (data != null) {
                photos2.addAll(data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS));
                certificateRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }else if (resultCode == RESULT_OK && requestCode == 125){
            if (data != null) {
                photos3.addAll(data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS));
                pactRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }
}
