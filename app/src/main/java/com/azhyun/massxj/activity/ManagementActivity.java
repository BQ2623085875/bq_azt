package com.azhyun.massxj.activity;

import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.ManagementAdapter;
import com.azhyun.massxj.bean.MyManageListResult;
import com.azhyun.massxj.bean.RegionResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.listener.OnRecyclerViewItemClickListener;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.RegionPopupWindowUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.StringUtils;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.RecycleViewDivider;
import com.example.zhouwei.library.CustomPopWindow;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementActivity extends BaseActivity {
    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_county)
    TextView tvCounty;
    @BindView(R.id.line_county)
    AutoLinearLayout lineCounty;
    @BindView(R.id.tv_township)
    TextView tvTownship;
    @BindView(R.id.line_township)
    AutoLinearLayout lineTownship;
    @BindView(R.id.tv_village)
    TextView tvVillage;
    @BindView(R.id.line_village)
    AutoLinearLayout lineVillage;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout mEmpty;
    @BindView(R.id.tv_agent_num)
    TextView tvAgentNum;
    private List<RegionResult.Data> dataList = new ArrayList<>();
    private CustomPopWindow mListPopWindow;
    private View contentView;
    private String region;
    private int page = 1;
    private int pagerow = 10;
    private List<MyManageListResult.Data.Rows> rowsList = new ArrayList<>();
    private String xianId = "";
    private String xiangId = "";
    private String cunId = "";

    @Override
    protected void initData() {
        title.setText("我管理的");
        String region = (String) SpUtils.get("region", "");

        //获取数据
        getData();
    }

    private void getData() {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyManageListResult> myManageList = service.getMyManageList(page, pagerow, User.INSTANCE.getToken(), region);
        myManageList.enqueue(new Callback<MyManageListResult>() {
            @Override
            public void onResponse(Call<MyManageListResult> call, Response<MyManageListResult> response) {
                if (response.isSuccessful()) {
                    MyManageListResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        SpUtils.put("JSESSIONID", body.getData().getJSESSIONID());
                        if (page == 1) {
                            rowsList.clear();
                            recyclerView.refreshComplete();
                        } else {
                            recyclerView.loadMoreComplete();
                        }
//                        xianId = body.getData().getRegion();
//                        region = body.getData().getRegion();

                        rowsList.addAll(body.getData().getRows());
                        recyclerView.getAdapter().notifyDataSetChanged();
                        tvArea.setText("土地托管总面积:"+ StringUtils.stringDouble(body.getData().getAreaSum())+"亩");
                        tvAgentNum.setText("经纪人总人数:"+body.getData().getTotalrows()+"人");
                    } else {
                        ToastUtils.showToast(ManagementActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyManageListResult> call, Throwable t) {
                Log.e("----->>", t.getMessage());
            }
        });
    }

    /**
     * 选择区域
     *
     * @param region1
     * @param
     */
    private void getRegion(final String region1, final TextView textView) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<RegionResult> regionCall = service.getRegion(region1);
        regionCall.enqueue(new Callback<RegionResult>() {
            @Override
            public void onResponse(Call<RegionResult> call, Response<RegionResult> response) {
                if (response.isSuccessful()) {
                    RegionResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        dataList.clear();
                        List<RegionResult.Data> data = body.getData();

                        dataList = data;
                        RegionResult.Data data1 = new RegionResult.Data("", "", "全部");
                        dataList.add(0, data1);

                        RegionPopupWindowUtils.show(ManagementActivity.this, textView, dataList);
                        RegionPopupWindowUtils.onClik(new RegionPopupWindowUtils.onClikListener() {
                            @Override
                            public void onClick(View v, int position) {

                                if (region1.length() == 2) {
                                    xiangId = dataList.get(position).getId();
                                    tvTownship.setText("县");
                                    tvVillage.setText("乡");

                                } else if (region1.length() == 4) {
                                    cunId = dataList.get(position).getId();
                                    tvVillage.setText("乡");
                                }
                                textView.setText(dataList.get(position).getName());
                                if (!dataList.get(position).getName().equals("全部")) {
                                    region = dataList.get(position).getId();
                                }
                                if (tvCounty.getText().toString().equals("全部")) {
                                    region = (String) SpUtils.get("region", "");
                                } else if (tvTownship.getText().toString().equals("全部")) {
                                    region = region1;
                                } else if (tvVillage.getText().toString().equals("全部")) {
                                    region = region1;
                                }
                                getData();

                            }
                        });


                    } else {
                        ToastUtils.showToast(ManagementActivity.this, body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        lineCounty.setOnClickListener(this);
        lineTownship.setOnClickListener(this);
        lineVillage.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setEmptyView(mEmpty);
        recyclerView.addItemDecoration(new RecycleViewDivider(this, DividerItemDecoration.HORIZONTAL, 10, Color.parseColor("#eeeeee")));

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                Log.e("----->","11");
                page++;
                getData();
            }
        });
        ManagementAdapter managementAdapter = new ManagementAdapter(rowsList);
        managementAdapter.onClik(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                Intent intent = new Intent(ManagementActivity.this,ManagementDetailsActivity.class);
//                intent.putExtra("id",rowsList.get(postion).getUserId());
//                startActivity(intent);
            }
        });
        recyclerView.setAdapter(managementAdapter);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_management;
    }

    @Override
    protected void otherViewClick(View view) {
        String region11 = (String) SpUtils.get("region", "");
        switch (view.getId()) {
            case R.id.bank:
                fund();
                break;
            case R.id.line_county:

                if (region11.length() == 2) {
                    page = 1;
                    xianId = region11;
                    getRegion(xianId, tvCounty);
                    xiangId = "";
                }

                break;
            case R.id.line_township:

                if (tvCounty.getText().toString().equals("全部")) {
                    ToastUtils.showToast(ManagementActivity.this, "已是全部信息");
                    break;
                } else if (xiangId.length() == 4 || region11.length() == 4) {

                    if (region11.length() == 6) {
                        xiangId = region11;
                    }
                    page = 1;
                    getRegion(xiangId, tvTownship);

                    cunId = "";

                } else if (region11.length() == 2) {
                    ToastUtils.showToast(ManagementActivity.this, "请先选择市");
                }
                break;
            case R.id.line_village:
                if (tvTownship.getText().toString().equals("全部")) {
                    ToastUtils.showToast(ManagementActivity.this, "已是全部信息");
                    break;
                } else if (cunId.length() == 6 || region11.length() == 6) {
                    if (region11.length() == 6) {
                        cunId = region11;
                    }
                    page = 1;
                    getRegion(cunId, tvVillage);
                } else if (region11.length() == 2) {
                    ToastUtils.showToast(ManagementActivity.this, "请先选择县");
                }
                break;
        }
    }

}
