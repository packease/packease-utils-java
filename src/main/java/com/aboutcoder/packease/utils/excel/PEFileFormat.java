package com.aboutcoder.packease.utils.excel;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 10:34 PM<br />
 * @description <br />
 */
public enum PEFileFormat {
    XLSX("xlsx"),
    XLS("xls");

    /**
     * file format
     */
    private String value;

    /**
     * Get value <br>
     *
     * @return Returns the value. <br>
     */
    public String getValue() {
        return value;
    }

    /**
     * Set value <br>
     *
     * @param value The value to set. <br>
     */
    public void setValue(String value) {
        this.value = value;
    }

    PEFileFormat(String value) {
        this.value = value;
    }
}
