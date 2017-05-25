package com.aboutcoder.packease.utils.string;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 3:24 PM<br />
 * @description <br />
 */
public interface PEIBase64EncodeConfig {

    /**
     * <p>Default configuration:</p>
     * <ul>
     * <li><em>lineLength</em> - <code>0</code></li>
     * <li><em>lineSeparator</em> - <code>null</code></li>
     * <li><em>urlSafe</em> - <code>false</code></li>
     * <li><em>isChunked</em> - <code>false</code></li>
     * <li><em>maxResultSize</em> - <code>Integer.MAX_VALUE</code></li>
     * <li><em>encoding</em> - <code>UTF-8</code></li>
     * </ul>
     */
    public static final PEIBase64EncodeConfig DEFAULT = new PEIBase64EncodeConfig() {
         @Override
         public int getLineLength() {
             return 0;
         }

         @Override
         public byte[] getLineSeparator() {
             return null;
         }

         @Override
         public boolean isUrlSafe() {
             return false;
         }

         @Override
         public boolean isChunked() {
             return false;
         }

         @Override
         public int getMaxResultSize() {
             return Integer.MAX_VALUE;
         }

         @Override
         public String getEncoding() {
             return "UTF-8";
         }
     };

    /**
     * lineLength
     *
     * Each line of encoded data will be at most of the given length (rounded down to nearest multiple of
     * 4). If lineLength &lt;= 0, then the output will not be divided into lines (chunks). Ignored when
     * decoding.
     */
    int getLineLength();

    /**
     * Line separator for encoding. Not used when decoding. Only used if lineLength &gt; 0.
     * Each line of encoded data will end with this sequence of bytes.
     */
    byte[] getLineSeparator();

    /**
     * urlSafe
     *
     * Instead of emitting '+' and '/' we emit '-' and '_' respectively. urlSafe is only applied to encode
     * operations. Decoding seamlessly handles both modes.
     * <b>Note: no padding is added when using the URL-safe alphabet.</b>
     */
    boolean isUrlSafe();

    /**
     * Chunk separator per RFC 2045 section 2.1.
     * When encoding the line length is 76, the line separator is CRLF, and the encoding table is STANDARD_ENCODE_TABLE.
     *
     * @see PEBase64EncodeConfig#lineSeparator
     */
    boolean isChunked();

    /**
     * The maximum result size to accept.
     */
    int getMaxResultSize();

    /**
     * The charset for Base64Utils implementations.
     */
    String getEncoding();

}
