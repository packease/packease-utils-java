package com.aboutcoder.packease.utils.xml;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/8/16 3:07 PM<br />
 * @description <br />
 */
public class PEJsonXmlConfigFactory {
    /**
     * Place a singleton instance of PEJsonXMLConfig.
     */
    protected final PEJsonXMLConfig config;

    /**
     * Create a new factory.
     */
    public PEJsonXmlConfigFactory() {
        this(new PEJsonXMLConfig());
    }

    /**
     * Initial PEJsonXmlConfigFactory properties.
     *
     * @param config
     */
    protected PEJsonXmlConfigFactory(PEJsonXMLConfig config) {
        this.config = config;
    }

    /**
     * Build a new configuration.
     *
     * @return configuration instance
     */
    public PEJsonXMLConfig newPEJsonXmlConfigInstance() {
        return config.clone();
    }
}
