package com.zhang.netty.wechat.server;

import com.zhang.netty.wechat.packet.command.Command;
import com.zhang.netty.wechat.packet.request.LoginRequestPacket;
import com.zhang.netty.wechat.packet.Packet;
import com.zhang.netty.wechat.packet.PacketCodec;
import com.zhang.netty.wechat.packet.request.MessageRequestPacket;
import com.zhang.netty.wechat.packet.response.LoginResponsePacket;
import com.zhang.netty.wechat.packet.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * description
 *
 * @author zb 2019/07/04 19:01
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        ByteBuf responseByte = null;
        LoginResponsePacket loginResponse = new LoginResponsePacket();
        loginResponse.setVersion(packet.getVersion());
        if(packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequest = (LoginRequestPacket)packet;
            if(valid(loginRequest)){
                loginResponse.setSuccess(true);
                log.info("客户端请求登录，密码验证通过");
                loginResponse.setReason(new Date()+"服务端验证通过！");
            }else {
                loginResponse.setSuccess(false);
                loginResponse.setReason(new Date()+"账号密码验证未通过！");
                log.info("登陆失败！");
            }
            responseByte = PacketCodec.INSTANCE.encode(ctx.alloc(),loginResponse);


        }else if(packet.getCommand().equals(Command.MESSAGE_REQUEST)) {
            // 处理消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
        /**
         * 回写消息
         */
        ctx.channel().writeAndFlush(responseByte);
    }

    private boolean valid(LoginRequestPacket loginP) {
        return  true;
    }
}
