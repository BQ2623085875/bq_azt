package com.azhyun.massxj.fragment.azt;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.OrderDetailsActivity;
import com.azhyun.massxj.activity.azt.OrderPayActivity;
import com.azhyun.massxj.activity.azt.SubmitOrderActivity;
import com.azhyun.massxj.adapter.azt.OrderAdapter;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBeans;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.JSONTool;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * 订单列表 Fragment
 */
public class OrderFragment extends LazyLoadFragment {

    @BindView(R.id.order_rv)
    RecyclerView mOrder_rv;
    @BindView(R.id.wushuju)
    TextView wushuju;

    private static int tatus = 0;
    private int ordserStatus;

    private String userId;

    public static Fragment newInstance(int status) {
        tatus = status;
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ordserStatus", status);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }


    public OrderFragment() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_order;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        gainData();
    }

    @Override
    protected void lazyLoad() {

    }

    /*
     * 请求数据
     * */
    private void gainData() {
        userId = (String) SpUtils.get("userId", "");
        Bundle arguments = getArguments();
        ordserStatus = arguments.getInt("ordserStatus");

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<OrdserBean> orderliebiao = service.orderliebiao(userId, ordserStatus);
        orderliebiao.enqueue(new Callback<OrdserBean>() {
            @Override
            public void onResponse(Call<OrdserBean> call, Response<OrdserBean> response) {
                if (response.isSuccessful()) {
                    OrdserBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {

                        List<OrdserBean.DataBeanX> data = body.getData();
                        String format = JSONTool.format(new Gson().toJson(data));
                        if (data.size() == 0) {
                            wushuju.setVisibility(ViewGroup.VISIBLE);
                            mOrder_rv.setVisibility(ViewGroup.GONE);
                        } else {
                            wushuju.setVisibility(ViewGroup.GONE);
                            mOrder_rv.setVisibility(ViewGroup.VISIBLE);

                            ArrayList<OrdserBean.DataBeanX> itemsBeans = new ArrayList<>();
                            itemsBeans.addAll(data);

                            OrderAdapter orderAdapter = new OrderAdapter(getContext(), itemsBeans);
                            mOrder_rv.setAdapter(orderAdapter);
                            mOrder_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            orderAdapter.notifyDataSetChanged();

                            //订单详情
                            orderAdapter.setOnInterface(new OrderAdapter.OnInterface() {
                                @Override
                                public void OnCilkInterface(OrdserBean.DataBeanX dataBeanX, int position, int type) {
                                    if (type == 0) {//整个条目
                                        gainOrder(dataBeanX, position);
                                    } else if (type == 1) {//取消订单
                                        shouPoew(1, dataBeanX.getId());
                                    } else if (type == 2) {//去支付
                                        payData(dataBeanX,position);
                                    } else if (type == 3) {//确认收货
                                        shouPoew(2, dataBeanX.getId());
                                    }
                                }
                            });
                        }

                    } else {

                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<OrdserBean> call, Throwable t) {

            }
        });
    }

    //跳支付页面
    private void payData(OrdserBean.DataBeanX dataBeanX, int position) {
        Intent intent = new Intent(getActivity(), OrderPayActivity.class);
        intent.putExtra("payNumber", dataBeanX.getPayOrderId());
        intent.putExtra("heji", dataBeanX.getRetailPayAmount()+"");
        startActivity(intent);
        getActivity().finish();
    }


    //1取消订单  2收货
    private void shouPoew(final int i, final int id) {
        final AlertDialog.Builder alertDialog7 = new AlertDialog.Builder(getActivity());
        View view1 = View.inflate(getActivity(), R.layout.dialog_setview, null);
        final TextView titles = view1.findViewById(R.id.titles);
        final TextView cler = view1.findViewById(R.id.cler);
        final TextView yes = view1.findViewById(R.id.yes);
        final AlertDialog alertDialog = alertDialog7.setView(view1)
                .create();

        alertDialog.show();

        if (i == 1) {
            titles.setText("订单一旦取消不可恢复，您确定要取消吗？");
        } else if (i == 3) {
            titles.setText("您确定要收货吗？");
        }

        cler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {
                    detletMetohd(id);
                    alertDialog.dismiss();
                } else if (i == 2) {
                    shouMethod(id);
                    alertDialog.dismiss();
                }
            }
        });

    }

    //确认收货
    private void shouMethod(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBeans> quxiaoorder = service.getYesData(id);
        quxiaoorder.enqueue(new Callback<ResultBeans>() {
            @Override
            public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                ResultBeans body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    gainData();
                } else {
                    ToastUtils.showToast(getActivity(), "收货失败");
                }
            }

            @Override
            public void onFailure(Call<ResultBeans> call, Throwable t) {

            }
        });
    }

    //取消订单
    private void detletMetohd(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBeans> quxiaoorder = service.quxiaoorder(id);
        quxiaoorder.enqueue(new Callback<ResultBeans>() {
            @Override
            public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                ResultBeans body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    gainData();
                } else {
                    ToastUtils.showToast(getActivity(), "取消失败");
                }
            }

            @Override
            public void onFailure(Call<ResultBeans> call, Throwable t) {

            }
        });
    }

    private void gainOrder(OrdserBean.DataBeanX dataBeanX, int position) {
        Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
        intent.putExtra("orderId", dataBeanX.getId());
        getContext().startActivity(intent);
    }

}
