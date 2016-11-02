package com.aboutcoder.packease.utils.date;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 6:18 PM<br />
 * @description <br />
 */
public class PEDateUtilsTest {

    @Test
    public void testParseDate() {
        String dateStr = "2016-01-01";
        Date dateObject = PEDateUtils.parseDate(dateStr);
        Assert.assertNotNull(dateObject);

        Calendar calendarSrc = Calendar.getInstance();
        calendarSrc.setTime(dateObject);

        Calendar calendarTarget = Calendar.getInstance();
        calendarTarget.set(2016, 1, 1);

        Assert.assertTrue(calendarTarget.get(Calendar.YEAR) == calendarSrc.get(Calendar.YEAR));
        Assert.assertTrue(calendarTarget.get(Calendar.MONTH) - 1 == calendarSrc.get(Calendar.MONTH));
        Assert.assertTrue(calendarTarget.get(Calendar.DATE) == calendarSrc.get(Calendar.DATE));
    }

    @Test
    public void testParseDatetime() {
        String dateStr = "2016-01-01 00:00:00";
        Date dateObject = PEDateUtils.parseDatetime(dateStr);
        Assert.assertNotNull(dateObject);

        Calendar calendarSrc = Calendar.getInstance();
        calendarSrc.setTime(dateObject);

        Calendar calendarTarget = Calendar.getInstance();
        calendarTarget.set(2016, 1, 1, 0, 0, 0);

        Assert.assertTrue(calendarTarget.get(Calendar.YEAR) == calendarSrc.get(Calendar.YEAR));
        Assert.assertTrue(calendarTarget.get(Calendar.MONTH) - 1 == calendarSrc.get(Calendar.MONTH));
        Assert.assertTrue(calendarTarget.get(Calendar.DATE) == calendarSrc.get(Calendar.DATE));
        Assert.assertTrue(calendarTarget.get(Calendar.HOUR_OF_DAY) == calendarSrc.get(Calendar.HOUR_OF_DAY));
        Assert.assertTrue(calendarTarget.get(Calendar.MINUTE) == calendarSrc.get(Calendar.MINUTE));
        Assert.assertTrue(calendarTarget.get(Calendar.SECOND) == calendarSrc.get(Calendar.SECOND));
    }

    @Test
    public void testFormatDate() {
        Calendar calendarTarget = Calendar.getInstance();
        calendarTarget.set(2016, 1, 1, 0, 0, 0);

        Date dateSrc = calendarTarget.getTime();
        String dateStrTarget = PEDateUtils.formatDate(dateSrc);

        Assert.assertTrue(dateStrTarget.equals("2016-02-01"));
    }

    @Test
    public void testFormatDatetime() {
        Calendar calendarTarget = Calendar.getInstance();
        calendarTarget.set(2016, 1, 1, 0, 0, 0);

        Date dateSrc = calendarTarget.getTime();
        String dateStrTarget = PEDateUtils.formatDatetime(dateSrc);

        Assert.assertTrue(dateStrTarget.equals("2016-02-01 00:00:00"));
    }

    @Test
    public void testGetCurrentDateAsString() {
        String dateStr = PEDateUtils.formatDate(new Date());
        String dateStrTarget = PEDateUtils.getCurrentDateAsString();
        Assert.assertTrue(dateStr.equals(dateStrTarget));
    }

    @Test
    public void testGetCurrentDatetimeAsString() {
        String dateStrTarget = PEDateUtils.getCurrentDatetimeAsString();
        Assert.assertNotNull(dateStrTarget);
    }

    @Test
    public void testGetBetweenDays() {
        Calendar calendarFirst = Calendar.getInstance();
        calendarFirst.set(2016, 1, 1, 0, 0, 0);

        Calendar calendarSecond = Calendar.getInstance();
        calendarSecond.set(2016, 1, 10, 0, 0, 0);

        int daysGtZero = PEDateUtils.getBetweenDays(calendarFirst.getTime(), calendarSecond.getTime());
        Assert.assertTrue(daysGtZero > 0 && daysGtZero == 9);
        int daysLtZero = PEDateUtils.getBetweenDays(calendarSecond.getTime(), calendarFirst.getTime());
        Assert.assertTrue(daysLtZero < 0 && daysLtZero == -9);
    }

    @Test
    public void testIsSameDayDate() {
        Calendar calendarFirst = Calendar.getInstance();
        calendarFirst.set(2016, 1, 1, 0, 0, 0);

        Calendar calendarSecond = Calendar.getInstance();
        calendarSecond.set(2016, 1, 1, 0, 0, 0);

        boolean result = PEDateUtils.isSameDay(calendarFirst.getTime(), calendarSecond.getTime());
        Assert.assertTrue(result);
    }

    @Test
    public void testIsSameDayCalendar() {
        Calendar calendarFirst = Calendar.getInstance();
        calendarFirst.set(2016, 1, 1, 0, 0, 0);

        Calendar calendarSecond = Calendar.getInstance();
        calendarSecond.set(2016, 1, 1, 0, 0, 0);

        boolean result = PEDateUtils.isSameDay(calendarFirst, calendarSecond);
        Assert.assertTrue(result);
    }

