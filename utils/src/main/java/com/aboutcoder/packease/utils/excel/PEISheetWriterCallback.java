package com.aboutcoder.packease.utils.excel;

import org.apache.poi.ss.usermodel.Row;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 10:30 PM<br />
 * @description <br />
 */
public interface PEISheetWriterCallback<T> {

    /**
     * Define sheet name
     *
     * @return
     */
    String getSheetName();

    /**
     * Process and collect data of rows.
     *
     * @param rowIndex
     * @param row
     * @param dataItem
     */
    void process(int rowIndex, Row row, T dataItem);
}
