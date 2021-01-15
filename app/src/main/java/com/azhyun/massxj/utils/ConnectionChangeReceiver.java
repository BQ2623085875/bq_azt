package com.azhyun.massxj.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class ConnectionChangeReceiver extends BroadcastReceiver {

    private AlertDialog.Builder dialog = null;
    private AlertDialog alertDialog;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        //如果有网络连接中就直接退出

                        return;
                    }
                }
            }
        }
////如果没有网络连接(网络已经断开)就采取相应的逻辑，跳到相应的界面。
//        SharedPreferences settings = context.getSharedPreferences(
//                Constant.PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putBoolean(Constant.PREFS_NETWORK_ONLINE, false);
//        editor.commit();
//        intent.setClass(context, NetworkListActivity.class);
////在BroadcastReceiver(或者像Service那些没有界面的Android组件)中启动Activity，应该设置FLAG_ACTIVITY_NEW_TASK标记。
//        //清除所有的Activity，应该设置FLAG_ACTIVITY_CLEAR_TOP标记。
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);

        Log.e("----->>", "段网路");
        final Activity activity = ActivityController.getCurrentActivity();
        Log.e("----->>",""+dialog);
        if (dialog == null){
            dialog = new AlertDialog.Builder(activity);
            dialog.setTitle("提示")
                    .setMessage("网路连接已断开,请先连接网路!")
                    .setCancelable(false)
                    .setNegativeButton("知道了", null)
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // 跳转到系统的网络设置界面
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            activity.startActivity(intent);
                        }
                    });
            alertDialog = dialog.create();
            if (!alertDialog.isShowing()) {//此时提示框未显示
//                alertDialog.dismiss();
                alertDialog.show();
            }
        }

    }

}