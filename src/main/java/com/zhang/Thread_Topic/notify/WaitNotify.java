package com.zhang.Thread_Topic.notify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author zb 2019/06/30 15:55
 */
public class WaitNotify {

    private static Object lock = new Object();
    private static Boolean flag = true;

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();


        /**
         * falg:true;16:05:15  //获取锁并执行同步代码快，wait()释放锁，进入等待队列
         * Thread[NotifyThread,5,main] hold lock. notify @ 16:05:16 //获取锁并执行同步代码快，notifyall唤醒等待队列的代码到同步代码块中，待自己同步代码快执行完成之后释放锁
         * Thread[NotifyThread,5,main] hold lock again. sleep @  16: 05: 21
         * 验证释放锁后代码是否会执行
         * falg:false;16:05:26
         */
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    //完成消费
                    System.out.println("falg:" + flag + ";" +
                            new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();//释放锁
                        /**
                         *在再次获取锁的时候会接着执行这段代码
                         */
                        System.out.println("验证释放锁后代码是否会执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("falg:" + flag + ";" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
            // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
            // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat(" HH: mm: ss ").format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


