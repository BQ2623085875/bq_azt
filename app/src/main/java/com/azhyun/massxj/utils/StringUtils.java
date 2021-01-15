package com.azhyun.massxj.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by ljh on 2016/9/5.
 */
public class StringUtils {
    /**
     * 在数字型字符串千分位加逗号
     *
     * @param str
     * @return sb.toString()
     */
    public static String addComma(String str) {
        boolean neg = false;
        if (str.startsWith("-")) {  //处理负数
            str = str.substring(1);
            neg = true;
        }
        String tail = null;
        if (str.indexOf('.') != -1) { //处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4) {
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg) {
            sb.insert(0, '-');
        }
        if (tail != null) {
            sb.append(tail);
        }
        return sb.toString();
    }

    /**
     * 字符串输入限制
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        //[/\:*~`!@#$%^&()_+=-};:'.,/?><|"]

        String regEx = "[^a-zA-Z0-9\u4e00-\u9fa5]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    public static String stringDouble(double d){
        DecimalFormat df = new DecimalFormat("#####0.00");
        String str = df.format(d);
        return str;
    }

}
