/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.clkj.common.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期处理
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     *
     * @param week 周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return 返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 获取月的开始 （精确到日）
     *
     * @param aDate aDate
     * @return Date
     */
    public static Date getMonthStart(Date aDate) {
        LocalDate date = new LocalDate(aDate);
        date = date.dayOfMonth().withMinimumValue();
        return date.toDate();
    }

    /**
     * 获取月的结束 （精确到日）
     *
     * @param aDate aDate
     * @return Date
     */
    public static Date getMonthEnd(Date aDate) {
        LocalDate date = new LocalDate(aDate);
        date = date.dayOfMonth().withMaximumValue();
        return date.toDate();
    }

    /**
     * 计算月份的天数
     *
     * @param aDate aDate
     * @return int
     */
    public static int getMonthDays(Date aDate) {
        LocalDate date = new LocalDate(aDate);
        return date.dayOfMonth().getMaximumValue();
    }

    public static Integer getWeekBetween(Date startDate, Date endDate) {
        DateTime start = new DateTime(startDate);
        DateTime end = new DateTime(endDate);
        Weeks between = Weeks.weeksBetween(start, end);
        return between.getWeeks();
    }

    public static Integer getDayBetween(Date startDate, Date endDate) {
        DateTime start = new DateTime(startDate);
        DateTime end = new DateTime(endDate);
        Days between = Days.daysBetween(start, end);
        return between.getDays();
    }

    public static int getMonthBetween(Date startDate, Date endDate) {
        DateTime start = new DateTime(startDate);
        DateTime end = new DateTime(endDate);
        Months between = Months.monthsBetween(start, end);
        return between.getMonths();
    }

    public static Date getStartOfDay(Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.withTime(0, 0, 0, 0).toDate();
    }

    public static Date getEndOfDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(1).withTime(0, 0, 0, 0).toDate();
    }

    /**
     * 计算2个时间的差值（分钟），包含时区夏令时
     *
     * @param start  start
     * @param end    end
     * @param zoneId zoneId
     * @return long
     */
    public static long betweenMinutes(String start, String end, String zoneId) {
        // 北京时间（new出来就是默认时区的时间）

        // 得到纽约的时区
        ZoneId tz = ZoneId.of(zoneId);

        java.time.format.DateTimeFormatter pattern = java.time.format.DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        LocalDateTime startDate = LocalDateTime.parse(start, pattern);

        LocalDateTime endDate = LocalDateTime.parse(end, pattern);

        long f = startDate.atZone(tz).toInstant().getEpochSecond();
        long s = endDate.atZone(tz).toInstant().getEpochSecond();
        return (s - f) / 60;
    }
}
