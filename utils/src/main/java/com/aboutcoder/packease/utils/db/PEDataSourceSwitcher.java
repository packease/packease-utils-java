package com.aboutcoder.packease.utils.db;

import org.aspectj.lang.JoinPoint;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 6:37 PM<br />
 * @description <br />
 */
public class PEDataSourceSwitcher {

    /**
     * define the master database node
     */
    private String masterDB;

    /**
     * define the slave database node
     */
    private String slaveDB;

    /**
     * Get masterDB <br>
     *
     * @return Returns the masterDB. <br>
     */
    public String getMasterDB() {
        return masterDB;
    }

    /**
     * Set masterDB <br>
     *
     * @param masterDB The masterDB to set. <br>
     */
    public void setMasterDB(String masterDB) {
        this.masterDB = masterDB;
    }

    /**
     * Get slaveDB <br>
     *
     * @return Returns the slaveDB. <br>
     */
    public String getSlaveDB() {
        return slaveDB;
    }

    /**
     * Set slaveDB <br>
     *
     * @param slaveDB The slaveDB to set. <br>
     */
    public void setSlaveDB(String slaveDB) {
        this.slaveDB = slaveDB;
    }

    /**
     * Switch to the master db
     *
     * @param joinPoint
     */
    public void toMaster(JoinPoint joinPoint) {
        PEDbContextHolder.setDbType(masterDB);
    }

    /**
     * Switch to the slave db
     *
     * @param joinPoint
     */
    public void toSlave(JoinPoint joinPoint) {
        PEDbContextHolder.setDbType(slaveDB);
    }
}
