package com.aboutcoder.packease.utils.excel;

import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 10:00 PM<br />
 * @description <br />
 */
public interface PEISheetReaderCallback<T> {

    /**
     * Process and collect data of rows.
     *
     * @param row
     * @param dataList
     */
    void process(Row row, List<T> dataList);
}
