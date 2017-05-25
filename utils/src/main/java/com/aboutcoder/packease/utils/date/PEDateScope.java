package com.aboutcoder.packease.utils.date;

import java.util.Date;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 26/10/2016 12:46 AM<br />
 * @description <br />
 */
public class PEDateScope {
    /**
     * begin date
     */
    private Date beginDate;

    /**
     * end date
     */
    private Date endDate;

    /**
     * Default constructor.
     */
    public PEDateScope() {
    }

    /**
     * Constructor with properties definitions.
     *
     * @param beginDate
     * @param endDate
     */
    public PEDateScope(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * Get beginDate <br>
     *
     * @return Returns the beginDate. <br>
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Set beginDate <br>
     *
     * @param beginDate The beginDate to set. <br>
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Get endDate <br>
     *
     * @return Returns the endDate. <br>
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set endDate <br>
     *
     * @param endDate The endDate to set. <br>
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
