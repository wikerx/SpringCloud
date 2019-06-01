package com.yb.mall.common.api.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : pzc
 * @create : 2017 - 05 - 27
 */
public class MD5Util {
    public static String getMD5Code16(String string) {
        return getMD5Code32(string).substring(8, 24);
    }

    public static String getMD5Code32(String string) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        byte[] md5Bytes = md5.digest(string.getBytes());
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
