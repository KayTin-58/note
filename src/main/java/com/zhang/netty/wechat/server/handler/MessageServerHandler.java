package com.zhang.netty.wechat.server.handler;

import com.zhang.netty.wechat.packet.request.MessageRequestPacket;
import com.zhang.netty.wechat.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

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
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
