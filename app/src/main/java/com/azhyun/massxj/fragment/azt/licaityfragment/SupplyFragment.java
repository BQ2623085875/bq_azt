package com.azhyun.massxj.fragment.azt.licaityfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.GongYingXqActivity;
import com.azhyun.massxj.adapter.azt.localityadapter.LocalityAdapter;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.GongQiuBean;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.azhyun.massxj.utils.LoadingDialog.cancel;

/**
 * A simple {@link Fragment} subclass.
 * 本地生活 供应
 */
public class SupplyFragment extends LazyLoadFragment {
    protected final String TAG = "SupplyFragment";


    @BindView(R.id.loca_all_rv)
    XRecyclerView loca_all_rv;
    @BindView(R.id.wsj)
    TextView wsj;

    private int currentPager = 1;
    private int categoryId;
    private String mFenLeiString;

    private LocalityAdapter localityAdapter;

    public SupplyFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_supply2;
    }

    @Override
    protected void lazyLoad() {
        initView();
        gainiData();
    }

    private void initView() {
        loca_all_rv.setPullRefreshEnabled(false);
        loca_all_rv.setLoadingMoreEnabled(true);
        loca_all_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        loca_all_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                gainiData();
                loca_all_rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                gainData2();
                loca_all_rv.loadMoreComplete();
            }
        });


    }

    private void gainiData() {
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<GongQiuBean> benDiGQ = service.getBenDiGQ(null, mFenLeiString, currentPager, 10, User.INSTANCE.getRegion(), 1, null);
        benDiGQ.enqueue(new Callback<GongQiuBean>() {
            @Override
            public void onResponse(Call<GongQiuBean> call, Response<GongQiuBean> response) {
                if (response.isSuccessful()) {
                    GongQiuBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<GongQiuBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        if (rows.size() > 0) {
                            wsj.setVisibility(ViewGroup.GONE);
                            loca_all_rv.setVisibility(ViewGroup.VISIBLE);
                            localityAdapter = new LocalityAdapter(getContext(), rows);
                            loca_all_rv.setAdapter(localityAdapter);

                            localityAdapter.setOnInterface(new LocalityAdapter.OnInterface() {
                                @Override
                                public void OnCilkInterface(GongQiuBean.DataBeanX.RowsBean rowsBean, int position) {
                                    Intent intent = new Intent(getContext(), GongYingXqActivity.class);
                                    intent.putExtra("id", rowsBean.getId());
                                    intent.putExtra("type", rowsBean.getType());
                                    intent.putExtra("judge", 1);
                                    intent.putExtra("userid", rowsBean.getUserId() + "");
                                    startActivity(intent);
                                }
                            });
                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            loca_all_rv.setVisibility(ViewGroup.GONE);
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

    private void gainData2() {
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<GongQiuBean> benDiGQ = service.getBenDiGQ(null, mFenLeiString, currentPager, 10, User.INSTANCE.getRegion(), 1, null);
        benDiGQ.enqueue(new Callback<GongQiuBean>() {
            @Override
            public void onResponse(Call<GongQiuBean> call, Response<GongQiuBean> response) {
                if (response.isSuccessful()) {
                    GongQiuBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<GongQiuBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        if (rows.size() > 0) {
                            localityAdapter.addMoreData(rows);
                        } else {
                            loca_all_rv.setNoMore(true);
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

    public void localitysou(String text) {
        mFenLeiString = text;
        gainiData();
    }
}
