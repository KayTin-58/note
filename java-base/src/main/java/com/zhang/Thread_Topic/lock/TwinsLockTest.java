package com.zhang.Thread_Topic.lock;


public class TwinsLockTest {

    static TwinsLock lock = new TwinsLock();

    public static void main(String[] args) {


// 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker(lock);
            w.setDaemon(true);
            w.start();
        }
// 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }


    static class Worker extends Thread {
        TwinsLock lock = null;
        public Worker(TwinsLock lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}