package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.OrderInfoActivity2;
import com.azhyun.massxj.adapter.ServiceOrderFragmentAdapter;
import com.azhyun.massxj.bean.MyServiceListResult;
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
 * Created by tl on 2018/8/20.
 */

public class ServiceOrderFragment extends LazyLoadFragment {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    private String statusString;
    private int status ;
    private int page = 1;
    private int pagerow = 10;
    private List<MyServiceListResult.Data.Rows> rowsList = new ArrayList<>();


    public static Fragment newIntent(String statusString) {
        Bundle bundle = new Bundle();
        bundle.putString("statusString", statusString);
        ServiceOrderFragment listFragment = new ServiceOrderFragment();
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        statusString = arguments.getString("statusString");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyLayout);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), DividerItemDecoration.HORIZONTAL, 2, Color.parseColor("#eeeeee")));



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
        ServiceOrderFragmentAdapter serviceOrderFragmentAdapter = new ServiceOrderFragmentAdapter(rowsList);
        serviceOrderFragmentAdapter.onClik(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                //跳转详情
//                Intent intent = new Intent(getContext(),OrderInfoActivity.class);
                Intent intent = new Intent(getContext(),OrderInfoActivity2.class);
                intent.putExtra("introduce",rowsList.get(postion).getIntroduce());
                intent.putExtra("id",rowsList.get(postion).getId());
                startActivityForResult(intent,13);
            }
        });

        recyclerView.setAdapter(serviceOrderFragmentAdapter);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_service_order;
    }

    @Override
    protected void lazyLoad() {
        initView();
        page = 1;
        getData();


    }

    public void getData(){
        if (statusString.equals("待确认")){
            status = 1;
        }else if (statusString.equals("已确认")){
            status = 2;
        }else if (statusString.equals("进行中")){
            status = 3;
        }else if (statusString.equals("已完成")){
            status = 4;
        }

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyServiceListResult> call = service.myServList(page, pagerow, status, User.INSTANCE.getToken());
        call.enqueue(new Callback<MyServiceListResult>() {
            @Override
            public void onResponse(Call<MyServiceListResult> call, Response<MyServiceListResult> response) {
                if (response.isSuccessful()){
                    MyServiceListResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
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
                        rowsList.addAll(body.getData().getRows());
                        if (recyclerView != null){
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }
                    }else {
                        ToastUtils.showToast(getContext(),body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyServiceListResult> call, Throwable t) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 13){
            if (recyclerView != null){
                page =1;
                getData();
            }

        }
    }
}
