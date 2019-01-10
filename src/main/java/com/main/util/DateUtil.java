package com.main.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author long
 * @date 2019/1/9 17:08
 */
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        String dateString = "2018-01-22 11:22:33";

        long start = System.currentTimeMillis();
        System.out.println(start);
        System.out.println(strToTimestamp("2018-01-01", DATE_FORMAT));
        long end = System.currentTimeMillis();
        System.out.println(end);

        System.out.println((end - start) / 1000.0);

    }

    //------------------------------ GET 相关

    /**
     * 获取当前日期
     * yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateString() {
        DateTime dateTime = new DateTime();
        return dateTime.toString(DATETIME_FORMAT);
    }

    /**
     * 获取当前日期
     * 自定义格式
     *
     * @return
     */
    public static String getDateString(String format) {
        DateTime dateTime = new DateTime();
        return dateTime.toString(format);
    }

    /**
     * 获取当前时间戳
     * 秒
     *
     * @return
     */
    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间戳
     * 毫秒
     *
     * @return
     */
    public static long getMillisTimestamp() {
        return System.currentTimeMillis();
    }


    //------------------------------ 日期字符串 -> Date对象

    /**
     * 日期字符串 -> Date对象
     * 固定格式 yyyy-MM-dd HH:ii:ss
     *
     * @param dateString
     * @return
     */
    public static Date strToDate(String dateString) {
        dateString = dateStringFormat(dateString);
        DateTime dateTime = DateTime.parse(dateString, DateTimeFormat.forPattern(DATETIME_FORMAT));
        return dateTime.toDate();
    }

    /**
     * 日期字符串 -> Date对象
     * 自定义格式
     *
     * @param dateString
     * @return
     */
    public static Date strToDate(String dateString, String format) {
        DateTime dateTime = DateTime.parse(dateString, DateTimeFormat.forPattern(format));
        return dateTime.toDate();
    }

    //------------------------------ 日期字符串 -> 时间戳

    /**
     * 日期字符串 -> 时间戳[毫秒]
     * 固定格式 yyyy-MM-dd HH:ii:ss
     *
     * @param dateString
     * @return
     */
    public static long strToMillisTimestamp(String dateString) {
        Date date = strToDate(dateString);
        return date.getTime();
    }

    /**
     * 日期字符串 -> 时间戳[毫秒]
     * 自定义格式
     *
     * @param dateString
     * @return
     */
    public static long strToMillisTimestamp(String dateString, String format) {
        Date date = strToDate(dateString, format);
        return date.getTime();
    }

    /**
     * 日期字符串 -> 时间戳[秒]
     * 固定格式 yyyy-MM-dd HH:ii:ss
     *
     * @param dateString
     * @return
     */
    public static long strToTimestamp(String dateString) {
        Date date = strToDate(dateString);
        return date.getTime() / 1000;
    }

    /**
     * 日期字符串 -> 时间戳[秒]
     * 自定义格式
     *
     * @param dateString
     * @return
     */
    public static long strToTimestamp(String dateString, String format) {
        Date date = strToDate(dateString, format);
        return date.getTime() / 1000;
    }

    //------------------------------ 其他

    /**
     * 提取字符串中的数字
     *
     * @param string
     * @return
     */
    public static String getNumInString(String string) {
        String regEx = "[^0-9]";
        Matcher m = Pattern.compile(regEx).matcher(string);
        return m.replaceAll("").trim();
    }

    /**
     * 日期字符串处理成标准格式
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateString
     * @return
     */
    public static String dateStringFormat(String dateString) {
        String num = getNumInString(dateString);
        // 日期补0
        List<String> ss = new ArrayList<String>();
        if (dateString.length() == num.length()) {
            // 纯数字日期 - 默认为标准格式 yyyyMMddHHmmss
            ss.add(dateString);
        } else {
            // 带格式化日期
            for (String item : dateString.replaceAll("[^0-9]", ",").split(",")) {
                if (item.length() > 0) {
                    // 补0
                    if (item.length() == 1) {
                        item = "0" + item;
                    }
                    ss.add(item);
                }
            }
        }
        // 补0后数字 处理成标准格式
        num = String.join("", ss);
        String[] array = num.split("");
        List<String> list = new ArrayList<>(Arrays.asList(array));
        if (list.size() != 14) {
            int total = 14 - list.size();
            for (int i = 0; i < total; i++) {
                list.add("0");
            }
            if ("0".equals(list.get(4)) && "0".equals(list.get(5))) {
                list.set(5, "1");
            }
            if ("0".equals(list.get(6)) && "0".equals(list.get(7))) {
                list.set(7, "1");
            }
        }
        list.add(12, ":");
        list.add(10, ":");
        list.add(8, " ");
        list.add(6, "-");
        list.add(4, "-");
        return String.join("", list);
    }

}
