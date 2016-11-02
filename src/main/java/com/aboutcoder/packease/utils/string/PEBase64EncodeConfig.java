package com.aboutcoder.packease.utils.string;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 2:51 PM<br />
 * @description <br />
 */
public class PEBase64EncodeConfig implements PEIBase64EncodeConfig {

    /**
     * Constructor and its initial values as default.
     */
    public PEBase64EncodeConfig() {
        this.lineLength = PEIBase64EncodeConfig.DEFAULT.getLineLength();
        this.lineSeparator = PEIBase64EncodeConfig.DEFAULT.getLineSeparator();
        this.urlSafe = PEIBase64EncodeConfig.DEFAULT.isUrlSafe();
        this.isChunked = PEIBase64EncodeConfig.DEFAULT.isChunked();
        this.maxResultSize = PEIBase64EncodeConfig.DEFAULT.getMaxResultSize();
        this.encoding = PEIBase64EncodeConfig.DEFAULT.getEncoding();
    }

    /**
     * lineLength
     *
     * Each line of encoded data will be at most of the given length (rounded down to nearest multiple of
     * 4). If lineLength &lt;= 0, then the output will not be divided into lines (chunks). Ignored when
     * decoding.
     */
    private int lineLength;

    /**
     * Line separator for encoding. Not used when decoding. Only used if lineLength &gt; 0.
     * Each line of encoded data will end with this sequence of bytes.
     */
    private byte[] lineSeparator;

    /**
     * urlSafe
     *
     * Instead of emitting '+' and '/' we emit '-' and '_' respectively. urlSafe is only applied to encode
     * operations. Decoding seamlessly handles both modes.
     * <b>Note: no padding is added when using the URL-safe alphabet.</b>
     */
    private boolean urlSafe;

    /**
     * Chunk separator per RFC 2045 section 2.1.
     * When encoding the line length is 76, the line separator is CRLF, and the encoding table is STANDARD_ENCODE_TABLE.
     *
     * @see PEBase64EncodeConfig#lineSeparator
     */
    private boolean isChunked;

    /**
     * The maximum result size to accept.
     */
    private int maxResultSize;

    /**
     * The charset for Base64Utils implementations.
     */
    private String encoding;

    public int getLineLength() {
        return lineLength;
    }

    /**
     * Set lineLength <br>
     *
     * @param lineLength The lineLength to set. <br>
     */
    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public byte[] getLineSeparator() {
        return lineSeparator;
    }

    /**
     * Set lineSeparator <br>
     *
     * @param lineSeparator The lineSeparator to set. <br>
     */
    public void setLineSeparator(byte[] lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public boolean isUrlSafe() {
        return urlSafe;
    }

    /**
     * Set urlSafe <br>
     *
     * @param urlSafe The urlSafe to set. <br>
     */
    public void setUrlSafe(boolean urlSafe) {
        this.urlSafe = urlSafe;
    }

    public boolean isChunked() {
        return isChunked;
    }

    /**
     * Set isChunked <br>
     *
     * @param isChunked The isChunked to set. <br>
     */
    public void setChunked(boolean chunked) {
        isChunked = chunked;
    }

    public int getMaxResultSize() {
        return maxResultSize;
    }

    /**
     * Set maxResultSize <br>
     *
     * @param maxResultSize The maxResultSize to set. <br>
     */
    public void setMaxResultSize(int maxResultSize) {
        this.maxResultSize = maxResultSize;
    }

    public String getEncoding() {
        return encoding;
    }

    /**
     * Set encoding <br>
     *
     * @param encoding The encoding to set. <br>
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
