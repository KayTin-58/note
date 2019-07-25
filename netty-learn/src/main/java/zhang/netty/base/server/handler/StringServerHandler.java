package zhang.netty.base.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * description
 *
 * @author zb 2019/07/24 19:13
 */
@Slf4j
public class StringServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        log.info("{client}:" + s + ";" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("{from server}" + UUID.randomUUID().toString());
    }

    /**
     * 异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("==zhang.netty.base.server.handler.StringServerHandler.channelActive==");
    }
}
