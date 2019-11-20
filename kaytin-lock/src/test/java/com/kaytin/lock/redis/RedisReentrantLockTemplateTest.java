package com.kaytin.lock.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import com.kaytin.lock.Callback;
import com.kaytin.lock.impl.redis.RedisDistributedLockTemplate;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPool;

/**
 * Created by sunyujia@aliyun.com on 2016/2/24.
 */
public class RedisReentrantLockTemplateTest {

    @Test
    public void testTry() throws InterruptedException {
        JedisPool jp=new JedisPool("127.0.0.1",6379);
        final RedisDistributedLockTemplate template=new RedisDistributedLockTemplate(jp);

        int size=100;
        final CountDownLatch startCountDownLatch = new CountDownLatch(1);
        final CountDownLatch endDownLatch=new CountDownLatch(size);
        for (int i =0;i<size;i++){
            new Thread() {
                public void run() {
                    try {
                        startCountDownLatch.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    final int sleepTime= ThreadLocalRandom.current().nextInt(5)*1000;
                    template.execute("test",5000, new Callback() {

                        @Override
                        public Object onGetLock() throws InterruptedException {
                            return null;
                        }

                        @Override
                        public Object onTimeout() throws InterruptedException {
                            return null;
                        }
                    });
                }
            }.start();
        }
        startCountDownLatch.countDown();
        endDownLatch.await();
    }
}
