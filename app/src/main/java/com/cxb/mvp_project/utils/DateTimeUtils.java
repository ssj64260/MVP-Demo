package com.cxb.mvp_project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 */

public class DateTimeUtils {

    private static SimpleDateFormat enDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    private static SimpleDateFormat enDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    // 获取当前日期，格式：2016-11-24
    public static String getEnDate() {
        return getEnDate(new Date());
    }

    public static String getEnDate(Date date) {
        return enDateFormat.format(date);
    }

    public static String getFriendDatetime(Date date) {
        final Date currentDate = new Date();
        final int minute = getNumberOfMinute(currentDate, date);
        final int absMinute = Math.abs(minute);
        if (minute < 0) {
            return absMinute + "分钟后";
        } else {
            if (absMinute < 60) {
                return absMinute + "分钟前";
            } else {
                final int hour = absMinute / 60;
                if (hour < 24) {
                    return hour + "小时前";
                } else {
                    return getEnDate(date);
                }
            }
        }
    }

    // 获取两个时间的分钟差
    public static int getNumberOfMinute(Date time1, Date time2) {
        if (time1 == null || time2 == null) {
            throw new IllegalAccessError("string can not convert date");
        } else {
            int timeStamp1 = (int) (time1.getTime() / (60 * 1000));
            int timeStamp2 = (int) (time2.getTime() / (60 * 1000));

            return timeStamp1 - timeStamp2;
        }
    }

    public static Date StringToDateTime(String datetime) {
        try {
            return enDateTimeFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
