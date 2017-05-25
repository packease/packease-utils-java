package com.aboutcoder.packease.utils.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/8/16 3:26 PM<br />
 * @description <br />
 */
public class PEInputStreamUtils {

    /**
     * Convert String into InputStream via ByteArrayInputStream class.
     *
     * @param str
     * @param encoding
     * encoding field contains only one item, other indexes will be skipped.
     * @return
     */
    public static InputStream convertStringToInputStream(String str, String... encoding) {
        if (null == str) {
            return null;
        }
        if (null == encoding || encoding.length <= 0) {
            encoding = new String[]{"UTF-8"};
        }
        try {
            return new ByteArrayInputStream(str.getBytes(encoding[0]));
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convert InputStream into String.
     *
     * @param inputStream
     * @param encoding
     * encoding field contains only one item, other indexes will be skipped.
     * @return
     */
    public static String convertInputStreamToString(InputStream inputStream, String... encoding) {
        if (null == inputStream) {
            return null;
        }
        if (null == encoding || encoding.length <= 0) {
            encoding = new String[]{"UTF-8"};
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = -1;
            while ((i = inputStream.read()) != -1) {
                byteArrayOutputStream.write(i);
            }
            return byteArrayOutputStream.toString(encoding[0]);
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * To get the InputStream from a specific WEB resource.
     *
     * @param httpResourceUrl
     * @param connectTimeOut
     * The unit of connectTimeOut is millisecond.
     * @param readTimeOut
     * The unit of readTimeOut is millisecond.
     * @return
     * @throws Exception
     */
    public static InputStream getHttpResourceAsInputStream(String httpResourceUrl, int connectTimeOut, int readTimeOut)
            throws Exception {
        URL url  = new URL(httpResourceUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(connectTimeOut);
        connection.setReadTimeout(readTimeOut);
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return connection.getInputStream();
        }
        return null;
    }

    /**
     * To get the InputStream from a specific local file path.
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getFileContentAsInputStream(String filePath) throws FileNotFoundException {
        if (!new File(filePath).exists()) {
            throw new FileNotFoundException();
        }
        return new FileInputStream(filePath);
    }
}
