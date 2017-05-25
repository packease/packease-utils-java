package com.aboutcoder.packease.utils.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 1:11 AM<br />
 * @description <br />
 */
public class PEOutputStreamUtilsTest {
    @Test
    public void testConvertStringToOutputStream() {
        // IDE default charset is UTF-8
        String str = "hello world";
        OutputStream inputStreamWithoutCharset = PEOutputStreamUtils.convertStringToOutputStream(str);
        OutputStream inputStreamWithCharset = PEOutputStreamUtils.convertStringToOutputStream(str, "GB2312");

        Assert.assertNotNull(inputStreamWithoutCharset);
        Assert.assertNotNull(inputStreamWithCharset);
    }

    @Test
    public void testConvertOutputStreamToString() {
        try {
            // IDE default charset is UTF-8
            String str = "hello 世界";

            OutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(str.getBytes());
            String resultStrWithoutCharset = PEOutputStreamUtils.convertOutputStreamToString(outputStream);

            outputStream = new ByteArrayOutputStream();
            outputStream.write(str.getBytes());
            String resultStrWithCharset = PEOutputStreamUtils.convertOutputStreamToString(outputStream, "GB2312");

            Assert.assertFalse("Encoding isn't the same. One is GB2312, the other is UTF-8. - 01",
                    resultStrWithCharset.equals(new String(str.getBytes("UTF-8"), "UTF-8")));

            Assert.assertTrue("Encoding isn't the same. One is GB2312, the other is UTF-8. - 02",
                    resultStrWithCharset.equals(new String(str.getBytes("UTF-8"), "GB2312")));

            Assert.assertFalse(resultStrWithCharset.equals(resultStrWithoutCharset));
            Assert.assertTrue(resultStrWithoutCharset.equals(str));
            Assert.assertFalse(resultStrWithCharset.equals(str));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.assertTrue("UnsupportedEncodingException occurs.", false);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue("IOException occurs.", false);
        }
    }
}
