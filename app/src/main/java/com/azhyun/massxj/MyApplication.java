package com.azhyun.massxj;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.azhyun.massxj.bean.SpUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import java.util.ArrayList;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by ljh on 2017/12/11.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private ArrayList<Activity> activityList;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        initTbs();

        //微信
        PlatformConfig.setWeixin("wx3b7ec90111ae6ac7", "7e7441278975fc586b115dc01f95ccfb");
        //QQ
        PlatformConfig.setQQZone("101520866", "48bc4d35b48f0e70e2ef3acd62412438");
        PlatformConfig.setSinaWeibo("1499630748","1c06ce6be117f4eb85b7e4aa9021f1e9","");

        UMConfigure.setLogEnabled(true);
        //友盟统计
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"");
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setDebugMode(true);

        boolean isLogin = (boolean) SpUtils.get("isLogin", false);
        String userId = (String)SpUtils.get("userId", "");
        if (isLogin){
            JPushInterface.init(getContext());             // 初始化 JPush
            Log.i("JPush","isPushStopped:"+JPushInterface.isPushStopped(getContext()));
            if(JPushInterface.isPushStopped(getContext())){
                JPushInterface.resumePush(getContext());//恢复极光推送
            }
            Log.i("JPush","JPushAlias:"+userId);

            JPushInterface.setAlias(getContext(), userId , new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {
                    Log.i("JPush","Login:");
                }
            });
        }

    }

    private void initTbs() {
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

                Log.i("X5内核","初始化完成");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。

                Log.i("X5内核初始化完成:",b+"");
            }
        });


       }

    public void addActivity(Activity activity) {
        if (activityList == null) {
            activityList = new ArrayList<Activity>();
        }
        activityList.add(activity);
    }

    /**
     * app退出
     */
    public void exit() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing() && activity != null) {
                activity.finish();
            }
        }
        clearActivity();
        System.exit(0);
    }
    /**
     * 清空列表，取消引用
     */
    public void clearActivity() {
        activityList.clear();
    }


    public static Context getContext() {
        return mContext;
    }


}
