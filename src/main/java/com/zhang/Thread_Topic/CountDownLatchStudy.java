package com.zhang.Thread_Topic;

import ch.qos.logback.core.util.ExecutorServiceUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * description
 *
 * @author zb 2019/07/02 17:43
 */
public class CountDownLatchStudy {
    static CountDownLatch cd = new CountDownLatch(100);
    /**
     * 不规范
     */
    static ExecutorService service = Executors.newFixedThreadPool(100);
    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            service.submit(new Run(i,cd));
        }

        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行完成------");
    }


    static class Run implements  Runnable{

        CountDownLatch countDownLatch = null;
        int i;
        public Run(int i,CountDownLatch countDownLatch) {
            this.i = i;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(this.i);
            countDownLatch.countDown();
        }
    }
}
