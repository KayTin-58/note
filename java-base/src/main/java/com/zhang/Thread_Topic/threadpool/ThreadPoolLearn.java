package com.zhang.Thread_Topic.threadpool;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * description
 *
 * @author zb 2019/07/23 10:46
 */
public class ThreadPoolLearn {
    public static void main(String[] args) {
        test01();
    }

    static void test01() {

        // 获取cpu数量
        int cpuCount = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuCount);
        /**
         * Creates a new {@code ThreadPoolExecutor} with the given initial parameters.
         *
         * @param corePoolSize the number of threads to keep in the pool, even if they are idle, unless
         *        {@code allowCoreThreadTimeOut} is set
         * @param maximumPoolSize the maximum number of threads to allow in the pool
         * @param keepAliveTime when the number of threads is greater than the core, this is the maximum
         *        time that excess idle threads will wait for new tasks before terminating.
         * @param unit the time unit for the {@code keepAliveTime} argument
         * @param workQueue the queue to use for holding tasks before they are executed. This queue will
         *        hold only the {@code Runnable} tasks submitted by the {@code execute} method.
         * @param threadFactory the factory to use when the executor creates a new thread
         * @param handler the handler to use when execution is blocked because the thread bounds and queue
         *        capacities are reached
         * @throws IllegalArgumentException if one of the following holds:<br>
         *         {@code corePoolSize < 0}<br>
         *         {@code keepAliveTime < 0}<br>
         *         {@code maximumPoolSize <= 0}<br>
         *         {@code maximumPoolSize < corePoolSize}
         * @throws NullPointerException if {@code workQueue} or {@code threadFactory} or {@code handler} is
         *         null
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 15, 20, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(1000), new DefaultThreadFactory("test-thread-pool"),
                        new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 19; i++) {
            final int num = i;

            Future<String> future = poolExecutor.submit(() -> {
                Thread.sleep(1000);
                return String.valueOf(num);
            });

            try {
                System.out.println("结果：" + future.get() + "-" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /*
         * try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
         */

        // 线程池常见的评价指标

        while (poolExecutor.getCompletedTaskCount() < 19) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 线程池需要执行的线程数量
            System.out.println(poolExecutor.getTaskCount());
            // 线程池在运行过程中已经完成的数量
            System.out.println(poolExecutor.getCompletedTaskCount());
            // 线程池里曾经创建过的最大线程数量
            System.out.println(poolExecutor.getLargestPoolSize());
            // 线程池里的线程数量
            System.out.println(poolExecutor.getPoolSize());
            // 获取活动线程数量
            System.out.println(poolExecutor.getActiveCount());
        }
    }

}

