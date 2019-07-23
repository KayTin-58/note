package zhang.netty.wechat.basehandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import zhang.netty.wechat.utils.LoginUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description：生命周期检测
 * @author zb 2019/07/14 18:12
 */
@Slf4j
public class LifeCyCleTestHandler extends ChannelInboundHandlerAdapter {

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public LifeCyCleTestHandler() {
        log.info("............LifeCyCleTestHandler()........");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("............channelRegistered().....");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("............channelUnregistered().....");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("..............channelActive().....");
        atomicInteger.getAndAdd(1);
        super.channelActive(ctx);
    }

    /**
     * "channelInactive(): 表面这条连接已经被关闭了，这条连接在 TCP 层面已经不再是 ESTABLISH 状态了"
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("..............channelInactive()....."+ LoginUtils.getSession(ctx.channel()).toString());
        atomicInteger.getAndAdd(-1);
        //LoginUtils.unBindSession(ctx.channel());
        super.channelInactive(ctx);
    }


    /**
     * 比如1000个字节的包分为了10个100字节的分包，底层socket每次read100字节就会调用channelReadComplete，
     * 而当读完10个分包组装成1000字节后才会调用channelRead()来处理。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("..............channelRead()....."+ctx.toString()+";;;;"+msg.toString());
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("..............channelReadComplete()....."+ctx.toString());
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("................userEventTriggered().....");
        super.userEventTriggered(ctx, evt);
    }
}
