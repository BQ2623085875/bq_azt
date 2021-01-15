package com.azhyun.massxj.activity.azt;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.NongYuZhiBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBeans;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * 我的积分
 * */
public class MyIntegralActivity extends BaseActivity {
    protected final String TAG = "MyIntegralActivity";

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.integral_nyz_tv)
    TextView integral_nyz_tv;
    @BindView(R.id.integral_dqjf_tv)
    TextView integral_dqjf_tv;
    @BindView(R.id.integral_dhnyz_tv)
    TextView integral_dhnyz_tv;
    @BindView(R.id.integral_xr)
    XRecyclerView integral_xr;
    @BindView(R.id.integral_dqjfxm_tv)
    TextView integral_dqjfxm_tv;
    @BindView(R.id.integral_jfjs_tv)
    TextView integral_jfjs_tv;
    @BindView(R.id.integral_nyzjf_tv)
    TextView integral_nyzjf_tv;
    @BindView(R.id.dq_tv)
    TextView dq_tv;

    private int type;
    private int mJiFenCode = 0;
    private ArrayList<NongYuZhiBean.CurrencyBean.DetailsBean> detailsBeans;
    private IntegralAdapter integralAdapter;
    private NongYuZhiBean.CurrencyBean currency;
    private TextView dilog_nyz_tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_integral;
    }

    @Override
    protected void initView() {
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

        type = getIntent().getIntExtra("type", 0);

        if (type == 0) {//积分
            title.setText("我的积分");
            dq_tv.setText("当前积分");
            integral_nyz_tv.setVisibility(ViewGroup.VISIBLE);
            integral_jfjs_tv.setVisibility(ViewGroup.VISIBLE);
            integral_dqjfxm_tv.setVisibility(ViewGroup.GONE);
            integral_nyzjf_tv.setVisibility(ViewGroup.GONE);
        } else {//农誉值
            title.setText("我的农誉值");
            dq_tv.setText("当前农誉值");
            integral_nyz_tv.setVisibility(ViewGroup.GONE);
            integral_jfjs_tv.setVisibility(ViewGroup.GONE);
            integral_dqjfxm_tv.setVisibility(ViewGroup.GONE);
            integral_nyzjf_tv.setVisibility(ViewGroup.GONE);
        }


        detailsBeans = new ArrayList<>();
        integralAdapter = new IntegralAdapter(detailsBeans);
        integral_xr.setAdapter(integralAdapter);
        integral_xr.setLayoutManager(new LinearLayoutManager(MyIntegralActivity.this));

    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        integral_nyz_tv.setOnClickListener(this);
        integral_dhnyz_tv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (type == 0) {
            jifenData();
        } else {
            nyzData();
        }
    }

    //获取积分
    private void jifenData() {
        LoadingDialog.show(MyIntegralActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NongYuZhiBean> nongYu = service.getJiFen(User.INSTANCE.getToken());
        nongYu.enqueue(new Callback<NongYuZhiBean>() {
            @Override
            public void onResponse(Call<NongYuZhiBean> call, Response<NongYuZhiBean> response) {
                LoadingDialog.cancel();
                NongYuZhiBean body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    currency = body.getCurrency();
//                    mJiFenCode = currency.getCredit();
                    double v = Double.parseDouble(currency.getCredit());
                    integral_dqjf_tv.setText(new DecimalFormat("#0.00").format(v) + "");
                    detailsBeans.clear();
                    detailsBeans.addAll(body.getCurrency().getDetails());
                    integralAdapter.notifyDataSetChanged();
                }
                ToastUtils.showToast(MyIntegralActivity.this, body.getResult().getMessage());
            }

            @Override
            public void onFailure(Call<NongYuZhiBean> call, Throwable t) {

            }
        });
    }

    //获取农誉值
    private void nyzData() {
        LoadingDialog.show(MyIntegralActivity.this);
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<NongYuZhiBean> nongYu = service.getNongYu(User.INSTANCE.getToken());
        nongYu.enqueue(new Callback<NongYuZhiBean>() {
            @Override
            public void onResponse(Call<NongYuZhiBean> call, Response<NongYuZhiBean> response) {
                LoadingDialog.cancel();
                NongYuZhiBean body = response.body();
                if (body.getResult().getCode().equals("200")) {
                    currency = body.getCurrency();
//                    mJiFenCode = currency.getCredit();
                    detailsBeans.clear();
                    detailsBeans.addAll(body.getCurrency().getDetails());
                    integralAdapter.notifyDataSetChanged();

                    integral_dqjfxm_tv.setText("当前积分 " + body.getCurrency().getCredit() + "");
                    integral_dqjf_tv.setText(body.getCurrency().getCurrency() + "");

                }
                ToastUtils.showToast(MyIntegralActivity.this, body.getResult().getMessage());
            }

            @Override
            public void onFailure(Call<NongYuZhiBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bank:
                finish();
                break;
            case R.id.integral_dhnyz_tv:
                if (mJiFenCode < 10) {
                    hintMethod();
                } else {
                    dhnyz();
                }
                break;
            case R.id.integral_nyz_tv:
                intent = new Intent(MyIntegralActivity.this, MyIntegralActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
        }
    }

    private void hintMethod() {
        final Dialog dialog = new Dialog(MyIntegralActivity.this, R.style.myNewsDialogStyle);
        // 自定义对话框布局
        View view = View.inflate(MyIntegralActivity.this, R.layout.jifen_tishi_item, null);
        TextView tv_yes = view.findViewById(R.id.tv_yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void dhnyz() {

        final Dialog dialog = new Dialog(MyIntegralActivity.this, R.style.myNewsDialogStyle);
        // 自定义对话框布局
        View view = View.inflate(MyIntegralActivity.this, R.layout.integrla_daligo_item, null);
        TextView dilog_yes_tv = view.findViewById(R.id.dilog_yes_tv);
        ImageView dilog_delete_img = view.findViewById(R.id.dilog_delete_img);
        dilog_nyz_tv = view.findViewById(R.id.dilog_nyz_tv);
        TextView dilog_dqjf = view.findViewById(R.id.dilog_dqjf);
        final EditText dilog_duihuan_ed = view.findViewById(R.id.dilog_duihuan_ed);

        dilog_dqjf.setText("当前积分   " + currency.getCredit() + "");

        dilog_delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dilog_yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String s = dilog_duihuan_ed.getText().toString();
//                if (s.toString().equals("")) {
//                    dilog_nyz_tv.setText("10");
//                } else {
//                    int str = Integer.parseInt(s.toString());
//                    if (str < 10) {
//                        dilog_nyz_tv.setText("10");
//                    } else {
//
//                    }
//                }
                if (dilog_nyz_tv.getText().toString().equals("0")) {
                    ToastUtils.showToast(MyIntegralActivity.this, "10个积分起兑");
                } else {
                    HttpService service = ServiceGenerator.createService(HttpService.class);
                    Call<ResultBeans> exchange = service.getExchange(dilog_duihuan_ed.getText().toString());
                    exchange.enqueue(new Callback<ResultBeans>() {
                        @Override
                        public void onResponse(Call<ResultBeans> call, Response<ResultBeans> response) {
                            ResultBeans body = response.body();
                            if (body.getResult().getCode().equals("200")) {
                                if (type == 0) {
                                    jifenData();
                                } else {
                                    nyzData();
                                }
                                dialog.dismiss();
                            }
                            ToastUtils.showToast(MyIntegralActivity.this, body.getResult().getMessage());
                        }

                        @Override
                        public void onFailure(Call<ResultBeans> call, Throwable t) {

                        }
                    });
                }
            }
        });

        dilog_duihuan_ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    dilog_nyz_tv.setText("0");
                } else {
                    int str = Integer.parseInt(s.toString());
                    if (str < 10) {
                        dilog_nyz_tv.setText("0");
                    } else {
                        if (str <= mJiFenCode) {
                            double floor = Math.floor(str / 10);
                            String v = String.valueOf(floor * 10);
                            String substring = v.substring(0, v.indexOf("."));
                            int i = Integer.parseInt(substring) / 10;
                            dilog_nyz_tv.setText(i + "");
                        } else {
                            double floor = Math.floor(mJiFenCode / 10);
                            String v = String.valueOf(floor * 10);
                            String substring = v.substring(0, v.indexOf("."));
                            int i = Integer.parseInt(substring) / 10;
                            dilog_nyz_tv.setText(i + "");
                            dilog_duihuan_ed.setText(substring);
                            ToastUtils.showToast(MyIntegralActivity.this, "可兑换积分最大值为" + i);
                        }
                    }
                }
            }
        });
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /*
     * 适配器
     * */
    public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.Holder> {
        private ArrayList<NongYuZhiBean.CurrencyBean.DetailsBean> list;

        public IntegralAdapter(ArrayList<NongYuZhiBean.CurrencyBean.DetailsBean> list) {
            this.list = list;
        }

        @Override
        public IntegralAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(MyIntegralActivity.this).inflate(R.layout.integral_item, null));
        }

        @Override
        public void onBindViewHolder(IntegralAdapter.Holder holder, int position) {
            NongYuZhiBean.CurrencyBean.DetailsBean detailsBean = list.get(position);

            holder.integral_item_name_tv.setText(detailsBean.getRemark());
            holder.integral_item_tiem_tv.setText(detailsBean.getAddTime());
            if (detailsBean.getType() == 1) {//加
                holder.integral_item_zhi_tv.setText(detailsBean.getDetail());
                holder.integral_item_zhi_tv.setTextColor(getResources().getColor(R.color.nongyuzhi_jia));
            } else {
                holder.integral_item_zhi_tv.setText(detailsBean.getDetail());
                holder.integral_item_zhi_tv.setTextColor(getResources().getColor(R.color.nongyuzhi_jian));
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private final TextView integral_item_name_tv;
            private final TextView integral_item_zhi_tv;
            private final TextView integral_item_tiem_tv;

            public Holder(View itemView) {
                super(itemView);
                integral_item_name_tv = itemView.findViewById(R.id.integral_item_name_tv);
                integral_item_zhi_tv = itemView.findViewById(R.id.integral_item_zhi_tv);
                integral_item_tiem_tv = itemView.findViewById(R.id.integral_item_tiem_tv);
            }
        }
    }

}
