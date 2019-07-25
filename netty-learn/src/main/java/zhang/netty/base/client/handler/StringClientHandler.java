package zhang.netty.base.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * description
 *
 * @author zb 2019/07/24 19:52
 */
@Slf4j
public class StringClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        log.info("{client}:"+s+";"+ctx.channel().remoteAddress());
        ctx.writeAndFlush("{from client}:"+LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--zhang.netty.base.client.handler.StringClientHandler.channelActive--");
        ctx.writeAndFlush("{from client}");
        System.out.println("----------");
    }
}
