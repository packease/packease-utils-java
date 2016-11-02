package com.aboutcoder.packease.utils.json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 24/09/2016 2:45 AM<br />
 * @description <br />
 */
public class PEJsonPathConfig {
    /**
     * This option makes JsonPath return null for missing leafs. Consider the following json
     */
    private boolean defaultPathLeafToNull;

    /**
     * This option configures JsonPath to return a list even when the path is definite.
     */
    private boolean alwaysReturnList;

    /**
     * This option makes sure no exceptions are propagated from path evaluation. It follows these simple rules:
     *
     * @see PEJsonPathConfig#alwaysReturnList
     * If option ALWAYS_RETURN_LIST is present an empty list will be returned
     * If option ALWAYS_RETURN_LIST is NOT present null returned
     */
    private boolean suppressExceptions;

    /**
     * In the Goessner implementation a JsonPath can return either Path or Value.
     * Value is the default and what all the examples above are returning.
     * If you rather have the path of the elements our query is hitting this can be acheived with this option.
     *
     * Value is default;
     */
    private boolean asPathList;

    /**
     * Configures JsonPath to require properties defined in path when an <bold>indefinite</bold> path is evaluated.
     */
    private  boolean requireProperties;

    /**
     * Json provider property.
     */
    private JsonProvider jsonProvider;

    /**
     * Json mapping property.
     */
    private MappingProvider mappingProvider;

    /**
     * Provides the default configuration example.
     *
     * @return
     */
    public static Configuration peDefaultConfiguration() {
        Set<Option> parseOptions = new HashSet<Option>();
        if (PEIJsonPathConfig.DEFAULT.isDefaultPathLeafToNull()) {
            parseOptions.add(Option.DEFAULT_PATH_LEAF_TO_NULL);
        }
        if (PEIJsonPathConfig.DEFAULT.isAlwaysReturnList()) {
            parseOptions.add(Option.ALWAYS_RETURN_LIST);
        }
        if (PEIJsonPathConfig.DEFAULT.isSuppressExceptions()) {
            parseOptions.add(Option.SUPPRESS_EXCEPTIONS);
        }
        if (PEIJsonPathConfig.DEFAULT.isAsPathList()) {
            parseOptions.add(Option.AS_PATH_LIST);
        }
        if (PEIJsonPathConfig.DEFAULT.isRequireProperties()) {
            parseOptions.add(Option.REQUIRE_PROPERTIES);
        }
        EnumSet<Option> optionEnumSet = EnumSet.copyOf(parseOptions);

        return Configuration
                .builder()
                .jsonProvider(PEIJsonPathConfig.DEFAULT.getJsonProvider())
                .mappingProvider(PEIJsonPathConfig.DEFAULT.getMappingProvider())
                .options(optionEnumSet)
                .build();
    }

    /**
     * Get the configuration of JsonPath via PEJsonPathConfig instance.
     *
     * @return
     */
    public Configuration getJsonPathConfiguration() {
        Set<Option> parseOptions = new HashSet<Option>();
        if (defaultPathLeafToNull) {
            parseOptions.add(Option.DEFAULT_PATH_LEAF_TO_NULL);
        }
        if (alwaysReturnList) {
            parseOptions.add(Option.ALWAYS_RETURN_LIST);
        }
        if (suppressExceptions) {
            parseOptions.add(Option.SUPPRESS_EXCEPTIONS);
        }
        if (asPathList) {
            parseOptions.add(Option.AS_PATH_LIST);
        }
        if (requireProperties) {
            parseOptions.add(Option.REQUIRE_PROPERTIES);
        }
        EnumSet<Option> optionEnumSet = EnumSet.copyOf(parseOptions);

        if (null == jsonProvider) {
            jsonProvider = PEIJsonPathConfig.DEFAULT.getJsonProvider();
        }
        if (null == mappingProvider) {
            mappingProvider = PEIJsonPathConfig.DEFAULT.getMappingProvider();
        }

        return Configuration
                .builder()
                .jsonProvider(jsonProvider)
                .mappingProvider(mappingProvider)
                .options(optionEnumSet)
                .build();
    }

    /**
     * Constructor of PEJsonPathConfig.
     */
    public PEJsonPathConfig() {
        setDefaultPathLeafToNull(true);
        setAlwaysReturnList(false);
        setSuppressExceptions(true);
        setAsPathList(false);
        setRequireProperties(false);
    }

    /**
     * Set defaultPathLeafToNull <br>
     *
     * @param defaultPathLeafToNull The defaultPathLeafToNull to set. <br>
     */
    public void setDefaultPathLeafToNull(boolean defaultPathLeafToNull) {
        this.defaultPathLeafToNull = defaultPathLeafToNull;
    }

    /**
     * Set alwaysReturnList <br>
     *
     * @param alwaysReturnList The alwaysReturnList to set. <br>
     */
    public void setAlwaysReturnList(boolean alwaysReturnList) {
        this.alwaysReturnList = alwaysReturnList;
    }

    /**
     * Set suppressExceptions <br>
     *
     * @param suppressExceptions The suppressExceptions to set. <br>
     */
    public void setSuppressExceptions(boolean suppressExceptions) {
        this.suppressExceptions = suppressExceptions;
    }

    /**
     * Set asPathList <br>
     *
     * @param asPathList The asPathList to set. <br>
     */
    public void setAsPathList(boolean asPathList) {
        this.asPathList = asPathList;
    }

    /**
     * Set requireProperties <br>
     *
     * @param requireProperties The requireProperties to set. <br>
     */
    public void setRequireProperties(boolean requireProperties) {
        this.requireProperties = requireProperties;
    }

    /**
     * Set jsonProvider <br>
     *
     * @param jsonProvider The jsonProvider to set. <br>
     */
    public void setJsonProvider(JsonProvider jsonProvider) {
        this.jsonProvider = jsonProvider;
    }

    /**
     * Set mappingProvider <br>
     *
     * @param mappingProvider The mappingProvider to set. <br>
     */
    public void setMappingProvider(MappingProvider mappingProvider) {
        this.mappingProvider = mappingProvider;
    }

    public interface PEIJsonPathConfig {
        /**
         * Default values of PEJsonPathConfig.
         */
        PEIJsonPathConfig DEFAULT = new PEIJsonPathConfig() {
            @Override
            public boolean isDefaultPathLeafToNull() {
                return true;
            }

            @Override
            public boolean isAlwaysReturnList() {
                return false;
            }

            @Override
            public boolean isSuppressExceptions() {
                return true;
            }

            @Override
            public boolean isAsPathList() {
                return false;
            }

            @Override
            public boolean isRequireProperties() {
                return false;
            }

            @Override
            public JsonProvider getJsonProvider() {
                return new PEJsonPathFastJsonProvider();
            }

            @Override
            public MappingProvider getMappingProvider() {
                return new PEJsonPathFastJsonMappingProvider();
            }
        };

        /**
         * defaultPathLeafToNull field.
         *
         * @return
         */
        boolean isDefaultPathLeafToNull();

        /**
         * alwaysReturnList field.
         *
         * @return
         */
        boolean isAlwaysReturnList();

        /**
         * suppressExceptions field.
         *
         * @return
         */
        boolean isSuppressExceptions();

        /**
         * asPathList field.
         *
         * @return
         */
        boolean isAsPathList();

        /**
         * requireProperties field.
         *
         * @return
         */
        boolean isRequireProperties();

        /**
         * jsonProvider field.
         *
         * @return
         */
        JsonProvider getJsonProvider();

        /**
         * mappingProvider field.
         *
         * @return
         */
        MappingProvider getMappingProvider();
    }
}
