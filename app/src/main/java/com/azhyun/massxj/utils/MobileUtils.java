package com.azhyun.massxj.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileUtils {
    //判断是否是电话号码
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[09])|(18[0-9])|(17[0-9])|(19[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

}