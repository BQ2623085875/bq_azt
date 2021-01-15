package com.azhyun.massxj.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.FarmingServiceInfoActivity;
import com.azhyun.massxj.adapter.FarmingServiceFragmentAdapter;
import com.azhyun.massxj.bean.ServiceListResult;
import com.azhyun.massxj.bean.SpUtils;
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

@SuppressLint("ValidFragment")
public class FarmingServiceFragment extends LazyLoadFragment {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    private ArrayList<String> list = new ArrayList<>();
    private int id;
    private int page = 1;
    private int pagerow = 10;
    private List<ServiceListResult.Data.Rows> rowsList = new ArrayList<>();

    @SuppressLint("ValidFragment")
    public FarmingServiceFragment(int i) {
        super();
        this.id = i;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_farming_service;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyLayout);
        recyclerView.addItemDecoration( new RecycleViewDivider(getActivity(), DividerItemDecoration.HORIZONTAL,2, Color.parseColor("#eeeeee")));
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                page ++;
                getData();
            }
        });
        FarmingServiceFragmentAdapter farmingServiceFragmentAdapter = new FarmingServiceFragmentAdapter(getContext(), rowsList);
        farmingServiceFragmentAdapter.getClik(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getContext(),FarmingServiceInfoActivity.class);
                intent.putExtra("id",rowsList.get(postion).getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(farmingServiceFragmentAdapter);

        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ServiceListResult> serviceList = service.getServiceList(id, page, pagerow, User.INSTANCE.getToken());
        serviceList.enqueue(new Callback<ServiceListResult>() {
            @Override
            public void onResponse(Call<ServiceListResult> call, Response<ServiceListResult> response) {
                if (response.isSuccessful()){
                    ServiceListResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        if (page ==1){
                            rowsList.clear();
                            recyclerView.refreshComplete();
                        }else {
                            recyclerView.loadMoreComplete();

                        }
                        rowsList.addAll(body.getData().getRows()) ;
                        recyclerView.getAdapter().notifyDataSetChanged();

                    }else {
                        if (page ==1){
                            recyclerView.refreshComplete();
                        }else {

                            recyclerView.loadMoreComplete();

                        }
                        ToastUtils.showToast(getContext(),body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ServiceListResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected void lazyLoad() {
        initView();

    }

    @Override
    public void onResume() {//刷新
        super.onResume();
        page = 1;
        isCanLoadData();
    }


}
