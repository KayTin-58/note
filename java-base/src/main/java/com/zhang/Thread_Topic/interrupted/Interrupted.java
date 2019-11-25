package com.zhang.Thread_Topic.interrupted;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author zb 2019/06/02 10:35
 */
public class Interrupted {

    public static int i = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
        }, "Interrupted");

        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println(i);
    }
}
