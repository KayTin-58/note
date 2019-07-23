package com.zhang.netty.wechat.server.handler;

import com.zhang.netty.wechat.packet.Session;
import com.zhang.netty.wechat.packet.request.LoginRequestPacket;
import com.zhang.netty.wechat.packet.response.LoginResponsePacket;
import com.zhang.netty.wechat.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * description
 *
 * @author zb 2019/07/05 9:21
 */
@Slf4j
public class LoginServerHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket requestPacket) throws Exception {
        log.info("【收到客户端的登录请求】");
        LoginResponsePacket loginResponse = new LoginResponsePacket();
        loginResponse.setVersion(requestPacket.getVersion());
        if (valid(requestPacket)) {
            LoginUtils.markLogin(ctx.channel());
            loginResponse.setSuccess(true);
            log.info("客户端请求登录，密码验证通过");
            loginResponse.setReason(new Date() + "服务端验证通过！");
            LoginUtils.bindSession(new Session(requestPacket.getUserId(),requestPacket.getUserName()),ctx.channel());
        } else {
            loginResponse.setSuccess(false);
            loginResponse.setReason(new Date() + "账号密码验证未通过！");
            log.info("登陆失败！");
        }
        ctx.channel().writeAndFlush(loginResponse);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("...........channelInactive.........");
        LoginUtils.unBindSession(ctx.channel());
        super.channelInactive(ctx);
    }

    /**
     * 从数据库读取数据判断
     * @param requestPacket
     * @return
     */
    private boolean valid(LoginRequestPacket requestPacket) {
        return true;
    }
}
