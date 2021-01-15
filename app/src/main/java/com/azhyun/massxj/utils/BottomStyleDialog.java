package com.azhyun.massxj.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.adapter.StyleAdapter;
import com.azhyun.massxj.bean.DiaLogItemResult;
import com.azhyun.massxj.bean.User;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class BottomStyleDialog extends Dialog {
    TextView textProvince;//省
    TextView textCity;//市
    TextView textDistrict;//区
    TextView textCountryside;//乡/镇
    TextView textVillage;//村

    RecyclerView recyclerViewProvince;//省
    RecyclerView recyclerViewCity;//市
    RecyclerView recyclerDistrict;//区
    RecyclerView recyclerCountryside;//乡/镇
    RecyclerView recyclerVillage;//村

    public List<DiaLogItemResult.Data> mListProvince;//省
    public List<DiaLogItemResult.Data> mListCity;//市
    public List<DiaLogItemResult.Data> mListDistrict;//区
    public List<DiaLogItemResult.Data> mListCountryside;//乡/镇
    public List<DiaLogItemResult.Data> mListVillage;//村

    public StyleAdapter mAdapter;
    public DiaLogItemResult diaLogItemResult;


    public TextView textConfirmOk;
    public TextView textConfirmNo;


    public int in;

    public OnTestListening onTestListening;
    private String string ="";
    private String city ="";
    private String province ="";
    private String district ="";
    private String countryside ="";
    private String village = "";

    public BottomStyleDialog(Context context, OnTestListening onTestListening) {
        // 在构造方法里, 传入主题
        super(context, R.style.BottomDialogStyle);
        this.onTestListening = onTestListening;
        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);

    }


    private interface httpServes {
        @FormUrlEncoded
        @POST("api/region")
        Call<DiaLogItemResult> itemList(
                @Field("limit") int limit,
                @Field("parentId") String parentId
        );
    }

    public void dialog(String parentId, final int in) {
        httpServes httpserves = ServiceGenerator.createService(httpServes.class);
        Call<DiaLogItemResult> itemListCall = httpserves.itemList(0, parentId);
        itemListCall.enqueue(new Callback<DiaLogItemResult>() {
            @Override
            public void onResponse(Call<DiaLogItemResult> call, Response<DiaLogItemResult> response) {
                if (response.isSuccessful()) {
                    diaLogItemResult = response.body();
                    if (diaLogItemResult.getResult().getCode().equals("200")) {
                        if (in == 1) {
                            mListProvince = diaLogItemResult.getData();
                            sheng();
                        } else if (in == 2) {
                            mListCity = diaLogItemResult.getData();
                            shi();
                        } else if (in == 3) {
                            mListDistrict = diaLogItemResult.getData();
                            qu();
                        } else if (in == 4) {
                            mListCountryside = diaLogItemResult.getData();
                            xiang();
                        } else if (in == 5) {
                            mListVillage = diaLogItemResult.getData();
                            zhen();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DiaLogItemResult> call, Throwable t) {
                ToastUtils.showToast(getContext(), diaLogItemResult.getResult().getMessage());
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        initview();
        textProvince.setText("");
//        textCity.setText("");
//        textDistrict.setText("");
//        textCountryside.setText("");
//        textVillage.setText("");
        dialog(User.INSTANCE.getStoreId(), 1);
    }

    public void initview() {
        textProvince = (TextView) findViewById(R.id.text_province);
//        textCity = (TextView) findViewById(R.id.text_city);
//        textDistrict = (TextView) findViewById(R.id.text_district);
//        textCountryside = (TextView) findViewById(R.id.text_countryside);
//        textVillage = (TextView) findViewById(R.id.text_town);
        recyclerViewProvince = (RecyclerView) findViewById(R.id.recuclerview1);
        recyclerViewCity = (RecyclerView) findViewById(R.id.recuclerview2);
        recyclerDistrict = (RecyclerView) findViewById(R.id.recuclerview3);
        recyclerCountryside = (RecyclerView) findViewById(R.id.recuclerview4);
        recyclerVillage = (RecyclerView) findViewById(R.id.recuclerview5);
        textConfirmOk = (TextView) findViewById(R.id.text_confirm_ok);
        textConfirmNo = (TextView) findViewById(R.id.text_confirm_no);
        textConfirmOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Province = textProvince.getText().toString();
//                String City = textCity.getText().toString();
//                String District = textDistrict.getText().toString();
//                String Countryside = textCountryside.getText().toString();
//                String Village = textVillage.getText().toString();
                StringBuffer temp = new StringBuffer();
                temp.append(Province);
//                temp.append(City);
//                temp.append(District);
//                temp.append(Countryside);
//                temp.append(Village);
                onTestListening.getTopicID(temp.toString());
                dismiss();
            }
        });
        textConfirmNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void sheng() {
        mAdapter = new StyleAdapter(mListProvince, getContext());
        recyclerViewProvince.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProvince.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new StyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                textCity.setText("");
//                textDistrict.setText("");
//                textCountryside.setText("");
//                textVillage.setText("");
                province = mListProvince.get(position).getName().toString();
                textProvince.setText(province);

                dialog(mListProvince.get(position).getId(), 2);
            }
        });
    }

    public void shi() {
        if (isEmpty(mListDistrict) == false) {
            mListDistrict.clear();
            recyclerDistrict.removeAllViews();
        }
        if (isEmpty(mListCountryside) == false) {
            mListCountryside.clear();
            recyclerCountryside.removeAllViews();
        }
        if (isEmpty(mListVillage) == false) {
            mListVillage.clear();
            recyclerVillage.removeAllViews();
        }
        recyclerDistrict.removeAllViews();
        recyclerCountryside.removeAllViews();
        recyclerVillage.removeAllViews();
        mAdapter = new StyleAdapter(mListCity, getContext());
        recyclerViewCity.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCity.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new StyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                textDistrict.setText("");
//                textCountryside.setText("");
//                textVillage.setText("");
//                textCity.setText();
                city = mListCity.get(position).getName().toString();
                textProvince.setText(province+city);
                dialog(mListCity.get(position).getId(), 3);
            }
        });
    }

    public void qu() {
        if (isEmpty(mListCountryside) == false) {
            mListCountryside.clear();
            recyclerCountryside.removeAllViews();
        }
        if (isEmpty(mListVillage) == false) {
            mListVillage.clear();
            recyclerVillage.removeAllViews();
        }
        mAdapter = new StyleAdapter(mListDistrict, getContext());
        recyclerDistrict.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDistrict.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new StyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                textCountryside.setText("");
//                textVillage.setText("");
//                textDistrict.setText(mListDistrict.get(position).getName().toString());
                 district = mListDistrict.get(position).getName().toString();
                textProvince.setText(province+city+district);
                dialog(mListDistrict.get(position).getId(), 4);
            }
        });
    }

    public void xiang() {
        if (isEmpty(mListVillage) == false) {
            mListVillage.clear();
            recyclerVillage.removeAllViews();
        }
        mAdapter = new StyleAdapter(mListCountryside, getContext());
        recyclerCountryside.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCountryside.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new StyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                textVillage.setText("");
//                textCountryside.setText(mListCountryside.get(position).getName().toString());
                countryside = mListCountryside.get(position).getName().toString();
                textProvince.setText(province+city+district+countryside);
                dialog(mListCountryside.get(position).getId(), 5);
            }
        });
    }

    public void zhen() {
        mAdapter = new StyleAdapter(mListVillage, getContext());
        recyclerVillage.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerVillage.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new StyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                textVillage.setText(mListVillage.get(position).getName().toString());

                village = mListVillage.get(position).getName().toString();
                textProvince.setText(province+city+district+countryside+village);
            }
        });
    }

    /*
    接口回调传值
     */
    public interface OnTestListening {
        void getTopicID(String topicID);
    }

    /*
    判断对象是否为空
     */
    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SimpleArrayMap && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        if (obj instanceof LongSparseArray && ((LongSparseArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (obj instanceof LongSparseArray
                    && ((LongSparseArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }
}