package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.ApplyForInfoActivity;
import com.azhyun.massxj.adapter.LandHostingAdapter;
import com.azhyun.massxj.bean.LandListResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.LazyLoadFragment;
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

public class MyLandHostingFragment extends LazyLoadFragment {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    int status = 1;
    private int page = 1;
    private int pagerow = 10;
    private List<LandListResult.Data.Rows> rowsList = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        final String statusString = getArguments().getString("status");
        //1.待审核2.已审核-1.已拒绝
        if (statusString.equals("待审核")){
            this.status = 1;
        }else if (statusString.equals("审核通过")){
            this.status = 2;
        }else if (statusString.equals("审核拒绝")){
            this.status = -1;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyLayout);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getMyLandHosting();
            }

            @Override
            public void onLoadMore() {
                page ++;
                getMyLandHosting();

            }
        });

        LandHostingAdapter landHostingAdapter = new LandHostingAdapter(rowsList);
        landHostingAdapter.getOnClick(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if (rowsList.size() != 0){
                    Intent intent = null;
                    intent = new Intent(getContext(),ApplyForInfoActivity.class);
                    intent.putExtra("type",status);
                    intent.putExtra("isImg",rowsList.get(postion).getIsImg());
                    intent.putExtra("id",rowsList.get(postion).getId());
                    startActivityForResult(intent,126);
                }

            }
        });
        recyclerView.setAdapter(landHostingAdapter);
    }

    private void getMyLandHosting() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<LandListResult> myLandList = service.getMyLandList(page, pagerow, User.INSTANCE.getToken(), status);
        myLandList.enqueue(new Callback<LandListResult>() {
            @Override
            public void onResponse(Call<LandListResult> call, Response<LandListResult> response) {
                if (response.isSuccessful()){
                    LandListResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        if (page == 1){
                            rowsList.clear();
                            if (recyclerView != null){
                                recyclerView.refreshComplete();
                            }
                        }else {
                            if (recyclerView != null){
                                recyclerView.loadMoreComplete();
                            }
                        }
                       rowsList.addAll( body.getData().getRows());
                        if (recyclerView != null){
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }

                    }else {
                        ToastUtils.showToast(getContext(),body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LandListResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_my_land_gosting;
    }

    @Override
    protected void lazyLoad() {
        getMyLandHosting();
    }

    public static Fragment newIntent(String status) {
        Bundle bundle = new Bundle();
        MyLandHostingFragment listFragment = new MyLandHostingFragment();
        bundle.putString("status", status);
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 126){
            page =1;
            getMyLandHosting();
        }
    }
}
