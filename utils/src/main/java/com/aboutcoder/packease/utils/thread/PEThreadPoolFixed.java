package com.aboutcoder.packease.utils.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 * <p>
 * peCorePoolSize    the number of threads to keep in the pool, even
 * if they are idle, unless {@code allowCoreThreadTimeOut} is set
 * peMaximumPoolSize the maximum number of threads to allow in the
 * pool
 * peKeepAliveTime   when the number of threads is greater than
 * the core, this is the maximum time that excess idle threads
 * will wait for new tasks before terminating.
 * peUnit            the time unit for the {@code keepAliveTime} argument
 * peWorkQueue       the queue to use for holding tasks before they are
 * executed.  This queue will hold only the {@code Runnable}
 * tasks submitted by the {@code execute} method.
 * peThreadFactory   the factory to use when the executor
 * creates a new thread
 * peRejectedExecutionHandler  the handler to use when execution is blocked
 * because the thread bounds and queue capacities are reached
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 11:50 PM<br />
 * @description <br />
 */
public class PEThreadPoolFixed {

    /**
     * These properties can be accessed and preset.
     */
    private int peCorePoolSize = 1;

    private int peMaximumPoolSize = 1;

    private long peKeepAliveTime = 0L;

    private int peBlockingQueueSize = 0;

    private boolean peAllowCoreThreadTimeOut = false;

    private RejectedExecutionHandler peRejectedExecutionHandler;

    /**
     * There properties should be protected by PEThreadPoolFixed.
     */
    private TimeUnit peUnit;

    private BlockingQueue<Runnable> peWorkQueue;

    private ThreadFactory peThreadFactory;

    /**
     * peThreadPoolExecutor is an instance of ThreadPoolExecutor service.
     * This could be accessed by its getter function.
     */
    private ThreadPoolExecutor peThreadPoolExecutor;

    /**
     * Define the default value of TimeUnit.
     *
     * @return
     */
    public static TimeUnit defaultTimeUnit() {
        return TimeUnit.MILLISECONDS;
    }

    /**
     * Define the default value of BlockingQueue use for ThreadPool.
     *
     * @return
     */
    public static BlockingQueue<Runnable> defaultBlockingQueue() {
        return new LinkedBlockingDeque<Runnable>();
    }

