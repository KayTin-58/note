package zhang.netty.wechat.packet.ecode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import zhang.netty.wechat.packet.Packet;
import zhang.netty.wechat.packet.PacketCodec;

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
