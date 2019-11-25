package com.zhang.Thread_Topic.lock;

/**
 * description
 *
 * @author zb 2019/07/19 15:40
 */
public class MyLockImplWithZKMain {

    static MyLockImplWithZK zkLock = new MyLockImplWithZK();
    static int count = 0;

    public static void main(String[] args) {

        pro();

        /*
         * new Thread(() -> { while (true) { cons(); } }).start();
         */
    }

    static void pro() {
        zkLock.lock();
        try {
            if (count % 2 == 0) {
                Thread.sleep(3000);
                count++;
                System.out.println("【pro】" + count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            zkLock.unlock();
        }
    }

    static void cons() {
        zkLock.lock();
        try {
            Thread.sleep(3000);
            count++;
            System.out.println("【cons】" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkLock.unlock();
        }
    }
}
