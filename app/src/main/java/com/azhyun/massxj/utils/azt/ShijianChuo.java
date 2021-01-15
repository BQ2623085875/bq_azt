package com.azhyun.massxj.utils.azt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dell on 2020/7/14.
 * 时间戳
 */

public class ShijianChuo {

    //yyyy年MM月dd日 HH:mm:ss E

    //全部
    public static String getWholeTiem(long tiem) {
        Date date = new Date(tiem);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E", Locale.getDefault());
        return format.format(date);
    }

    //年月日
    public static String getYMDTiem(long tiem) {
        Date date = new Date(tiem);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    //月日
    public static String getMD(long tiem) {
        Date date = new Date(tiem);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
