package com.zhang.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * description
 *
 * @author zb 2019/07/19 9:05
 */
public class BlockQueue {

    /**
     * 内部持有两把锁 一把生产者 一把消费者 所以可以看到 add/poll可以同时进行
     *
     * 【add操作】1563500986741 【poll操作】1563500986741 【add操作】1563500986941 【poll操作】1563500986941
     */
    private static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
    /**
     * 内部持有一把锁
     *
     * put(e) { checkNull(e) lock() while(count==queue.length) { await() } addQueue(e) signal() finally{
     * unlock() } }
     */
    private static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        // LinkedBlockQueue();
        ArrayBlockQueue();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static void LinkedBlockQueue() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    linkedBlockingQueue.put(String.valueOf(System.currentTimeMillis()));
                    System.out.println("【add操作】" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                    /**
                     * public E poll() { final AtomicInteger count = this.count; if (count.get() == 0) return null; E x
                     * = null; int c = -1; 加锁 final ReentrantLock takeLock = this.takeLock; takeLock.lock(); try { if
                     * (count.get() > 0) { x = dequeue(); c = count.getAndDecrement(); if (c > 1) notEmpty.signal(); } }
                     * finally { takeLock.unlock(); } if (c == capacity) //唤醒生产者 final ReentrantLock putLock =
                     * this.putLock; putLock.lock(); try { notFull.signal(); } finally { putLock.unlock(); } return x; }
                     */
                    linkedBlockingQueue.poll();
                    System.out.println("【poll操作】" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    static void ArrayBlockQueue() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    arrayBlockingQueue.put(String.valueOf(System.currentTimeMillis()));
                    System.out.println("【put操作】" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                    arrayBlockingQueue.poll();
                    System.out.println("【arrayBlockingQueue poll操作】" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
