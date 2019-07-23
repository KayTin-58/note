package zhang.netty.wechat.server.handler;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import zhang.netty.wechat.utils.LoginUtils;

/**
 * description:登陆校验
 *
 * @author zb 2019/07/16 20:17
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {
    /**
     * 校验
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtils.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            //验证通过,移除AuthHandler
            ctx.pipeline().remove(this);
            super.channelRead(ctx,msg);
        }

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("------com.zhang.netty.wechat.server.handler.AuthHandler---被移除！");
        super.handlerRemoved(ctx);
    }
}
