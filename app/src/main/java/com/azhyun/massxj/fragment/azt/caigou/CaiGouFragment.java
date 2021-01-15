package com.azhyun.massxj.fragment.azt.caigou;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.azhyun.massxj.activity.azt.CaiPurchaseActivity;
import com.azhyun.massxj.activity.azt.caigou.CaiGouCommodityDetailsActivity;
import com.azhyun.massxj.bean.aizhongtian.CaiBean;
import com.azhyun.massxj.bean.aizhongtian.CaiGouQiYeBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
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
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CaiGouFragment extends LazyLoadFragment {

    @BindView(R.id.rv_purchase)
    XRecyclerView mMall_rv;
    @BindView(R.id.wsj)
    TextView wsj;

    private String region;
    private int currentPager = 1;
    private int qiyecurrentPager = 1;
    private AgriculturalMaterialsMallFragmentAdapter agriculturalMaterialsMallFragmentAdapter;
    private int mInteger = 0;
    private QiYeAdapter qiYeAdapter;

    @SuppressLint("ValidFragment")
    public CaiGouFragment(Integer integer) {
        // Required empty public constructor
        mInteger = integer;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_cai_gou;
    }

    private void initView() {

        if (mInteger == 0) {
            mMall_rv.setPullRefreshEnabled(false);
            mMall_rv.setLoadingMoreEnabled(true);
            mMall_rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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
        } else {
            mMall_rv.setPullRefreshEnabled(false);
            mMall_rv.setLoadingMoreEnabled(true);
            mMall_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            mMall_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    qiyecurrentPager = 1;
                    gainDataQiye();
                    mMall_rv.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    ++qiyecurrentPager;
                    gainDataQiye2();
                    mMall_rv.loadMoreComplete();
                }
            });
        }


    }


    @Override
    protected void lazyLoad() {
        initView();
        if (mInteger == 0) {
            gainData();
        } else {
            gainDataQiye();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    //企业名录
    private void gainDataQiye() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<CaiGouQiYeBean> caiCompay = service.getCaiCompay(qiyecurrentPager, 10);
        caiCompay.enqueue(new Callback<CaiGouQiYeBean>() {
            @Override
            public void onResponse(Call<CaiGouQiYeBean> call, Response<CaiGouQiYeBean> response) {
                if (response.isSuccessful()) {
                    CaiGouQiYeBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<CaiGouQiYeBean.DataBeanX.RowsBean> data = body.getData().getRows();
                        Log.d("api/har/companyLists", "onResponse: " + data.size());
                        if (data.size() > 0) {
                            wsj.setVisibility(ViewGroup.GONE);
                            mMall_rv.setVisibility(ViewGroup.VISIBLE);
                            qiYeAdapter = new QiYeAdapter(data);
                            mMall_rv.setAdapter(qiYeAdapter);
                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            mMall_rv.setVisibility(ViewGroup.GONE);
                        }

                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<CaiGouQiYeBean> call, Throwable t) {

            }
        });
    }

    private void gainData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<CaiBean> caiGou = service.getCaiGou(currentPager, 6, region);
        caiGou.enqueue(new Callback<CaiBean>() {
            @Override
            public void onResponse(Call<CaiBean> call, Response<CaiBean> response) {
                if (response.isSuccessful()) {
                    CaiBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        CaiBean.DataBeanX data = body.getData();
                        List<CaiBean.DataBeanX.RowsBean> rows = data.getRows();
                        if (rows.size() > 0) {
                            wsj.setVisibility(ViewGroup.GONE);
                            mMall_rv.setVisibility(ViewGroup.VISIBLE);
                            agriculturalMaterialsMallFragmentAdapter = new AgriculturalMaterialsMallFragmentAdapter(rows);
                            mMall_rv.setAdapter(agriculturalMaterialsMallFragmentAdapter);
                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            mMall_rv.setVisibility(ViewGroup.GONE);
                        }

                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<CaiBean> call, Throwable t) {

            }
        });
    }

    private void gainData2() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<CaiBean> caiGou = service.getCaiGou(currentPager, 6, region);
        caiGou.enqueue(new Callback<CaiBean>() {
            @Override
            public void onResponse(Call<CaiBean> call, Response<CaiBean> response) {
                if (response.isSuccessful()) {
                    CaiBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        CaiBean.DataBeanX data = body.getData();
                        if (data.getRows().size() > 0) {
                            List<CaiBean.DataBeanX.RowsBean> rows = data.getRows();
                            agriculturalMaterialsMallFragmentAdapter.addMoreData(rows);
                        } else {
                            mMall_rv.setNoMore(true);
                        }

                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<CaiBean> call, Throwable t) {

            }
        });
    }

    private void gainDataQiye2() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<CaiGouQiYeBean> caiCompay = service.getCaiCompay(qiyecurrentPager, 10);
        caiCompay.enqueue(new Callback<CaiGouQiYeBean>() {
            @Override
            public void onResponse(Call<CaiGouQiYeBean> call, Response<CaiGouQiYeBean> response) {
                if (response.isSuccessful()) {
                    CaiGouQiYeBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<CaiGouQiYeBean.DataBeanX.RowsBean> data = body.getData().getRows();
                        if (data.size() > 0) {
                            qiYeAdapter.addMoreData(data);
                        } else {
                            mMall_rv.setNoMore(true);
                        }

                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<CaiGouQiYeBean> call, Throwable t) {

            }
        });
    }


    /*
  * 采购数据adapter
  * */
    public class AgriculturalMaterialsMallFragmentAdapter extends RecyclerView.Adapter<AgriculturalMaterialsMallFragmentAdapter.Holder> {

        private List<CaiBean.DataBeanX.RowsBean> list;

        public AgriculturalMaterialsMallFragmentAdapter(List<CaiBean.DataBeanX.RowsBean> list) {
            this.list = list;
        }

        public void addMoreData(List<CaiBean.DataBeanX.RowsBean> data) {
            if (data != null) {
                list.addAll(list.size(), data);
                notifyDataSetChanged();
            }
        }


        @Override
        public AgriculturalMaterialsMallFragmentAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.fragment_agricultural_materials_mall_adapter_item, null));
        }

        @Override
        public void onBindViewHolder(AgriculturalMaterialsMallFragmentAdapter.Holder holder, final int position) {

            final CaiBean.DataBeanX.RowsBean rowsBean = list.get(position);

            holder.mall_item_name.setText("【" + rowsBean.getBrand() + "】" + rowsBean.getOnlineTitle());
            if (rowsBean.getPrice() > 0) {
                holder.mall_item_price.setText("¥" + new DecimalFormat("#0.00").format(rowsBean.getPrice()));
            } else {
                holder.mall_item_price.setText("¥0.00");
            }
            Glide.with(getContext()).load(Constant.IMG_URL + rowsBean.getDefaultImg()).into(holder.mall_item_img);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CaiGouCommodityDetailsActivity.class);
                    intent.putExtra("commodityid", rowsBean.getId());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private final ImageView mall_item_img;
            private final TextView mall_item_name;
            private final TextView mall_item_price;

            public Holder(View itemView) {
                super(itemView);
                mall_item_img = itemView.findViewById(R.id.mall_item_img);
                mall_item_name = itemView.findViewById(R.id.mall_item_name);
                mall_item_price = itemView.findViewById(R.id.mall_item_price);
            }
        }
    }

    /*
      * 企业名录adapter
      * */
    public class QiYeAdapter extends RecyclerView.Adapter<QiYeAdapter.Holder> {

        private List<CaiGouQiYeBean.DataBeanX.RowsBean> list;

        public QiYeAdapter(List<CaiGouQiYeBean.DataBeanX.RowsBean> list) {
            this.list = list;
        }

        public void addMoreData(List<CaiGouQiYeBean.DataBeanX.RowsBean> data) {
            if (data != null) {
                list.addAll(list.size(), data);
                notifyDataSetChanged();
            }
        }


        @Override
        public QiYeAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.caigou_qiye_item, null));
        }

        @Override
        public void onBindViewHolder(QiYeAdapter.Holder holder, final int position) {

            CaiGouQiYeBean.DataBeanX.RowsBean dataBeanX = list.get(position);
            holder.compayname.setText(dataBeanX.getName());
            holder.address.setText("地址:" + dataBeanX.getAddress());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private final TextView compayname;
            private final TextView address;

            public Holder(View itemView) {
                super(itemView);
                compayname = itemView.findViewById(R.id.compayname);
                address = itemView.findViewById(R.id.address);
            }
        }
    }


}
