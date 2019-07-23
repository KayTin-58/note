package com.zhang.Thread_Topic.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * description
 *
 * @author zb 2019/07/06 23:12
 */
public class ConditionLearn {
    /**
     * 共享资源
     */
    static private ArrayList<String> arrayList = new ArrayList();
    static private ReentrantLock reentrantLock = new ReentrantLock();

    static Condition condition = reentrantLock.newCondition();
    static boolean off;

    public static void main(String[] args) {

    }


    static void write() {
        try {
            reentrantLock.lock();
            if (off == false) {

                /**
                 *
                 * Block until signalled or interrupted.
                 *
                 *  public final void await() throws InterruptedException {
                 *             //中断检查
                 *             if (Thread.interrupted())
                 *                 throw new InterruptedException();
                 *                 //加入到等待队列
                 *             Node node = addConditionWaiter();
                 *             //释放锁
                 *             long savedState = fullyRelease(node);
                 *             int interruptMode = 0;
                 *             while (!isOnSyncQueue(node)) {
                 *             //阻塞当前线程
                 *                 LockSupport.park(this);
                 *                 if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                 *                     break;
                 *             }
                 *             if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
                 *                 interruptMode = REINTERRUPT;
                 *             if (node.nextWaiter != null) // clean up if cancelled
                 *                 unlinkCancelledWaiters();
                 *             if (interruptMode != 0)
                 *                 reportInterruptAfterWait(interruptMode);
                 *         }
                 */
                condition.await();
            }
            System.out.println("生产！");
            off = false;
            condition.signalAll();
        } catch (InterruptedException e) {
        } finally {
            reentrantLock.unlock();
        }
    }

    static void get() {
        try {
            reentrantLock.lock();
            if(off == true) {
                condition.await();
            }
            System.out.println("消费！");
            off = true;
            condition.signalAll();
        } catch (InterruptedException e) {
        }finally {
            reentrantLock.unlock();
        }
    }

}
