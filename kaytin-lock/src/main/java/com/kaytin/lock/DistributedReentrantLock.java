package com.kaytin.lock;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2018/06/07 13:48
 */
public interface DistributedReentrantLock {
    /**
     * 获取锁
     * 
     * @param timeout 超时时间
     * @param unit 单位
     * @return
     * @throws InterruptedException
     */
    boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * 释放锁
     */
    void unlock();
}
