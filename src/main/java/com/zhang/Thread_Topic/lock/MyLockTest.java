package com.zhang.Thread_Topic.lock;

/**
 * description
 *
 * @author zb 2019/07/02 21:06
 */
public class MyLockTest {
    static MyLockWithZiXuan myLock = new MyLockWithZiXuan();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Run(i)).start();
        }
    }


    public static void lock(int i) {
        if (!myLock.tryLock()) {
            System.out.println("获取锁失败！" + "----" + i);
        }
        try {
            System.out.println("获取锁成功：" + i);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myLock.unlock();
        }
    }


    static class Run implements Runnable {
        int i;

        public Run(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            lock(i);
        }
    }
}
