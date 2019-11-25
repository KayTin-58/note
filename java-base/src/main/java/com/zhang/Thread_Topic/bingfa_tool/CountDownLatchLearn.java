package com.zhang.Thread_Topic.bingfa_tool;

import java.util.concurrent.CountDownLatch;

/**
 * description
 *
 * @author zb 2019/07/23 11:41
 */
public class CountDownLatchLearn {
    static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("----1----");
            countDownLatch.countDown();
            System.out.println("----2----");
            countDownLatch.countDown();
        }).start();

        System.out.println("----3----");
        // countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---4---");
    }
}
