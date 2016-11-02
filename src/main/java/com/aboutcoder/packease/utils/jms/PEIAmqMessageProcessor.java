package com.aboutcoder.packease.utils.jms;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 22/09/2016 12:25 AM<br />
 * @description <br />
 */
public interface PEIAmqMessageProcessor {
    /**
     * Define a process function for business logic.
     *
     * @param jsonMessage
     */
    void process(String jsonMessage);
}
