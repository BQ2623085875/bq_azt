package com.azhyun.massxj.activity.azt;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.adapter.azt.NewFieldAdapter;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.NewFieldBean;
import com.azhyun.massxj.bean.aizhongtian.NewFieldContentBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.JSONTool;
import com.azhyun.massxj.utils.azt.ShijianChuo;
import com.azhyun.massxj.x5webview.TencentBrowserActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 新品试验田
* */
public class NewFieldActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView mBank;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.wushuju)
    TextView wushuju;
    @BindView(R.id.newfield_rv)
    RecyclerView newfield_rv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_field;
    }

    @Override
    protected void initView() {

        mTitle.setText("新品试验田");
        mTitle.setTextColor(getResources().getColor(R.color.black));
        mTitle.setTextSize(18);
    }

    @Override
    protected void initListener() {

        mBank.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        LoadingDialog.show(NewFieldActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NewFieldBean> xinpintian = service.xinpintian(49, User.INSTANCE.getRegion());
        xinpintian.enqueue(new Callback<NewFieldBean>() {
            @Override
            public void onResponse(Call<NewFieldBean> call, Response<NewFieldBean> response) {
                if (response.isSuccessful()) {
                    NewFieldBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        LoadingDialog.cancel();
                        NewFieldBean.DataBeanX data = body.getData();
                        Log.d("kkkkkkkk", "onResponse: " + JSONTool.format(new Gson().toJson(data)));
                        if (data.getRows().size() == 0) {
                            wushuju.setVisibility(ViewGroup.VISIBLE);
                            newfield_rv.setVisibility(ViewGroup.GONE);

                        } else {
                            wushuju.setVisibility(ViewGroup.GONE);
                            newfield_rv.setVisibility(ViewGroup.VISIBLE);

                            ArrayList<NewFieldBean.DataBeanX.RowsBean> rowsBeans = new ArrayList<>();
                            rowsBeans.addAll(data.getRows());
                            NewFieldAdapter newFieldAdapter = new NewFieldAdapter(NewFieldActivity.this, rowsBeans);
                            newfield_rv.setAdapter(newFieldAdapter);
                            newfield_rv.setLayoutManager(new LinearLayoutManager(NewFieldActivity.this));
                            newFieldAdapter.notifyDataSetChanged();

                            newFieldAdapter.setOnInterface(new NewFieldAdapter.OnInterface() {
                                @Override
                                public void OnCilkeface(NewFieldBean.DataBeanX.RowsBean rowsBean, int position) {
                                    gain(rowsBean);
                                }
                            });

                        }


                    } else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(NewFieldActivity.this, body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<NewFieldBean> call, Throwable t) {

            }
        });
    }

    private void gain(final NewFieldBean.DataBeanX.RowsBean rowsBean) {
        LoadingDialog.show(NewFieldActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NewFieldContentBean> newfieldconent = service.newfieldconent(rowsBean.getId());
        newfieldconent.enqueue(new Callback<NewFieldContentBean>() {
            @Override
            public void onResponse(Call<NewFieldContentBean> call, Response<NewFieldContentBean> response) {
                if (response.isSuccessful()) {
                    NewFieldContentBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        LoadingDialog.cancel();
                        NewFieldContentBean.DataBeanX data = body.getData();
                        Log.d("kkkkk", "onResponse: " + JSONTool.format(new Gson().toJson(data)));

                        Intent intent = new Intent(NewFieldActivity.this, TencentBrowserActivity.class);
                        intent.putExtra("url", Constant.IMG_URL + data.getContent());
                        intent.putExtra("title", "文章详情");
                        intent.putExtra("shi", ShijianChuo.getYMDTiem(rowsBean.getPublishTime()));
                        intent.putExtra("name", rowsBean.getTitle());
                        intent.putExtra("lai", rowsBean.getSource());
                        startActivity(intent);
                    } else {
                        LoadingDialog.cancel();
                        ToastUtils.showToast(NewFieldActivity.this, body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<NewFieldContentBean> call, Throwable t) {

            }
        });
    }


    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoadingDialog.cancel();
    }
}
