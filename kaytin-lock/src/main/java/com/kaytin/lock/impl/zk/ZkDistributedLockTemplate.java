package com.kaytin.lock.impl.zk;

import java.util.concurrent.TimeUnit;

import com.kaytin.lock.Callback;
import com.kaytin.lock.DistributedLockTemplate;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.LoggerFactory;

/**
 * Created by sunyujia@aliyun.com on 2016/2/26.
 */
public class ZkDistributedLockTemplate implements DistributedLockTemplate {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ZkDistributedLockTemplate.class);

    private final CuratorFramework client;


    public ZkDistributedLockTemplate(CuratorFramework client) {
        this.client = client;
    }



    private boolean tryLock(ZkReentrantLock distributedReentrantLock, Long timeout) throws Exception {
        return distributedReentrantLock.tryLock(timeout, TimeUnit.MILLISECONDS);
    }

    public Object execute(String lockId, int timeout, Callback callback) {
        ZkReentrantLock distributedReentrantLock = null;
        boolean getLock=false;
        try {
            distributedReentrantLock = new ZkReentrantLock(client,lockId);
            if(tryLock(distributedReentrantLock,new Long(timeout))){
                getLock=true;
                return callback.onGetLock();
            }else{
                return callback.onTimeout();
            }
        }catch(InterruptedException ex){
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            if(getLock){
                // 自动释放锁
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }
}
