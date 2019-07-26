package zhang.netty.base.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * description:处理WebSocket的文本信息
 *
 * @author zb 2019/07/26 14:19
 */
@Slf4j
public class TextWebSocketChannelServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
            log.info("{server:}"+msg.text());
            ctx.writeAndFlush(new TextWebSocketFrame(msg.text()+":"+ LocalDateTime.now()));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("--channelActive--："+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("--handlerAdded--:"+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("--handlerRemoved--:"+ctx.channel().id().asLongText());
    }

}
