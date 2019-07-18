package com.zhang.container;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * description
 *
 * @author zb 2019/07/15 18:37
 */
public class ConcurrentLinkedQueueLearn {
    public static void main(String[] args) {

    }

    static void test() {
        /**
         * 初始化队列
         *
         * head = tail = new Node<E>(null);
         */
        ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();

        /**
         * 1、找到尾节点
         * 2、将新的节点设置成尾节点的next节点
         */
        clq.add("Hash");
    }
}
