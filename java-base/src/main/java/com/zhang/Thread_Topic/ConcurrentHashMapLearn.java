package com.zhang.Thread_Topic;

import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 *
 * @author zb 2019/07/06 18:01
 */
public class ConcurrentHashMapLearn {

    public static void main(String[] args) {

        /**
         * tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
         */
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1000);
        /**
         * 在新的JDK中取消了Segment模式
         *
         * //自旋 防止tab被更新过 for (Node<K,V>[] tab = table;;){ if (tab == null || (n = tab.length) == 0) tab =
         * initTable(); else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) { if (casTabAt(tab, i, null,
         * new Node<K,V>(hash, key, value, null))) break; // no lock when adding to empty bin }
         *
         * //使用 synchronized (f) {
         *
         * }
         *
         *
         * //最后 计数增加 addCount(1L, binCount); }
         *
         *
         */
        concurrentHashMap.put("key-01", "01");
        concurrentHashMap.put("key-02", "02");

        String value = (String) concurrentHashMap.get("key-01");

        System.out.println("value:" + value);

        concurrentHashMap.size();


        /**
         * 1、首先尝试两次不加锁的情况计算每个segment中数据容量的大小 2、如果在计算之后发现数据计算过程中数据容量发生变化则加锁禁止 put/remove/clear操作
         */
        concurrentHashMap.size();

    }



    static void concurrentHashMap() {

    }
}
