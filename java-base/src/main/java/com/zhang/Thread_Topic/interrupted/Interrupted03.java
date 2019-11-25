package com.zhang.Thread_Topic.interrupted;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:优雅的停止线程
 *
 * @author zb 2019/06/25 17:49
 */
public class Interrupted03 {

    public static void main(String[] args) {
        WorkThread workThread = new WorkThread();
        Thread thread = new Thread(workThread, "WorkThread");
        thread.start();
        try {
            Thread.sleep(10);

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("异常");
        }

        workThread.cancel();
        System.out.println("thread status:" + thread.getState());
        // 休眠1s，再看目标线程的状态
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程状态: " + thread.getState());


    }

    public static class WorkThread implements Runnable {


        private AtomicInteger i = new AtomicInteger();
        public volatile boolean flag = true;

        @Override
        public void run() {
            // 双重支持&& !Thread.currentThread().isInterrupted()

            while (flag && !Thread.currentThread().isInterrupted()) {
                i.getAndAdd(1);
                System.out.println("flag:" + flag + ";" + "i:" + i.get());
            }
        }

        public void cancel() {
            System.out.println("中止线程！");
            flag = false;
        }

    }
}
