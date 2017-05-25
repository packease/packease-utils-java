package com.aboutcoder.packease.utils.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 7/25/16 2:41 PM<br />
 * @description <br />
 */
public class PEDynamicDataSource extends AbstractRoutingDataSource {
    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    protected Object determineCurrentLookupKey() {
        return PEDbContextHolder.getDbType();
    }
}
