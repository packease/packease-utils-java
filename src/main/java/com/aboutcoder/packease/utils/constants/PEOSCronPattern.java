package com.aboutcoder.packease.utils.constants;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/10/16 5:18 PM<br />
 * @description <br />
 *
 * *     *     *   *    *      command to be executed
 * -     -     -   -    -
 * |     |     |   |    |
 * |     |     |   |    +----- day of week (0 - 6) (Sunday=0)
 * |     |     |   +------- month (1 - 12)
 * |     |     +--------- day of        month (1 - 31)
 * |     +----------- hour (0 - 23)
 * +------------- min (0 - 59)
 *
 */
public class PEOSCronPattern {
    /**
     * Run once every minute.
     */
    public final static String EVERY_MINUTE = "*/1 * * * ? *";

    /**
     * Run once every a quarter of an hour.
     */
    public final static String EVERY_QUARTER = "*/15 * * * ? *";

    /**
     * Run once every half an hour.
     */
    public final static String EVERY_HALF_HOUR = "*/30 * * * ? *";

    /**
     * Run once every hour.
     */
    public final static String EVERY_HOUR = "0 */1 * * ? *";
}
