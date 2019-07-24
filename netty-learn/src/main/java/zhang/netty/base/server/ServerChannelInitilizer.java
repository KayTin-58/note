package zhang.netty.base.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import zhang.netty.base.server.handler.HttpHandler;

/**
 * description:
 *
 * @author zb 2019/07/24 11:31
 */
public class ServerChannelInitilizer extends ChannelInitializer<io.netty.channel.socket.nio.NioSocketChannel> {


    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        channelPipeline.addLast("HttpHandler",new HttpHandler());
    }
}
