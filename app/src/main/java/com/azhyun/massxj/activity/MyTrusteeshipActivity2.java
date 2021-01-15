package com.azhyun.massxj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.LandTypeResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.utils.AlignTextView;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2019/1/14.
 */

public class MyTrusteeshipActivity2 extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_view_tudi)
    RecyclerView recyclerViewTudi;
    @BindView(R.id.recycler_view_gendi)
    RecyclerView recyclerViewGendi;
    @BindView(R.id.edt_acreage)
    EditText edtAcreage;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.text_area)
    TextView textArea;
    @BindView(R.id.selected_area)
    AutoLinearLayout selectedArea;
    @BindView(R.id.describe)
    AlignTextView describe;
    @BindView(R.id.edt_describe)
    EditText edtDescribe;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private List<LandTypeResult.Data.TuDI> tudiList = new ArrayList<>();
    private List<LandTypeResult.Data.GengDi> gengdiList = new ArrayList<>();
    private int landType = 0;
    private int landWay = 0;
    private BottomStyleDialog bottomStyleDialog;
    private String region = "";

    @Override
    protected void initData() {
        getLand();
        title.setText("添加托管");
    }

    private void getLand() {
        LoadingDialog.show(this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<LandTypeResult> landTypeResultCall = service.typeLand();
        landTypeResultCall.enqueue(new Callback<LandTypeResult>() {
            @Override
            public void onResponse(Call<LandTypeResult> call, Response<LandTypeResult> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    LandTypeResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        tudiList.clear();
                        tudiList.addAll(body.getData().getTudi());
                        gengdiList.addAll(body.getData().getGengdi());
                        recyclerViewTudi.getAdapter().notifyDataSetChanged();
                        recyclerViewGendi.getAdapter().notifyDataSetChanged();
                    } else {
                        ToastUtils.showToast(MyTrusteeshipActivity2.this, body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<LandTypeResult> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        textArea.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initView() {

        bottomStyleDialog = new BottomStyleDialog(this, new BottomStyleDialog.OnItemListener() {
            @Override
            public void getArea(String area, String id) {
                textArea.setText(area);
                region = id;
            }
        }, 1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTudi.setLayoutManager(linearLayoutManager);
        LandImageAdapter landImageAdapter = new LandImageAdapter(tudiList);
        recyclerViewTudi.setAdapter(landImageAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewGendi.setLayoutManager(linearLayoutManager2);
        LandImageAdapter2 landImageAdapter2 = new LandImageAdapter2(gengdiList);
        recyclerViewGendi.setAdapter(landImageAdapter2);

        String regionName = (String) SpUtils.get("fullName", "");
        String region = (String) SpUtils.get("region", "");
        if (region.length() == 12) {
            this.region = region;
            textArea.setText(regionName);
        } else {
            this.region = "";
            textArea.setText("");
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_trusteeship_2;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.text_area:
                //选择区域
                bottomStyleDialog.show();
                break;
            case R.id.btn_submit:
                if (landType == 0) {
                    ToastUtils.showToast(MyTrusteeshipActivity2.this, "请选择土地类型");
                    break;
                }
                if (landWay == 0) {
                    ToastUtils.showToast(MyTrusteeshipActivity2.this, "请选择耕地类型");
                    break;
                }
                String acreage = edtAcreage.getText().toString().trim();
                if (acreage.isEmpty()) {
                    ToastUtils.showToast(MyTrusteeshipActivity2.this, "请输入土地面积");
                    break;
                }
                String name = edtName.getText().toString().trim();
                if (name.isEmpty()) {
                    ToastUtils.showToast(MyTrusteeshipActivity2.this, "请输入托管人姓名");
                    break;
                }
                String phone = edtPhone.getText().toString().trim();
                String substring = phone.substring(0, 1);
                if (phone.isEmpty()) {
                    ToastUtils.showToast(MyTrusteeshipActivity2.this, "请输入托管人联系方式");
                    break;
                } else if (!substring.equals("1") || phone.length() != 11) {
                    ToastUtils.showToast(MyTrusteeshipActivity2.this, "请输入正确的手机号");
                    break;
                }
//                }else if (!MobileUtils.isMobile(phone)){
//                    ToastUtils.showToast(MyTrusteeshipActivity2.this,"请输入正确的手机号");
//                    break;
//                }
                if (region.isEmpty()) {
                    ToastUtils.showToast(MyTrusteeshipActivity2.this, "请选择区域");
                    break;
                }
                String describe = edtDescribe.getText().toString().trim();

                Intent intent = new Intent(MyTrusteeshipActivity2.this, SelectApproverAcitivity.class);
                intent.putExtra("type", 2);
                intent.putExtra("landType", landType);
                intent.putExtra("landWay", landWay);
                intent.putExtra("acreage", acreage);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("region", region);
                intent.putExtra("describe", describe);
                startActivityForResult(intent, 200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 201) {
            fund();
        }
    }

    private class LandImageAdapter extends RecyclerView.Adapter<LandImageAdapter.LandImageHolder> {

        private final List<LandTypeResult.Data.TuDI> list;

        public LandImageAdapter(List<LandTypeResult.Data.TuDI> tudiList) {
            this.list = tudiList;
        }

        @Override
        public LandImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_recyclerview, parent, false);
            return new LandImageHolder(view);
        }

        @Override
        public void onBindViewHolder(LandImageHolder holder, final int position) {
            if (list.get(position).isSelect()) {
                holder.tvName.setTextColor(Color.parseColor("#ff5500"));
            } else {
                holder.tvName.setTextColor(Color.parseColor("#666666"));
            }
            Glide.with(getApplicationContext())
                    .load(Constant.IMG_URL + list.get(position).getUrl())
                    .placeholder(R.drawable.err)
                    .into(holder.image);
            holder.tvName.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < tudiList.size(); i++) {
                        if (i == position) {
                            landType = list.get(position).getId();
                            tudiList.get(i).setSelect(true);
                        } else {
                            tudiList.get(i).setSelect(false);
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

        public class LandImageHolder extends RecyclerView.ViewHolder {

            private final ImageView image;
            private final TextView tvName;

            public LandImageHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.tv_image);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
            }
        }
    }

    private class LandImageAdapter2 extends RecyclerView.Adapter<LandImageAdapter2.LandImageHolder2> {

        private final List<LandTypeResult.Data.GengDi> list;

        public LandImageAdapter2(List<LandTypeResult.Data.GengDi> gengdiList) {
            this.list = gengdiList;
        }

        @Override
        public LandImageHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_recyclerview, parent, false);
            return new LandImageHolder2(view);
        }

        @Override
        public void onBindViewHolder(LandImageHolder2 holder, final int position) {
            if (list.get(position).isSelect()) {
                holder.tvName.setTextColor(Color.parseColor("#ff5500"));
            } else {
                holder.tvName.setTextColor(Color.parseColor("#666666"));
            }
            Glide.with(getApplicationContext())
                    .load(Constant.IMG_URL + list.get(position).getUrl())
                    .placeholder(R.drawable.err)
                    .into(holder.image);
            holder.tvName.setText(list.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < gengdiList.size(); i++) {
                        if (i == position) {
                            landWay = list.get(position).getId();
                            gengdiList.get(i).setSelect(true);
                        } else {
                            gengdiList.get(i).setSelect(false);
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

        public class LandImageHolder2 extends RecyclerView.ViewHolder {

            private final ImageView image;
            private final TextView tvName;

            public LandImageHolder2(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.tv_image);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
            }
        }
    }
}
