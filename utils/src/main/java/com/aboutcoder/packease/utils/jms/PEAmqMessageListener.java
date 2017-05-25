package com.aboutcoder.packease.utils.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 22/09/2016 12:10 AM<br />
 * @description <br />
 */
public class PEAmqMessageListener implements MessageListener {
    private final static Logger logger = LoggerFactory.getLogger(PEAmqMessageListener.class);

    /**
     * PEAmqMessageProcessor
     */
    private PEIAmqMessageProcessor amqMessageProcessor;

    /**
     * Get amqMessageProcessor <br>
     *
     * @return Returns the amqMessageProcessor. <br>
     */
    public PEIAmqMessageProcessor getAmqMessageProcessor() {
        return amqMessageProcessor;
    }

    /**
     * Set amqMessageProcessor <br>
     *
     * @param amqMessageProcessor The amqMessageProcessor to set. <br>
     */
    public void setAmqMessageProcessor(PEIAmqMessageProcessor amqMessageProcessor) {
        this.amqMessageProcessor = amqMessageProcessor;
    }

    public void onMessage(Message message) {
        String jsonMessage = null;
        if (message instanceof TextMessage) {
            try {
                jsonMessage = ((TextMessage) message).getText();

                // Processing business logic.
                amqMessageProcessor.process(jsonMessage);
                logger.info("PEAmqMessageListener-TextMessage===>" + jsonMessage);
            } catch (Exception e) {
                logger.error("PEAmqMessageListener-Exception===>", e);
            }
        }
    }
}
