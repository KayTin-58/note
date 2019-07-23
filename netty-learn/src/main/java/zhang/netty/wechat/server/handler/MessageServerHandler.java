package zhang.netty.wechat.server.handler;



import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import zhang.netty.wechat.packet.Session;
import zhang.netty.wechat.packet.request.MessageRequestPacket;
import zhang.netty.wechat.packet.response.MessageResponsePacket;
import zhang.netty.wechat.utils.LoginUtils;

/**
 * description
 *
 * @author zb 2019/07/05 14:38
 */
@Slf4j
public class MessageServerHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        log.info("【服务端收到客户端的消息了】"+messageRequestPacket.getMessage());

        // 1.拿到消息发送方的会话信息
        Session session = LoginUtils.getSession(ctx.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        messageResponsePacket.setFromUserId(session.getUserId());

        /*Channel channel = LoginUtils.getChannel(messageRequestPacket.getToUserId());
        ctx.channel().writeAndFlush(messageResponsePacket);*/

        //拿到消息接收方的channel
        Channel channel = LoginUtils.getChannel(messageRequestPacket.getToUserId());
        if(channel !=null &&LoginUtils.hasLogin(channel)) {
            channel.writeAndFlush(messageResponsePacket);
        }else {
            System.out.println("----------------------");
        }

    }
}
