package com.aboutcoder.packease.utils.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Assert;
import org.junit.Test;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/13/16 11:31 AM<br />
 * @description <br />
 */
public class PEFastJsonHandlerTest {

    /**
     * Define json string for comparing.
     */
    private final String testJsonString = "{\"id\":1,\"name\":\"name\",\"number\":2}";

    @Test
    public void testToJsonString() {
        PETestJsonClass testJsonClass = new PETestJsonClass();
        testJsonClass.initTestJsonClass(1, 2, "name");
        String targetJsonString = PEFastJsonHandler.toJsonString(testJsonClass);
        Assert.assertEquals(testJsonString, targetJsonString);
    }

    @Test
    public void testParseObject() {
        PETestJsonClass targetJsonClass = PEFastJsonHandler.parseObject(testJsonString, PETestJsonClass.class);
        Assert.assertNotNull(targetJsonClass);
    }

    @Test
    public void testParseObjectTypeReference() {
        PETestJsonClass targetJsonClass = PEFastJsonHandler.parseObject(testJsonString, new TypeReference<PETestJsonClass>(){});
        Assert.assertNotNull(targetJsonClass);
    }

    @Test
    public void testToJsonObjectViaObject() {
        PETestJsonClass testJsonClass = new PETestJsonClass();
        testJsonClass.initTestJsonClass(1, 2, "name");
        JSONObject targetJsonObject = PEFastJsonHandler.toJsonObject(testJsonClass);
        Assert.assertNotNull(targetJsonObject);
    }

    @Test
    public void testToJsonObjectViaJsonString() {
        JSONObject targetJsonObject = PEFastJsonHandler.toJsonObject(testJsonString);
        Assert.assertNotNull(targetJsonObject);
    }
}
