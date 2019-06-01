package com.yb.mall.common.api.utils;

import java.util.regex.Pattern;

/**
 * @Author: kyo
 * @Description: 正则
 * @Date: create in 2018-07-31 10:54
 * @Modified:
 */
public class RegUtil {
    // 手机号
    public static final String PHONE_REG = "^0?(13[0-9]|16[0-9]|19[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])[0-9]{8}$";
    // 正整数
    private static final String NUMBER_REG = "^[0-9]*$";
    // 浮点数 4+2 ****.**
    private static final String DOUBLE_NUMBER42 = "^([1-9]\\d{0,3}|0)(\\.\\d{1,2})?$";
    // 浮点数 6+2 ****.**
    public static final String DOUBLE_NUMBER62 = "^([1-9]\\d{0,5}|0)(\\.\\d{1,2})?$";

    public static final String CHINESE = "[\u4e00-\u9fa5]";

    public static boolean notPhone (String str){
        return !Pattern.matches(PHONE_REG,str == null ? "" : str);
    }

    public static boolean notNumber(String str){
        return !Pattern.matches(NUMBER_REG,str == null ? "" : str);
    }

    public static boolean notDoubleNumber42(String str){
        return !Pattern.matches(DOUBLE_NUMBER42,str == null ? "" : str);
    }

    public static boolean containChinese(String str){
        return Pattern.matches(CHINESE,str == null ? "" : str);
    }

}