PE.utils.thread
=============

* PEAsyncProxy/PEAsyncProxyParam/PEIAsyncProxyCallback

提供异步执行处理逻辑的回调方式。

```java
PEAsyncProxyParam peAsyncProxyParam1 = new PEAsyncProxyParam() {
    /**
     * Processing async function
     */
    @Override
    public void execute() {
        super.execute();
        System.out.println("Executing in a new thread.........");
    }
};

new PEAsyncProxy<PEAsyncProxyParam>(peAsyncProxyParam1, new PEIAsyncProxyCallback() {
    public void onSuccess() {
        System.out.println("Invoking successfully.");
    }

    public void onError() {
        System.out.println("Invoking fail.");
    }
});
```

* PEThreadPoolFixed

提供jdk线程池的通用封装办法。例如看如下一个比较复杂的案例:

```java
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
```

```java
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

// 以下是输出的Console控制台信息
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
```