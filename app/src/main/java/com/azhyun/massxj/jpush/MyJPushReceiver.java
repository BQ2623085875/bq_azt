package com.azhyun.massxj.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.activity.AgentInfoActivity;
import com.azhyun.massxj.activity.ApplyForInfoActivity;
import com.azhyun.massxj.activity.OrderInfoActivity2;
import com.azhyun.massxj.bean.JPushMessageResult;
import com.azhyun.massxj.bean.MyManageInfoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.bean.User;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyJPushReceiver extends BroadcastReceiver {
    private static String TAG = "pushreceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + bundle.toString());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
// 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println("收到了自定义消息@@消息内容是:" + content);
            System.out.println("收到了自定义消息@@消息内容是:" + content);
            System.out.println("收到了自定义消息@@消息extra是:" + extra);
//**************解析推送过来的json数据并存放到集合中 begin******************
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(extra);
                String string = jsonObject.toString();
                String type = jsonObject.getString("type");
                Log.e("---type string-->", string);
                map.put("type", type);
            } catch (JSONException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            map.put("content", content);
//获取接收到推送时的系统时间
            Calendar rightNow = Calendar.getInstance();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            String date = fmt.format(rightNow.getTime());
            map.put("date", date);
//MyApp.data.add(map);
//**************解析推送过来的json数据并存放到集合中 end******************
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了通知");
// 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            JPushMessageResult jPushMessageResult = json2Obj(extra);

            Log.e("--->>",jPushMessageResult.toString());

            boolean isLogin = (boolean) SpUtils.get("isLogin", false);
            if (isLogin && jPushMessageResult.getUserId().equals((String) SpUtils.get("userId",""))){
                read(jPushMessageResult.getId());
                if (jPushMessageResult.getType() == 3 || jPushMessageResult.getType() == 2){
                    SpUtils.put("managerRole",0);
                }
                if (jPushMessageResult.getType() == 1){//预约单
                    if (jPushMessageResult.getWorkStatus() != 5){
//                        intent = new Intent(MyApplication.getContext(),OrderInfoActivity.class);
                        intent = new Intent(MyApplication.getContext(),OrderInfoActivity2.class);
                        intent.putExtra("id",jPushMessageResult.getWorkId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContext().startActivity(intent);
                    }


                }else if (jPushMessageResult.getType() == 2){//土地托管申请
                    intent = new Intent(MyApplication.getContext(),ApplyForInfoActivity.class);
                    intent.putExtra("type",jPushMessageResult.getWorkStatus());
                    intent.putExtra("isImg",jPushMessageResult.getIsImg());
                    intent.putExtra("id",jPushMessageResult.getWorkId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getContext().startActivity(intent);


                }else if (jPushMessageResult.getType() == 3){//经纪人申请
                    intent = new Intent(MyApplication.getContext(),AgentInfoActivity.class);
                    intent.putExtra("id",jPushMessageResult.getWorkId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getContext().startActivity(intent);
                }
            }

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
    private void read(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyManageInfoResult> myManageInfoResultCall = service.MyManageRead(User.INSTANCE.getToken(), id);
        myManageInfoResultCall.enqueue(new Callback<MyManageInfoResult>() {
            @Override
            public void onResponse(Call<MyManageInfoResult> call, Response<MyManageInfoResult> response) {
                if (response.isSuccessful()){
                    MyManageInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")){

                    }else {
                        ToastUtils.showToast(MyApplication.getContext(),body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyManageInfoResult> call, Throwable t) {

            }
        });
    }

    public static JPushMessageResult json2Obj(String json) {
        Gson gson = new Gson();
        JPushMessageResult jPushMessageResult = gson.fromJson(json, JPushMessageResult.class);
        return jPushMessageResult;
    }
}
