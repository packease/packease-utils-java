package com.aboutcoder.packease.utils.redis;

import java.io.Serializable;
import java.util.Map;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 11/13/16 2:51 PM<br />
 * @description <br />
 */
public class PEJedisMapVo<K, V extends Serializable> implements Serializable {
    private static final long serialVersionUID = -2091808016523468635L;

    private Map<K, V> valueMap;

    public PEJedisMapVo(Map<K, V> valueMap) {
        this.valueMap = valueMap;
    }

    /**
     * Get valueMap <br>
     *
     * @return Returns the valueMap. <br>
     */
    public Map<K, V> getValueMap() {
        return valueMap;
    }

    /**
     * Set valueMap <br>
     *
     * @param valueMap The valueMap to set. <br>
     */
    public void setValueMap(Map<K, V> valueMap) {
        this.valueMap = valueMap;
    }
}
