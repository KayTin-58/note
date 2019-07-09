package com.zhang.netty.wechat.packet.ecode;

import com.zhang.netty.wechat.packet.Packet;
import com.zhang.netty.wechat.packet.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * description
 *
 * @author zb 2019/07/05 14:29
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodec.INSTANCE.encode(byteBuf,packet);
    }
}
