package com.mouqukeji.hmdeer.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DateUtils {
    private static Calendar calendar;

    //获取当前日期
    public static String getData() {
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }

    //获取当前时间
    public static String getTime() {
        String hour = "";
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        if (calendar.get(Calendar.AM_PM) == 0)
            hour = String.valueOf(calendar.get(Calendar.HOUR));
        else
            hour = String.valueOf(calendar.get(Calendar.HOUR) + 12);
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if (Integer.parseInt(minute)<10){
            minute="0"+minute;
        }
        return hour + ":" + minute;
    }

    //获取当前小时
    public static String getHour() {
        String hour = "";
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        if (calendar.get(Calendar.AM_PM) == 0)
            hour = String.valueOf(calendar.get(Calendar.HOUR));
        else
            hour = String.valueOf(calendar.get(Calendar.HOUR) + 12);
        return hour;
    }

    //获取当前分钟
    public static String getMinute() {
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        return minute;
    }


    //获得明天日期
    public static String getTomoData() {

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        List<String> list_big = Arrays.asList(months_big);
        List<String> list_little = Arrays.asList(months_little);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        if (day == 30) {
            if (list_big.contains(String.valueOf(month))) {
                day = 31;
            }
            if (list_little.contains(String.valueOf(month))) {
                day = 1;
                if (month == 12) {
                    year++;
                    month = 1;
                } else {
                    month++;
                }

            }
        } else if (day == 31) {
            day = 1;
            if (month == 12) {
                year++;
                month = 1;
            } else {
                month++;
            }

        } else {
            day++;
        }
        String data;
        if (day < 10) {

            data = year + "-" + "0" + month + "-" + "0" + day;
        } else {
            data = year + "-" + "0" + month + "-" + day;
        }

        return data;
    }

}
