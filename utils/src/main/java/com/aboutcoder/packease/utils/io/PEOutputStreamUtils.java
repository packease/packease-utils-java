package com.aboutcoder.packease.utils.io;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/8/16 10:44 PM<br />
 * @description <br />
 */
public class PEOutputStreamUtils {

    /**
     * Convert String into OutputStream via ByteArrayInputStream class.
     *
     * @param str
     * @param encoding
     * encoding field contains only one item, other indexes will be skipped.
     * @return
     */
    public static OutputStream convertStringToOutputStream(String str, String... encoding) {
        if (null == str) {
            return null;
        }
        if (null == encoding || encoding.length <= 0) {
            encoding = new String[]{"UTF-8"};
        }
        try {
            OutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(str.getBytes(encoding[0]));
            return outputStream;
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convert OutputStream into String.
     *
     * @param outputStream
     * @param encoding
     * encoding field contains only one item, other indexes will be skipped.
     * @return
     */
    public static String convertOutputStreamToString(OutputStream outputStream, String... encoding) {
        if (null == outputStream) {
            return null;
        }
        if (null == encoding || encoding.length <= 0) {
            encoding = new String[]{"UTF-8"};
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(outputStream.toString().getBytes());
            return byteArrayOutputStream.toString(encoding[0]);
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
