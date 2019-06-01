package com.yb.mall.common.api.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date <===> LocalDateTime/LocalDate/LocalTime
 *
 * @author : Frank
 * @create : 2017 - 05 - 31
 */
public class DateTimeUtil {
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_FORMAT_CH = "yyyy年MM月dd日";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd_HH:mm:ss
     */
    public static final String DATE_TIME_FORMAT_NOT_BLANK = "yyyy-MM-dd_HH-mm-ss";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String DATE_TIME_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm:ss:SSS
     */
    public static final String DATE_TIME_SECONDS_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * yyyyMMdd
     */
    public static final String DATE_WITHOUT_LINE_FORMAT = "yyyyMMdd";

    /**
     * @Fields DATE_WITHOUT_LINE_FORMAT_time :HHmmss
     */
    public static final String DATE_WITHOUT_LINE_FORMAT_TIME = "HHmmss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String DATE_TIME_WITHOUT_LINE_FORMAT = "yyyyMMddHHmmss";
    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String DATE_TIME_SECONDS_WITHOUT_LINE_FORMAT = "yyyyMMddHHmmssSSS";



    /**
     * MM/dd
     */
    public static final String DATE_TIME_MONTH_DAY_FORMAT = "MM/dd";

    /**
     * dd/MM
     */
    public static final String DATE_TIME_DAY_MONTH_FORMAT = "dd/MM";

    /**
     * MM/yyyy
     */
    public static final String DATE_MONTH_YEAR_FORMAT = "MM/yyyy";

    /**
     * yyyy/MM
     */
    public static final String DATE_YEAR_MONTH_FORMAT = "yyyy/MM";

    /**
     * yy/MM
     */
    public static final String DATE_HALF_YEAR_MONTH_FORMAT = "yy/MM";

    /**
     * yyyy
     */
    public static final String DATE_YEAR_FORMAT = "yyyy";

    /**
     * dd
     */
    public static final String DATE_TIME_DAY_FORMAT = "dd";

    /**
     * MM
     */
    public static final String DATE_TIME_MONTH_FORMAT = "MM";

    public static final String DATE_TIME_HOUR_FORMAT = "HH";

    public static final String DATE_TIME_MINUTE_FORMAT = "mm";

    public final static long ONE_DAY_MILLISECOND = 1000 * 24 * 60 * 60;

    public final static long ONE_HOUR_MILLISECOND = 1000 * 60 * 60;

    public final static long ONE_MINUTE_MILLISECOND = 1000 * 60;

    public static Date nowDate() {
        return new Date();
    }


    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    public static final DateTimeFormatter DATETIME_FORMATTER =  DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter DATETIME_SYMBOL_FORMATTER =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 返回当前的日期
     * @return
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     * @return
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回当前日期时间
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * yyMMdd
     *
     * @return
     */
    public static String getCurrentShortDateStr() {
        return LocalDate.now().format(SHORT_DATE_FORMATTER);
    }

    public static String getCurrentMonthStr() {
        return LocalDate.now().format(MONTH_FORMATTER);
    }

