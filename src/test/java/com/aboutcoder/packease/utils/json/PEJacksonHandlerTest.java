package com.aboutcoder.packease.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/13/16 11:31 AM<br />
 * @description <br />
 */
public class PEJacksonHandlerTest {

    /**
     * Define json string for comparing.
     */
    private final String testJsonString = "{\"id\":1,\"number\":2,\"name\":\"name\"}";

    @Test
    public void testGetObjectMapper() {
        ObjectMapper objectMapper = PEJacksonHandler.getObjectMapper();
        Assert.assertNotNull(objectMapper);
    }

    @Test
    public void testToJsonString() {
        PETestJsonClass testJsonClass = new PETestJsonClass();
        testJsonClass.initTestJsonClass(1, 2, "name");
        try {
            Assert.assertEquals(testJsonString, PEJacksonHandler.toJsonString(testJsonClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Assert.assertTrue("PEJacksonHandlerTest#testToJsonString JsonProcessingException occurs.", false);
        }
    }

    @Test
    public void testParseObject() {
        PETestJsonClass targetJsonClass = null;
        try {
            targetJsonClass = PEJacksonHandler.parseObject(testJsonString, PETestJsonClass.class);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue("PEJacksonHandlerTest#testParseObject IOException occurs.", false);
        }
        Assert.assertNotNull(targetJsonClass);
    }

    @Test
    public void testParseObjectViaTypeReference() {
        PETestJsonClass targetJsonClass = null;
        try {
            targetJsonClass = PEJacksonHandler.parseObject(testJsonString, new TypeReference<PETestJsonClass>(){});
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue("PEJacksonHandlerTest#testParseObjectViaTypeReference IOException occurs.", false);
        }
        Assert.assertNotNull(targetJsonClass);
    }
}
