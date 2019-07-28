package zhang.netty.base.client;

import com.example.tutorial.Messages;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import zhang.netty.base.client.handler.MessageClientHandler;


/**
 * description
 *
 * @author zb 2019/07/27 14:54
 */
@SuppressWarnings("all")
public class ProtoBufClientChannelInitilizer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new ProtobufVarint32FrameDecoder());
        //channelPipeline.addLast(new ProtobufDecoder(Persons.Person.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufDecoder(Messages.ChatMsg.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        channelPipeline.addLast(new ProtobufEncoder());
        //channelPipeline.addLast(new MyProtoBufClientHandler());
        channelPipeline.addLast(new MessageClientHandler());
    }
}