    /**
     * yyyyMMddHHmmss
     * @return
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * yyMMddHHmmss
     * @return
     */
    public static String getCurrentShortDateTimeStr() {
        return LocalDateTime.now().format(SHORT_DATETIME_FORMATTER);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentShortXDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_SYMBOL_FORMATTER);
    }

    /**
     * HHmmss
     * @return
     */
    public static String getCurrentTimeStr() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    public static String getCurrentDateStr(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentDateTimeStr(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentTimeStr(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parseLocalTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDateTime(LocalDateTime datetime, String pattern) {
        return datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalTime(LocalTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    public static LocalTime parseLocalTime(String timeStr) {
        return LocalTime.parse(timeStr, TIME_FORMATTER);
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime datetime) {
        return datetime.format(DATETIME_FORMATTER);
    }

    public static String formatLocalTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    /**
     * 日期相隔天数
     * @param startDateInclusive
     * @param endDateExclusive
     * @return
     */
    public static int periodDays(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        return Period.between(startDateInclusive, endDateExclusive).getDays();
    }

    /**
     * 日期相隔小时
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toHours();
    }

    /**
     * 日期相隔分钟
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMinutes();
    }

    /**
     * 日期相隔毫秒数
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMillis();
    }

    /**
     * 是否当天
     * @param date
     * @return
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }

    public static Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }



    /**
     * Date ---> LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).withNano(0);
    }

    /**
     * Date ---> LocalDateTime
     *
     * @return LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime() {
        return date2LocalDateTime(new Date());
    }

    /**
     * Date ---> LocalTime
     *
     * @param date
     * @return LocalTime
     */
    public static LocalTime date2LocalTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime().withNano(0);
    }

    public static String LocalDateTimeToString(Date date){
        if(date != null){
            LocalDateTime localDateTime = date2LocalDateTime(date);
            return  localDateTime2String(localDateTime, "yyyy.MM.dd");
        }
        return null;
    }

    public static String LocalDateTimeToStringTow(Date date){
        if(date != null){
            LocalDateTime localDateTime = date2LocalDateTime(date);
            return  localDateTime2String(localDateTime, "yyyy-MM-dd");
        }
        return null;
    }

    public static String LocalDateTimeToStringHHmmss(Date date){
        if(date != null){
            LocalDateTime localDateTime = date2LocalDateTime(date);
            return  localDateTime2String(localDateTime, "yyyy-MM-dd HH:mm:ss");
        }
        return null;
    }
    
    /**
     * Date ---> LocalTime
     *
     * @return LocalTime
     */
    public static LocalTime date2LocalTime() {
        return date2LocalTime(new Date());
    }

    /**
     * Date ---> LocalDate
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date ---> LocalDate
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate date2LocalDate(String date) {
        return string2LocalDateTime(date).toLocalDate();
    }

    /**
     * Date ---> LocalDate
     *
     * @return LocalDate
     */
    public static LocalDate date2LocalDate() {
        return date2LocalDate(new Date());
    }

    /**
     * LocalDateTime ---> Date
     *
     * @param localDateTime
     * @return Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime ---> Date
     *
     * @return Date
     */
    public static Date localDateTime2Date() {
        return localDateTime2Date(LocalDateTime.now());
    }

    /**
     * LocalDate ---> Date
     *
     * @param localDate
     * @return Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalTime ---> Date
     *
     * @param localTime
     * @return Date
     */
    public static Date localTime2Date(LocalTime localTime) {
        return Date.from(LocalDateTime.of(LocalDate.now(), localTime).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalTime ---> Date
     *
     * @return Date
     */
    public static Date localTime2Date() {
        return localTime2Date(LocalTime.now());
    }

    /**
     * LocalDateTime ---> String
     *
     * @param localDateTime
     * @param pattern
     * @return String
     */
    public static String localDateTime2String(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * LocalDate ---> String
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDate2String(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * LocalDateTime ---> String
     *
     * @param localDateTime
     * @return String
     */
    public static String localDateTime2String(LocalDateTime localDateTime) {
        return localDateTime2String(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * String ---> LocalDateTime
     *
     * @param string
     * @param pattern
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String string, String pattern) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * String ---> LocalDateTime
     *
     * @param string
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String string) {
        return string2LocalDateTime(string, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * String ---> LocalTime
     *
     * @param string
     * @param pattern
     * @return
     */
    public static LocalTime string2LocalTime(String string, String pattern) {
        return LocalTime.parse(string, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * String ---> LocalTime
     *
     * @param string
     * @return
     */
    public static LocalTime string2LocalTime(String string) {
        return string2LocalTime(string, "HH:mm:ss");
    }

    /**
     * String ---> LocalDateTime
     *
     * @param string
     * @return
     */
    public static LocalDate string2LocalDate(String string) {
        return LocalDate.parse(string);
    }

    /**
     * String ---> Date
     *
     * @param string
     * @return
     */
    public static Date string2Date(String string) {
        return localDate2Date(LocalDate.parse(string));
    }

    /**
     * 年份差值
     *
     * @param earlyDate
     * @param lateDate
     * @return
     */
    public static int getYearDiff(LocalDate earlyDate, LocalDate lateDate) {
        return (int) ChronoUnit.YEARS.between(earlyDate, lateDate);
    }

    /**
     * 天数差值
     *
     * @param earlyDate
     * @param lateDate
     * @return
     */
    public static int getDayDiff(String earlyDate, String lateDate) {
        return (int) ChronoUnit.DAYS.between(string2LocalDate(earlyDate), string2LocalDate(lateDate));
    }

    /**
     * 天数差值
     *
     * @param earlyDate
     * @param lateDate
     * @return
     */
    public static int getDayDiff(LocalDate earlyDate, LocalDate lateDate) {
        return (int) ChronoUnit.DAYS.between(earlyDate, lateDate);
    }

    /**
     * 天数
     *
     * @param date 小于当前时间
     * @return
     */
    public static long getDateDayDiff(Date date) {
        return ChronoUnit.DAYS.between(date2LocalDate(date), LocalDate.now());
    }

    /**
     * 分钟差值
     *
     * @param date 小于当前时间
     * @return
     */
    public static long getMinuteDiff(Date date) {
        return ChronoUnit.MINUTES.between(date2LocalDateTime(date), LocalDateTime.now());
    }

    /**
     * isBefore
     *
     * @param initiative 比较时间
     * @param passive    被比较时间
     * @return
     */
    public static boolean isBefore(LocalDate initiative, LocalDate passive) {
        return initiative.isBefore(passive);
    }

    /**
     * isBefore
     *
     * @param initiative 比较时间
     * @param passive    被比较时间
     * @return
     */
    public static boolean isBefore(String initiative, String passive) {
        return string2LocalDate(initiative).isBefore(string2LocalDate(passive));
    }

    /**
     * isBefore
     *
     * @param initiative 比较时间
     * @param passive    被比较时间
     * @return
     */
    public static boolean isBefore(LocalDate initiative, String passive) {
        return initiative.isBefore(string2LocalDate(passive));
    }

    /**
     * isBefore
     *
     * @param initiative 比较时间
     * @param passive    被比较时间
     * @return
     */
    public static boolean isBefore(String initiative, LocalDate passive) {
        return string2LocalDate(initiative).isBefore(passive);
    }

    /**
     * isAfter
     *
     * @param initiative 比较时间
     * @param passive    被比较时间
     * @return
     */
    public static boolean isAfter(String initiative, LocalDate passive) {
        return string2LocalDate(initiative).isAfter(passive);
    }

    /**
     * isBefore
     *
     * @param initiative 比较时间
     * @param passive    被比较时间
     * @return
     */
    public static boolean isBefore(LocalTime initiative, LocalTime passive) {
        return initiative.isBefore(passive);
    }

    /**
     * isBefore
     *
     * @param initiative 比较时间
     * @param passive    被比较时间
     * @return
     */
    public static boolean isBefore(LocalDateTime initiative, LocalDateTime passive) {
        return initiative.isBefore(passive);
    }


    /**
     * plusHours
     *
     * @param localTime
     * @param hoursToAdd
     * @return
     */
    public static LocalTime plusHours(LocalTime localTime, Long hoursToAdd) {
        return localTime.plusHours(hoursToAdd);
    }

    /**
     * plusMinutes
     *
     * @param localTime
     * @param minutesToAdd
     * @return
     */
    public static LocalTime plusMinutes(LocalTime localTime, Long minutesToAdd) {
        return localTime.plusMinutes(minutesToAdd);
    }
    /**
     * plusDays
     *
     * @param localDate
     * @param daysToAdd
     * @return
     */
    public static LocalDate plusDays(LocalDate localDate, Integer daysToAdd) {
        return localDate.plusDays(Long.valueOf(daysToAdd.toString()));
    }

    /**
     * plusDays
     *
     * @param dateString
     * @param daysToAdd
     * @return
     */
    public static LocalDate plusDays(String dateString, Integer daysToAdd) {
        return string2LocalDate(dateString).plusDays(Long.valueOf(daysToAdd.toString()));
    }

    /**
     * todayPlusDays
     *
     * @param daysToAdd
     * @return
     */
    public static LocalDate todayPlusDays(Integer daysToAdd) {
        return plusDays(LocalDate.now(), daysToAdd);
    }

    /**
     * withoutMinutes
     *
     * @param string
     * @return
     */
    public static String withoutMinutes(String string) {
        return localDateTime2String(string2LocalDateTime(string), "yyyy-MM-dd HH:mm");
    }

    /**
     * isTimeBetween
     *
     * @param start
     * @param end
     * @param src
     * @return
     */
    public static boolean isTimeBetween(LocalDateTime start, LocalDateTime end, LocalDateTime src) {
        return src.isAfter(start) && src.isBefore(end);
    }

    /**
     * dateOf
     *
     * @param string
     * @return
     */
    public static String dateOf(String string) {
        return string2LocalDateTime(string).toLocalDate().toString();
    }

    /**
     * yearOf
     *
     * @param string
     * @return
     */
    public static int yearOf(String string) {
        return string2LocalDate(string).getYear();
    }

    /**
     * dateString format
     *
     * @param src
     * @param pattern
     * @return
     */
    public static String dateStringProcess(String src, String pattern) {
        return localDate2String(string2LocalDate(src), pattern);
    }

    /**
     * 比较两个日期大小
     *
     * @return time2大于或等于time1-true : false
     */
    public static boolean compareTo(LocalDateTime time1, LocalDateTime time2) {
        return time2.compareTo(time1) >= 0;
    }

    /**
     * 比较两个日期大小
     *
     * @return time2大于time1-true : false
     */
    public static boolean compareToAbs(LocalDateTime time1, LocalDateTime time2) {
        return time2.compareTo(time1) > 0;
    }

    public static boolean compareTo(LocalDate date1,LocalDate date2){
        return date2.compareTo(date1) >= 0;
    }

    public static boolean compareToAbs(LocalDate date1,LocalDate date2){
        return date2.compareTo(date1) > 0;
    }

    /**
     * 获取指定日期的毫秒
     * @param time
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     * @param time
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     * @param time
     * @param pattern
     * @return
     */
    public static String formatTime(LocalDateTime time,String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field){
        return time.minus(number,field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     * @param startTime
     * @param endTime
     * @param field  单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS){
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS){
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        if (time == null){
            return null;
        }

        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     * @param time
     * @return
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        if (time == null){
            return time;
        }
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }


    /**
     * 获取指定时间的那个月的第一天开始  如：2017-03-01 00:00:00
     * @param now 指定日期
     * @return
     */
    public static LocalDateTime getMonthBegin(LocalDateTime now) {
        if (now == null){
            now = LocalDateTime.now();
        }

        //本月的第一天
        return getDayStart(now.with(TemporalAdjusters.firstDayOfMonth()));

    }

    /**
     * 获取指定时间的那个月的最后一天结束  如：2017-03-31 23:59:59
     * @param now 指定日期
     * @return
     */
    public static LocalDateTime getMonthEnd(LocalDateTime now) {
        if (now == null){
            now = LocalDateTime.now();
        }

        //本月的第一天
        return getDayEnd(now.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * 比较两个时间段是否有交集
     * @param startTime1 时间段1
     * @param endTime1 时间段1
     * @param startTime2 时间段2
     * @param endTime2 时间段2
     * @return true 有交集，false 无交集
     */
    public static boolean betweenTwoTime(LocalDateTime startTime1,LocalDateTime endTime1,LocalDateTime startTime2,LocalDateTime endTime2){
        return !(startTime1.isAfter(endTime2) || endTime1 .isBefore(startTime2));
    }

    /**
     * 比较两个日期相差几个月几天
     * date2 > date1 才会返回正数
     * @param date1 日期1
     * @param date2 日期2
     * @return 几个月几天
     */
    public static String diffDate(LocalDate date1,LocalDate date2){
        String result = "";
        Period period = Period.between(date1,date2);
        if (period.getMonths() != 0){
            result += (period.getMonths() + Math.abs(period.getYears()) * 12) + "个月";
        }
        if (period.getDays() != 0){
            result += period.getDays() + "天";
        }
        return result;
    }

    /**
     * 比较两个日期相差几个月几天
     * @param date1 日期1
     * @param date2 日期2
     * @return 几个月几天
     */
    public static String diffDateABS(LocalDate date1,LocalDate date2){
        String result = "";
        Period period = Period.between(date1,date2);
        if (period.getMonths() != 0){
            result += (Math.abs(period.getMonths()) + Math.abs(period.getYears()) * 12) + "个月";
        }
        result += Math.abs(period.getDays()) + "天";
        return result;
    }

    /**
     * 比较两个日期相差几个月几天
     * @param date1 日期1
     * @param date2 日期2
     * @return 几个月几天
     */
    public static Integer diffMontABS(LocalDate date1,LocalDate date2){
        Period period = Period.between(date1,date2);
        return Math.abs(period.getMonths()) + Math.abs(period.getYears() * 12) + 1;
    }

    /**
     * 日期
     * @param start 2018-01-01
     * @param end 2018-01-31
     * @return 01->31
     */
    public static List<LocalDate> resolutionDay(LocalDate start,LocalDate end){
        List<LocalDate> result = new ArrayList<>();
        while (end.compareTo(start) >= 0){
            result.add(start);
            start = start.plusDays(1);
        }
        return result;
    }

    /**
     * 日期
     * @param start 2018-01-01
     * @param end 2018-01-31
     * @return 01->31
     */
    public static List<LocalDate> resolutionMonth(LocalDate start,LocalDate end){
        List<LocalDate> result = new ArrayList<>();
        while (end.compareTo(start) >= 0){
            result.add(start);
            start = start.plusMonths(1);
        }
        return result;
    }

    /**
     * java8(别的版本获取2月有bug) 获取某月最后一天的23:59:59
     *
     * @return
     */
    public static String getLastDayOfMonth(Date datestr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(datestr.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return sdf.format(dates);
    }

    /**
     * 时间之间的月份
     *
     * @param start
     * @param end
     * @return
     */
    public static long month(LocalDate start, LocalDate end) {
        return start.until(end.minusDays(5), ChronoUnit.MONTHS);
    }

}
