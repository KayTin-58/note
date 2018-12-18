package com.zhang.thread;

/**
 * 内存 ：int num = 10;
 * 线程1：
 *     缓存：int mun = 10;
 * 线程2：
 *     缓存：int num = 10;
 *
 * 非volatitle 变量：
 *    线程1修改 num = 100;
 *
 * 内存 ：int num = 10;
 * 线程1：
 *     缓存：int mun = 100;
 * 线程2：
 *     缓存：int num = 10;
 *
 *
 * 此时线程2读到的num是缓存中的num值 num = 10
 *
 * volatitle 变量：
 *    线程1修改 num = 100;
 *
 * 内存 ：int num = 100;
 * 线程1：
 *     缓存：int mun = 100;
 * 线程2：
 *     缓存：int num = 100;
 *
 * 在线程1更新缓存中num值后会迅速刷新内存中的num，同时导致其他线程的缓存num失效
 *
 * 此时线程2读到的num是缓存中的num值 num = 100
 *
 *
 * Created by zb on 2018/12/13.
 */
public class VolatitleLearn {


        public static void main(String[]args){
            Thread thread01 = new MThread();
            Thread thread02 = new MThread();
            thread01.start();
            thread02.start();
        }


    /**
     * 以下代码还是会出现 counter 出现重复值
     * 原因在于 counter++ 不是一个原子操作
     */
    public static  class MThread extends Thread {
        public  static volatile  int counter = 0;
        @Override
        public void run() {
            while (counter < 100) {
                counter++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("counter:"+this.counter);

            }
        }
    }
}
