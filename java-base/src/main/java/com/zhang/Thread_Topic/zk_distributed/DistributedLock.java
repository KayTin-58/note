package com.zhang.Thread_Topic.zk_distributed;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * description
 *
 * @author zb 2019/06/24 15:51
 */
public class DistributedLock implements Lock, Watcher {


    private ZooKeeper zk = null;
    private String ROOT_LOCK = "/locks"; // 定义根节点
    private String WAIT_LOCK; // 等待前一个锁
    private String CURRENT_LOCK; // 表示当前的锁


    // 作为阻塞
    private CountDownLatch countDownLatch; //



    public DistributedLock() {

        try {
            zk = new ZooKeeper("192.168.230.140:2181", 4000, this);
            // 判断根节点是否存在
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (stat == null) {// 如果不存在创建
                zk.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void lock() {
        if (this.tryLock()) { // 如果获得锁成功
            System.out.println(Thread.currentThread().getName() + "->" + CURRENT_LOCK + "->获得锁成功");
            return;
        }
        try {
            waitForLock(WAIT_LOCK); // 没有获得锁，继续等待获得锁
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private boolean waitForLock(String prev) throws KeeperException, InterruptedException {
        // 监听当前节点的上一个节点 注册事件，这里需要在默认的 watch 事件里面处理
        // 这里是我们之前提到的 watch 事件触发最后执行的 process 回调里面的 请看最下行代码
        Stat stat = zk.exists(prev, true);
        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + "->等待锁" + ROOT_LOCK + "/" + prev + "释放");
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();// 进入等待，这里需要
            // TODO watcher触发以后，还需要再次判断当前等待的节点是不是最小的
            System.out.println(Thread.currentThread().getName() + "->获得锁成功");
        }
        return true;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {

        try {
            // 创建临时有序节点
            CURRENT_LOCK = zk.create(ROOT_LOCK + "/", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + "->" + CURRENT_LOCK + "，尝试竞争锁");
            List<String> childrens = zk.getChildren(ROOT_LOCK, false); // 获取根节点下的所有子节点
            SortedSet<String> sortedSet = new TreeSet();// 定义一个集合进行排序
            for (String children : childrens) { // 排序
                sortedSet.add(ROOT_LOCK + "/" + children);
            }
            String firstNode = sortedSet.first(); // 获得当前所有子节点中最小的节点
            // 取出比我创建的节点还小的节点，没有的话为null
            SortedSet<String> lessThenMe = ((TreeSet<String>) sortedSet).headSet(CURRENT_LOCK);
            if (CURRENT_LOCK.equals(firstNode)) {// 通过当前的节点和子节点中最小的节点进行比较，如果相等，表示获得锁成功
                return true;
            }
            if (!lessThenMe.isEmpty()) {
                WAIT_LOCK = lessThenMe.last();// 获得比当前节点更小的最后一个节点，设置给WAIT_LOCK
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + "->释放锁" + CURRENT_LOCK);
        try {
            // -1 表示无论如何先把这个节点删了再说
            zk.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 事件回调 countDownLatch.countDown();
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    DistributedLock distributedLock = new DistributedLock();
                    distributedLock.lock(); // 获得锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
            countDownLatch.countDown();
        }
        // System.in.read();
    }
}
