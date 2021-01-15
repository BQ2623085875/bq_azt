package com.azhyun.massxj.fragment.azt.farmingInsurance;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.farmingInsurance.FarmingInsuranceDetailsActivity;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.baoxian.BaoLieBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 农保中心
 */
@SuppressLint("ValidFragment")
public class FarmingInsuranceFragment extends LazyLoadFragment {
    protected final String TAG = "FarmingInsuranceFragment";

    @BindView(R.id.mall_rv)
    XRecyclerView mMall_rv;
    @BindView(R.id.wushuju)
    TextView wushuju;

    private int mId;
    private int currentPager = 1;
    private FarmingInsuranceAdapter farmingInsuranceAdapter;

    @SuppressLint("ValidFragment")
    public FarmingInsuranceFragment(Integer integer) {
        // Required empty public constructor
        mId = integer;
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_bao_agricultura;
    }

    @Override
    protected void lazyLoad() {
        initView();
        gainData();
    }

    private void gainData() {
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<BaoLieBean> baoLie = service.getBaoLie(mId, currentPager, 6, User.INSTANCE.getRegion());
        baoLie.enqueue(new Callback<BaoLieBean>() {
            @Override
            public void onResponse(Call<BaoLieBean> call, Response<BaoLieBean> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    BaoLieBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<BaoLieBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        if (rows.size() > 0) {
                            wushuju.setVisibility(ViewGroup.GONE);
                            mMall_rv.setVisibility(ViewGroup.VISIBLE);
                            farmingInsuranceAdapter = new FarmingInsuranceAdapter(rows);
                            mMall_rv.setAdapter(farmingInsuranceAdapter);
                        } else {
                            wushuju.setVisibility(ViewGroup.VISIBLE);
                            mMall_rv.setVisibility(ViewGroup.GONE);
                        }
                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<BaoLieBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
        mMall_rv.setPullRefreshEnabled(false);
        mMall_rv.setLoadingMoreEnabled(true);
        mMall_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMall_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                gainData();
                mMall_rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                gainData2();
                mMall_rv.loadMoreComplete();
            }
        });
    }

    private void gainData2() {
        LoadingDialog.show(getContext());
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<BaoLieBean> baoLie = service.getBaoLie(mId, currentPager, 6, User.INSTANCE.getRegion());
        baoLie.enqueue(new Callback<BaoLieBean>() {
            @Override
            public void onResponse(Call<BaoLieBean> call, Response<BaoLieBean> response) {
                if (response.isSuccessful()) {
                    LoadingDialog.cancel();
                    BaoLieBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<BaoLieBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        if (rows.size() > 0) {
                            farmingInsuranceAdapter.addMoreData(rows);
                        } else {
                            mMall_rv.setNoMore(true);
                        }
                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<BaoLieBean> call, Throwable t) {

            }
        });
    }

    /*
   * adapter
   * */
    public class FarmingInsuranceAdapter extends RecyclerView.Adapter<FarmingInsuranceAdapter.Holder> {
        protected final String TAG = "FarmingInsuranceAdapter";

        private List<BaoLieBean.DataBeanX.RowsBean> list;

        public FarmingInsuranceAdapter(List<BaoLieBean.DataBeanX.RowsBean> list) {
            this.list = list;
        }

        public void addMoreData(List<BaoLieBean.DataBeanX.RowsBean> data) {
            if (data != null) {
                list.addAll(list.size(), data);
                notifyDataSetChanged();
            }
        }


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.farming_insurance_item, null));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            final BaoLieBean.DataBeanX.RowsBean rowsBean = list.get(position);

            Glide.with(getContext()).load(Constant.IMG_URL + rowsBean.getImg()).crossFade().placeholder(R.drawable.me_hard_danwei).error(R.drawable.me_hard_danwei).into(holder.img);
            holder.name.setText(rowsBean.getInsuranceName());
            holder.meimu_price.setText("保险金额:" + rowsBean.getAmount());
            holder.zeren.setText("保险责任:" + rowsBean.getResponsibility());

            if (rowsBean.getPrice() > 0) {
                holder.price.setText(new DecimalFormat("#0.00").format(rowsBean.getPrice()));
            } else {
                holder.price.setText("价格待定");
            }
            holder.price_danwei.setText("元/" + rowsBean.getUnits());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), FarmingInsuranceDetailsActivity.class);
                    intent.putExtra("id", rowsBean.getId());
                    intent.putExtra("price", "¥" + new DecimalFormat("#0.00").format(rowsBean.getPrice()) + "/" + rowsBean.getUnits());
                    intent.putExtra("title", rowsBean.getInsuranceName());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private final TextView name;
            private final ImageView img;
            private final TextView meimu_price;
            private final TextView zeren;
            private final TextView price;
            private final TextView price_danwei;

            public Holder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                img = itemView.findViewById(R.id.img);
                meimu_price = itemView.findViewById(R.id.meimu_price);
                zeren = itemView.findViewById(R.id.zeren);
                price = itemView.findViewById(R.id.price);
                price_danwei = itemView.findViewById(R.id.price_danwei);
            }
        }
    }


}
