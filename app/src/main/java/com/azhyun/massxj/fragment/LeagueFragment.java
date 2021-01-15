package com.azhyun.massxj.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.SelectApproverAcitivity;
import com.azhyun.massxj.activity.SubmitSucceedActivity;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.AlignTextView;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.MobileUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.azhyun.massxj.view.BottomStyleDialog;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tl on 2018/8/10.
 */

public class LeagueFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.bank)
    ImageView bank;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.text_name)
    AlignTextView textName;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.text_gender)
    AlignTextView textGender;
    @BindView(R.id.edit_gender)
    EditText editGender;
    @BindView(R.id.text_phone)
    AlignTextView textPhone;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.text_area)
    EditText textArea;
    @BindView(R.id.text_address)
    AlignTextView textAddress;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.text_soil)
    AlignTextView textSoil;
    @BindView(R.id.edit_soil)
    EditText editSoil;
    @BindView(R.id.text_synopsis)
    AlignTextView textSynopsis;
    @BindView(R.id.edit_synopsis)
    EditText editSynopsis;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.text_service_name)
    AlignTextView textServiceName;
    @BindView(R.id.edit_service_name)
    EditText editServiceName;
    @BindView(R.id.line_service_name)
    AutoLinearLayout lineServiceName;
    @BindView(R.id.line_address)
    AutoLinearLayout lineAddress;

    private String region;
    private BottomStyleDialog bottomStyleDialog;
    private int applyRole = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {
        bottomStyleDialog = new BottomStyleDialog(getContext(), new BottomStyleDialog.OnItemListener() {
            @Override
            public void getArea(String area, String id) {
                textArea.setText(area);
                region = id;
            }
        },1);
        bank.setVisibility(View.GONE);
        title.setText("我要加盟");
        textArea.setOnClickListener(this);
        submit.setOnClickListener(this);
        editGender.setOnClickListener(this);
        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);
//        String phone = (String) SpUtils.get("phone", "");
//        editPhone.setInputType(InputType.TYPE_NULL);
//        editPhone.setText(phone);
        setPhone();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //1.村级理事长 2.乡级理事长 3.服务商
            case R.id.radio1:
                applyRole = 1;
                lineServiceName.setVisibility(View.GONE);
                lineAddress.setVisibility(View.GONE);
                setPhone();
                break;
            case R.id.radio2:
                applyRole = 2;
                lineServiceName.setVisibility(View.GONE);
                lineAddress.setVisibility(View.GONE);
                setPhone();
                break;
            case R.id.radio3:
                applyRole = 3;
                lineServiceName.setVisibility(View.VISIBLE);
                lineAddress.setVisibility(View.VISIBLE);
                setPhone();
                break;

            case R.id.text_area:
                //获取区域
                bottomStyleDialog.show();


                break;
            case R.id.submit://提交
                String serviceName = editServiceName.getText().toString().trim();
                String name = editName.getText().toString().trim();
                String gender = editGender.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String area = textArea.getText().toString().trim();
                String address = editAddress.getText().toString().trim();
                String soil = editSoil.getText().toString().trim();
                String sunopsis = editSynopsis.getText().toString().trim();

                if (applyRole == 3 ){
                   if (serviceName.isEmpty()){
                       ToastUtils.showToast(getContext(), "请输入服务商名称");
                       break;
                   }
//                   break;
                }
                if (name.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入姓名");
                    break;
                }
                if (gender.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请选择姓别");
                    break;
                }
                if (phone.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入联系方式");
                    break;
                } else if (!MobileUtils.isMobile(phone)) {
                    ToastUtils.showToast(getContext(), "请输入正确的联系方式");
                    break;
                }
                if (area.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入选择区域地址");
                    break;
                }
                if (applyRole == 3){
                    if (address.isEmpty()) {
                    ToastUtils.showToast(getContext(), "请输入详细地址");
                    break;
                }
                }
//
//                if (soil.isEmpty()) {
//                    ToastUtils.showToast(getContext(), "请输入土地面积");
//                    break;
//                }
//                if (sunopsis.isEmpty()) {
//                    ToastUtils.showToast(getContext(), "请输入简介");
//                    break;
//                }

            if (applyRole == 3){
                submit(address, applyRole, soil,serviceName ,null, phone, name, region, gender, User.INSTANCE.getToken());

            }else {

                Intent intent = new Intent(getActivity(),SelectApproverAcitivity.class);
                intent.putExtra("type",1);
                intent.putExtra("applyRole",applyRole);
                intent.putExtra("name",name);
                intent.putExtra("companyName",serviceName);
                intent.putExtra("gender",gender);
                intent.putExtra("phone",phone);
                intent.putExtra("area",region);
                intent.putExtra("address",address);
                startActivity(intent);
            }


                break;

            case R.id.edit_gender:

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("请选择性别");
                final String[] sex = {"男", "女"};
                //    设置一个单项选择下拉框
                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(sex, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getContext(), "性别为：" + sex[which], Toast.LENGTH_SHORT).show();
                        editGender.setText(sex[which]);
                        dialog.dismiss();
                    }
                });

                builder.show();
                break;
        }
    }

    private void setPhone() {
        String phone = (String) SpUtils.get("phone", "");
        String fullName = (String) SpUtils.get("fullName", "");
        String region = (String) SpUtils.get("region", "");
        if (applyRole != 3){
            editPhone.setInputType(InputType.TYPE_NULL);
            editPhone.setText(phone);

            this.region = region;
            textArea.setText(fullName);
        }else {
            editPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
            editPhone.setText("");

            this.region = "";
            textArea.setText("");
        }
    }

    private void submit(String address, int applyRole, String soil,String serviceName,String sunopsis, String phone, String name, String region, String gender, String token) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<ManagerResult> manager = service.managerNew(address, applyRole, 0,0,serviceName,sunopsis, phone, name, region, gender, token);
        manager.enqueue(new Callback<ManagerResult>() {
            @Override
            public void onResponse(Call<ManagerResult> call, Response<ManagerResult> response) {
                if (response.isSuccessful()) {
                    ManagerResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                        Intent intent = new Intent(getContext(), SubmitSucceedActivity.class);
                        intent.putExtra("type",2);//2代表经纪人申请
                        intent.putExtra("id",body.getData().getId());
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(getContext(), body.getResult().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ManagerResult> call, Throwable t) {
                ToastUtils.showToast(getContext(), t.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bottomStyleDialog.cancel();
    }


}
