package com.yb.mall.common.api.utils;

import java.time.format.DateTimeFormatter;

/**
 * @Author: kyo
 * @Description:
 * @Date: create in 2018-11-23 16:50
 * @Modified:
 */
public class DateTimeFormatterPattern {
    // LocalDate
    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_CN_PATTERN = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    // LocalDateTime
    public static final DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
