package com.aboutcoder.packease.utils.db;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 7/25/16 2:42 PM<br />
 * @description <br />
 */
public final class PEDbContextHolder {

    /**
     * contextHolder <br>
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

    /**
     * private constructor
     */
    private PEDbContextHolder() {

    }

    /**
     * Description: <br>
     *
     * @taskId <br>
     * @param dbType <br>
     */
    public static void setDbType(String dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    /**
     * Description: <br>
     *
     * @taskId <br>
     * @return <br>
     */
    public static String getDbType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * Description: <br>
     *
     * @taskId <br>
     * <br>
     */
    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }
}
