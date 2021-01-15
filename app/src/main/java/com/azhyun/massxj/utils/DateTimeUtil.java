package com.azhyun.massxj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间、日期工具类
 * Created by fanji on 2017/7/19.
 */

public class DateTimeUtil {

    /**
     * 时间戳转换成字符窜
     * @param milSecond
     * @param pattern
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

}
