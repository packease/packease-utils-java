package com.aboutcoder.packease.utils.json;

import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 24/09/2016 2:46 PM<br />
 * @description <br />
 */
public class PEJsonPathFastJsonMappingProvider implements MappingProvider {
    /**
     * @param source        object to map
     * @param targetType    the type the source object should be mapped to
     * @param configuration current configuration
     * @return return the mapped object
     */
    @Override
    public <T> T map(Object source, Class<T> targetType, Configuration configuration) {
        String jsonString = JSON.toJSONString(source);
        return JSON.parseObject(jsonString, targetType);
    }

    /**
     * @param source        object to map
     * @param targetType    the type the source object should be mapped to
     * @param configuration current configuration
     * @return return the mapped object
     */
    @Override
    public <T> T map(Object source, TypeRef<T> targetType, Configuration configuration) {
        String jsonString = JSON.toJSONString(source);
        return JSON.parseObject(jsonString, targetType.getType());
    }
}
