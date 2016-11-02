package com.aboutcoder.packease.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 4:25 PM<br />
 * @description <br />
 */
public class PEDateUtils {
    /**
     * date format pattern
     */
    private static final String DATE_STR = "yyyy-MM-dd";

    /**
     * datetime format pattern
     */
    private static final String DATETIME_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * singleton simpleDateFormatForDate object
     */
    private static SimpleDateFormat simpleDateFormatForDate = null;

    /**
     * singleton simpleDateFormatForDatetime object
     */
    private static SimpleDateFormat simpleDateFormatForDatetime = null;

    /**
     * Get simpleDateFormatForDate <br>
     *
     * @return Returns the simpleDateFormatForDate. <br>
     */
    private static SimpleDateFormat getSimpleDateFormatForDate() {
        return null == simpleDateFormatForDate ? new SimpleDateFormat(DATE_STR) : simpleDateFormatForDate;
    }

    /**
     * Get simpleDateFormatForDatetime <br>
     *
     * @return Returns the simpleDateFormatForDatetime. <br>
     */
    private static SimpleDateFormat getSimpleDateFormatForDatetime() {
        return null == simpleDateFormatForDatetime ? new SimpleDateFormat(DATETIME_STR) : simpleDateFormatForDatetime;
    }

    /**
     * Parses text from the beginning of the given string to produce a date.
     * The method may not use the entire text of the given string.
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        try {
            return getSimpleDateFormatForDate().parse(dateStr);
        } catch (ParseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parses text from the beginning of the given string to produce a date.
     * The method may not use the entire text of the given string.
     *
     * @param datetimeStr
     * @return
     */
    public static Date parseDatetime(String datetimeStr) {
        if (null == datetimeStr || datetimeStr.length() <= 0) {
            return null;
        }
        try {
            return getSimpleDateFormatForDatetime().parse(datetimeStr);
        } catch (ParseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formats a Date into a date/time string.
     *
     * @param dateObject
     * @return
     */
    public static String formatDate(Date dateObject) {
        if (null == dateObject) {
            return null;
        }
        return getSimpleDateFormatForDate().format(dateObject);
    }

    /**
     * Formats a Date into a date/time string.
     *
     * @param datetimeObject
     * @return
     */
    public static String formatDatetime(Date datetimeObject) {
        if (null == datetimeObject) {
            return null;
        }
        return getSimpleDateFormatForDatetime().format(datetimeObject);
    }

    /**
     * To get the current date and output as string format.
     *
     * @return
     */
    public static String getCurrentDateAsString() {
        return formatDate(new Date());
    }

    /**
     * To get the current datetime and output as string format.
     *
     * @return
     */
    public static String getCurrentDatetimeAsString() {
        return formatDatetime(new Date());
    }

    /**
     * 获取两个日期之间的天数 (精确到天)
     * firstDate > secondDate: 负数
     * firstDate = secondDate: 0
     * firstDate < secondDate: 正数
     *
     * @param firstDate Date
     * @param secondDate Date
     * @return Long
     */
    public static Integer getBetweenDays(Date firstDate, Date secondDate) {
        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(firstDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(secondDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;
    }

    /**
     * Checks if two date objects are on the same day ignoring time.
     * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same day
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * Checks if two calendar objects are on the same day ignoring time.
     * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same day
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * Adds a number of days to a date returning a new object. The original date object is unchanged.
     *
     * @param date the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     */
    public static Date addDays(Date date, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }

    /**
     * Adds a number of months to a date returning a new object. The original date object is unchanged.
     *
     * @param date the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     */
    public static Date addMonths(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        return cal.getTime();
    }

    /**
     * Adds a number of hours to a date returning a new object. The original date object is unchanged.
     *
     * @param date the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     */
    public static Date addHours(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, amount);
        return cal.getTime();
    }
}
