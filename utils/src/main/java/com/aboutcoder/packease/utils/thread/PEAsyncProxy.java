package com.aboutcoder.packease.utils.thread;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 7:23 PM<br />
 * @description <br />
 */
public class PEAsyncProxy<T extends PEAsyncProxyParam> {

    /**
     * place the request which extends AsyncProxyParam,
     * meanwhile it also implements execute() function.
     */
    private T request;

    /**
     * place the callback functions which extends AsyncProxyParam,
     * meanwhile it also implements onSuccess() and onError().
     */
    private PEIAsyncProxyCallback asyncProxyCallback;

    /**
     * Constructor of AsyncProxy
     *
     * @param req
     * @param callback
     */
    public PEAsyncProxy(T req, PEIAsyncProxyCallback callback) {
        this.request = req;
        this.asyncProxyCallback = callback;
        new NewThread().run();
    }

    /**
     * An thread class definition for async invokings.
     */
    private class NewThread implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        public void run() {
            try {
                request.execute();
                asyncProxyCallback.onSuccess();
            } catch (Exception e) {
                asyncProxyCallback.onError();
            }
        }
    }
}
