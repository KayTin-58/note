package com.zhang.Thread_Topic.lock;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * description
 *
 * @author zb 2019/07/19 15:15
 */
public class ZookeeperLearn {

    private static ZooKeeper zk = null;
    private String ROOT_LOCK = "/locks"; // 定义根节点

    private static final String CMD = "/locks/onlyLock";
    /**
     * 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号
     */
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            zk = new ZooKeeper("192.168.230.140:2181", 4000, new Watcher() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {



            // 创建父节点
            /*
             * String result = zk.create("/testRoot", "testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
             * CreateMode.PERSISTENT);
             */

            // System.out.println("result:"+result);
            System.out.println(zk.exists("/testRoot/children", false));
            System.out.println(zk.exists("/testRoot", false));


            // System.out.println(result);
            // 创建子节点
            zk.create("/testRoot/children", "children data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            CreateMode.PERSISTENT);

            // 获取节点洗信息
            byte[] data = zk.getData("/testRoot", false, null);
            System.out.println(new String(data));
            System.out.println(zk.getChildren("/testRoot", false));

            // 修改节点的值
            zk.setData("/testRoot", "modify data root".getBytes(), -1);
            byte[] data1 = zk.getData("/testRoot", false, null);
            System.out.println(new String(data1));

            // 判断节点是否存在
            System.out.println(zk.exists("/testRoot/children", false));
            // 删除节点
            zk.delete("/testRoot/children", -1);
            System.out.println(zk.exists("/testRoot/children", false));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
