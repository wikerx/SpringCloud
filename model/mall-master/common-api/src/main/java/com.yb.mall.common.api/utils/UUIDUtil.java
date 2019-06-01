package com.yb.mall.common.api.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @Author: kyo
 * @Description:
 * @Date: create in 2018-07-18 16:36
 * @Modified:
 */
public class UUIDUtil {
    public static String getUUID(){
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }
}
