package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.SupplyInfoActivity;
import com.azhyun.massxj.activity.azt.GongYingXqActivity;
import com.azhyun.massxj.adapter.SupplyFragmentAdapter;
import com.azhyun.massxj.adapter.azt.localityadapter.LocalityAdapter;
import com.azhyun.massxj.bean.CategoryResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.SupplyListResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.GongQiuBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBeans;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.azhyun.massxj.utils.LoadingDialog.cancel;

/**
 * 我发布的====供应
 * Created by tl on 2018/8/16.
 */

public class SupplyFragment extends LazyLoadFragment implements View.OnClickListener {

    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    Unbinder unbinder;
    private List<CategoryResult.Data.Rows> rowsList = new ArrayList();
    private int categoryId = 0;
    private int page = 1;
    private int pagerow = 10;
    private String regionId = "";
    private List<SupplyListResult.Data.Rows> list = new ArrayList<>();
    private SupplyFragmentAdapter supplyFragmentAdapter;

    private void initView() {
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                gainiData();
            }

            @Override
            public void onLoadMore() {
                page++;
                gainData2();
            }
        });
    }

    public static SupplyFragment newIntent() {
        Bundle bundle = new Bundle();
//        bundle.putString("text",text);
        SupplyFragment listFragment = new SupplyFragment();
        listFragment.setArguments(bundle);
        return listFragment;
    }


    public SupplyFragment() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_supply;
    }

    @Override
    protected void lazyLoad() {
        initView();
        gainiData();
    }

    private void gainiData() {
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<GongQiuBean> benDiGQ = service.getBenDiGQ(null, null, page, 10, User.INSTANCE.getRegion(), 1, SpUtils.get("userId", ""));
        benDiGQ.enqueue(new Callback<GongQiuBean>() {
            @Override
            public void onResponse(Call<GongQiuBean> call, Response<GongQiuBean> response) {
                if (response.isSuccessful()) {
                    GongQiuBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<GongQiuBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        if (rows.size() > 0) {
                            wsj.setVisibility(ViewGroup.GONE);
                            recyclerView.setVisibility(ViewGroup.VISIBLE);
                            supplyFragmentAdapter = new SupplyFragmentAdapter(getContext(), rows);
                            recyclerView.setAdapter(supplyFragmentAdapter);

                            supplyFragmentAdapter.setOnInterface(new SupplyFragmentAdapter.OnInterface() {
                                @Override
                                public void OnCilkInterface(GongQiuBean.DataBeanX.RowsBean rowsBean, int position, int type) {
                                    if (type == 1) {//下线
                                        if (rowsBean.getStatus() == 1) {
                                            longer(rowsBean.getId());
                                        } else {
                                            ToastUtils.showToast(getContext(), "已下线");
                                        }
                                    } else {
                                        Intent intent = new Intent(getContext(), GongYingXqActivity.class);
                                        intent.putExtra("id", rowsBean.getId());
                                        intent.putExtra("type", rowsBean.getType());
                                        intent.putExtra("judge", 0);
                                        intent.putExtra("userid", rowsBean.getUserId() + "");
                                        startActivity(intent);
                                    }
                                }
                            });


                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            recyclerView.setVisibility(ViewGroup.GONE);
                        }
                    }
                    cancel();
                    ToastUtils.showToast(getContext(), body.getResult().getMessage());
                } else {
                    cancel();
                }
            }

            @Override
            public void onFailure(Call<GongQiuBean> call, Throwable t) {
                cancel();
            }
        });
    }

    private void longer(int id) {
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBeans> xixian = service.getXixian(id);
        xixian.enqueue(new Callback<ResultBeans>() {
            @Override
            public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                if (response.isSuccessful()) {
                    ResultBeans body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        gainiData();
                    }
                    cancel();
                    ToastUtils.showToast(getContext(), body.getResult().getMessage());
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

    private void gainData2() {
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<GongQiuBean> benDiGQ = service.getBenDiGQ(null, null, page, 10, User.INSTANCE.getRegion(), 1, SpUtils.get("userId", ""));
        benDiGQ.enqueue(new Callback<GongQiuBean>() {
            @Override
            public void onResponse(Call<GongQiuBean> call, Response<GongQiuBean> response) {
                if (response.isSuccessful()) {
                    GongQiuBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<GongQiuBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        if (rows.size() > 0) {
                            supplyFragmentAdapter.addMoreData(rows);
                        } else {
                            recyclerView.setNoMore(true);
                        }
                    }
                    cancel();
                    ToastUtils.showToast(getContext(), body.getResult().getMessage());
                } else {
                    cancel();
                }
            }

            @Override
            public void onFailure(Call<GongQiuBean> call, Throwable t) {
                cancel();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
