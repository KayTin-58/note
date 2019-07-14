package com.zhang.netty.wechat.basehandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author zb 2019/07/14 18:12
 */
@Slf4j
public class LifeCyCleTestHandler extends ChannelInboundHandlerAdapter {
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
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("..............channelRead().....");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("..............channelReadComplete().....");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("................userEventTriggered().....");
        super.userEventTriggered(ctx, evt);
    }
}
