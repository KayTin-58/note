package zhang.wechat.server;// package com.zhang.wechat.server;

//
// import com.zhang.wechat.packet.command.Command;
// import com.zhang.wechat.packet.request.LoginRequestPacket;
// import com.zhang.wechat.packet.Packet;
// import com.zhang.wechat.packet.PacketCodec;
// import com.zhang.wechat.packet.request.MessageRequestPacket;
// import com.zhang.wechat.packet.response.LoginResponsePacket;
// import com.zhang.wechat.packet.response.MessageResponsePacket;
// import io.netty.buffer.ByteBuf;
// import io.netty.channel.ChannelHandlerContext;
// import io.netty.channel.ChannelInboundHandlerAdapter;
// import lombok.extern.slf4j.Slf4j;
//
// import java.util.Date;
//
// /**
// * description
// *
// * @author zb 2019/07/04 19:01
// */
// @Slf4j
// public class ServerHandler extends ChannelInboundHandlerAdapter {
//
// @Override
// public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
// ByteBuf byteBuf = (ByteBuf)msg;
// Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
// LoginResponsePacket loginResponse = new LoginResponsePacket();
// log.info("packet:"+(packet==null));
// loginResponse.setVersion(packet.getVersion());
// log.info(packet.getCommand().toString());
//
// if(packet instanceof LoginRequestPacket) {
// /*LoginRequestPacket loginRequest = (LoginRequestPacket)packet;
// if(valid(loginRequest)){
// loginResponse.setSuccess(true);
// log.info("客户端请求登录，密码验证通过");
// loginResponse.setReason(new Date()+"服务端验证通过！");
// }else {
// loginResponse.setSuccess(false);
// loginResponse.setReason(new Date()+"账号密码验证未通过！");
// log.info("登陆失败！");
// }
// ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(),loginResponse);
// ctx.channel().writeAndFlush(responseByteBuf);*/
//
// }else if(packet.getCommand().equals(Command.MESSAGE_REQUEST)) {
// // 处理消息
// MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
// System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//
// MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
// messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
// ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
// ctx.channel().writeAndFlush(responseByteBuf);
// }
// }
//
// private boolean valid(LoginRequestPacket loginP) {
// return true;
// }
// }
