package com.aboutcoder.packease.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/2/16 12:08 AM<br />
 * @description <br />
 */
public class PEJacksonHandler {

    /**
     * ObjectMapper init definition.
     */
    private static ObjectMapper objectMapper;

    /**
     * Generate singleton objectMapper.
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper;
        } else {
            return objectMapper;
        }
    }

    /**
     * Convert object to JSON String.
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(object);
    }

    /**
     * Convert json string to specific object.
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) throws IOException {
        return getObjectMapper().readValue(jsonString, clazz);
    }

    /**
     * Convert json string to specific object via FastJson TypeReference.
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonString, TypeReference<T> type) throws IOException {
        return getObjectMapper().readValue(jsonString, type);
    }
}
