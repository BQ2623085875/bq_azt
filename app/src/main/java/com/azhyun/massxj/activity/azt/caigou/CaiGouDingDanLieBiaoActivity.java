package com.azhyun.massxj.activity.azt.caigou;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.activity.azt.CaiGouOrderDetailsActivity;
import com.azhyun.massxj.adapter.azt.caigou.CaiGouOrderAdapter;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaiGouDingDanLieBiaoActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.xrv)
    RecyclerView xrv;
    @BindView(R.id.quanbu_l)
    LinearLayout quanbu_l;
    @BindView(R.id.quanbu_t)
    TextView quanbu_t;

    @BindView(R.id.daichuli_l)
    LinearLayout daichuli_l;
    @BindView(R.id.daichuli_t)
    TextView daichuli_t;

    @BindView(R.id.yiwancheng_l)
    LinearLayout yiwancheng_l;
    @BindView(R.id.yiwancheng_t)
    TextView yiwancheng_t;
    @BindView(R.id.a)
    TextView a;
    @BindView(R.id.b)
    TextView b;
    @BindView(R.id.c)
    TextView c;
    @BindView(R.id.wsj)
    TextView wsj;

    private int tiem = 0;

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<OrdserBean> caigouorderliebiao = service.caigouorderliebiao((String) SpUtils.get("userId", ""), tiem);
        caigouorderliebiao.enqueue(new Callback<OrdserBean>() {
            @Override
            public void onResponse(Call<OrdserBean> call, Response<OrdserBean> response) {
                if (response.isSuccessful()) {
                    OrdserBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<OrdserBean.DataBeanX> data = response.body().getData();
                        ArrayList<OrdserBean.DataBeanX> itemsBeans = new ArrayList<>();

                        if (data.size() != 0) {
                            xrv.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);

                            itemsBeans.addAll(data);
                            CaiGouOrderAdapter orderAdapter = new CaiGouOrderAdapter(CaiGouDingDanLieBiaoActivity.this, itemsBeans);
                            xrv.setAdapter(orderAdapter);
                            xrv.setLayoutManager(new LinearLayoutManager(CaiGouDingDanLieBiaoActivity.this));
                            orderAdapter.notifyDataSetChanged();
                            orderAdapter.setOnInterface(new CaiGouOrderAdapter.OnInterface() {
                                @Override
                                public void OnCilkInterface(OrdserBean.DataBeanX dataBeanX, int position) {
                                    Intent intent = new Intent(CaiGouDingDanLieBiaoActivity.this, CaiGouOrderDetailsActivity.class);
                                    intent.putExtra("orderId", dataBeanX.getId());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            xrv.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    } else {
                        ToastUtils.showToast(CaiGouDingDanLieBiaoActivity.this, body.getResult().getMessage());
                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<OrdserBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initListener() {
        quanbu_l.setOnClickListener(this);
        daichuli_l.setOnClickListener(this);
        yiwancheng_l.setOnClickListener(this);
        bank.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title.setText("订单");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));
        tiem = getIntent().getIntExtra("tiem", 0);

        if (this.tiem == 0) {
            quanbu_t.setVisibility(ViewGroup.VISIBLE);
            daichuli_t.setVisibility(ViewGroup.GONE);
            yiwancheng_t.setVisibility(ViewGroup.GONE);
            a.setTextColor(getResources().getColor(R.color.caigoutext));
            b.setTextColor(getResources().getColor(R.color.black));
            c.setTextColor(getResources().getColor(R.color.black));
        } else if (this.tiem == 1) {
            quanbu_t.setVisibility(ViewGroup.GONE);
            daichuli_t.setVisibility(ViewGroup.VISIBLE);
            yiwancheng_t.setVisibility(ViewGroup.GONE);
            a.setTextColor(getResources().getColor(R.color.black));
            b.setTextColor(getResources().getColor(R.color.caigoutext));
            c.setTextColor(getResources().getColor(R.color.black));
        } else {
            quanbu_t.setVisibility(ViewGroup.GONE);
            daichuli_t.setVisibility(ViewGroup.GONE);
            yiwancheng_t.setVisibility(ViewGroup.VISIBLE);
            a.setTextColor(getResources().getColor(R.color.black));
            b.setTextColor(getResources().getColor(R.color.black));
            c.setTextColor(getResources().getColor(R.color.caigoutext));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cai_gou_ding_dan_lie_biao;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
            case R.id.quanbu_l:
                tiem = 0;
                quanbu_t.setVisibility(ViewGroup.VISIBLE);
                daichuli_t.setVisibility(ViewGroup.GONE);
                yiwancheng_t.setVisibility(ViewGroup.GONE);
                a.setTextColor(getResources().getColor(R.color.caigoutext));
                b.setTextColor(getResources().getColor(R.color.black));
                c.setTextColor(getResources().getColor(R.color.black));
                getData();
                break;
            case R.id.daichuli_l:
                tiem = 1;
                quanbu_t.setVisibility(ViewGroup.GONE);
                daichuli_t.setVisibility(ViewGroup.VISIBLE);
                yiwancheng_t.setVisibility(ViewGroup.GONE);
                a.setTextColor(getResources().getColor(R.color.black));
                b.setTextColor(getResources().getColor(R.color.caigoutext));
                c.setTextColor(getResources().getColor(R.color.black));
                getData();
                break;
            case R.id.yiwancheng_l:
                tiem = 2;
                quanbu_t.setVisibility(ViewGroup.GONE);
                daichuli_t.setVisibility(ViewGroup.GONE);
                yiwancheng_t.setVisibility(ViewGroup.VISIBLE);
                a.setTextColor(getResources().getColor(R.color.black));
                b.setTextColor(getResources().getColor(R.color.black));
                c.setTextColor(getResources().getColor(R.color.caigoutext));
                getData();
                break;
        }
    }
}
