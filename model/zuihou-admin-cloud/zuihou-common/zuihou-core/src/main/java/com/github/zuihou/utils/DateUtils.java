package com.github.zuihou.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：日期工具类
 * 修改人：zhaopengfei
 * 修改时间：2018/4/24
 */
@Slf4j
public class DateUtils {
    private DateUtils() {
    }

    public static final int NEXT_DAY = 5;
    public final static String DEFAULT_YEAR_FORMAT = "yyyy";
    public final static String DEFAULT_MONTH_FORMAT = "yyyy-MM";
    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
//--格式化日期start-----------------------------------------

    /**
     * 格式化日期,返回格式为 HH:mm:ss 例:12:24:24
     *
     * @param date 日期
     * @return
     */
    public static String formatAsTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 yyyy-MM-dd
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 dd ,即对应的天数.
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd");
        return df.format(date);
    }

    /**
     * 根据传入的格式格式化日期.默认格式为MM月dd日
     *
     * @param d 日期
     * @param f 格式
     * @return
     */
    public static String format(Date d, String f) {
        Date date = d;
        String format = f;
        if (date == null) {
            date = new Date();
        }
        if (format == null) {
            format = DATE_TIME_FORMAT;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }


    //--格式化日期end-----------------------------------------

    //--解析日期start-----------------------------------------

    /**
     * 将字符转换成日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parse(String dateStr, String format) {
        Date date = null;
        SimpleDateFormat sdateFormat = new SimpleDateFormat(format);
        sdateFormat.setLenient(false);
        try {
            date = sdateFormat.parse(dateStr);

        } catch (Exception e) {
            log.info("DateUtils error {} ", e);
        }
        return date;
    }


//    /**
//     * 将string类型解析为日期类型
//     *
//     * @param dateStr
//     * @param format  需要解析的格式
//     * @return
//     */
//    public static Date toDate(String dateStr, String format) {
//        SimpleDateFormat df = new SimpleDateFormat(format);
//        try {
//            return df.parse(dateStr);
//        } catch (ParseException e) {
//            return new Date();
//        }
//    }

    /**
     * 根据传入的String返回对应的date
     *
     * @param dateString
     * @return
     */
    public static Date parseAsDate(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 按给定参数返回Date对象
     *
     * @param dateTime 时间对象格式为("yyyy-MM-dd HH:mm:ss");
     * @return
     */
    public static Date parseAsDateTime(String dateTime) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpledateformat.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取指定日期的开始时间
     * 如：00:00:00
     *
     * @param value
     * @return
     */
    public static Date getDate0000(Date value) {
        LocalDateTime today_start = LocalDateTime.of(DateUtils.date2LocalDate(value), LocalTime.MIN);
        return DateUtils.localDateTime2Date(today_start);
    }

    /**
     * 获取指定日期的结束时间
     * 如：23:59:59
     *
     * @param value
     * @return
     */
    public static Date getDate2359(Date value) {
        LocalDateTime date_end = LocalDateTime.of(DateUtils.date2LocalDate(value), LocalTime.MAX);
        return DateUtils.localDateTime2Date(date_end);
    }


    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        return Date.from(zdt.toInstant());
    }

    /***
     * 获得今天的指定时间点
     * @param time 格式为"HH:mm:ss"
     * @return
     */
    public static Date getTodayTime(String time) {
        return getDateTime(new Date(), time);
    }

    /***
     *获得日期的指定时间点
     * @param date
     * @param time  格式为"hh:mm:ss"
     * @return
     */
    public static Date getDateTime(Date date, String time) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(
                "HH:mm:ss");
        try {
            Date parse = simpledateformat.parse(time);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(parse);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);

            calendar2.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
            calendar2.set(Calendar.SECOND, calendar1.get(Calendar.SECOND));
            calendar2.set(Calendar.MILLISECOND, 0);
            return calendar2.getTime();
        } catch (ParseException e) {

        }
        return null;
    }
    //--解析日期 end-----------------------------------------


    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        if (date == null) {
            return LocalDateTime.now();
        }
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 日期转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date) {
        if (date == null) {
            return LocalDate.now();
        }
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 日期转 LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime date2LocalTime(Date date) {
        if (date == null) {
            return LocalTime.now();
        }
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        return instant.atZone(zoneId).toLocalTime();
    }

    //-计算日期 start------------------------------------------

    /**
     * 得到当前时间前一天的时间
     *
     * @return
     */
    public static String getForwardDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    //当前时间下一天的时间
    public static String getNextDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 得到本月的日期
     *
     * @return
     */
    public static String getForwardMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(cal.getTime());

    }

    //得到当前月
    public static String getNowMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(cal.getTime());

    }


    /**
     * 给定日期后增加多少天后的日期
     *
     * @param date 日期
     * @param days 天数
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(date);
            cal.add(Calendar.DATE, days);

        } catch (Exception e) {
            // log.info("DateUtils error {} ", e);
        }
        return cal.getTime();

    }

    /**
     * 检测给定的日期是否在指定的时间内
     *
     * @param date      日期
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static boolean isDateInRange(Date date, Date startDate, Date endDate) {
        if (null == date || null == startDate || null == endDate) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String dateStr = df.format(date);
        String startDateStr = df.format(startDate);
        String endDateStr = df.format(endDate);
        return isDateInRange(dateStr, startDateStr, endDateStr);
    }

    /**
     * 检测给定的日期是否在指定的时间内
     *
     * @param date      日期
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static boolean isDateInRange(String date, String startDate,
                                        String endDate) {
        if (null == date || null == startDate || null == endDate) {
            return false;
        }
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    /**
     * @param @param  currTime
     * @param @param  startTime
     * @param @param  endTime
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: isDateTimeInRange
     * @Description: check date time in range
     */
    public static boolean isDateTimeInRange(Date currTime, Date startTime, Date endTime) {
        if (null == currTime || null == startTime || null == endTime) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        String timeStr = df.format(currTime);
        String startTimeStr = df.format(startTime);
        String endTimeStr = df.format(endTime);
        return isDateInRange(timeStr, startTimeStr, endTimeStr);
    }

    /**
     * 比较两个日期的大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return 0;
        }
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String date1Str = df.format(date1);
        String date2Str = df.format(date2);
        return date1Str.compareTo(date2Str);
    }

    //-计算日期 end------------------------------------------

    /**
     * 计算结束时间与当前时间中的天数
     *
     * @param endDate 结束日期
     * @return
     */
    public static long until(Date endDate) {
        return LocalDateTime.now().until(date2LocalDateTime(endDate), ChronoUnit.DAYS);
    }

    /**
     * 计算结束时间与开始时间中的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static long until(Date startDate, Date endDate) {
        return date2LocalDateTime(startDate).until(date2LocalDateTime(endDate), ChronoUnit.DAYS);
    }

    /**
     * 计算2个日期之间的所有的日期 yyyy-MM-dd
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenDay(Date start, Date end) {
        return getBetweenDay(format(start, "yyyy-MM-dd"), format(end, "yyyy-MM-dd"));
    }

    /**
     * 计算2个日期之间的所有的日期 yyyy-MM-dd
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenDay(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).
                limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }

    /**
     * 计算2个日期之间的所有的周 yyyy-ww
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenWeek(Date start, Date end) {
        return getBetweenWeek(format(start, "yyyy-MM-dd"), format(end, "yyyy-MM-dd"));
    }

    /**
     * 计算2个日期之间的所有的周 yyyy-ww
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenWeek(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.WEEKS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusWeeks(1)).
                limit(distance + 1).forEach(f -> list.add(f.format(DateTimeFormatter.ofPattern("yyyy-ww"))));
        return list;
    }

    /**
     * 计算2个日期之间的所有的月 yyyy-MM
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenMonth(Date start, Date end) {
        return getBetweenMonth(format(start, "yyyy-MM-dd"), format(end, "yyyy-MM-dd"));
    }

    /**
     * 计算2个日期之间的所有的月 yyyy-MM
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenMonth(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.MONTHS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }

        Stream.iterate(startDate, d -> d.plusMonths(1))
                .limit(distance + 1)
                .forEach(f -> list.add(f.format(DateTimeFormatter.ofPattern("yyyy-MM"))));
        return list;
    }

}