package com.azhyun.massxj.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.bean.RegionResult;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomStyleDialog extends Dialog {

    private final OnItemListener onItemListener;
    private int numType = 0;
    private int limit = 0;
    private RecyclerView mRecyclerView;
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;
    private TextView mText5;
    private int type;
    private int parentId = 0;
    private List<String> data = new ArrayList<>();
    private List<RegionResult.Data> areaList = new ArrayList<>();

    private int provinceID;//省ID
    private int cityID;//市ID
    private int areaID;//区ID
    private int townID;//镇ID
    private int villageID;//村ID


    public BottomStyleDialog(Context context, OnItemListener onItemListener, int limit) {
        // 在构造方法里, 传入主题
        super(context, R.style.BottomDialogStyle);
        this.onItemListener = onItemListener;
        //是否限制区域
        this.limit = limit;

        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        this.setCanceledOnTouchOutside(true);
        window.setAttributes(attributes);
    }

    public BottomStyleDialog(Context context, int type, OnItemListener onItemListener, int limit) {
        // 在构造方法里, 传入主题
        super(context, R.style.BottomDialogStyle);
        this.onItemListener = onItemListener;
        //是否限制区域
        this.limit = limit;
        this.numType = type;

        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        this.setCanceledOnTouchOutside(true);
        window.setAttributes(attributes);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_popup_window);
        initView();
        initData();
    }

    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mText1 = (TextView) findViewById(R.id.text1);
        mText2 = (TextView) findViewById(R.id.text2);
        mText3 = (TextView) findViewById(R.id.text3);
        mText4 = (TextView) findViewById(R.id.text4);
        mText5 = (TextView) findViewById(R.id.text5);
        mText1.setText("请选择");
        mText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData(1);
                parentId = 0;
                getRegion(parentId, 1);
                mText1.setText("请选择");
                mText3.setText("");
                mText4.setText("");
                mText5.setText("");
                mText2.setText("");


            }
        });

        mText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData(2);
                getRegion(provinceID, 2);
                mText2.setText("请选择");
                mText3.setText("");
                mText4.setText("");
                mText5.setText("");
            }
        });


        mText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData(3);
                getRegion(cityID, 3);
                mText3.setText("请选择");
                mText4.setText("");
                mText5.setText("");

            }
        });
        mText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData(4);
                getRegion(areaID, 4);
                mText4.setText("请选择");
                mText5.setText("");

            }
        });

    }

    private void initData() {
        setData(getContext());
    }

    private void setData(Context context) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        getData(1);

        getRegion(parentId, 1);
    }

    private void getRegion(int parentId, int type1) {
        type = type1;
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<RegionResult> region = service.getRegion(parentId + "");

        region.enqueue(new Callback<RegionResult>() {
            @Override
            public void onResponse(Call<RegionResult> call, Response<RegionResult> response) {
                if (response.isSuccessful()) {
                    RegionResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        areaList = body.getData();
                        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(areaList, type));
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionResult> call, Throwable t) {

            }
        });

    }


    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

        private final List<RegionResult.Data> list;
        private int type1;

        public MyRecyclerViewAdapter(List<RegionResult.Data> data, int i) {
            this.type1 = i;
            this.list = data;
        }

        @Override
        public MyRecyclerViewAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);

            return new MyRecyclerViewAdapter.MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.MyHolder holder, final int position) {
            holder.item.setText(list.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type == 1) {
                        provinceID = Integer.parseInt(list.get(position).getId());
                        parentId = provinceID;
                        mText1.setText(list.get(position).getName());
                        mText2.setText("请选择");
                    } else if (type == 2) {
                        cityID = Integer.parseInt(list.get(position).getId());
                        parentId = cityID;
                        mText2.setText(list.get(position).getName());
                        mText3.setText("请选择");
                    } else if (type == 3) {
                        areaID = Integer.parseInt(list.get(position).getId());
                        parentId = areaID;
                        mText3.setText(list.get(position).getName());
                        if (numType == 1) {

                            onItemListener.getArea(list.get(position).getFullName(), list.get(position).getId());
                            dismiss();
                        } else {
                            mText4.setText("请选择");
                        }

                    } else if (type == 4) {
                        townID = Integer.parseInt(list.get(position).getId());
                        parentId = townID;
                        mText4.setText(list.get(position).getName());
                        mText5.setText("请选择");
                    } else if (type == 5) {

                        mText5.setText(list.get(position).getName());


                        onItemListener.getArea(list.get(position).getFullName(), list.get(position).getId());

                        dismiss();
                    }
                    //                           showAsDropDown(getContentView(),Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, -100);
                    if (type != 5) {
//                        getData(type + 1);
                        if (type == 3 && numType == 1) {

                        } else {
                            getRegion(parentId, type + 1);
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {


            private final TextView item;

            public MyHolder(View itemView) {
                super(itemView);
                item = (TextView) itemView.findViewById(R.id.item);
            }
        }
    }

    /*
接口回调传值
 */
    public interface OnItemListener {
        void getArea(String area, String id);
    }
}