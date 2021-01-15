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
import com.azhyun.massxj.activity.AgentInfoActivity;
import com.azhyun.massxj.adapter.AgentFragmentAdapter;
import com.azhyun.massxj.bean.ManagerListResult;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
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

public class AgentFragment extends LazyLoadFragment implements View.OnClickListener{
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
    private int status = 1;
    private int page = 1;
    private int pagerow = 10;
    private List<ManagerListResult.Data.Rows> rowsList = new ArrayList<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_agent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        lineNotAudit.setOnClickListener(this);
        lineApproved.setOnClickListener(this);
        lineReject.setOnClickListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyLayout);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), DividerItemDecoration.HORIZONTAL, 10, Color.parseColor("#eeeeee")));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getManagerList();
            }

            @Override
            public void onLoadMore() {
                page ++;
                getManagerList();

            }
        });


        AgentFragmentAdapter agentFragmentAdapter = new AgentFragmentAdapter(rowsList);

        agentFragmentAdapter.onClik(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getContext(),AgentInfoActivity.class);
                intent.putExtra("id",rowsList.get(postion).getUserId());
                startActivityForResult(intent,131);

            }
        });

        recyclerView.setAdapter(agentFragmentAdapter);
    }

    @Override
    protected void lazyLoad() {
      getManagerList();
    }

    private void getManagerList() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerListResult> managerList = service.getManagerList(page, pagerow, status, User.INSTANCE.getToken());
        managerList.enqueue(new Callback<ManagerListResult>() {
            @Override
            public void onResponse(Call<ManagerListResult> call, Response<ManagerListResult> response) {
                if (response.isSuccessful()){
                    ManagerListResult body = response.body();
                    if (body.getResult().getCode().equals("200")){

                        if (page == 1){
                            rowsList.clear();
                            recyclerView.refreshComplete();
                        }else {
                            recyclerView.loadMoreComplete();
                        }
                        List<ManagerListResult.Data.Rows> rows = body.getData().getRows();

                        if (rows != null){
                            rowsList.addAll(body.getData().getRows());
                        }

                        recyclerView.getAdapter().notifyDataSetChanged();
                    }else {
                        ToastUtils.showToast(getContext(),body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerListResult> call, Throwable t) {
                Log.e("------->>",t.getMessage());
            }
        });
    }

    public static Fragment newIntent() {
        Bundle bundle = new Bundle();
//        bundle.putInt("id",id);
        AgentFragment fragment = new AgentFragment();
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
                status = 1;
                page =1;
                getManagerList();
                break;
            case R.id.line_approved://审核通过
                tvApproved.setTextColor(Color.parseColor("#6CA323"));
                viewApproved.setBackgroundColor(Color.parseColor("#6CA323"));

                tvNotAudit.setTextColor(Color.parseColor("#999999"));
                viewNotAudit.setBackgroundColor(Color.parseColor("#ffffff"));
                tvReject.setTextColor(Color.parseColor("#999999"));
                viewReject.setBackgroundColor(Color.parseColor("#ffffff"));
                status = 2;
                page =1;
                getManagerList();
                break;
            case R.id.line_reject://审核拒绝
                tvReject.setTextColor(Color.parseColor("#6CA323"));
                viewReject.setBackgroundColor(Color.parseColor("#6CA323"));

                tvNotAudit.setTextColor(Color.parseColor("#999999"));
                viewNotAudit.setBackgroundColor(Color.parseColor("#ffffff"));
                tvApproved.setTextColor(Color.parseColor("#999999"));
                viewApproved.setBackgroundColor(Color.parseColor("#ffffff"));
                status = -1;
                page =1;
                getManagerList();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 131){
            lazyLoad();
        }
    }
}
