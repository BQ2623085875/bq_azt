package com.azhyun.massxj.utils;

/**
 * Created by ljh on 2016/10/17.
 */

public class IdNumberUtils {
    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(regex);
    }
}