    @Test
    public void testAddDays() {
        Calendar calendarFirst = Calendar.getInstance();
        calendarFirst.set(2016, 1, 1, 0, 0, 0);

        Calendar calendarSecond = Calendar.getInstance();
        calendarSecond.set(2016, 1, 10, 0, 0, 0);

        Date dateSrc1 = PEDateUtils.addDays(calendarFirst.getTime(), 9);
        Calendar calendarCheck = Calendar.getInstance();
        calendarCheck.setTime(dateSrc1);

        Assert.assertTrue(calendarCheck.get(Calendar.YEAR) == calendarSecond.get(Calendar.YEAR));
        Assert.assertTrue(calendarCheck.get(Calendar.MONTH) == calendarSecond.get(Calendar.MONTH));
        Assert.assertTrue(calendarCheck.get(Calendar.DATE) == calendarSecond.get(Calendar.DATE));

        Date dateSrc2 = PEDateUtils.addDays(calendarSecond.getTime(), -9);
        calendarCheck.setTime(dateSrc2);
        Assert.assertTrue(calendarCheck.get(Calendar.YEAR) == calendarFirst.get(Calendar.YEAR));
        Assert.assertTrue(calendarCheck.get(Calendar.MONTH) == calendarFirst.get(Calendar.MONTH));
        Assert.assertTrue(calendarCheck.get(Calendar.DATE) == calendarFirst.get(Calendar.DATE));

        Date dateSrc3 = PEDateUtils.addDays(calendarSecond.getTime(), 0);
        calendarCheck.setTime(dateSrc3);
        Assert.assertTrue(calendarCheck.get(Calendar.YEAR) == calendarSecond.get(Calendar.YEAR));
        Assert.assertTrue(calendarCheck.get(Calendar.MONTH) == calendarSecond.get(Calendar.MONTH));
        Assert.assertTrue(calendarCheck.get(Calendar.DATE) == calendarSecond.get(Calendar.DATE));
    }

    @Test
    public void testAddMonths() {
        Calendar calendarFirst = Calendar.getInstance();
        calendarFirst.set(2016, 1, 1, 0, 0, 0);

        Calendar calendarSecond = Calendar.getInstance();
        calendarSecond.set(2016, 2, 1, 0, 0, 0);

        Date dateSrc1 = PEDateUtils.addMonths(calendarFirst.getTime(), 1);
        Calendar calendarCheck = Calendar.getInstance();
        calendarCheck.setTime(dateSrc1);

        Assert.assertTrue(calendarCheck.get(Calendar.YEAR) == calendarSecond.get(Calendar.YEAR));
        Assert.assertTrue(calendarCheck.get(Calendar.MONTH) == calendarSecond.get(Calendar.MONTH));

        Date dateSrc2 = PEDateUtils.addMonths(calendarSecond.getTime(), -1);
        calendarCheck.setTime(dateSrc2);
        Assert.assertTrue(calendarCheck.get(Calendar.YEAR) == calendarFirst.get(Calendar.YEAR));
        Assert.assertTrue(calendarCheck.get(Calendar.MONTH) == calendarFirst.get(Calendar.MONTH));

        Date dateSrc3 = PEDateUtils.addMonths(calendarSecond.getTime(), 0);
        calendarCheck.setTime(dateSrc3);
        Assert.assertTrue(calendarCheck.get(Calendar.YEAR) == calendarSecond.get(Calendar.YEAR));
        Assert.assertTrue(calendarCheck.get(Calendar.MONTH) == calendarSecond.get(Calendar.MONTH));
    }

    @Test
    public void testAddHours() {
        Calendar calendarFirst = Calendar.getInstance();
        calendarFirst.set(2016, 1, 1, 0, 0, 0);

        Calendar calendarSecond = Calendar.getInstance();
        calendarSecond.set(2016, 1, 1, 12, 0, 0);

        Date dateSrc1 = PEDateUtils.addHours(calendarFirst.getTime(), 12);
        Calendar calendarCheck = Calendar.getInstance();
        calendarCheck.setTime(dateSrc1);

        Assert.assertTrue(calendarCheck.get(Calendar.HOUR_OF_DAY) == calendarSecond.get(Calendar.HOUR_OF_DAY));

        Date dateSrc2 = PEDateUtils.addHours(calendarSecond.getTime(), -12);
        calendarCheck.setTime(dateSrc2);
        Assert.assertTrue(calendarCheck.get(Calendar.HOUR_OF_DAY) == calendarFirst.get(Calendar.HOUR_OF_DAY));

        Date dateSrc3 = PEDateUtils.addHours(calendarSecond.getTime(), 0);
        calendarCheck.setTime(dateSrc3);
        Assert.assertTrue(calendarCheck.get(Calendar.HOUR_OF_DAY) == calendarSecond.get(Calendar.HOUR_OF_DAY));
    }
}
