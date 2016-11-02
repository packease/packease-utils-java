package com.aboutcoder.packease.utils.string;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 2:40 PM<br />
 * @description <br />
 */
public class PEBase64Utils {

    /**
     * Encodes binary data using the base64 algorithm.
     *
     * @param str
     * @param peBase64Config
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str, PEBase64EncodeConfig... peBase64Config) throws UnsupportedEncodingException {
        PEBase64EncodeConfig base64Config;
        if (null == peBase64Config || peBase64Config.length <= 0) {
            base64Config = new PEBase64EncodeConfig();
        } else {
            base64Config = peBase64Config[0];
        }
        byte[] preProcessBytes = null;
        if (null != base64Config.getEncoding()) {
            preProcessBytes = str.getBytes(base64Config.getEncoding());
        } else {
            preProcessBytes = str.getBytes("UTF-8");
        }

        byte[] result = Base64.encodeBase64(preProcessBytes, base64Config.isChunked(),
                base64Config.isUrlSafe(), base64Config.getMaxResultSize());
        return new String(result, base64Config.getEncoding());
    }

    /**
     * Decodes a Base64 String into octets.
     *
     * @param str
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String decode(String str, String... encoding) throws Exception {
        if (!Base64.isBase64(str)) {
            throw new IllegalArgumentException("Current string parameter isn't valid characters within the Base64 alphabet.");
        }
        if (null == encoding || encoding.length <= 0) {
            encoding = new String[]{"UTF-8"};
        }
        try {
            byte[] result = Base64.decodeBase64(str.getBytes(encoding[0]));
            return new String(result, encoding[0]);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("The charset/encoding isn't available for parsing.");
        }
    }
}