    /**
     * Define the default value of RejectedExecutionHandler parameter
     * which is one of the ThreadPoolExecutor initial parameters.
     *
     * @return
     */
    public static RejectedExecutionHandler defaultRejectedExecutionHandler() {
        return new ThreadPoolExecutor.CallerRunsPolicy() {
            /**
             * Executes task r in the caller's thread, unless the executor
             * has been shut down, in which case the task is discarded.
             *
             * @param r the runnable task requested to be executed
             * @param e the executor attempting to execute this task
             */
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                super.rejectedExecution(r, e);
                // System.out.println("r = [" + r + "], e = [" + e + "]");
                // System.out.println("Current running thread-----#" + Thread.currentThread().getName());
            }
        };
    }

    /**
     * Set peRejectedExecutionHandler <br>
     *
     * @param peRejectedExecutionHandler The peRejectedExecutionHandler to set. <br>
     */
    public void setPeRejectedExecutionHandler(RejectedExecutionHandler peRejectedExecutionHandler) {
        this.peRejectedExecutionHandler = peRejectedExecutionHandler;
    }

    /**
     * Get peRejectedExecutionHandler <br>
     *
     * @return Returns the peRejectedExecutionHandler. <br>
     */
    public RejectedExecutionHandler getPeRejectedExecutionHandler() {
        return peRejectedExecutionHandler;
    }

    /**
     * Get peThreadPoolExecutor <br>
     *
     * @return Returns the peThreadPoolExecutor. <br>
     */
    public ThreadPoolExecutor getPeThreadPoolExecutor() {
        return peThreadPoolExecutor;
    }

    /**
     * Set peCorePoolSize <br>
     *
     * @param peCorePoolSize The peCorePoolSize to set. <br>
     */
    public void setPeCorePoolSize(int peCorePoolSize) {
        this.peCorePoolSize = peCorePoolSize;
    }

    /**
     * Set peMaximumPoolSize <br>
     *
     * @param peMaximumPoolSize The peMaximumPoolSize to set. <br>
     */
    public void setPeMaximumPoolSize(int peMaximumPoolSize) {
        this.peMaximumPoolSize = peMaximumPoolSize;
    }

    /**
     * Set peKeepAliveTime <br>
     *
     * @param peKeepAliveTime The peKeepAliveTime to set. <br>
     */
    public void setPeKeepAliveTime(long peKeepAliveTime) {
        this.peKeepAliveTime = peKeepAliveTime;
    }

    /**
     * Set peBlockingQueueSize <br>
     *
     * @param peBlockingQueueSize The peBlockingQueueSize to set. <br>
     */
    public void setPeBlockingQueueSize(int peBlockingQueueSize) {
        this.peBlockingQueueSize = peBlockingQueueSize;
    }

    /**
     * Set peAllowCoreThreadTimeOut <br>
     *
     * @param peAllowCoreThreadTimeOut The peAllowCoreThreadTimeOut to set. <br>
     */
    public void setPeAllowCoreThreadTimeOut(boolean peAllowCoreThreadTimeOut) {
        this.peAllowCoreThreadTimeOut = peAllowCoreThreadTimeOut;
    }

    /**
     * Get peCorePoolSize <br>
     *
     * @return Returns the peCorePoolSize. <br>
     */
    public int getPeCorePoolSize() {
        return peCorePoolSize;
    }

    /**
     * Get peMaximumPoolSize <br>
     *
     * @return Returns the peMaximumPoolSize. <br>
     */
    public int getPeMaximumPoolSize() {
        return peMaximumPoolSize;
    }

    /**
     * Get peKeepAliveTime <br>
     *
     * @return Returns the peKeepAliveTime. <br>
     */
    public long getPeKeepAliveTime() {
        return peKeepAliveTime;
    }

    /**
     * Get peBlockingQueueSize <br>
     *
     * @return Returns the peBlockingQueueSize. <br>
     */
    public int getPeBlockingQueueSize() {
        return peBlockingQueueSize;
    }

    /**
     * Get peAllowCoreThreadTimeOut <br>
     *
     * @return Returns the peAllowCoreThreadTimeOut. <br>
     */
    public boolean isPeAllowCoreThreadTimeOut() {
        return peAllowCoreThreadTimeOut;
    }

    /**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters.
     * <p>
     * corePoolSize    the number of threads to keep in the pool, even
     * if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * maximumPoolSize the maximum number of threads to allow in the
     * pool
     * keepAliveTime   when the number of threads is greater than
     * the core, this is the maximum time that excess idle threads
     * will wait for new tasks before terminating.
     * unit            the time unit for the {@code keepAliveTime} argument
     * workQueue       the queue to use for holding tasks before they are
     * executed.  This queue will hold only the {@code Runnable}
     * tasks submitted by the {@code execute} method.
     * threadFactory   the factory to use when the executor
     * creates a new thread
     * handler         the handler to use when execution is blocked
     * because the thread bounds and queue capacities are reached
     *
     * @throws IllegalArgumentException if one of the following holds:<br>
     *                                  {@code corePoolSize < 0}<br>
     *                                  {@code keepAliveTime < 0}<br>
     *                                  {@code maximumPoolSize <= 0}<br>
     *                                  {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException     if {@code workQueue}
     *                                  or {@code threadFactory} or {@code handler} is null
     */
    public void initPEThreadPoolFixed() {
        beforeInit();
        peThreadPoolExecutor = new ThreadPoolExecutor(peCorePoolSize, peMaximumPoolSize, peKeepAliveTime, peUnit, peWorkQueue,
                peThreadFactory, peRejectedExecutionHandler);
        afterInit();
    }

    /**
     * Do some initial processes before creating
     * PEThreadPoolExecutor instance.
     */
    private void beforeInit() {
        if (peBlockingQueueSize == 0) {
            peWorkQueue = PEThreadPoolFixed.defaultBlockingQueue();
        } else {
            peWorkQueue = new LinkedBlockingDeque<Runnable>(peBlockingQueueSize);
        }

        if (null == peThreadFactory) {
            peThreadFactory = Executors.defaultThreadFactory();
        }

        if (null == peUnit) {
            peUnit = PEThreadPoolFixed.defaultTimeUnit();
        }

        if (null == peRejectedExecutionHandler) {
            peRejectedExecutionHandler = PEThreadPoolFixed.defaultRejectedExecutionHandler();
        }
    }

    /**
     * Do some initial processes after creating
     * PEThreadPoolExecutor instance.
     */
    private void afterInit() {
        peThreadPoolExecutor.allowCoreThreadTimeOut(peAllowCoreThreadTimeOut);
    }
}
