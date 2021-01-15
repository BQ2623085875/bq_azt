package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.MineBuyInfoActivity;
import com.azhyun.massxj.adapter.BuyFragmentAdapter;
import com.azhyun.massxj.bean.CategoryResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.SupplyListResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/16.
 */

public class MineBuyFragment extends LazyLoadFragment implements View.OnClickListener {


    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    private List<CategoryResult.Data.Rows> rowsList = new ArrayList<>();
    private int page = 1;
    private int pagerow = 10;
    private List<SupplyListResult.Data.Rows> mSupplyList = new ArrayList<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_mine_buy;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyLayout);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), DividerItemDecoration.HORIZONTAL, 2, Color.parseColor("#eeeeee")));
        BuyFragmentAdapter buyFragmentAdapter = new BuyFragmentAdapter(mSupplyList);
        buyFragmentAdapter.getOnClick(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getContext(), MineBuyInfoActivity.class);
                intent.putExtra("id", mSupplyList.get(postion).getId());
                startActivityForResult(intent,126);

            }
        });

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData();

            }
        });
        recyclerView.setAdapter(buyFragmentAdapter);

    }

    private void getData() {
        int type = 2;
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<SupplyListResult> supplyList = service.getMySupplyList(0, page, pagerow, User.INSTANCE.getToken(), type);
        supplyList.enqueue(new Callback<SupplyListResult>() {
            @Override
            public void onResponse(Call<SupplyListResult> call, Response<SupplyListResult> response) {
                if (response.isSuccessful()) {
                    SupplyListResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        if (page == 1) {
                            mSupplyList.clear();
                            recyclerView.refreshComplete();
                        } else {
                            recyclerView.loadMoreComplete();
                        }
                        mSupplyList.addAll(body.getData().getRows());

                        recyclerView.getAdapter().notifyDataSetChanged();

                    } else {

                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                    recyclerView.refreshComplete();
                    recyclerView.loadMoreComplete();
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }


            @Override
            public void onFailure(Call<SupplyListResult> call, Throwable t) {

            }
        });
    }


    @Override
    protected void lazyLoad() {
        getData();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 126){
            page = 1;
            getData();
        }
    }
}
