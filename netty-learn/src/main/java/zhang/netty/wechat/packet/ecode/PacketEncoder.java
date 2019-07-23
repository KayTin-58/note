package zhang.netty.wechat.packet.ecode;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import zhang.netty.wechat.packet.Packet;
import zhang.netty.wechat.packet.PacketCodec;


/**
 * description：编码器
 *
 * @author zb 2019/07/05 14:29
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodec.INSTANCE.encode(byteBuf,packet);
    }
}
