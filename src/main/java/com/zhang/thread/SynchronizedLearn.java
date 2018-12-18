package com.zhang.thread;

/**
 *补充说明：每个java对象的对象头里都含有一个锁标记,java对象头里信息丰富
 *   其中包括：锁标记、hashcode值、分代年龄
 *   锁标记又分为：轻量级锁、重量级锁、偏向锁
 *
 *
 *
 *   1、偏向锁
 *   为什么会出现偏向锁？
 *   因为线程获取锁是随机的，一个线程可能会多次获取锁，作为锁的对象会在对象头的位置记录线程的ID
 *   在该线程以后进入和退出同步代码的时候不需要通过cas操作来获取锁和释放锁，只需要检测对象头中的标记即可
 *
 *   偏向锁的撤销过程读者可以自行去查看相关资料了解
 *
 *
 *
 *   这里对 偏向锁、轻量级锁、重量级锁做一个对比
 *
 **                优点                                   缺点                           适用场景
 *
 * 偏向锁       获取线程和释放锁速度             不适合用于多个线程竞争的场景            适合单线程场景
 *                较快比较容易                      多线程场景下失去意义
 *
 *
 *轻量级锁       使用自旋等待方式获取锁             在获取不到锁的情况下耗费资源         追求响应时间，同步代码执行速度快
 *                 不会组赛
 *
 *
 * 重量级锁                                            线程会组赛                     追求吞吐量，同步代码执行时间长
 *
 *
 *
 *
 *
 *
 *
 * 代码块的锁对象：synchronized ()  括号里的参数
 *
 * 方法上的锁对象：调用这个方法的对象
 *
 * 静态方法的锁对象：SynchronizedLearn.class
 *
 *
 *
 *
 *
 * Created by zb on 2018/12/13.
 */
public class SynchronizedLearn {



    //用法1：代码快
    public void Sysn() {
        synchronized (this) {
           System.out.println("同步代码快");
        }
    }

    //用法2：同步方法
    public synchronized  void SysnF() {

    }

    //用法3：静态方法
    public synchronized static void SysnS() {

    }
}
