package zhang.netty.base.server.handler;

import com.example.tutorial.Messages;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author zb 2019/07/27 16:32
 */
@Slf4j
public class MessageServerHandler extends SimpleChannelInboundHandler<Messages.ChatMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Messages.ChatMsg msg) throws Exception {
        /* log.info("服务端收到消息："+msg.getToUser()+"\n"+msg.getFromUser()+"\n"+msg.getId()); */
        Messages.ChatMsg.MsgType msgType = msg.getMsgType();
        switch (msg.getMsgType()) {
            case Message: {
                log.info("服务端收到消息Message：" + msg.getMsg().getFromUser() + "\n" + msg.getMsg().getToUser() + "\n"
                                + msg.getMsg().getMsg());
                break;
            }
            case Login: {
                log.info("服务端收到消息Login：" + msg.getLogin().getUserName());
                break;
            }
            case CreateGroup: {
                log.info("服务端收到消息CreateGroup：" + msg.getCreateGroup().getGroupName());
                break;
            }

        }
    }
}
