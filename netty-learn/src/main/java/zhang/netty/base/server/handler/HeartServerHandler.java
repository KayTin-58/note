package zhang.netty.base.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author zb 2019/07/26 9:41
 */
@Slf4j
public class HeartServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 用户事件触发
     * 
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("============userEventTriggered================");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            IdleState idleState = stateEvent.state();
            String evtType = null;
            switch (idleState) {
                case READER_IDLE:
                    evtType = "读空闲";
                    break;
                case WRITER_IDLE:
                    evtType = "写空闲";
                    break;
                case ALL_IDLE:
                    evtType = "读写空闲";
                    break;
            }

            System.out.println("{心跳检测：}" + evtType);
        }
    }
}
