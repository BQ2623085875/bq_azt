package com.azhyun.massxj.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.MyTrusteeshipAdapter;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.MyServiceInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ImgUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.SpaceItemDecoration;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.RecyclerItemClickListener;
import com.bumptech.glide.Glide;
import com.example.xlhratingbar_lib.XLHRatingBar;
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

/**
 * Created by tl on 2018/8/20.
 */

public class AddCommentActivity extends BaseActivity {
    @BindView(R.id.ratingBar)
    XLHRatingBar ratingBar;
    @BindView(R.id.ratingBar2)
    XLHRatingBar ratingBar2;
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edt_content)
    EditText edtContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_release)
    Button btnRelease;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.img_good)
    ImageView imgGood;
    @BindView(R.id.tv_good)
    TextView tvGood;
    @BindView(R.id.tv_describe_good)
    TextView tvDescribeGood;
    @BindView(R.id.img_ordinary)
    ImageView imgOrdinary;
    @BindView(R.id.tv_ordinary)
    TextView tvOrdinary;
    @BindView(R.id.tv_describe_ordinary)
    TextView tvDescribeOrdinary;
    @BindView(R.id.img_poor)
    ImageView imgPoor;
    @BindView(R.id.tv_poor)
    TextView tvPoor;
    @BindView(R.id.tv_describe_poor)
    TextView tvDescribePoor;
    @BindView(R.id.line_good)
    AutoLinearLayout lineGood;
    @BindView(R.id.line_ordinary)
    AutoLinearLayout lineOrdinary;
    @BindView(R.id.line_poor)
    AutoLinearLayout linePoor;
    private List<String> photos = new ArrayList<>();
    private MyServiceInfoResult.Data.Info info;
    private int ratingBarSelected = 5;
    private int ratingBar2Selected = 5;
    private int level = 1;
    private HashMap<String, RequestBody> FileImgs = new HashMap<>();

    @Override
    protected void initData() {
        info = (MyServiceInfoResult.Data.Info) getIntent().getSerializableExtra("orderInfo");
        setData(info);
    }

    private void setData(MyServiceInfoResult.Data.Info info) {
        title.setText("发表评价");
        Glide.with(this)
                .load(Constant.IMG_URL + info.getDefaultImg())
                .error(R.drawable.err)
                .into(img);

        tvClassify.setText(info.getServiceCategory());
        tvName.setText(info.getIntroduce());
        tvPrice.setText("¥" + StringUtils.stringDouble(info.getServicePrice()) + "元/亩");

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        lineGood.setOnClickListener(this);
        lineOrdinary.setOnClickListener(this);
        linePoor.setOnClickListener(this);
        btnRelease.setOnClickListener(this);
        ratingBar.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(int countSelected) {
                ratingBarSelected = ratingBar.getCountSelected();
            }
        });
        ratingBar2.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(int countSelected) {
                ratingBar2Selected = ratingBar2.getCountSelected();
            }
        });
    }

    @Override
    protected void initView() {
        //图片
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        final MyTrusteeshipAdapter myTrusteeshipAdapter = new MyTrusteeshipAdapter(photos, 4);
        recyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 10), 4));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == photos.size() && photos.size() < 4) {
                    //去相册
                    RxPermissions rxPermissions = new RxPermissions(AddCommentActivity.this);
                    rxPermissions
                            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    if (aBoolean) {
                                        PhotoPicker.builder()
                                                .setShowCamera(true)
                                                .setPhotoCount(4 - photos.size())
                                                .setPreviewEnabled(false)
                                                .start(AddCommentActivity.this, PhotoPicker.REQUEST_CODE);
                                    } else {
                                        ToastUtils.showToast(AddCommentActivity.this, "请在权限设置中打开相机权限");
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
    protected int getLayoutId() {
        return R.layout.activity_add_comment;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.line_good://好评
                level = 1;
                level = 1;
                imgGood.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.manyi_2));
                imgOrdinary.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.zhongping_1));
                imgPoor.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.chaping_1));

                tvGood.setTextColor(Color.parseColor("#6ca323"));
                tvOrdinary.setTextColor(Color.parseColor("#666666"));
                tvPoor.setTextColor(Color.parseColor("#666666"));

                tvDescribeGood.setTextColor(Color.parseColor("#FFC6B2"));
                tvDescribeOrdinary.setTextColor(Color.parseColor("#cccccc"));
                tvDescribePoor.setTextColor(Color.parseColor("#cccccc"));

                break;
            case R.id.line_ordinary://中评
                level = 2;
                imgGood.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.manyi_1));
                imgOrdinary.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.zhongping_2));
                imgPoor.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.chaping_1));

                tvGood.setTextColor(Color.parseColor("#666666"));
                tvOrdinary.setTextColor(Color.parseColor("#6ca323"));
                tvPoor.setTextColor(Color.parseColor("#666666"));

                tvDescribeGood.setTextColor(Color.parseColor("#cccccc"));
                tvDescribeOrdinary.setTextColor(Color.parseColor("#FFC6B2"));
                tvDescribePoor.setTextColor(Color.parseColor("#cccccc"));

                break;
            case R.id.line_poor://差评
                level = 3;
                imgGood.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.manyi_1));
                imgOrdinary.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.zhongping_1));
                imgPoor.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.chaping_2));

                tvGood.setTextColor(Color.parseColor("#666666"));
                tvOrdinary.setTextColor(Color.parseColor("#666666"));
                tvPoor.setTextColor(Color.parseColor("#6ca323"));

                tvDescribeGood.setTextColor(Color.parseColor("#cccccc"));
                tvDescribeOrdinary.setTextColor(Color.parseColor("#cccccc"));
                tvDescribePoor.setTextColor(Color.parseColor("#FFC6B2"));

                break;

//            case R.id.ratingBar:
//                 ratingBarSelected = ratingBar.getCountSelected();
//                break;
//             case R.id.ratingBar2:
//                 ratingBar2Selected = ratingBar2.getCountSelected();
//                break;

            case R.id.btn_release:
                String trim = edtContent.getText().toString().trim();
                if (trim.isEmpty()) {
                    ToastUtils.showToast(AddCommentActivity.this, "请输入评价内容");
                    break;
                }
                evaluation(trim);
                break;
        }
    }

    private void evaluation(String trim) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        RequestBody Bodycontent = RequestBody.create(MediaType.parse("text/plain"), trim);
        RequestBody BodyToken = RequestBody.create(MediaType.parse("text/plain"), User.INSTANCE.getToken());


        for (int i = 0; i < photos.size(); i++) {
            String targetPath = Environment.getExternalStorageDirectory() + "/download/" + photos.get(i) + ".jpg";//压缩后图片的路径
            final String compressImage = ImgUtils.compressImage(photos.get(i), targetPath, 30);//进行图片压缩，返回压缩后图片的路径
            final File file = new File(compressImage); //压缩后的图片

            RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
            FileImgs.put("file\"; filename=\"icon" + i + ".png", photo);

        }

        Call<ManagerResult> evaluation = service.evaluation(Bodycontent, ratingBar2Selected, FileImgs, level, info.getId(), ratingBarSelected, BodyToken);
        evaluation.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()) {
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        ToastUtils.showToast(AddCommentActivity.this, body.getResult().getMessage());
                        fund();
                    } else {
                        ToastUtils.showToast(AddCommentActivity.this, body.getResult().getMessage());
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
        //获取图片返回
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {

            if (data != null) {

                photos.addAll(data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS));

                recyclerView.getAdapter().notifyDataSetChanged();
            }

        }
    }

}
