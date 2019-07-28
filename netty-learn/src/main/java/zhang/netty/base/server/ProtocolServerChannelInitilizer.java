package zhang.netty.base.server;

import com.example.tutorial.Messages;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import zhang.netty.base.server.handler.MessageServerHandler;

/**
 * description
 *
 * @author zb 2019/07/27 14:42
 */
@SuppressWarnings("all")
public class ProtocolServerChannelInitilizer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new ProtobufVarint32FrameDecoder());
        //channelPipeline.addLast(new ProtobufDecoder(Persons.Person.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufDecoder(Messages.ChatMsg.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        channelPipeline.addLast(new ProtobufEncoder());
        //channelPipeline.addLast(new MyProtoBufServerHandler());
        channelPipeline.addLast(new MessageServerHandler());
    }
}
