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
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.ApplyForInfoActivity;
import com.azhyun.massxj.adapter.LandHostingAdapter;
import com.azhyun.massxj.bean.LandListResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/21.
 */

public class LandHostingFragment extends LazyLoadFragment implements View.OnClickListener {
    @BindView(R.id.tv_not_audit)
    TextView tvNotAudit;
    @BindView(R.id.view_not_audit)
    View viewNotAudit;
    @BindView(R.id.line_not_audit)
    AutoLinearLayout lineNotAudit;
    @BindView(R.id.tv_approved)
    TextView tvApproved;
    @BindView(R.id.view_approved)
    View viewApproved;
    @BindView(R.id.line_approved)
    AutoLinearLayout lineApproved;
    @BindView(R.id.tv_reject)
    TextView tvReject;
    @BindView(R.id.view_reject)
    View viewReject;
    @BindView(R.id.line_reject)
    AutoLinearLayout lineReject;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    Unbinder unbinder;
    private int type =1;
    private int page = 1;
    private int pagerow = 10;
    private List<LandListResult.Data.Rows> rowsList = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void intiData() {
        //获取数据
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<LandListResult> landList = service.getListNewList(page, pagerow, type, User.INSTANCE.getToken());
        landList.enqueue(new Callback<LandListResult>() {
            public void onResponse(Call<LandListResult> call, Response<LandListResult> response) {
                if (response.isSuccessful()){
                    LandListResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        if (page == 1){
                           rowsList.clear();
                           recyclerView.refreshComplete();
                        }else {
                            recyclerView.loadMoreComplete();
                        }
                        rowsList.addAll(body.getData().getRows());
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }else {
                        recyclerView.refreshComplete();
                        recyclerView.loadMoreComplete();
//                        ToastUtils.showToast(getContext(),body.getResult().getMessage());
                    }
                }else {
                    recyclerView.refreshComplete();
                    recyclerView.loadMoreComplete();
                }
            }

            @Override
            public void onFailure(Call<LandListResult> call, Throwable t) {
                Log.e("---->>",t.getMessage());
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        });


    }

    private void initView() {
        lineNotAudit.setOnClickListener(this);
        lineApproved.setOnClickListener(this);
        lineReject.setOnClickListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyLayout);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),DividerItemDecoration.HORIZONTAL,20,Color.parseColor("#eeeeee")));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                intiData();
            }

            @Override
            public void onLoadMore() {
                page ++;
                intiData();
            }
        });

        LandHostingAdapter landHostingAdapter = new LandHostingAdapter(rowsList);
        landHostingAdapter.getOnClick(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = null;
                intent = new Intent(getContext(),ApplyForInfoActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("isImg",rowsList.get(postion).getIsImg());
                intent.putExtra("id",rowsList.get(postion).getId());
                startActivityForResult(intent,124);
            }
        });

        recyclerView.setAdapter(landHostingAdapter);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_land_hosting;
    }

    @Override
    protected void lazyLoad() {
        intiData();
    }

    public static Fragment newIntent() {
        Bundle bundle = new Bundle();
//        bundle.putInt("id",id);
        LandHostingFragment fragment = new LandHostingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_not_audit://待审核
                tvNotAudit.setTextColor(Color.parseColor("#6CA323"));
                viewNotAudit.setBackgroundColor(Color.parseColor("#6CA323"));

                tvApproved.setTextColor(Color.parseColor("#999999"));
                viewApproved.setBackgroundColor(Color.parseColor("#ffffff"));
                tvReject.setTextColor(Color.parseColor("#999999"));
                viewReject.setBackgroundColor(Color.parseColor("#ffffff"));
                page = 1;
                type = 1;
                intiData();
                break;
            case R.id.line_approved://审核通过
                tvApproved.setTextColor(Color.parseColor("#6CA323"));
                viewApproved.setBackgroundColor(Color.parseColor("#6CA323"));

                tvNotAudit.setTextColor(Color.parseColor("#999999"));
                viewNotAudit.setBackgroundColor(Color.parseColor("#ffffff"));
                tvReject.setTextColor(Color.parseColor("#999999"));
                viewReject.setBackgroundColor(Color.parseColor("#ffffff"));
                type = 2;
                page = 1;

                intiData();
                break;
            case R.id.line_reject://审核拒绝
                tvReject.setTextColor(Color.parseColor("#6CA323"));
                viewReject.setBackgroundColor(Color.parseColor("#6CA323"));

                tvNotAudit.setTextColor(Color.parseColor("#999999"));
                viewNotAudit.setBackgroundColor(Color.parseColor("#ffffff"));
                tvApproved.setTextColor(Color.parseColor("#999999"));
                viewApproved.setBackgroundColor(Color.parseColor("#ffffff"));
                type = -1;
                page = 1;

                intiData();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 124){
            page = 1;
            intiData();
        }
    }
}
