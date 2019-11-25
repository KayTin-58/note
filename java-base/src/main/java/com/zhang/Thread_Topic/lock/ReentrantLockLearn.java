package com.zhang.Thread_Topic.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/11/19
 */
public class ReentrantLockLearn implements Lock, Serializable {


    private final Sync sync;

    public ReentrantLockLearn() {
        sync = new NonfairSync();
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {

        abstract void lock();

        /**
         * 非公平锁
         * 
         * @param acquires
         * @return
         */
        final boolean nonfairTryAcquire(int acquires) {
            // 获取当前线程
            final Thread current = Thread.currentThread();
            // 核心
            int c = getState();
            if (c == 0) {
                // 更新状态量
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()) {
                // 可重入锁 ：current == getExclusiveOwnerThread() 当前线程已经拥有锁
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                {
                    throw new Error("Maximum lock count exceeded");
                }
                // 状态
                setState(nextc);
                return true;
            }
            return true;
        }

        protected final boolean tryRelease(int releases) {
            // 这里为什么要相减
            int c = getState() - releases;
            // 非持锁线程释放锁会非法
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

        final boolean isLocked() {
            return getState() != 0;
        }
    }



    /**
     * Sync object for non-fair locks
     */
    static final class NonfairSync extends Sync {
        private static final long serialVersionUID = 7316153563782823691L;

        /**
         * Performs lock. Try immediate barge, backing up to normal acquire on failure.
         */
        @Override
        final void lock() {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                acquire(1);
            }
        }

        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }

    }


    /**
     * 加锁
     */
    @Override
    public void lock() {

    }

    /**
     * 中断
     * 
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 尝试加锁
     * 
     * @return
     */
    @Override
    public boolean tryLock() {
        return false;
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
