package com.aboutcoder.packease.utils.constants;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/10/16 5:20 PM<br />
 * @description <br />
 *
 * All expression is not case-sensitive.
 * *      *     *     *   *    *   *
 * -      -     -     -   -    -   -
 * |      |     |     |   |    |   |
 * |      |     |     |   |    |   +----- year (optional position) (1970 - 2099 or empty or ', - * /')
 * |      |     |     |   |    +----- day of week (1 - 7 or 'SUN/MON/TUE/WED/THU/FRI/SAT' or ', - * ? / L C #') (SUN=1),
 * |      |     |     |   +------- month (1 - 12 or ', - * /')
 * |      |     |     +--------- day of month (1 - 31 or ', - * / ? L W C')
 * |      |     +----------- hour (0 - 23 or ', - * /')
 * |      +------------- min (0 - 59 or ', - * /')
 * +--------------- second (0 - 59 or ', - * /')
 *
 */
public class PESpringCronPattern {
    /**
     * Run once every minute.
     */
    public final static String EVERY_MINUTE = "0 0/1 * * * ? *";

    /**
     * Run once every a quarter of an hour.
     */
    public final static String EVERY_QUARTER = "0 0/15 * * * ? *";

    /**
     * Run once every half an hour.
     */
    public final static String EVERY_HALF_HOUR = "0 0/30 * * * ? *";

    /**
     * Run once every hour.
     */
    public final static String EVERY_HOUR = "0 0 0/1 * * ? *";
}
