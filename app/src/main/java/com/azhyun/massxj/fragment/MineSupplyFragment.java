package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.MineSupplyInfoActivity;
import com.azhyun.massxj.adapter.SupplyFragmentAdapter;
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
 * Created by tl on 2018/9/7.
 */

public class MineSupplyFragment extends LazyLoadFragment {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    private SupplyFragmentAdapter supplyFragmentAdapter;
    private int page = 1;
    private int pagerow = 10;
    private int categoryId = 0;
    private int type = 1;
    private List<SupplyListResult.Data.Rows> rowsList = new ArrayList<>();


    @Override
    protected int setContentView() {
        return R.layout.fragment_mine_supply;
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

//        supplyFragmentAdapter = new SupplyFragmentAdapter(rowsList);
        supplyFragmentAdapter.getOnClick(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                //供应详情
                SupplyListResult.Data.Rows rows = rowsList.get(postion);
                Intent intent = new Intent(getContext(), MineSupplyInfoActivity.class);
                intent.putExtra("id",rows.getId());
                startActivityForResult(intent,125);
            }
        });
//        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                page = 1;
//                getMySupplyList();
//            }
//
//            @Override
//            public void onLoadMore() {
//                page ++;
//                getMySupplyList();
//            }
//        });
        recyclerView.setAdapter(supplyFragmentAdapter);


    }


    @Override
    protected void lazyLoad() {
        getMySupplyList();
    }

    private void getMySupplyList() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<SupplyListResult> mySupplyList = service.getMySupplyList(categoryId, page, pagerow, User.INSTANCE.getToken(), type);
        mySupplyList.enqueue(new Callback<SupplyListResult>() {
            @Override
            public void onResponse(Call<SupplyListResult> call, Response<SupplyListResult> response) {
                if (response.isSuccessful()){
                    SupplyListResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        if (page == 1){
                            rowsList.clear();
                            recyclerView.refreshComplete();
                        }else {
                            recyclerView.loadMoreComplete();
                        }
                        rowsList.addAll( body.getData().getRows());
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }else {
                        ToastUtils.showToast(getContext(),body.getResult().getMessage());
                    }
                }else {
                    Log.e("-11--->>", response.message().toString());

                }
            }

            @Override
            public void onFailure(Call<SupplyListResult> call, Throwable t) {
                Log.e("---->>",t.getMessage());
            }
        });
    }

    public static Fragment newIntent() {
        Bundle bundle = new Bundle();
        MineSupplyFragment listFragment = new MineSupplyFragment();
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 125){
            page = 1;
            getMySupplyList();
        }
    }
}
