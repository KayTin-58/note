package com.zhang.Thread_Topic.pck_02;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author zb 2019/06/02 10:53
 */
public class Interrupted02 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //这个异常也可以导致中断复位
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread.interrupt();
        System.out.println("之前："+thread.isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("之后："+thread.isInterrupted());
    }
}
