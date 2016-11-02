package com.aboutcoder.packease.utils.thread;

import com.google.common.base.Function;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/14/16 10:25 AM<br />
 * @description <br />
 */
public class PEThreadPoolFixedTest {
    final static int sumDigit = 3;

    @Test
    public void testThreadCallbackViaFutureAndCallable() {
        // prepare parameter
        Task task = new Task();

        // add task into thread pool
        PEThreadPoolFixed peThreadPoolFixed = new PEThreadPoolFixed();
        peThreadPoolFixed.initPEThreadPoolFixed();
        Future<Integer> future = peThreadPoolFixed.getPeThreadPoolExecutor().submit(task);

        // pause the main thread, waiting for the sub thread's response.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread is running.");

        try {
            System.out.println("To get the response of sub thread:-----" + future.get());

            // assert the response as expected.
            Assert.assertTrue(future.get() == PEThreadPoolFixedTest.sumDigit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.assertTrue("Exceptions occurs.", false);
        } catch (ExecutionException e) {
            e.printStackTrace();
            Assert.assertTrue("Exceptions occurs.", false);
        }
    }

    /**
     * The generic type of Callable defines the response type of the task.
     *
     * @see java.util.concurrent.Callable
     * @see PEThreadPoolFixedTest#testThreadCallbackViaFutureAndCallable
     * @see PEThreadPoolFixedTest#testThreadCallbackViaFutureTaskAndCallable
     * @see PEThreadPoolFixedTest#testThreadCallbackViaGuavaFuture
     */
    private class Task implements Callable<Integer> {
        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Integer call() throws Exception {
            System.out.println("Thread is running and calculating...#-----" +
                    Thread.currentThread().getName());

            // To set the current thread delay for executing.
            Thread.sleep(5000);

            int sum = PEThreadPoolFixedTest.sumDigit;
            for (int i = 1; i <= sum; i++) {
                System.out.println("calculating:-----" + i);
            }
            return sum;
        }
    }

    @Test
    public void testThreadCallbackViaFutureTaskAndCallable() {
        // prepare parameter
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);

        // add task into thread pool
        PEThreadPoolFixed peThreadPoolFixed = new PEThreadPoolFixed();
        peThreadPoolFixed.initPEThreadPoolFixed();
        peThreadPoolFixed.getPeThreadPoolExecutor().submit(futureTask);

        // pause the main thread, waiting for the sub thread's response.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread is running.");

        try {
            System.out.println("To get the response of sub thread:-----" + futureTask.get());

            // assert the response as expected.
            Assert.assertTrue(futureTask.get() == PEThreadPoolFixedTest.sumDigit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.assertTrue("Exceptions occurs.", false);
        } catch (ExecutionException e) {
            e.printStackTrace();
            Assert.assertTrue("Exceptions occurs.", false);
        }
    }

    @Test
    public void testThreadCallbackViaGuavaFuture() {

        PEThreadPoolFixed peThreadPoolFixed = new PEThreadPoolFixed();
        peThreadPoolFixed.setPeCorePoolSize(2);
        peThreadPoolFixed.setPeMaximumPoolSize(2);
        peThreadPoolFixed.initPEThreadPoolFixed();
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(peThreadPoolFixed.getPeThreadPoolExecutor());

        // add ListenableFutureTask into pool
        Task task1 = new Task();
        ListenableFutureTask listenableFuture1 = ListenableFutureTask.create(task1);
        listeningExecutorService.submit(listenableFuture1);

        // add ListenableFuture into pool
        Task task2 = new Task();
        ListenableFuture listenableFuture2 = listeningExecutorService.submit(task2);

        // generate Future set
        final ListenableFuture allFutures = Futures.allAsList(listenableFuture1, listenableFuture2);

        // transform Future set

        // These use the async strategy for transform the response values.
        //
        // final ListenableFuture transformFutures = Futures.transformAsync(allFutures, new AsyncFunction() {
        //     @Override
        //     public ListenableFuture apply(Object input) throws Exception {
        //         return null;
        //     }
        // });
        //
        // These use the sync strategy for transform the response values.
        //
        final ListenableFuture transformFutures = Futures.transform(allFutures, new Function() {
            @Override
            public Object apply(Object input) {
                return null;
            }
        });

        // pause the main thread, waiting for the sub thread's response.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread is running.");

        try {
            Futures.addCallback(transformFutures, new FutureCallback() {
                @Override
                public void onSuccess(Object result) {
                    System.out.println("To get the response of sub thread:-----onSuccess");
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("To get the response of sub thread:-----onFailure");
                    Assert.assertTrue("Throwable occurs.", false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue("Exceptions occurs.", false);
        }
    }

    /**
     * @see PEThreadPoolFixedTest#testThreadRejectedHandler
     * @see java.lang.Runnable
     */
    private class TaskRunnable implements Runnable {

        private int milliseconds;

        public TaskRunnable(int milliseconds) {
            this.milliseconds = milliseconds;
        }

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
        @Override
        public void run() {
            System.out.println("Thread is running and calculating...#" + this.milliseconds +
                    "#-----" + Thread.currentThread().getName());

            // To set the current thread delay for executing.
            try {
                Thread.sleep(this.milliseconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testThreadRejectedHandler() {
        try {
            TaskRunnable task1 = new TaskRunnable(70000);
            TaskRunnable task2 = new TaskRunnable(60000);
            TaskRunnable task3 = new TaskRunnable(50000);
            TaskRunnable task4 = new TaskRunnable(40000);
            TaskRunnable task5 = new TaskRunnable(30000);
            TaskRunnable task6 = new TaskRunnable(20000);
            TaskRunnable task7 = new TaskRunnable(10000);

            PEThreadPoolFixed peThreadPoolFixed = new PEThreadPoolFixed();
            // preset the pool ability up to 5 threads.
            peThreadPoolFixed.setPeCorePoolSize(1);
            peThreadPoolFixed.setPeMaximumPoolSize(1);
            peThreadPoolFixed.setPeBlockingQueueSize(2);
            // peThreadPoolFixed.setPeRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
            peThreadPoolFixed.initPEThreadPoolFixed();
            // add 5 threads and set the pool to be full.
            peThreadPoolFixed.getPeThreadPoolExecutor().execute(task1); // 5 seconds
            System.out.println("=================== Been added task 1");
            System.out.println("线程池中线程数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getPoolSize() +
                    "，队列中等待执行的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getQueue().size() +
                    "，已执行玩别的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getCompletedTaskCount());
            System.out.println("===================");

            peThreadPoolFixed.getPeThreadPoolExecutor().execute(task2); // 5 seconds
            System.out.println("=================== Been added task 2");
            System.out.println("线程池中线程数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getPoolSize() +
                    "，队列中等待执行的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getQueue().size() +
                    "，已执行玩别的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getCompletedTaskCount());
            System.out.println("===================");

            peThreadPoolFixed.getPeThreadPoolExecutor().execute(task3); // 5 seconds
            System.out.println("=================== Been added task 3");
            System.out.println("线程池中线程数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getPoolSize() +
                    "，队列中等待执行的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getQueue().size() +
                    "，已执行玩别的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getCompletedTaskCount());
            System.out.println("===================");

            peThreadPoolFixed.getPeThreadPoolExecutor().execute(task4); // 5 seconds
            System.out.println("=================== Been added task 4");
            System.out.println("线程池中线程数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getPoolSize() +
                    "，队列中等待执行的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getQueue().size() +
                    "，已执行玩别的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getCompletedTaskCount());
            System.out.println("===================");

            // this will jump to the RejectedExecutionHandler as expected.
            // task5
            peThreadPoolFixed.getPeThreadPoolExecutor().execute(task5);
            System.out.println("=================== Been added task 5");
            System.out.println("线程池中线程数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getPoolSize() +
                    "，队列中等待执行的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getQueue().size() +
                    "，已执行玩别的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getCompletedTaskCount());
            System.out.println("===================");

            peThreadPoolFixed.getPeThreadPoolExecutor().execute(task6);
            System.out.println("=================== Been added task 6");
            System.out.println("线程池中线程数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getPoolSize() +
                    "，队列中等待执行的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getQueue().size() +
                    "，已执行玩别的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getCompletedTaskCount());
            System.out.println("===================");

            peThreadPoolFixed.getPeThreadPoolExecutor().execute(task7);
            System.out.println("=================== Been added task 7");
            System.out.println("线程池中线程数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getPoolSize() +
                    "，队列中等待执行的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getQueue().size() +
                    "，已执行玩别的任务数目：" + peThreadPoolFixed.getPeThreadPoolExecutor().getCompletedTaskCount());
            System.out.println("===================");
        } catch (Exception e) {
            Assert.assertTrue("Exception occurs:", false);
        }
    }
    /*
     * Thread is running and calculating...#70000#-----pool-1-thread-1
     * =================== Been added task 1
     * 线程池中线程数目：1，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
     * ===================
     * =================== Been added task 2
     * 线程池中线程数目：1，队列中等待执行的任务数目：1，已执行玩别的任务数目：0
     * ===================
     * =================== Been added task 3
     * 线程池中线程数目：1，队列中等待执行的任务数目：2，已执行玩别的任务数目：0
     * ===================
     * Thread is running and calculating...#40000#-----main
     * r = [PEThreadPoolFixedTest$TaskRunnable@531e3c55], e = [java.util.concurrent.ThreadPoolExecutor@660b1b14[Running, pool size = 1, active threads = 1, queued tasks = 2, completed tasks = 0]]
     * Current running thread-----#main
     * =================== Been added task 4
     * 线程池中线程数目：1，队列中等待执行的任务数目：2，已执行玩别的任务数目：0
     * ===================
     * Thread is running and calculating...#30000#-----main
     * Thread is running and calculating...#60000#-----pool-1-thread-1
     * r = [PEThreadPoolFixedTest$TaskRunnable@631ef262], e = [java.util.concurrent.ThreadPoolExecutor@660b1b14[Running, pool size = 1, active threads = 1, queued tasks = 1, completed tasks = 1]]
     * Current running thread-----#main
     * =================== Been added task 5
     * 线程池中线程数目：1，队列中等待执行的任务数目：1，已执行玩别的任务数目：1
     * ===================
     * =================== Been added task 6
     * 线程池中线程数目：1，队列中等待执行的任务数目：2，已执行玩别的任务数目：1
     * ===================
     * Thread is running and calculating...#10000#-----main
     * r = [PEThreadPoolFixedTest$TaskRunnable@4bb32c5], e = [java.util.concurrent.ThreadPoolExecutor@660b1b14[Running, pool size = 1, active threads = 1, queued tasks = 2, completed tasks = 1]]
     * Current running thread-----#main
     * =================== Been added task 7
     * 线程池中线程数目：1，队列中等待执行的任务数目：2，已执行玩别的任务数目：1
     * ===================
     *
     * Process finished with exit code 0
     */
}
