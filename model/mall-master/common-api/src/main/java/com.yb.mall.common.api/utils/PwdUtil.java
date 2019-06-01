package com.yb.mall.common.api.utils;

/**
 * 密码工具
 *
 * Created by Frank on 2018-08-08.
 */
public class PwdUtil {
    /**
     * 生成用户密码
     * @param account 用户帐号
     * @return 加密后的密码
     */
    public static String getPassword(String account){
        // 手机号后6位 + 4位随机小写英文字母
        String result = account.substring(account.length()-6,account.length());
        // 4位随机小写英文字符
        return result + randomStr();
    }

    /**
     * 4位随机小写英文字母
     * @return 随机英文字母
     */
    private static String randomStr(){
        StringBuilder result = new StringBuilder();
        String model = "abcdefghijklmnopqrstuvwxyz";
        char[] m = model.toCharArray();
        for (int j=0;j<4 ;j++ )
        {
            char c = m[(int)(Math.random()*26)];
            result.append(c);
        }
        return result.toString();
    }
}
