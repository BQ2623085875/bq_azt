package com.azhyun.massxj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static float getHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static Drawer getDrawer(final Bundle mBundle , final Context context){

        Drawer mainDrawer = new DrawerBuilder()
                .withActivity((Activity) context)
//                .withHeader(cn.argiseed.countyservicestation.R.layout.drawer_header)
                .withSavedInstance(mBundle)
                .withTranslucentStatusBar(false)
                .withDrawerGravity(Gravity.END)
                .withDrawerWidthDp(200)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("首页").withIdentifier(1).withTextColor(Color.parseColor("#333333")),
                        new PrimaryDrawerItem().withName("生产管理").withIdentifier(2).withTextColor(Color.parseColor("#333333")),
                        new PrimaryDrawerItem().withName("仓库管理").withIdentifier(3).withTextColor(Color.parseColor("#333333")),
                        new PrimaryDrawerItem().withName("销售管理").withIdentifier(4).withTextColor(Color.parseColor("#333333")),
                        new PrimaryDrawerItem().withName("财务管理").withIdentifier(5).withTextColor(Color.parseColor("#333333")),
                        new PrimaryDrawerItem().withName("客户管理").withIdentifier(6).withTextColor(Color.parseColor("#333333")),
                        new PrimaryDrawerItem().withName("品种信息").withIdentifier(7).withTextColor(Color.parseColor("#333333")),
                        new PrimaryDrawerItem().withName("个人信息").withIdentifier(8).withTextColor(Color.parseColor("#333333"))
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = null;
//                        ((Activity) context).finish();
                        switch ((int) drawerItem.getIdentifier()) {
//                            case 1:
//                                intent = new Intent(context, HomeActivity.class);
//                                break;
//                            case 2://生产
//                                intent = new Intent(context, ManagementActivity.class);
//                                intent.putExtra("value",1);
//                                break;
//                            case 3://仓储
//                                intent = new Intent(context, ManagementActivity.class);
//                                intent.putExtra("value",2);
//                                break;
//                            case 4://销售
//                                intent = new Intent(context, ManagementActivity.class);
//                                intent.putExtra("value",3);
//                                break;
//                            case 5://财务
//                                intent = new Intent(context, ManagementActivity.class);
//                                intent.putExtra("value",4);
//                                break;
//                            case 6://客户管理
//                                intent = new Intent(context, ClientManagementActivity.class);
//                                break;
//                            case 7:
//                                intent = new Intent(context, SpeciesInfoActivity.class);
//                                break;
//                            case 8:
//                                intent = new Intent(context, SettingActivity.class);
//                                break;
                        }
                        if (intent != null) {
                            context.startActivity(intent);
                        }
                        return false;
                    }
                })
                .build();
        return mainDrawer;
    }

}