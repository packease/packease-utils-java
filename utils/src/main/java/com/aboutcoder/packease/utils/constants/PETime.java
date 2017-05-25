package com.aboutcoder.packease.utils.constants;

import java.util.concurrent.TimeUnit;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/10/16 3:54 PM<br />
 * @description <br />
 */
public class PETime {
    /**
     * Useful and common hours definitions
     */
    public final static long QUARTER_HOUR_AS_SECONDS = TimeUnit.MINUTES.toSeconds(15L);
    public final static long HALF_HOUR_AS_SECONDS = TimeUnit.MINUTES.toSeconds(30L);
    public final static long ONE_HOUR_AS_SECONDS = TimeUnit.HOURS.toSeconds(1L);

    /**
     * Useful and common days definitions
     */
    public final static long HALF_DAY_AS_SECONDS = TimeUnit.HOURS.toSeconds(12L);
    public final static long ONE_DAY_AS_SECONDS = TimeUnit.DAYS.toSeconds(1L);

    /**
     * Convert from day to second
     *
     * @param days
     * @return
     */
    public static long convertToSecondsFromDays(long days) {
        return TimeUnit.DAYS.toSeconds(days);
    }

    /**
     * Convert from hour to second
     *
     * @param hours
     * @return
     */
    public static long convertToSecondsFromHours(long hours) {
        return TimeUnit.HOURS.toSeconds(hours);
    }

    /**
     * Convert from second to millisecond
     *
     * @param seconds
     * @return
     */
    public static long convertToMillisFromSeconds(long seconds) {
        return TimeUnit.SECONDS.toMillis(seconds);
    }

    /**
     * Convert from second to microsecond
     *
     * @param seconds
     * @return
     */
    public static long convertToMicroFromSeconds(long seconds) {
        return TimeUnit.SECONDS.toMicros(seconds);
    }

    /**
     * Convert from second to nanosecond
     *
     * @param seconds
     * @return
     */
    public static long convertToNanosFromSeconds(long seconds) {
        return TimeUnit.SECONDS.toNanos(seconds);
    }
}
