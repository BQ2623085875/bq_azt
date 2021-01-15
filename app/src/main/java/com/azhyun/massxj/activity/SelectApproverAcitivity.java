package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.AuditUsersResult;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2019/1/14.
 */

public class SelectApproverAcitivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.relation_title)
    AutoRelativeLayout relationTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.xrecycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private int applyRole;
    private String name;
    private String gender;
    private String phone;
    private String area;
    private String address;
    private List<AuditUsersResult.Data> data = new ArrayList<>();
    private String companyName;
    private int type;
    private int landType;
    private int landWay;
    private String acreage;
    private String LandName;
    private String LandPhone;
    private String region;
    private String describe;

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);
        if (type == 1){//申请加盟
            applyRole = getIntent().getIntExtra("applyRole", 0);
            name = getIntent().getStringExtra("name");
            companyName = getIntent().getStringExtra("companyName");
            gender = getIntent().getStringExtra("gender");
            phone = getIntent().getStringExtra("phone");
            area = getIntent().getStringExtra("area");
            address = getIntent().getStringExtra("address");

            if (applyRole == 1){
                tvType.setText("请选择关联乡级理事长");
            }else {
                tvType.setText("请选择关联县级理事长");
            }

            getUsers(area, User.INSTANCE.getToken(), applyRole);
        }else if (type == 2){//申请土地托管
            tvType.setText("请选择关联村级理事长");

            landType = getIntent().getIntExtra("landType",0);
            landWay = getIntent().getIntExtra("landWay",0);
            acreage = getIntent().getStringExtra("acreage");
            LandName = getIntent().getStringExtra("name");
            LandPhone = getIntent().getStringExtra("phone");
            region = getIntent().getStringExtra("region");
            describe = getIntent().getStringExtra("describe");


            getUsers(region, User.INSTANCE.getToken(), 0);
        }



        tvConfirm.setOnClickListener(this);

    }

    private void getUsers(String area, String token, int applyRole) {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<AuditUsersResult> auditUsersResultCall = service.auditUsers(area, token, applyRole);
        auditUsersResultCall.enqueue(new Callback<AuditUsersResult>() {
            @Override
            public void onResponse(Call<AuditUsersResult> call, Response<AuditUsersResult> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    AuditUsersResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        data.addAll(body.getData());
                        recyclerView.getAdapter().notifyDataSetChanged();
                    } else {
                        ToastUtils.showToast(SelectApproverAcitivity.this, body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<AuditUsersResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setEmptyView(emptyLayout);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.addItemDecoration(new RecycleViewDivider(SelectApproverAcitivity.this, DividerItemDecoration.HORIZONTAL, 2, Color.parseColor("#eeeeee")));

        final SelectApproverAdapter selectApproverAdapter = new SelectApproverAdapter(data,applyRole);

        recyclerView.setAdapter(selectApproverAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_approver;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.bank:
                fund();
                break;
            case R.id.tv_confirm:

                int auditUserId = 0;
               for (int i = 0 ; i <data.size();i++){
                   if (data.get(i).isSelect()){
                     auditUserId = data.get(i).getUserId();
                   }
               }
               if (auditUserId != 0){

                   if (type == 1){
                       int status = getIntent().getIntExtra("status", 0);
                       if (status == 2){//修改经纪人申请
                           myapplyUpdate(auditUserId);
                       }else {
                           confirm(auditUserId);
                       }

                   }else if (type == 2){
                       landNew(auditUserId);
                   }
               }else {
                   ToastUtils.showToast(SelectApproverAcitivity.this,"请选择审核人");
               }
                break;
        }
    }
    private void myapplyUpdate(int auditUserId) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        String token = (String) SpUtils.get("token", "");
        Call<ManagerResult> managerResultCall = service.MyapplyUpdate(address, applyRole, 0,auditUserId,null ,phone, name, region, gender, token);
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        ToastUtils.showToast(SelectApproverAcitivity.this,body.getResult().getMessage());
                        fund();
                        Intent intent = new Intent(SelectApproverAcitivity.this, SubmitSucceedActivity.class);
                        intent.putExtra("type",2);//2代表经纪人申请
                        intent.putExtra("id",body.getData().getId());
                        startActivity(intent);
                    }else {
                        ToastUtils.showToast(SelectApproverAcitivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }
    private void landNew(int auditUserId) {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.landNew(null, Double.parseDouble(acreage), auditUserId, null, landType, landWay, LandName, LandPhone, region, describe, null,User.INSTANCE.getToken());
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    LoadingDialog.cancel();
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        ToastUtils.showToast(SelectApproverAcitivity.this,body.getResult().getMessage());
                        Intent intent = new Intent(SelectApproverAcitivity.this,SubmitSucceedActivity.class);
                        intent.putExtra("id",body.getData().getId());
                        intent.putExtra("type",1);//1 代表土地托管
                        startActivity(intent);
                    }else {
                        ToastUtils.showToast(SelectApproverAcitivity.this,body.getResult().getMessage());
                    }
                }else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }

    private void confirm(int userId) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> managerResultCall = service.managerNew(address, applyRole, 0, userId, companyName, null, phone, name, area, gender, User.INSTANCE.getToken());
        managerResultCall.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()){
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")){
                        ToastUtils.showToast(SelectApproverAcitivity.this,body.getResult().getMessage());
                        fund();
                        Intent intent = new Intent(SelectApproverAcitivity.this, SubmitSucceedActivity.class);
                        intent.putExtra("type",2);//2代表经纪人申请
                        intent.putExtra("id",body.getData().getId());
                        startActivity(intent);
                    }else {
                        ToastUtils.showToast(SelectApproverAcitivity.this,body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {

            }
        });
    }

    public class SelectApproverAdapter extends RecyclerView.Adapter<SelectApproverAdapter.SelectApproverHolder> {

        private final int applyRole;
        private List<AuditUsersResult.Data> list;
        private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

        public SelectApproverAdapter(List<AuditUsersResult.Data> data, int applyRole) {
            this.list = data;
            this.applyRole = applyRole;
        }

        public void onClik(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
        }

        @Override
        public SelectApproverAdapter.SelectApproverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_approver_recyclerview, parent, false);
            return new SelectApproverAdapter.SelectApproverHolder(view);
        }

        @Override
        public void onBindViewHolder(final SelectApproverAdapter.SelectApproverHolder holder, final int position) {

            holder.checkbox.setChecked(list.get(position).isSelect());
            holder.tvName.setText(list.get(position).getName());

                holder.tvArea.setText(list.get(position).getFullName());

//                holder.tvArea.setText(list.get(position).getFullName());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < data.size(); i++) {
                        if (i == position) {
                            data.get(i).setSelect(true);
                        } else if (i != position){
                            data.get(i).setSelect(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class SelectApproverHolder extends RecyclerView.ViewHolder {

            private final CheckBox checkbox;
            private final TextView tvName;
            private final TextView tvArea;

            public SelectApproverHolder(View itemView) {
                super(itemView);

                checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
                tvArea = (TextView) itemView.findViewById(R.id.tv_area);
            }
        }
    }


}
