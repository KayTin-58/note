package com.zhang.Thread_Topic.interrupted;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author zb 2019/06/02 10:48
 */
public class Interrupted01 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                boolean in = Thread.currentThread().isInterrupted();
                if (in) {
                    System.out.println("之前：" + in);
                    Thread.interrupted();// 复位
                    System.out.println("之后：" + Thread.currentThread().isInterrupted());
                }
            }
        });

        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }
}
