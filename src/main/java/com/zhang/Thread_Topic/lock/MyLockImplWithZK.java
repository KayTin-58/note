package com.zhang.Thread_Topic.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * description:基于Zookeeper实现的分布式锁
 *
 * @author zb 2019/07/19 15:06
 */
public class MyLockImplWithZK implements Lock, Watcher {

    private Syn syn;
    private static ZooKeeper zk = null;
    private String ROOT_LOCK = "/testRoot"; //定义根节点

    private static final String LOCK = "/testRoot/children" ;

    /**
     * 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号
     */
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public MyLockImplWithZK() {
        try {
            zk = new ZooKeeper("192.168.230.140:2181",
                    40000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    Event.KeeperState state = watchedEvent.getState();
                    Event.EventType eventType = watchedEvent.getType();
                    if (Event.KeeperState.SyncConnected == state) {
                        if ((Event.EventType.None == eventType)) {
                            System.out.println("zk建立连接");
                            connectedSemaphore.countDown();
                        }
                    }
                }
            });
            connectedSemaphore.await();
            //判断根节点是否存在
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (stat == null) {//如果不存在创建
                zk.create(ROOT_LOCK, "0".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            syn = new Syn();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    class Syn extends AbstractQueuedSynchronizer {

        /**
         * 获取锁
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            /**
             * 这里因为创建了持久节点 在zk挂掉的时候节点不会消失  所以会形无限自旋
             */
            for (; ; ) {
                try {
                    if (zk.exists(LOCK, false) == null) {
                        zk.create(LOCK, LOCK.getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
                        setExclusiveOwnerThread(Thread.currentThread());
                        return true;
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 释放锁
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            try {
                if (zk.exists(LOCK, false) == null) {
                    throw new RuntimeException();
                }
                for(;;) {
                    zk.delete(LOCK, -1);
                    setExclusiveOwnerThread(null);
                    return true;
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }


    @Override
    public void lock() {
        syn.tryAcquire(0);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
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
        return null;
    }

    /**
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
