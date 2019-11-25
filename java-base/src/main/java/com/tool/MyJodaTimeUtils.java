package com.tool;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.awt.dnd.DropTarget;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/11/5
 */
@Slf4j
public class MyJodaTimeUtils {
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        log.info(dateTime.toString());
        System.out.println(dateTime.getMillis());

        DateTime dateTime1 = dateTime.plusDays(1);
        log.info(dateTime1.toString());
        System.out.println(dateTime1.getMillis());

        DateTime dateTime3 = new DateTime(1572944828077L);
        log.info("dateTime3:{}", dateTime3.toString());

        DateTime dateTime2 = dateTime.plusDays(-1);
        System.out.println(dateTime2.toString());

        DateTime dateTime4 = dateTime.plusWeeks(-1);
        System.out.println(dateTime4);


        System.out.println(dateTimeParse(dateTime3));


        System.out.println(new DateTime(1573885376712L).toString("yyyy-MM-dd HH:mm:ss"));

    }


    /**
     * 日期转时间戳 dateTime 转时间戳
     * 
     * @param dateTime dateTime
     * @return 毫秒数
     */
    public static Long dateTime2Millis(DateTime dateTime) {
        return dateTime.getMillis();
    }


    /**
     * 时间戳转日期
     * 
     * @param mills 时间戳
     * @return
     */
    public static DateTime mills2DateTime(Long mills) {
        return new DateTime(mills);
    }

    /**
     * 时间格式化 默认格式：yyyy-MM-dd HH:mm:ss
     * 
     * @param dateTime 日期
     * @return 格式化后的字符串
     */
    public static String dateTimeParse(DateTime dateTime) {
        return dateTime.toString("yyyy-MM-dd HH:mm:ss");
    }



}
