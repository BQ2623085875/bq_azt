package com.azhyun.massxj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ljh on 2016/8/16.
 */
public class DateUtils {
    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static String getNowDateShort(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

//    public static String getStandardDate(String timeStr) {
//
//        StringBuffer sb = new StringBuffer();
//
//        long t = Long.parseLong(timeStr);
//        long time = System.currentTimeMillis() - (t*1000);
//        long mill = (long) Math.ceil(time /1000);//秒前
//
//        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前
//
//        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时
//
//        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前
//
//        if (day - 1 > 0) {
//            sb.append(day + "天");
//        } else if (hour - 1 > 0) {
//            if (hour >= 24) {
//                sb.append("1天");
//            } else {
//                sb.append(hour + "小时");
//            }
//        } else if (minute - 1 > 0) {
//            if (minute == 60) {
//                sb.append("1小时");
//            } else {
//                sb.append(minute + "分钟");
//            }
//        } else if (mill - 1 > 0) {
//            if (mill == 60) {
//                sb.append("1分钟");
//            } else {
//                sb.append(mill + "秒");
//            }
//        } else {
//            sb.append("刚刚");
//        }
//        if (!sb.toString().equals("刚刚")) {
//            sb.append("前");
//        }
//        return sb.toString();
//    }

    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis();
        long time1 = curTime - timeStamp;
        long time = time1 / 1000;
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }
}
