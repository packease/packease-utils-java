package com.aboutcoder.packease.utils.date;

/**
 * <Description>
 * Copyright © 2006-2017 AboutCoder.COM, All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 08/03/2017 2:50 PM<br />
 * @description <br />
 */
public class PEDateItem {
    /**
     * year
     */
    private Short year;

    /**
     * month
     */
    private Byte month;

    /**
     * day of month
     */
    private Byte dayOfMonth;

    public PEDateItem() {
    }

    public PEDateItem(Short year, Byte month) {
        this.year = year;
        this.month = month;
    }

    public PEDateItem(Short year, Byte month, Byte dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * Get year <br>
     *
     * @return Returns the year. <br>
     */
    public Short getYear() {
        return year;
    }

    /**
     * Set year <br>
     *
     * @param year The year to set. <br>
     */
    public void setYear(Short year) {
        this.year = year;
    }

    /**
     * Get month <br>
     *
     * @return Returns the month. <br>
     */
    public Byte getMonth() {
        return month;
    }

    /**
     * Set month <br>
     *
     * @param month The month to set. <br>
     */
    public void setMonth(Byte month) {
        this.month = month;
    }

    /**
     * Get dayOfMonth <br>
     *
     * @return Returns the dayOfMonth. <br>
     */
    public Byte getDayOfMonth() {
        return dayOfMonth;
    }

    /**
     * Set dayOfMonth <br>
     *
     * @param dayOfMonth The dayOfMonth to set. <br>
     */
    public void setDayOfMonth(Byte dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
