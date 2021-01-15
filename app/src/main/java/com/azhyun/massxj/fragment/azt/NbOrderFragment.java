package com.azhyun.massxj.fragment.azt;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.NbOrderDetailsActivity;
import com.azhyun.massxj.activity.azt.OrderDetailsActivity;
import com.azhyun.massxj.adapter.azt.NbOrderAdapter;
import com.azhyun.massxj.adapter.azt.OrderAdapter;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.aizhongtian.NbLieBiaoBean;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NbOrderFragment extends LazyLoadFragment {
    protected final String TAG = "NbOrderFragment";


    @BindView(R.id.order_rv)
    RecyclerView mOrder_rv;
    @BindView(R.id.wushuju)
    TextView wushuju;

    private int ordserStatus;
    private String userId;


    public static Fragment newInstance(int status) {
        NbOrderFragment nbOrderFragment = new NbOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ordserStatus", status);
        nbOrderFragment.setArguments(bundle);
        return nbOrderFragment;
    }

    public NbOrderFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_nb_order;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        gainData();
    }

    private void gainData() {
        userId = (String) SpUtils.get("userId", "");
        Bundle arguments = getArguments();
        ordserStatus = arguments.getInt("ordserStatus");

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NbLieBiaoBean> ordserBeanCall = service.nbOrderliebiao(userId, ordserStatus);
        ordserBeanCall.enqueue(new Callback<NbLieBiaoBean>() {
            @Override
            public void onResponse(Call<NbLieBiaoBean> call, Response<NbLieBiaoBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult().getCode().equals("200")) {
                        List<NbLieBiaoBean.DataBeanX.RowsBean> rows = response.body().getData().getRows();

                        if (rows.size() == 0) {
                            wushuju.setVisibility(ViewGroup.VISIBLE);
                            mOrder_rv.setVisibility(ViewGroup.GONE);
                        } else {
                            wushuju.setVisibility(ViewGroup.GONE);
                            mOrder_rv.setVisibility(ViewGroup.VISIBLE);

                            ArrayList<NbLieBiaoBean.DataBeanX.RowsBean> itemsBeans = new ArrayList<>();
                            itemsBeans.addAll(rows);

                            NbOrderAdapter nbOrderAdapter = new NbOrderAdapter(getContext(), itemsBeans);
                            mOrder_rv.setAdapter(nbOrderAdapter);
                            mOrder_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            nbOrderAdapter.notifyDataSetChanged();

                            nbOrderAdapter.setOnInterface(new NbOrderAdapter.OnInterface() {
                                @Override
                                public void OnCilkInterface(NbLieBiaoBean.DataBeanX.RowsBean rowsBean, int position) {
                                    Intent intent = new Intent(getContext(), NbOrderDetailsActivity.class);
                                    intent.putExtra("orderId", rowsBean.getId());
                                    intent.putExtra("type", rowsBean.getStatus());
                                    getContext().startActivity(intent);
                                }
                            });
                        }

                    } else {
                        ToastUtils.showToast(getContext(), response.body().getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<NbLieBiaoBean> call, Throwable t) {

            }
        });
    }

}
