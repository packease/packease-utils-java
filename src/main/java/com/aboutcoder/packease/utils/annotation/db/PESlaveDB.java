package com.aboutcoder.packease.utils.annotation.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 6:33 PM<br />
 * @description <br />
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface PESlaveDB {

    /**
     * name <br>
     */
    String name() default "";

    /**
     * desc <br>
     */
    String desc() default "";
}
