package com.aboutcoder.packease.utils.string;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 6:05 PM<br />
 * @description <br />
 */
@RunWith(Parameterized.class)
public class PEBase64UtilsTest {

    @Parameterized.Parameter(value = 0)
    public String beforeBase64Encode;

    @Parameterized.Parameter(value = 1)
    public String afterBase64Encode;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testEncode() {
        try {
            PEBase64EncodeConfig base64EncodeConfig = new PEBase64EncodeConfig();
            Assert.assertEquals(PEBase64Utils.encode(beforeBase64Encode, base64EncodeConfig), afterBase64Encode);
        } catch (UnsupportedEncodingException e) {
            Assert.assertNotNull("There throws an UnsupportedEncodingException.", null);
        }
    }

    @Test
    public void testDecode() throws Exception {
        Assert.assertEquals(PEBase64Utils.decode(afterBase64Encode, "UTF-8"), beforeBase64Encode);

        expectedException.expect(UnsupportedEncodingException.class);
        PEBase64Utils.decode(afterBase64Encode, "UNSUPPORTED-ENCODING");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"hello world", "aGVsbG8gd29ybGQ="},
                {"hi China", "aGkgQ2hpbmE="}
        });
    }
}
