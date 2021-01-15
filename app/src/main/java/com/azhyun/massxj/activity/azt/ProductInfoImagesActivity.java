package com.azhyun.massxj.activity.azt;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.PhotoViewPager;
import com.azhyun.massxj.utils.azt.SDFileHelper;
import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import rx.functions.Action1;
import uk.co.senab.photoview.PhotoView;

import static me.iwf.photopicker.PhotoPicker.KEY_SELECTED_PHOTOS;

/*
* 图片详情
* */
public class ProductInfoImagesActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    PhotoViewPager mViewPager;
    @BindView(R.id.back_text_view)
    TextView mBackTextView;
    @BindView(R.id.delete_image_view)
    ImageView mDeleteImageView;
    @BindView(R.id.title_text_view)
    TextView mTitleTextView;

    public static Intent newIntent(Context context, ArrayList<String> list, int currentPager) {
        Intent intent = new Intent(context, ProductInfoImagesActivity.class);
        intent.putStringArrayListExtra("image_list", list);
        intent.putExtra("current_pager", currentPager);
        return intent;
    }

    public static Intent newIntent(Context context, ArrayList<String> list, boolean showDeleteButton, int currentPager) {
        Intent intent = new Intent(context, ProductInfoImagesActivity.class);
        intent.putStringArrayListExtra("image_list", list);
        intent.putExtra("delete_button", showDeleteButton);
        intent.putExtra("current_pager", currentPager);
        return intent;
    }

    private ArrayList<String> images;
    private ImagesPager imagesPagerAdapter;
    private boolean showDeleteButton;
    private int currentPager;//默认选中页面
    private int currentPosition;//当前页面

    private void findViews() {

        mTitleTextView.setText("1/" + images.size());

        imagesPagerAdapter = new ImagesPager();
        mViewPager.setAdapter(imagesPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTitleTextView.setText((position + 1) + "/" + images.size());
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (showDeleteButton) {
            mDeleteImageView.setVisibility(View.VISIBLE);
        }
        mViewPager.setCurrentItem(currentPager);
    }

    @Override
    public void onBackPressed() {

        if (showDeleteButton) {
            setResultList();
        }

        super.onBackPressed();
    }

    public void setResultList() {
        Intent intent = new Intent();
        intent.putExtra(KEY_SELECTED_PHOTOS, images);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void deleteImage(String url) {
//        LoadingDialog.show(ProductInfoImagesActivity.this);
//        ServiceManager.RemovePlotsInfoImageService service = ServiceGenerator.createService(ServiceManager.RemovePlotsInfoImageService.class, LoginInfo.GETINSTANCE.getSession());
//        Subscription subscribe = service.getResult(url)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonResult>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LoadingDialog.cancel();
//                    }
//
//                    @Override
//                    public void onNext(CommonResult commonResult) {
//                        LoadingDialog.cancel();
//                        if ("200".equals(commonResult.getResult().getCode())) {
//                            images.remove(currentPosition);
//                            if (images.size() <= 0) {
//                                setResultList();
//                            } else {
//                                imagesPagerAdapter.notifyDataSetChanged();
//                                mTitleTextView.setText((currentPosition + 1) + "/" + images.size());
//                            }
//                        } else {
//                            ToastUtils.showToast(ProductInfoImagesActivity.this, commonResult.getResult().getMessage());
//                        }
//                    }
//                });
//        subscribes.add(subscribe);
    }

    class ImagesPager extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = (View) LayoutInflater.from(ProductInfoImagesActivity.this).inflate(R.layout.activity_product_info_item, null);
            PhotoView photoView = (PhotoView) view.findViewById(R.id.photo_view);
            final Uri uri;
            if (images.get(position).startsWith("http")) {
                uri = Uri.parse(images.get(position));
            } else {
                uri = Uri.fromFile(new File(images.get(position)));
            }

            Glide.with(ProductInfoImagesActivity.this)
                    .load(uri)
                    .crossFade()
                    .placeholder(R.drawable.me_hard_danwei)
                    .into(photoView);

            photoView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoImagesActivity.this);
                    builder.setCancelable(true)
                            .setMessage("保存图片？")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialogInterface, int i) {
                                    RxPermissions rxPermissions = new RxPermissions(ProductInfoImagesActivity.this);
                                    rxPermissions
                                            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                            .subscribe(new Action1<Boolean>() {
                                                @Override
                                                public void call(Boolean aBoolean) {
                                                    if (aBoolean) {
                                                        SDFileHelper helper = new SDFileHelper(getApplicationContext());
                                                        helper.savePicture("aizhong" + System.currentTimeMillis() + ".jpg", uri);
                                                        dialogInterface.dismiss();
                                                    } else {
                                                        ToastUtils.showToast(ProductInfoImagesActivity.this, "请在权限设置中打开读写sd卡权限");
                                                    }
                                                }
                                            });
                                }
                            }).show();
                    return true;
                }
            });

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBackTextView.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        images = getIntent().getStringArrayListExtra("image_list");
        showDeleteButton = getIntent().getBooleanExtra("delete_button", false);
        currentPager = getIntent().getIntExtra("current_pager", 0);
        findViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_info_images;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.back_text_view:
                onBackPressed();
                break;
            case R.id.delete_image_view:
                String url = images.get(currentPosition);
                if (url.startsWith("http")) {
                    url = url.substring(Constant.IMG_URL.length(), url.length());
                    deleteImage(url);
                } else {
                    images.remove(currentPosition);
                    if (images.size() <= 0) {
                        setResultList();
                    } else {
                        imagesPagerAdapter.notifyDataSetChanged();
                        mTitleTextView.setText((currentPosition + 1) + "/" + images.size());
                    }
                }
                break;
        }
    }
}
