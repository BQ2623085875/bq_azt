package com.azhyun.massxj.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.azhyun.massxj.utils.LogUtils;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.azhyun.massxj.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.azhyun.massxj.jpush.MyJPushReceiver.json2Obj;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            LogUtils.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                LogUtils.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                LogUtils.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                LogUtils.d(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                LogUtils.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                LogUtils.d(TAG, "[MyReceiver] 用户点击打开了通知");
//				//打开自定义的Activity

                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                JPushMessageResult jPushMessageResult = json2Obj(extra);

                Log.e("--->>", jPushMessageResult.toString());

                boolean isLogin = (boolean) SpUtils.get("isLogin", false);
                if (isLogin && jPushMessageResult.getUserId().equals((String) SpUtils.get("userId", ""))) {
                    read(jPushMessageResult.getId());
                    if (jPushMessageResult.getType() == 3) {
                        SpUtils.put("managerRole", 0);
                    }
                    if (jPushMessageResult.getType() == 1) {//预约单
                        if (jPushMessageResult.getWorkStatus() != 5) {
//                        intent = new Intent(MyApplication.getContext(),OrderInfoActivity.class);
                            intent = new Intent(MyApplication.getContext(), OrderInfoActivity2.class);
                            intent.putExtra("id", jPushMessageResult.getWorkId());
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            MyApplication.getContext().startActivity(intent);
                        }

                    } else if (jPushMessageResult.getType() == 2) {//土地托管申请
                        intent = new Intent(MyApplication.getContext(), ApplyForInfoActivity.class);
                        intent.putExtra("type", jPushMessageResult.getWorkStatus());
                        intent.putExtra("isImg", jPushMessageResult.getIsImg());
                        intent.putExtra("id", jPushMessageResult.getWorkId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContext().startActivity(intent);

                    } else if (jPushMessageResult.getType() == 3) {//经纪人申请
                        intent = new Intent(MyApplication.getContext(), AgentInfoActivity.class);
                        intent.putExtra("id", jPushMessageResult.getWorkId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContext().startActivity(intent);
                    }
                }
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                LogUtils.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                LogUtils.d(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                LogUtils.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    LogUtils.d(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    LogUtils.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.get(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        try {
            JSONObject extraJson = new JSONObject(extras);
            Log.e("111-extraJson-->>", extras);
            Log.e("111-message-->>", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void read(int id) {
        HttpService service = ServiceGenerator.createService(HttpService.class);
        Call<MyManageInfoResult> myManageInfoResultCall = service.MyManageRead(User.INSTANCE.getToken(), id);
        myManageInfoResultCall.enqueue(new Callback<MyManageInfoResult>() {
            @Override
            public void onResponse(Call<MyManageInfoResult> call, Response<MyManageInfoResult> response) {
                if (response.isSuccessful()) {
                    MyManageInfoResult body = response.body();
                    if (body.getResult().getCode().equals("200")) {

                    } else {
                        ToastUtils.showToast(MyApplication.getContext(), body.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyManageInfoResult> call, Throwable t) {

            }
        });
    }
}
