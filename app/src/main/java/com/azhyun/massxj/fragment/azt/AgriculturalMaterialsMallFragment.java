package com.azhyun.massxj.fragment.azt;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.azt.CommodityDetailsActivity;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.MallShangPinBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.JSONTool;
import com.azhyun.massxj.view.LazyLoadFragment;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.azhyun.massxj.utils.LoadingDialog.cancel;

/**
 * A simple {@link Fragment} subclass.
 * 农资商城 Fragment
 */
@SuppressLint("ValidFragment")
public class AgriculturalMaterialsMallFragment extends LazyLoadFragment {

    @BindView(R.id.mall_rv)
    XRecyclerView mMall_rv;
    @BindView(R.id.wushuju)
    TextView wushuju;

    private int mId;
    private int currentPager = 1;

    private AgriculturalMaterialsMallFragmentAdapter agriculturalMaterialsMallFragmentAdapter;

    @SuppressLint("ValidFragment")
    public AgriculturalMaterialsMallFragment(int id) {
        mId = id;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_agricultural_materials_mall;
    }

    @Override
    protected void lazyLoad() {
        initView();
        gainData();
    }

    private void initView() {
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

    }

    private void gainData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MallShangPinBean> mallshang = service.mallshang(mId, currentPager, 6, User.INSTANCE.getRegion());
        mallshang.enqueue(new Callback<MallShangPinBean>() {
            @Override
            public void onResponse(Call<MallShangPinBean> call, Response<MallShangPinBean> response) {
                if (response.isSuccessful()) {
                    MallShangPinBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<MallShangPinBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        Log.d("nnnnnnn", "onResponse: " + JSONTool.format(new Gson().toJson(response.body())));

                        if (rows.size() > 0) {
                            wushuju.setVisibility(ViewGroup.GONE);
                            mMall_rv.setVisibility(ViewGroup.VISIBLE);
                            agriculturalMaterialsMallFragmentAdapter = new AgriculturalMaterialsMallFragmentAdapter(rows);
                            mMall_rv.setAdapter(agriculturalMaterialsMallFragmentAdapter);
                        } else {
                            wushuju.setVisibility(ViewGroup.VISIBLE);
                            mMall_rv.setVisibility(ViewGroup.GONE);
                        }

                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<MallShangPinBean> call, Throwable t) {
                cancel();
            }
        });
    }


    private void gainData2() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MallShangPinBean> mallshang = service.mallshang(mId, currentPager, 6, User.INSTANCE.getRegion());
        mallshang.enqueue(new Callback<MallShangPinBean>() {
            @Override
            public void onResponse(Call<MallShangPinBean> call, Response<MallShangPinBean> response) {
                if (response.isSuccessful()) {
                    MallShangPinBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        List<MallShangPinBean.DataBeanX.RowsBean> rows = body.getData().getRows();
                        if (rows.size() > 0) {
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
            public void onFailure(Call<MallShangPinBean> call, Throwable t) {
                cancel();
            }
        });
    }


    /*
    * adapter
    * */
    public class AgriculturalMaterialsMallFragmentAdapter extends RecyclerView.Adapter<AgriculturalMaterialsMallFragmentAdapter.Holder> {

        private List<MallShangPinBean.DataBeanX.RowsBean> list;

        public AgriculturalMaterialsMallFragmentAdapter(List<MallShangPinBean.DataBeanX.RowsBean> list) {
            this.list = list;
        }

        public void addMoreData(List<MallShangPinBean.DataBeanX.RowsBean> data) {
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

            final MallShangPinBean.DataBeanX.RowsBean rowsBean = list.get(position);

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
                    Intent intent = new Intent(getContext(), CommodityDetailsActivity.class);
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

}
