package com.aboutcoder.packease.utils.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 1:11 AM<br />
 * @description <br />
 */
public class PEInputStreamUtilsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testConvertStringToInputStream() {
        // IDE default charset is UTF-8
        String str = "hello world";
        InputStream inputStreamWithoutCharset = PEInputStreamUtils.convertStringToInputStream(str);
        InputStream inputStreamWithCharset = PEInputStreamUtils.convertStringToInputStream(str, "GB2312");

        Assert.assertNotNull(inputStreamWithoutCharset);
        Assert.assertNotNull(inputStreamWithCharset);
    }

    @Test
    public void testConvertInputStreamToString() {
        // IDE default charset is UTF-8
        String str = "hello 世界";

        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        String resultStrWithoutCharset = PEInputStreamUtils.convertInputStreamToString(inputStream);

        inputStream = new ByteArrayInputStream(str.getBytes());
        String resultStrWithCharset = PEInputStreamUtils.convertInputStreamToString(inputStream, "GB2312");

        try {
            Assert.assertFalse("Encoding isn't the same. One is GB2312, the other is UTF-8. - 01",
                    resultStrWithCharset.equals(new String(str.getBytes("UTF-8"), "UTF-8")));

            Assert.assertTrue("Encoding isn't the same. One is GB2312, the other is UTF-8. - 02",
                    resultStrWithCharset.equals(new String(str.getBytes("UTF-8"), "GB2312")));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.assertTrue("UnsupportedEncodingException occurs.", false);
        }

        Assert.assertFalse(resultStrWithCharset.equals(resultStrWithoutCharset));
        Assert.assertTrue(resultStrWithoutCharset.equals(str));
        Assert.assertFalse(resultStrWithCharset.equals(str));
    }

    @Test
    public void testGetHttpResourceAsInputStream() throws Exception {
        String url = "http://www.baidu.com";
        InputStream httpResourceInputStream1 = PEInputStreamUtils.getHttpResourceAsInputStream(url, 2000, 5000);
        Assert.assertNotNull(httpResourceInputStream1);

        expectedException.expect(SocketTimeoutException.class);
        InputStream httpResourceInputStream2 = PEInputStreamUtils.getHttpResourceAsInputStream(url, 1, 1);
    }
}
