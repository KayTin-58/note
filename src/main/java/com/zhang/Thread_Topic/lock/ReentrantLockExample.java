package com.zhang.Thread_Topic.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description
 *
 * @author zb 2019/06/25 15:05
 */
public class ReentrantLockExample {
    static  ReentrantLock reentrantLock =new ReentrantLock();




    private String appName = "安卓";
    public static void main(String[] args) {
        Condition condition = reentrantLock.newCondition();

    }

    public void write(String name) {
         reentrantLock.lock();
        /**
         * 可重入获取锁
         */
        reentrantLock.tryLock();
        try {
            this.appName = name;
        } finally {
            reentrantLock.unlock();
        }
    }

    public String read() {
        reentrantLock.lock();
        try {
            return this.appName;
        } finally {
            reentrantLock.unlock();
        }
    }

}
