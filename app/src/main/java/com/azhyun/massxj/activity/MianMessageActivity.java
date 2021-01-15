package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.MianMessageAdapter;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.MyManageInfoResult;
import com.azhyun.massxj.bean.MyinfoListResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/9/10.
 */

public class MianMessageActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    private int page = 1;
    private int pagerow = 10;
    private List<MyinfoListResult.Data.Rows> rowsList = new ArrayList<>();
    private MianMessageAdapter mianMessageAdapter;

    @Override
    protected void initData() {
        title.setText("系统消息");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setEmptyView(emptyLayout);
        mianMessageAdapter = new MianMessageAdapter(rowsList);
        recyclerView.addItemDecoration(new RecycleViewDivider(this, DividerItemDecoration.HORIZONTAL,2, Color.parseColor("#eeeeee")));
        mianMessageAdapter.onClik(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = null;
                if (rowsList.get(postion).getType() == 1){//预约单
                    if (rowsList.get(postion).getWorkStatus() != 5){
//                        intent = new Intent(MianMessageActivity.this,OrderInfoActivity.class);
                        intent = new Intent(MianMessageActivity.this,OrderInfoActivity2.class);
                        intent.putExtra("id",rowsList.get(postion).getWorkId());
                        startActivityForResult(intent,128);
                    }


                }else if (rowsList.get(postion).getType() == 2){//土地托管申请
                    intent = new Intent(MianMessageActivity.this,ApplyForInfoActivity.class);
                    intent.putExtra("type",rowsList.get(postion).getWorkStatus());
                    intent.putExtra("isImg",rowsList.get(postion).getIsImg());
                    intent.putExtra("id",rowsList.get(postion).getWorkId());
                    startActivityForResult(intent,128);


                }else if (rowsList.get(postion).getType() == 3){//经纪人申请
                     intent = new Intent(MianMessageActivity.this,AgentInfoActivity.class);
                    intent.putExtra("id",rowsList.get(postion).getWorkId());
                    startActivityForResult(intent,128);
                }
                //已读
                MyManageRead(rowsList.get(postion).getId());
            }
        });
        recyclerView.setAdapter(mianMessageAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getMessageList();
            }

            @Override
            public void onLoadMore() {
                page  ++;
                getMessageList();
            }
        });



    }

    private void MyManageRead(int id) {

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyManageInfoResult> myManageInfoResultCall = service.MyManageRead(User.INSTANCE.getToken(), id);
        myManageInfoResultCall.enqueue(new Callback<MyManageInfoResult>() {
            @Override
            public void onResponse(Call<MyManageInfoResult> call, Response<MyManageInfoResult> response) {
                if (response.isSuccessful()){
                    MyManageInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")){

                    }else {
                        ToastUtils.showToast(MianMessageActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyManageInfoResult> call, Throwable t) {

            }
        });
    }

    private void getMessageList() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyinfoListResult> myinfoList = service.getMyinfoList(page, pagerow, User.INSTANCE.getToken());
        myinfoList.enqueue(new Callback<MyinfoListResult>() {
            @Override
            public void onResponse(Call<MyinfoListResult> call, Response<MyinfoListResult> response) {
                if (response.isSuccessful()){
                    MyinfoListResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        if (page == 1){
                            rowsList.clear();
                            rowsList.addAll(body.getData().getRows());
                            recyclerView.refreshComplete();
                        }else {
                            rowsList.addAll(body.getData().getRows());
                            recyclerView.loadMoreComplete();
                        }
                        mianMessageAdapter.notifyDataSetChanged();

                    }else {
                        ToastUtils.showToast(MianMessageActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyinfoListResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initListener() {
        empty.setOnClickListener(this);
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        //获取消息
        getMessageList();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mian_nessage;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.empty:
                //清空
                getEmpty();
                break;
        }
    }

    private void getEmpty() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.MyInfoDelall(User.INSTANCE.getToken());
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        SpUtils.put("JSESSIONID",body.getData().getJSESSIONID());
                        ToastUtils.showToast(MianMessageActivity.this,body.getResult().getMessage());
                        page = 1;
                        getMessageList();
                    }else {
                        ToastUtils.showToast(MianMessageActivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 128){
            page =1;
            getMessageList();
        }
    }
}
