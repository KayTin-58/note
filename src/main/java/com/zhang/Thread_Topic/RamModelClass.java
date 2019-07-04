package com.zhang.Thread_Topic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zb
 * @version 1.0
 * @name RamModelClass
 * @description java内训模型
 * @date 2018/12/19
 *
 * 在下面的代码中的内存模型将会如下所示
 *
 *            主内存变量    num = 0；
 *
 * thread_01    本地内存（相当于内存变量的一个副本）    num = 0；
 *
 * thread_02    本地内存（相当于内存变量的一个副本）    num = 0；
 *
 * 下面的代码将会得出错误的结果，是因为多线程造成的，我们下面来分析原因
 *
 * 假设在开始：thread_01  线程开始操作共享变量（基于本地内存操作），是的num++，此时num的值为2，但是这个值只是存在
 * 于本地主内存中，在主内存中的num变量的值依然等于1，
 * 在thread_01 线程对num的操作还没有结束的时候thread_02进入了操作num的代码快，此刻他看到的num也是本地内存的num=1,所以会导致错误
 *
 * 同样加入还有一个线程来操作num，他看到的也是主内存中的num的值，即num=1
 *
 *
 *
 */



public class RamModelClass {

    /**
     * 主内存中的共享变量
     */
    private static Integer num = 0;

    public static void main(String[]args){
        //启动多个线程

        MyThraed thread_01 =  new MyThraed("thread_01");
        MyThraed thraed_02 = new MyThraed("thread_02");


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 100, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));
        //同时启动像个线程
        threadPoolExecutor.execute(thread_01);
        threadPoolExecutor.execute(thread_01);
    }





    static class MyThraed implements  Runnable {


        private String thredName;//线程名字

        public MyThraed(String thredName) {
            this.thredName = thredName;
        }
        @Override
        public void run() {
            //线程操作共享变量（不安全的）
            num++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MyThraed{" +
                    "thredName='" + thredName + '\'' +
                    '}';
        }
    }
}
