package zhang.netty.base.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import zhang.netty.base.client.handler.StringClientHandler;
import zhang.netty.wechat.basehandler.Spliter;


/**
 * description
 *
 * @author zb 2019/07/24 18:54
 */
@SuppressWarnings("all")
public class ClinetChannelInitilizer extends ChannelInitializer<NioSocketChannel>  {

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline channelPipeline = nioSocketChannel.pipeline();
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringClientHandler());
    }
}
