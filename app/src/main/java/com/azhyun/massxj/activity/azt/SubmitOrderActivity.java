package com.azhyun.massxj.activity.azt;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.BaseActivity;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.bean.aizhongtian.CommodityDetailsBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBean;
import com.azhyun.massxj.bean.aizhongtian.SubmitBean;
import com.azhyun.massxj.utils.Constant;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.LoadingDialog;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.utils.azt.SoftKeyBoardListener;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* 提交订单
* */
public class SubmitOrderActivity extends BaseActivity {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.submit_shuliang)
    EditText submit_shuliang;
    @BindView(R.id.submit_address)
    TextView submit_address;
    @BindView(R.id.submit_user_name)
    TextView submit_user_name;
    @BindView(R.id.submit_user_phone)
    TextView submit_user_phone;
    @BindView(R.id.submit_compaye)
    TextView submit_compaye;
    @BindView(R.id.submit_commodity_name)
    TextView submit_commodity_name;
    @BindView(R.id.submit_commodity_guige)
    TextView submit_commodity_guige;
    @BindView(R.id.submit_commodity_price)
    TextView submit_commodity_price;
    @BindView(R.id.submit_commodity_jine)
    TextView submit_commodity_jine;
    @BindView(R.id.submit_commodity_yunfei)
    TextView submit_commodity_yunfei;
    @BindView(R.id.submit_heji)
    TextView submit_heji;
    @BindView(R.id.submit_bott_heji)
    TextView submit_bott_heji;
    @BindView(R.id.submit_tijiao)
    RelativeLayout submit_tijiao;
    @BindView(R.id.submit_commodity_jianhao)
    LinearLayout submit_commodity_jianhao;
    @BindView(R.id.submit_commodity_jiahao)
    LinearLayout submit_commodity_jiahao;
    @BindView(R.id.submit_commodity_img)
    ImageView submit_commodity_img;
    @BindView(R.id.gongjan)
    TextView gongjan;
    @BindView(R.id.danwei)
    TextView danwei;

    private String qty;
    private String phone;
    private String fullName;
    private String name;
    private String userId;
    private String mAmount = "1";

    private List<SubmitBean.DataBeanX> orderData;

    private CommodityDetailsBean.DataBeanX data;
    private int guigeid;
    private int type;
    private int zuixiaoshuliang;
    private String supplierId;
    private String supplierName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_order;
    }

    @Override
    protected void initView() {
        title.setText("提交订单");
        title.setTextSize(18);
        title.setTextColor(getResources().getColor(R.color.black));

        qty = getIntent().getStringExtra("qty");
        guigeid = getIntent().getIntExtra("guigeid", 0);
        type = getIntent().getIntExtra("type", 0);
        zuixiaoshuliang = getIntent().getIntExtra("zuixiaoshuliang", 0);
        data = (CommodityDetailsBean.DataBeanX) getIntent().getSerializableExtra("data");
        supplierId = getIntent().getStringExtra("supplierId");
        supplierName = getIntent().getStringExtra("SupplierName");

        submit_shuliang.setText(qty);

        phone = (String) SpUtils.get("phone", "");
        fullName = (String) SpUtils.get("fullName", "");
        userId = (String) SpUtils.get("userId", "");
        name = (String) SpUtils.get("name", "");


        submit_shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                qty = s.toString();
                if (s.toString().startsWith("0") || s.toString().equals("")) {
                    submit_shuliang.setText("1");
                    submit_shuliang.setSelection(1);
                }
            }
        });

        SoftKeyBoardListener.setListener(SubmitOrderActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {//键盘显示
            }

            @Override
            public void keyBoardHide(int height) {//键盘隐藏
                gainData();
            }
        });


    }

    @Override
    protected void initListener() {
        bank.setOnClickListener(this);
        submit_tijiao.setOnClickListener(this);
        submit_commodity_jianhao.setOnClickListener(this);
        submit_commodity_jiahao.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        LoadingDialog.show(SubmitOrderActivity.this);

        HashMap<String, Object> dicStrMap = new HashMap<>();
        HashMap<String, Object> NcompanyIdMap = new HashMap<>();
        HashMap<String, Object> paramsMap = new HashMap<>();
        HashMap<String, Object> guigeidMap = new HashMap<>();
        HashMap<String, Object> idMap = new HashMap<>();

        if (type == 0) {
            idMap.put("id", data.getSkus().get(0).getId() + "");
            idMap.put("qty", qty);
            guigeidMap.put(data.getSkus().get(0).getId() + "", idMap);
        } else {
            idMap.put("id", guigeid + "");
            idMap.put("qty", qty);
            guigeidMap.put(guigeid + "", idMap);
        }

        paramsMap.put("clientId", userId);
        paramsMap.put("companyId", supplierId);
        paramsMap.put("region", User.INSTANCE.getRegion());
        paramsMap.put("items", guigeidMap);
        NcompanyIdMap.put("N" + supplierId, paramsMap);
        dicStrMap.put("dicStr", NcompanyIdMap);

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<SubmitBean> submit = service.submit(dicStrMap);
        submit.enqueue(new Callback<SubmitBean>() {
            @Override
            public void onResponse(Call<SubmitBean> call, Response<SubmitBean> response) {
                if (response.isSuccessful()) {
                    SubmitBean body1 = response.body();
                    if (body1.getResult().getCode().equals("200")) {
                        LoadingDialog.cancel();
                        orderData = body1.getData();
                        List<SubmitBean.DataBeanX.ItemListBean> itemList = orderData.get(0).getItemList();
                        gongjan.setText("共计" + orderData.get(0).getItemList().get(0).getQty() + "件商品");
//                        danwei.setText("/" + orderData.get(0).getItemList().get(0).getUnits());
                        submit_address.setText(fullName);
                        submit_user_phone.setText(phone);
                        submit_user_name.setText(name);
                        submit_compaye.setText(itemList.get(0).getCompanyName());
                        submit_commodity_name.setText(itemList.get(0).getBrand() + itemList.get(0).getItemName());
                        submit_commodity_guige.setText(itemList.get(0).getNorm() + "/" + orderData.get(0).getItemList().get(0).getUnits());
                        submit_commodity_jine.setText("￥" + new DecimalFormat("#0.00").format(orderData.get(0).getOrderAmt()));
                        submit_commodity_price.setText("￥" + new DecimalFormat("#0.00").format(itemList.get(0).getRealityPrice()));
                        submit_shuliang.setText(itemList.get(0).getQty() + "");
                        submit_commodity_yunfei.setText("￥" + new DecimalFormat("#0.00").format(orderData.get(0).getFreight()));
                        submit_heji.setText("￥" + new DecimalFormat("#0.00").format(orderData.get(0).getOrderAmt()));
                        submit_bott_heji.setText("￥" + new DecimalFormat("#0.00").format(orderData.get(0).getOrderAmt()));
                        Glide.with(SubmitOrderActivity.this).load(Constant.IMG_URL + orderData.get(0).getItemList().get(0).getImg()).into(submit_commodity_img);
                    } else {
                        ToastUtils.showToast(SubmitOrderActivity.this, body1.getResult().getMessage());
                        LoadingDialog.cancel();
                    }
                } else {
                    LoadingDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<SubmitBean> call, Throwable t) {
                LoadingDialog.cancel();
            }
        });
    }

    /*
    * 提交订单
    * */
    private void submitOrder() {
        int s = Integer.parseInt(submit_shuliang.getText().toString());


        HashMap<String, Object> commguigeidMap = new HashMap<>();
        HashMap<String, Object> itemsMap = new HashMap<>();
        HashMap<String, Object> NcompanyIdMap = new HashMap<>();
        HashMap<String, Object> itemCartsMap = new HashMap<>();
        HashMap<String, Object> dicStrMap = new HashMap<>();
        HashMap<String, Object> Map = new HashMap<>();
        HashMap<String, Object> M = new HashMap<>();

        if (type == 0) {
            Map.put("id", orderData.get(0).getItemList().get(0).getSkuId());
            Map.put("qty", s);
            commguigeidMap.put(data.getSkus().get(0).getId() + "", Map);
        } else {
            Map.put("id", guigeid);
            Map.put("qty", s);
            commguigeidMap.put(guigeid + "", Map);
        }

        itemsMap.put("items", commguigeidMap);
        NcompanyIdMap.put("clientId", userId);
        NcompanyIdMap.put("companyId", supplierId);
        NcompanyIdMap.put("region", User.INSTANCE.getRegion());
        NcompanyIdMap.put("items", commguigeidMap);
        M.put("N" + supplierId, NcompanyIdMap);


        itemCartsMap.put("buyer", userId);
        itemCartsMap.put("itemCarts", M);

//        dicStrMap.put("buyer", userId);

        dicStrMap.put("dicStr", itemCartsMap);

        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ResultBean> tijiaoorder = service.tijiaoorder(dicStrMap);
        tijiaoorder.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {
                if (response.isSuccessful()) {
                    ResultBean body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        Intent intent = new Intent(SubmitOrderActivity.this, OrderPayActivity.class);
                        intent.putExtra("payNumber", body.getPayNumber());
                        intent.putExtra("heji",submit_bott_heji.getText().toString().replace("￥", ""));
                        startActivity(intent);
                        finish();

//                        Intent intent = new Intent(SubmitOrderActivity.this, OrderActivity.class);
//                        intent.putExtra("status", 3);
//                        intent.putExtra("distinguish", "order");
//                        startActivity(intent);
//                        finish();
//                        ToastUtils.showToast(SubmitOrderActivity.this, "提交成功!");
                    } else {
                        ToastUtils.showToast(SubmitOrderActivity.this, body.getResult().getMessage());
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void otherViewClick(View view) {
        int equal = 1;
        int amount = Integer.parseInt(submit_shuliang.getText().toString());
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bank:
                onBackPressed();
                break;
            case R.id.submit_tijiao://提交订单
                int s = Integer.parseInt(submit_shuliang.getText().toString());
                if (zuixiaoshuliang <= s) {
                    submitOrder();
                } else {
                    ToastUtils.showToast(SubmitOrderActivity.this, "最小起订量为" + zuixiaoshuliang);
                }
                break;
            case R.id.submit_commodity_jianhao:
                if (amount > 1) {
                    if (amount > zuixiaoshuliang) {
                        equal = amount - 1;
                        qty = equal + "";
                        gainData();
                    } else {
                        ToastUtils.showToast(SubmitOrderActivity.this, "最小起订量为" + zuixiaoshuliang);
                    }
                } else {
                    ToastUtils.showToast(SubmitOrderActivity.this, "不能再减了！");
                }
                break;
            case R.id.submit_commodity_jiahao:
                if (amount > 0) {
                    if (equal < 999) {
                        equal = amount + 1;
                        qty = equal + "";
                        gainData();
                    } else {
                        ToastUtils.showToast(SubmitOrderActivity.this, "最大起订量为999！");
                    }
                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
