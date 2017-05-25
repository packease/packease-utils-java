package com.aboutcoder.packease.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/2/16 12:09 AM<br />
 * @description <br />
 */
public class PEFastJsonHandler {

    /**
     * Convert object to JSON String.
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * Convert json string to specific object.
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * Convert json string to specific object via FastJson TypeReference.
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonString, TypeReference<T> type) {
        return JSON.parseObject(jsonString, type);
    }

    /**
     * Convert an object to FastJson JSONObject.
     *
     * @param object
     * @return
     */
    public static JSONObject toJsonObject(Object object) {
        if (object instanceof JSONObject) {
            return (JSONObject) object;
        }
        String jsonString = JSON.toJSONString(object);
        return JSON.parseObject(jsonString);
    }

    /**
     * Convert json string to FastJson Json Object.
     *
     * @param jsonString
     * @return
     */
    public static JSONObject toJsonObject(String jsonString) {
        return JSON.parseObject(jsonString);
    }
}
