package com.zhang.Thread_Topic.interrupted.stopthread;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/9/24
 */
public class ThreadStop {
    public static void main(String[] args) {
        interrupted();
    }


    public static void interrupted() {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println("----------:" + new Date().toString());
            }

        });
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

        System.out.println(Thread.currentThread().isInterrupted());

    }
}
