package com.aboutcoder.packease.utils.json;

import com.aboutcoder.packease.utils.io.PEInputStreamUtils;
import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.spi.json.AbstractJsonProvider;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 24/09/2016 11:43 AM<br />
 * @description <br />
 */
public class PEJsonPathFastJsonProvider extends AbstractJsonProvider {

    /**
     * Parse the given json string
     *
     * @param json json string to parse
     * @return Object representation of json
     * @throws InvalidJsonException
     */
    @Override
    public Object parse(String json) throws InvalidJsonException {
        return JSON.parseObject(json);
    }

    /**
     * Parse the given json string
     *
     * @param jsonStream input stream to parse
     * @param charset    charset to use
     * @return Object representation of json
     * @throws InvalidJsonException
     * @see PEInputStreamUtils#convertInputStreamToString(InputStream, String...)
     */
    @Override
    public Object parse(InputStream jsonStream, String charset) throws InvalidJsonException {
        String jsonString = PEInputStreamUtils.convertInputStreamToString(jsonStream, charset);
        if (null == jsonString) {
            return null;
        } else {
            return JSON.parseObject(jsonString);
        }
    }

    /**
     * Convert given json object to a json string
     *
     * @param obj object to transform
     * @return json representation of object
     */
    @Override
    public String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * Creates a provider specific json array
     *
     * @return new array
     */
    @Override
    public Object createArray() {
        return new LinkedList<Object>();
    }

    /**
     * Creates a provider specific json object
     *
     * @return new object
     */
    @Override
    public Object createMap() {
        return new LinkedHashMap<String, Object>();
    }
}
