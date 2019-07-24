package zhang.netty.base.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import zhang.netty.base.server.handler.StringServerHandler;
import zhang.netty.wechat.basehandler.Spliter;

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
       /* channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        channelPipeline.addLast("HttpHandler",new HttpHandler());*/
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringServerHandler());
    }
}
