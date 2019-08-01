package com.zhang.Thread_Topic.pck_01;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * description
 *
 * @author zb 2019/05/31 0:06
 */
public class PrinProcess extends Thread implements RequestProcess {

    private LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private RequestProcess process;

    public PrinProcess(RequestProcess process) {
        this.process = process;
    }

    public PrinProcess() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = linkedBlockingQueue.take();
                /**
                 * 执行真正的消息处理逻辑
                 */
                Thread.sleep(100);
                Optional.ofNullable(this.process).map(o ->
                        o.processReq(request)
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object processReq(Request request) {
        linkedBlockingQueue.add(request);
        return null;
    }
}
