package com.zhang.Thread_Topic.threadpool;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * description
 *
 * @author zb 2019/06/30 23:13
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList();


    /**
     * 初始化连接池
     *
     * @param initialSize
     */
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }


    public Connection get(long mills) {
        synchronized (pool) {
            /**
             * 立即获取
             */
            if (mills < 0) {
                while (pool.isEmpty()) {
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return pool.getFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    try {
                        /**
                         * 当前线程等待有控限连接时被唤醒
                         */
                        pool.wait(remaining);
                        /**
                         * 更新时间
                         */
                        remaining = future - System.currentTimeMillis();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.getFirst();
                }
                return result;
            }
        }
    }

    public void release(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.add(connection);
                /**
                 * 这里很重要，唤醒等待获取连接的线程
                 */
                pool.notifyAll();
            }
        }
    }
}
