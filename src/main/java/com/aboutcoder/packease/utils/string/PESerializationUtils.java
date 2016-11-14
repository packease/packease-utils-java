package com.aboutcoder.packease.utils.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 11/13/16 11:50 PM<br />
 * @description <br />
 */
public class PESerializationUtils {
    private final static Logger logger = LoggerFactory.getLogger(PESerializationUtils.class);

    /**
     * Do serializable job.
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            logger.error("SerializationUtil-serialize error:", e);
            return null;
        }
    }

    /**
     * Do deserializable job.
     *
     * @param bytes
     * @return
     */
    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error("SerializationUtil-unserialize error:", e);
            return null;
        }
    }

    /**
     * Do serializable job for single String.
     *
     * @param bytes
     * @return
     * @since JDK1.6+
     */
    public static String stringDeserialize(byte[] bytes) {
        return bytes == null ? null : new String(bytes, Charset.forName("UTF8"));
    }

    /**
     * Do deserializable job for single String.
     *
     * @param string
     * @return
     * @since JDK1.6+
     */
    public static byte[] stringSerialize(String string) {
        return string == null ? null : string.getBytes(Charset.forName("UTF8"));
    }
}
