package com.zhang.Thread_Topic.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description
 *
 * @author zb 2019/07/02 19:34
 */
public class ReadAndWriteLockStudy {
    private static  Integer i = 0;

    static ReentrantReadWriteLock rwLock =new ReentrantReadWriteLock();
    static Lock r = rwLock.readLock();
    static Lock w = rwLock.writeLock();
    public static void main(String[] args) {
    }

    public static int get() {
        r.lock();
        try{
            return i;
        }finally {
           r.unlock();
        }
    }

    public static void set() {
        w.lock();
        try{
            i++;
        }finally {
            w.unlock();
        }
    }
}
