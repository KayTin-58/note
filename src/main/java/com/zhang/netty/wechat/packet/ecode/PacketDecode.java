package com.zhang.netty.wechat.packet.ecode;

import com.zhang.netty.wechat.packet.Packet;
import com.zhang.netty.wechat.packet.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * description:解码器
 *
 * @author zb 2019/07/05 14:33
 */
public class PacketDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        list.add(packet);
    }
}
