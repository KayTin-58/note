package com.tool;

import java.util.Date;

import org.joda.time.*;

/**
 * <p>
 * Joda 时间工具类
 * </p>
 *
 * @author aaron.yi
 */
public class JodaTimeUtils {

    /**
     * <p>
     * 禁止实例化，禁止反射
     * </p>
     *
     * @throws IllegalAccessException 禁止访问异常
     */
    private JodaTimeUtils() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    static {
        DateTimeZone.setDefault(DateTimeZone.getDefault());
    }

    /**
     * <p>
     * 获取当天起始时间毫秒值
     * </p>
     *
     * @return 毫秒值
     */
    public static long getCurrentStartOfDayMillis() {
        return startOfDayMillis(new Date());
    }

    /**
     * <p>
     * 获取某天起始时间毫秒值
     * </p>
     *
     * @param date 指定某天
     * @return 毫秒值
     */
    public static long startOfDayMillis(Date date) {
        return startOfDay(date).getTime();
    }

    /**
     * <p>
     * 获取某天截止时间毫秒值
     * </p>
     *
     * @param date 指定某天
     * @return 毫秒值
     */
    public static long endOfDayMillis(Date date) {
        return endOfDay(date).getTime();
    }

    /**
     * <p>
     * 某天剩余毫秒值
     * </p>
     *
     * @param date 指定某天
     * @return 毫秒值
     */
    public static long endToDayMillis(Date date) {
        DateTime nowTime = new DateTime(date);
        DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
        return endOfDay.getMillis() - nowTime.getMillis();
    }

    /**
     * <p>
     * 获取某天起始时间
     * </p>
     *
     * @param date {@link Date} 指定某天
     * @return {@link Date}日期
     */
    public static Date startOfDay(Date date) {
        DateTime startOfDay = baseStartDay(date);
        return startOfDay.toDate();
    }

    /**
     * <p>
     * 获取某天截止时间
     * </p>
     *
     * @param date {@link Date} 指定某天
     * @return {@link Date}日期
     */
    public static Date endOfDay(Date date) {
        DateTime endOfDay = baseEndDay(date);
        return endOfDay.toDate();
    }

    /**
     * <p>
     * 根据[年月日时分秒毫秒]生成日期
     * </p>
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param min 份
     * @param sec 秒
     * @param mSec 毫秒
     * @return {@link Date} 日期
     */
    public static Date generateDate(int year, int month, int day, int hour, int min, int sec, int mSec) {
        DateTime dt = new DateTime(year, month, day, hour, min, sec, mSec);
        return dt.toDate();
    }

    /**
     * <p>
     * 获取某天起始时间字符串
     * </p>
     *
     * @param date {@link Date}指定某天
     * @param pattern 指定格式
     * @return 时间格式化字符串
     */
    public static String startOfDay(Date date, String pattern) {
        DateTime startOfDay = baseStartDay(date);
        return startOfDay.toString(pattern);
    }

    /**
     * <p>
     * 获取某天起始时间字符串, 默认{@link BaseConstants.Pattern#DATETIME}
     * </p>
     *
     * @param date {@link Date}指定某天
     * @return 时间格式化字符串
     */
    /*
     * public static String startOfDayString(Date date) { DateTime startOfDay = baseStartDay(date);
     * return startOfDay.toString(BaseConstants.Pattern.DATETIME); }
     * 
     *//**
        * <p>
        * 获取某天起始截止时间字符串
        * </p>
        *
        * @param date {@link Date}指定某天
        * @param pattern 指定格式,{@link BaseConstants.Pattern}
        * @return 时间格式化字符串
        */
    /*
     * public static String endOfDayString(Date date, String pattern) { DateTime endOfDay =
     * baseEndDay(date); return endOfDay.toString(pattern); }
     * 
     *//**
        * <p>
        * 获取某天截止时间字符串, 默认格式{@link BaseConstants.Pattern#DATETIME}
        * </p>
        *
        * @param date {@link Date}指定某天
        * @return 时间格式化字符串
        *//*
           * public static String endOfDayString(Date date) { DateTime endOfDay = baseEndDay(date); return
           * endOfDay.toString(BaseConstants.Pattern.DATETIME); }
           */

    /**
     * <p>
     * 判断是否同一天
     * </p>
     *
     * @param startTime 起始时间毫秒值
     * @param endTime 截止时间毫秒值
     * @return 返回 {@code true} 即相同，反之则反
     */
    public static boolean isSameDay(long startTime, long endTime) {
        DateTime startDay = new DateTime(startTime);
        DateTime endDay = new DateTime(endTime);
        Period p = new Period(startDay, endDay, PeriodType.days());
        int days = p.getDays();
        return days == 0;
    }

    /**
     * <p>
     * 判断是否同一天
     * </p>
     *
     * @param startTime {@link Date}起始时间
     * @param endTime {@link Date}截止时间
     * @return 返回 {@code true} 即相同，反之则反
     */
    public static boolean isSameDay(Date startTime, Date endTime) {
        return isSameDay(startTime.getTime(), endTime.getTime());
    }

    /**
     * <p>
     * 两毫秒值相差天数
     * </p>
     *
     * @param startTime 起始时间
     * @param endTime 截止时间
     * @return 相差天数
     */
    public static int daysBetween(long startTime, long endTime) {
        DateTime startDay = new DateTime(startTime);
        DateTime endDay = new DateTime(endTime);
        return Days.daysBetween(startDay, endDay).getDays();
    }

    /**
     * <p>
     * 两日期相差天数
     * </p>
     *
     * @param startTime {@link Date}起始时间
     * @param endTime {@link Date}截止时间
     * @return 相差天数
     */
    public static int daysBetween(Date startTime, Date endTime) {
        return daysBetween(startTime.getTime(), endTime.getTime());
    }

    /**
     * <p>
     * 格式化日期 格式定义使用：{@link BaseConstants.Pattern}
     * </p>
     *
     * @param date {@link Date}指定日期
     * @param pattern 指定格式
     * @return 格式化的日期字符串
     */
    public static String format(Date date, String pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(pattern);
    }

    /* *//**
          * <p>
          * 格式化日期 默认格式：{@link BaseConstants.Pattern#DATETIME}
          * </p>
          *
          * @param date {@link Date}指定日期
          * @return 格式化的日期字符串
          *//*
             * public static String format(Date date) { DateTime dateTime = new DateTime(date); return
             * dateTime.toString(BaseConstants.Pattern.DATETIME); }
             */

    private static DateTime baseStartDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.withTimeAtStartOfDay();
    }

    private static DateTime baseEndDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.millisOfDay().withMaximumValue();
    }

}
