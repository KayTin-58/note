package com.zhang.Thread_Topic;

/**
 * description
 *
 * @author zb 2019/06/25 17:32
 */
public class Interrupted {

    public static void main(String[] args) throws Exception {
        Thread sleep = new Thread(new SleepThread(), "SleepThread");
        Thread work = new Thread(new WorkThread(), "WorkThread");
        sleep.setDaemon(true);
        work.setDaemon(true);
        sleep.start();
        work.start();

        Thread.sleep(2000);

        sleep.interrupt();
        work.interrupt();
        /**
         * SleepThread:false WorkThread:true
         */
        System.out.println(sleep.getName() + ":" + sleep.isInterrupted());
        System.out.println(work.getName() + ":" + work.isInterrupted());


    }

    private static class SleepThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("异常捕获时的中断状态：" + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        }



    }


    private static class WorkThread implements Runnable {

        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
