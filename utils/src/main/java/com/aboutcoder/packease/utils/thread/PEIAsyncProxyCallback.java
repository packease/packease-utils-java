package com.aboutcoder.packease.utils.thread;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 8:35 PM<br />
 * @description <br />
 */
public interface PEIAsyncProxyCallback {
    /**
     * Callback function when finishing invoking job successfully.
     */
    void onSuccess();

    /**
     * Callback function when errors occur.
     */
    void onError();
}
