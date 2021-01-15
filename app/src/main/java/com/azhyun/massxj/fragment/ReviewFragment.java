package com.azhyun.massxj.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.FarmingServiceOrderActivity;
import com.azhyun.massxj.adapter.ReViewAdapter;
import com.azhyun.massxj.adapter.ReViewTabAdapter;
import com.azhyun.massxj.bean.EvaluationsResult;
import com.azhyun.massxj.bean.FarmingInfo;
import com.azhyun.massxj.bean.ReviewType;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.DensityUtil;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.SpaceItemDecoration;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by tl on 2018/8/23.
 */

public class ReviewFragment extends LazyLoadFragment implements View.OnClickListener{
    @BindView(R.id.title_recycler_view)
    RecyclerView titleRecyclerView;
    @BindView(R.id.review_recycler_view)
    XRecyclerView reviewRecyclerView;
    @BindView(R.id.empty_text)
    TextView empty;
    @BindView(R.id.btn_order)
    Button btnOrder;
    private List<ReviewType> tabList = new ArrayList<>();
    private int page = 1;
    private int pagerow =10;
    private int type = 0;
    private List<EvaluationsResult.Data.Rows> rowsList = new ArrayList<>();
    private int id = 0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        btnOrder.setOnClickListener(this);

        tabList.add(new ReviewType("全部",true));
        tabList.add(new ReviewType("好评",false));
        tabList.add(new ReviewType("中评",false));
        tabList.add(new ReviewType("差评",false));
        tabList.add(new ReviewType("有图",false));


        titleRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        titleRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(getContext(), 5), 4));
        titleRecyclerView.setNestedScrollingEnabled(false);


        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), DividerItemDecoration.HORIZONTAL, 2, Color.parseColor("#eeeeee")));
        reviewRecyclerView.setNestedScrollingEnabled(false);
        reviewRecyclerView.setLoadingMoreEnabled(true);
        reviewRecyclerView.setPullRefreshEnabled(true);
        reviewRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getEvaluations(id);
            }

            @Override
            public void onLoadMore() {
                page ++;
                getEvaluations(id);
            }
        });
        ReViewAdapter reViewAdapter = new ReViewAdapter(rowsList);

        reviewRecyclerView.setAdapter(reViewAdapter);
    }

    private void setReViewRecyclerView() {



    }

    private void setTabRecyclerView() {
        id = getArguments().getInt("id", 0);
        //获取评价信息
        getEvaluations(id);


    }

    private void getEvaluations(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<EvaluationsResult> evaluations = service.getEvaluations(id, page, pagerow, User.INSTANCE.getToken(), type);
        evaluations.enqueue(new Callback<EvaluationsResult>() {
            @Override
            public void onResponse(Call<EvaluationsResult> call, Response<EvaluationsResult> response) {
                if (response.isSuccessful()){
                    EvaluationsResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        EvaluationsResult.Data data = body.getData();

                        ReViewTabAdapter reViewTabAdapter = new ReViewTabAdapter(tabList, data);
                        reViewTabAdapter.onClik(new OnRecyclerViewItemClickListener() {
                            @Override
                            public void onItemClick(View view, int postion) {
                                type = postion;
                                setTabRecyclerView();
                            }
                        });
                        titleRecyclerView.setAdapter(reViewTabAdapter);

                        if (page == 1){
                            rowsList.clear();
                            reviewRecyclerView.refreshComplete();
                        }else {
                            reviewRecyclerView.loadMoreComplete();
                        }
                        rowsList.addAll(data.getRows());
                        if (rowsList.size() == 0){
                            empty.setVisibility(View.VISIBLE);
                        }else {
                            empty.setVisibility(View.GONE);
                        }
                        reviewRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<EvaluationsResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_review;
    }

    @Override
    protected void lazyLoad() {
        setTabRecyclerView();
        setReViewRecyclerView();
    }

    public static Fragment newIntent(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_order:
                Intent intent = new Intent(getContext(), FarmingServiceOrderActivity.class);
                intent.putExtra("info",(Serializable) FarmingInfo.info);
                startActivity(intent);
                break;
        }
    }
}
