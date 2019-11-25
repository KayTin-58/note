package com.zhang.Thread_Topic;

/**
 * description:守护进程测试
 *
 * @author zb 2019/06/25 17:14
 */
public class Daemon {


    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new DaemonRun(), "DaemonRun");
        thread.setDaemon(true);
        thread.start();
        // Thread.sleep(2000);
    }


    private static class DaemonRun implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 并不会被打印
                System.out.println("哈哈哈，守护线程打印测试！");
            }
        }
    }
}
