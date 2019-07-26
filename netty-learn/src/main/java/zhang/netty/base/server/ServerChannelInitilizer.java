package zhang.netty.base.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import zhang.netty.base.server.handler.HeartServerHandler;

/**
 * description:
 *
 * @author zb 2019/07/24 11:31
 */
@SuppressWarnings("all")
public class ServerChannelInitilizer extends ChannelInitializer<io.netty.channel.socket.nio.NioSocketChannel> {


    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        System.out.println("==zhang.netty.base.server.ServerChannelInitilizer.initChannel==");
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        channelPipeline.addLast("HttpServerCodec",new ChunkedWriteHandler());
        channelPipeline.addLast("HttpServerCodec",new HttpObjectAggregator(8192));
        /*channelPipeline.addLast("HttpHandler",new HttpHandler());
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));*/
        channelPipeline.addLast(new IdleStateHandler(5,7,10));
        channelPipeline.addLast(new HeartServerHandler());
    }
}
