package com.aboutcoder.packease.utils.json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.TypeRef;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 23/09/2016 4:53 PM<br />
 * @description <br />
 */
public class PEJsonPathHandler {

    /**
     * Read specific field of json string
     * via peDefaultConfiguration.
     *
     * @param json
     * @param pathExpression
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression) {
        return JsonPath
                .using(PEJsonPathConfig.peDefaultConfiguration())
                .parse(json)
                .read(pathExpression);
    }

    /**
     * Read specific field of json string
     * via peDefaultConfiguration.
     *
     * @param json
     * @param pathExpression
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, Class<T> clazz) {
        return JsonPath
                .using(PEJsonPathConfig.peDefaultConfiguration())
                .parse(json)
                .read(pathExpression, clazz);
    }

    /**
     * Read specific field of json string
     * via peDefaultConfiguration.
     *
     * @param json
     * @param pathExpression
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, Class<T> clazz, Predicate predicate) {
        return JsonPath
                .using(PEJsonPathConfig.peDefaultConfiguration())
                .parse(json)
                .read(pathExpression, clazz, predicate);
    }

    /**
     * Read specific field of json string
     * via peDefaultConfiguration.
     *
     * @param json
     * @param pathExpression
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, TypeRef<T> typeRef) {
        return JsonPath
                .using(PEJsonPathConfig.peDefaultConfiguration())
                .parse(json)
                .read(pathExpression, typeRef);
    }

    /**
     * Read specific field of json String with custom configurations.
     *
     * @param json
     * @param pathExpression
     * @param peJsonPathConfig
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, PEJsonPathConfig peJsonPathConfig) {
        Configuration configuration = peJsonPathConfig.getJsonPathConfiguration();
        return PEJsonPathHandler.readProperties(json, pathExpression, configuration);
    }

    /**
     * Read specific field of json String with custom configurations.
     *
     * @param json
     * @param pathExpression
     * @param configuration
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, Configuration configuration) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .read(pathExpression);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param peJsonPathConfig
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, PEJsonPathConfig peJsonPathConfig, Predicate predicate) {
        Configuration configuration = peJsonPathConfig.getJsonPathConfiguration();
        return PEJsonPathHandler.readProperties(json, pathExpression, configuration, predicate);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param configuration
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, Configuration configuration, Predicate predicate) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .read(pathExpression, predicate);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param peJsonPathConfig
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, PEJsonPathConfig peJsonPathConfig, Class<T> clazz) {
        Configuration configuration = peJsonPathConfig.getJsonPathConfiguration();
        return PEJsonPathHandler.readProperties(json, pathExpression, configuration, clazz);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param configuration
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, Configuration configuration, Class<T> clazz) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .read(pathExpression, clazz);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param peJsonPathConfig
     * @param clazz
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, PEJsonPathConfig peJsonPathConfig, Class<T> clazz, Predicate predicate) {
        Configuration configuration = peJsonPathConfig.getJsonPathConfiguration();
        return PEJsonPathHandler.readProperties(json, pathExpression, configuration, clazz, predicate);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param configuration
     * @param clazz
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, Configuration configuration, Class<T> clazz, Predicate predicate) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .read(pathExpression, clazz, predicate);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param peJsonPathConfig
     * @param typeRef
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, PEJsonPathConfig peJsonPathConfig, TypeRef<T> typeRef) {
        Configuration configuration = peJsonPathConfig.getJsonPathConfiguration();
        return PEJsonPathHandler.readProperties(json, pathExpression, configuration, typeRef);
    }

    /**
     * Read specific field of json String.
     *
     * @param json
     * @param pathExpression
     * @param configuration
     * @param typeRef
     * @param <T>
     * @return
     */
    public static <T> T readProperties(String json, String pathExpression, Configuration configuration, TypeRef<T> typeRef) {
        return JsonPath
                .using(configuration)
                .parse(json)
                .read(pathExpression, typeRef);
    }
}
