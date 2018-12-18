package com.zhang.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这里将会介绍如何实现原子性的常见手段以及java中的处理当方法
 *
 * 1、通过锁住总线来保证：简而言之就是当一个变量被多个cpu操作的时候，在一个cpu获得对这个变量的操作全之后
 * 系统会锁住cpu和内存的通信，使得其他cpu无法访问内存。（不建议使用这种策略）
 *
 *
 * 2、使用缓存策略
 *
 *
 * java中的策略
 *    1、借助于CAS操作实现（需要考虑通过version来防止ABA问题）
 *    2、借助于锁实现
 *
 *
 * Created by zb on 2018/12/18.
 */
public class CASOperation {
     static int i = 0;
     static  AtomicInteger atomicInteger = new AtomicInteger(0);

    static Counter counter = new Counter();

    public static void main(String[]args)throws Exception{
        //这里使用线程池启动执行100个线程
        //线程池的参数
        /**@param corePoolSize the number of threads to keep in the pool, even
         *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
         * @param maximumPoolSize the maximum number of threads to allow in the
         *        pool
         * @param keepAliveTime when the number of threads is greater than
         *        the core, this is the maximum time that excess idle threads
         *        will wait for new tasks before terminating.
         * @param unit the time unit for the {@code keepAliveTime} argument
         * @param workQueue the queue to use for holding tasks before they are
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 100, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for(int i = 0;i<100;i++ ) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    counter.safeCount();
                    counter.unSafeCount();
                }
            });
        }

        countDownLatch.await();

        System.out.println("线程安全的计数："+atomicInteger.get());
        System.out.println("线程不安全的计数："+atomicInteger.get());
    }





    static  class Counter {

        //线程安全的计数
        public void safeCount() {
            while(true) {
                int  i = atomicInteger.get();
                //注意这个函数的额用法
                /**
                 * @param expect the expected value
                 * @param update the new value
                 * @return {@code true} if successful. False return indicates that
                 * the actual value was not equal to the expected value.
                 */
                boolean falg = atomicInteger.compareAndSet(i,i++);
                //代表更新成功
                if(falg) {
                    break;
                }
            }
        }

        //线程不安全的计数
        public void unSafeCount() {
              i++;  //等价于  i = i+1;
        }
    }
}
