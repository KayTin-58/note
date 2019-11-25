package zhang.netty.base.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import zhang.netty.base.server.handler.TextWebSocketChannelServerHandler;

/**
 * description
 *
 * @author zb 2019/07/26 13:41
 */
@SuppressWarnings("all")
public class WebSocketChannelInitilizer extends ChannelInitializer<NioSocketChannel> {

    private static final String PATH = "/ws";

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        System.out.println("==zhang.netty.base.server.ServerChannelInitilizer.initChannel==");
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("HttpServerCodec", new HttpServerCodec());
        channelPipeline.addLast("ChunkedWriteHandler", new ChunkedWriteHandler());
        channelPipeline.addLast("HttpObjectAggregator", new HttpObjectAggregator(8192));
        channelPipeline.addLast(new WebSocketServerProtocolHandler(PATH));
        channelPipeline.addLast(new TextWebSocketChannelServerHandler());
    }
}
