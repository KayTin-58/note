package com.zhang.Thread_Topic.pck_01;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * description
 *
 * @author zb 2019/05/30 23:56
 */
public class SaveProcess extends Thread implements RequestProcess {

    /**
     * 使用一个组赛队列对维护请求
     */
    private LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private RequestProcess process;

    public SaveProcess(RequestProcess process) {
        this.process = process;
    }

    public SaveProcess() {}

    @Override
    public void run() {
        while (true) {
            try {
                Request request = linkedBlockingQueue.take();
                /**
                 * 执行真正的消息处理逻辑
                 */
                Thread.sleep(100);
                /**
                 * 做成可以动态组合的链式处理 如果传入了下游链处理器则执行下游处理逻辑
                 */
                Optional.ofNullable(this.process).map(o -> o.processReq(request));
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
