package com.aboutcoder.packease.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
     * @param firstDate  Date
     * @param secondDate Date
     * @return Long
     */
    public static Long getBetweenDays(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return null;
        }
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.clear();
        calendarStart.setTime(firstDate);
        int yearStart = calendarStart.get(Calendar.YEAR);
        int monthStart = calendarStart.get(Calendar.MONTH);
        int dayStart = calendarStart.get(Calendar.DAY_OF_MONTH);
        calendarStart.clear();
        calendarStart.set(yearStart, monthStart, dayStart);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.clear();
        calendarEnd.setTime(secondDate);
        int yearEnd = calendarEnd.get(Calendar.YEAR);
        int monthEnd = calendarEnd.get(Calendar.MONTH);
        int dayEnd = calendarEnd.get(Calendar.DAY_OF_MONTH);
        calendarEnd.clear();
        calendarEnd.set(yearEnd, monthEnd, dayEnd);


        return (calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取两个日期之间的毫秒数 (精确到ms)
     * firstDate > secondDate: 负数
     * firstDate = secondDate: 0
     * firstDate < secondDate: 正数
     *
     * @param firstDate  Date
     * @param secondDate Date
     * @return Long
     */
    public static Long getBetweenMilliseconds(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return null;
        }
        return secondDate.getTime() - firstDate.getTime();
    }

    /**
     * 获取两个日期之间的分钟数 (精确到min)
     * firstDate > secondDate: 负数
     * firstDate = secondDate: 0
     * firstDate < secondDate: 正数
     *
     * @param firstDate  Date
     * @param secondDate Date
     * @return Long
     */
    public static Long getBetweenMinutes(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return null;
        }
        return (secondDate.getTime() - firstDate.getTime()) / (60 * 1000);
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
     * @param date   the date, not null
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
     * @param date   the date, not null
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
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     */
    public static Date addHours(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, amount);
        return cal.getTime();
    }

    /**
     * Adds to a date returning a new object. The original date object is unchanged.
     *
     * @param date the date, not null
     * @param calendarField the calendar field to add to
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     */
    public static Date addCustomField(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 获取指定日期的当前月第一天
     *
     * @author chenjinlong 20160824
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDateStringOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期的当前月最后一天
     *
     * @author chenjinlong 20160824
     * @param year
     * @param month
     * @return
     */
    public static String getLastDateStringOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期的当前月第一天
     *
     * @author chenjinlong 20160824
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDateOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的当前月最后一天
     *
     * @author chenjinlong 20160824
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDateOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的月份
     *
     * @author chenjinlong 20160825
     * @param srcDate
     * @return
     */
    public static Integer getMonthNumberFromDate(Date srcDate) {
        if (null == srcDate) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定日期的年份
     *
     * @author chenjinlong 20160825
     * @param srcDate
     * @return
     */
    public static Integer getYearNumberFromDate(Date srcDate) {
        if (null == srcDate) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回指定日期的季度
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 获取指定日期所在周的第一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在周的最后一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的前(-)/后(+)amount周第一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @param amount
     * @return
     */
    public static Date getFirstDayOfAmountWeek(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) + amount);
    }

    /**
     * 取得当前日期所在周的前(-)/后(+)amount周最后一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @param amount
     * @return
     */
    public static Date getLastDayOfAmountWeek(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) + amount);
    }

    /**
     * 得到某年某周的第一天
     *
     * @author chenjinlong 20161025
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 获取某年某周的最后一天
     *
     * @author chenjinlong 20161025
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的前(-)/后(+)amount月的第一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getFirstDayOfAmountMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + amount, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的前(-)/后(+)amount月的最后一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getLastDayOfAmountMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + amount, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定日期的前(-)/后(+)amount季的第一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @param amount
     * @return
     */
    public static Date getFirstDayOfAmountQuarter(Date date, int amount) {
        Date firstDayOfCurrentQuarter = getFirstDayOfQuarter(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDayOfCurrentQuarter);

        calendar.add(Calendar.MONTH, amount * 3 + 1);
        return getFirstDayOfQuarter(calendar.getTime());
    }

    /**
     * 返回指定日期的前(-)/后(+)amount季的最后一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @param amount
     * @return
     */
    public static Date getLastDayOfAmountQuarter(Date date, int amount) {
        Date firstDayOfCurrentQuarter = getFirstDayOfQuarter(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDayOfCurrentQuarter);

        calendar.add(Calendar.MONTH, amount * 3 + 1);
        return getLastDayOfQuarter(calendar.getTime());
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @author chenjinlong 20161025
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = 0;
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @author chenjinlong 20161025
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @author chenjinlong 20161025
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = 0;
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @author chenjinlong 20161025
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @author chenjinlong 20161025
     * @param date
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @author chenjinlong 20161025
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = 0;
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 根据beginDate和endDate,离散出各个日期
     *
     * @author chenjinlong 20161025
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<Date> generateMultiDateList(Date beginDate, Date endDate) {
        List<Date> resultList = new ArrayList<Date>();
        if (isSameDay(beginDate, endDate)) {
            resultList.add(beginDate);
            return resultList;
        }
        if (getBetweenDays(beginDate, endDate) < 0) {
            return resultList;
        }
        while (getBetweenDays(beginDate, endDate) >= 0) {
            resultList.add(beginDate);
            beginDate = addDays(beginDate, 1);
        }
        return resultList;
    }

    /**
     * 根据beginDate和endDate,离散出各个星期
     *
     * @author chenjinlong 20161025
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<PEDateScope> generateDateScopeListAsWeek(Date beginDate, Date endDate) {
        List<PEDateScope> resultList = new ArrayList<PEDateScope>();
        if (isSameDay(beginDate, endDate)) {
            Date firstDayOfWeek = getFirstDayOfWeek(beginDate);
            Date lastDayOfWeek = getLastDayOfWeek(endDate);
            resultList.add(new PEDateScope(firstDayOfWeek, lastDayOfWeek));
            return resultList;
        }
        if (getBetweenDays(beginDate, endDate) < 0) {
            return resultList;
        }

        // 此处用于确保星期的完整性
        beginDate = getFirstDayOfWeek(beginDate);
        endDate = getLastDayOfWeek(endDate);

        while (getBetweenDays(beginDate, endDate) >= 0) {
            Date tmpEndDate = addDays(beginDate, 6);

            resultList.add(new PEDateScope(beginDate, tmpEndDate));

            beginDate = addDays(beginDate, 7);
        }
        return resultList;
    }

    /**
     * 根据beginDate和endDate,离散出各个月
     *
     * @author chenjinlong 20161025
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<PEDateScope> generateDateScopeListAsMonth(Date beginDate, Date endDate) {
        List<PEDateScope> resultList = new ArrayList<PEDateScope>();
        if (isSameDay(beginDate, endDate)) {
            Date firstDayOfMonth = getFirstDayOfMonth(beginDate);
            Date lastDayOfMonth = getLastDayOfMonth(endDate);
            resultList.add(new PEDateScope(firstDayOfMonth, lastDayOfMonth));
            return resultList;
        }
        if (getBetweenDays(beginDate, endDate) < 0) {
            return resultList;
        }

        // 此处用于确保月份的完整性
        beginDate = getFirstDayOfMonth(beginDate);
        endDate = getLastDayOfMonth(endDate);

        while (getBetweenDays(beginDate, endDate) >= 0) {
            Date tmpEndDate = getLastDayOfMonth(beginDate);

            resultList.add(new PEDateScope(beginDate, tmpEndDate));

            beginDate = getFirstDayOfAmountMonth(beginDate, 1);
        }
        return resultList;
    }

    /**
     * 根据beginDate和endDate,离散出各个季度
     *
     * @author chenjinlong 20161025
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<PEDateScope> generateDateScopeListAsQuarter(Date beginDate, Date endDate) {
        List<PEDateScope> resultList = new ArrayList<PEDateScope>();
        if (isSameDay(beginDate, endDate)) {
            Date firstDayOfQuarter = getFirstDayOfQuarter(beginDate);
            Date lastDayOfQuarter = getLastDayOfQuarter(endDate);
            resultList.add(new PEDateScope(firstDayOfQuarter, lastDayOfQuarter));
            return resultList;
        }
        if (getBetweenDays(beginDate, endDate) < 0) {
            return resultList;
        }

        // 此处用于确保月份的完整性
        beginDate = getFirstDayOfQuarter(beginDate);
        endDate = getLastDayOfQuarter(endDate);

        while (getBetweenDays(beginDate, endDate) >= 0) {
            Date tmpEndDate = getLastDayOfQuarter(beginDate);

            resultList.add(new PEDateScope(beginDate, tmpEndDate));

            beginDate = getFirstDayOfAmountQuarter(beginDate, 1);
        }
        return resultList;
    }

    /**
     * 校验指定日期checkDate是否在dateScope范围内
     *
     * @author chenjinlong 20161026
     * @param checkDate
     * @param PEDateScope
     * @return
     */
    public static boolean checkDateExistInScope(Date checkDate, PEDateScope PEDateScope) {
        if (null == checkDate
                || null == PEDateScope
                || (null == PEDateScope.getBeginDate() && null == PEDateScope.getEndDate())) {
            return false;
        }
        if (null != PEDateScope.getBeginDate() && null == PEDateScope.getEndDate()) {
            return getBetweenDays(PEDateScope.getBeginDate(), checkDate) >= 0;
        }

        if (null == PEDateScope.getBeginDate() && null != PEDateScope.getEndDate()) {
            return getBetweenDays(checkDate, PEDateScope.getEndDate()) >= 0;
        }

        return getBetweenDays(PEDateScope.getBeginDate(), checkDate) >= 0
                && getBetweenDays(checkDate, PEDateScope.getEndDate()) >= 0;
    }

    /**
     * 获取指定年月范围内的dateItem集合
     *
     * @author jeromechan 20170308
     * @param yearBegin
     * @param monthBegin
     * @param yearEnd
     * @param monthEnd
     * @return
     */
    public static List<PEDateItem> generateDateItemListAsMonth(short yearBegin, byte monthBegin,
                                                             short yearEnd, byte monthEnd) {
        if (yearEnd < yearBegin || (yearBegin == yearEnd && monthBegin > monthEnd)) {
            return null;
        }

        List<PEDateItem> resultList = new ArrayList<PEDateItem>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.YEAR, yearBegin);
        calendar.set(Calendar.MONTH, monthBegin - 1);

        // loop of year field.
        while (calendar.get(Calendar.YEAR) < yearEnd) {
            resultList.add(new PEDateItem((short)calendar.get(Calendar.YEAR),
                    (byte)(calendar.get(Calendar.MONTH) + 1)));
            calendar.add(Calendar.MONTH, 1);
        }

        // loop of month field when year equals each other.
        while (calendar.get(Calendar.YEAR) == yearEnd && calendar.get(Calendar.MONTH) < monthEnd) {
            resultList.add(new PEDateItem((short)calendar.get(Calendar.YEAR),
                    (byte)(calendar.get(Calendar.MONTH) + 1)));
            calendar.add(Calendar.MONTH, 1);
        }

        return resultList;
    }
}
