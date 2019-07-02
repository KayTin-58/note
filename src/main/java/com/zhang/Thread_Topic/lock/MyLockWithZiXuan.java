package com.zhang.Thread_Topic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * description:带自旋的获取锁
 *
 * @author zb 2019/07/02 18:55
 */
public class MyLockWithZiXuan implements Lock {

   public MyLockWithZiXuan() {
       this.syn = new Syn();
   }

   private Syn syn;

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return syn.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
       syn.tryRelease(0);
    }

    @Override
    public Condition newCondition() {
        return syn.newCondition();
    }


    class Syn extends AbstractQueuedSynchronizer {
        /**
         * 是否处于占用状态
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return this.getState() == 1;
        }

        /**
         * 获取锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            /**
             * 自旋
             */
            for(;;) {
                if(compareAndSetState(0,1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
        }

        /**
         * 释放锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            if(getState() ==0) {
               throw new RuntimeException();
            }
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }
}
