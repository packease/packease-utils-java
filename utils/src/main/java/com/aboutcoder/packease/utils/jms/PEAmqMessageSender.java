package com.aboutcoder.packease.utils.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 22/09/2016 1:27 AM<br />
 * @description <br />
 */
public class PEAmqMessageSender {
    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(PEAmqMessageSender.class);

    /**
     * jmsTemplate
     */
    private JmsTemplate jmsTemplate;

    /**
     * Get jmsTemplate <br>
     *
     * @return Returns the jmsTemplate. <br>
     */
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    /**
     * Set jmsTemplate <br>
     *
     * @param jmsTemplate The jmsTemplate to set. <br>
     */
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Send message to specific destination defined in spring xml.
     *
     * @param destination
     * @param jsonMessage
     */
    public void sendMessage(Destination destination, final String jsonMessage) {
        logger.info("Send message to Queue#" + destination.toString() + "# with message #" + jsonMessage);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonMessage);
            }
        });
    }
}
