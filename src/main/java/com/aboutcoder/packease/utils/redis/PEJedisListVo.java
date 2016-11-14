package com.aboutcoder.packease.utils.redis;

import java.io.Serializable;
import java.util.List;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 11/13/16 2:51 PM<br />
 * @description <br />
 */
public class PEJedisListVo<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -2099976016559461636L;

    private List<T> valueList;

    public PEJedisListVo(List<T> valueList) {
        this.valueList = valueList;
    }

    /**
     * Get valueList <br>
     *
     * @return Returns the valueList. <br>
     */
    public List<T> getValueList() {
        return valueList;
    }

    /**
     * Set valueList <br>
     *
     * @param valueList The valueList to set. <br>
     */
    public void setValueList(List<T> valueList) {
        this.valueList = valueList;
    }
}
