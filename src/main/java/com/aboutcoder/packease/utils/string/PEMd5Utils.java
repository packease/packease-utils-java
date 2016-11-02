package com.aboutcoder.packease.utils.string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 11:50 PM<br />
 * @description <br />
 */
public class PEMd5Utils {
    /**
     * md5 chars
     */
    private static final char[] MD5_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * To generate string of md5 encrypt.
     *
     * @param msg
     * @return
     */
    public static String generateMd5(String msg) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return byteArrayToString(digest.digest(msg.getBytes()));
    }

    /**
     * Convert byte array into a single string.
     *
     * @param md5Byte
     * @return
     */
    private static String byteArrayToString(byte[] md5Byte) {
        char[] resultChar = new char[md5Byte.length * 2];
        int index = 0;
        for (byte b : md5Byte) {
            /**高四位**/
            resultChar[index++] = MD5_CHAR[(b >>> 4) & 0xf];
            /**低四位**/
            resultChar[index++] = MD5_CHAR[b & 0xf];
        }
        return new String(resultChar);
    }
}
