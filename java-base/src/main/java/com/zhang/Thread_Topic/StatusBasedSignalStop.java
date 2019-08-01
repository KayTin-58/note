package com.zhang.Thread_Topic;

public class StatusBasedSignalStop {

    private static volatile boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 检查变量的值，判断是否应该中止执行
                while (isRunning) {
                    System.out.println("线程状态: " + Thread.currentThread().getState());
                    try {
                        Thread.sleep(1100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 退出执行前可以做一些善后工作，比如资源清理
            }
        });
        thread.start();
        // 3s后修改变量的值，表明目标线程应该中止了
        Thread.sleep(3000);
        isRunning = false;
        // 休眠1s，再看目标线程的状态
        Thread.sleep(1000);
        System.out.println("线程状态: " + thread.getState());
    }
}
