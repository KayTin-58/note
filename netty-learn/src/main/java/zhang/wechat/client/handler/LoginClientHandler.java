package zhang.wechat.client.handler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import zhang.wechat.packet.request.LoginRequestPacket;
import zhang.wechat.packet.request.MessageRequestPacket;
import zhang.wechat.packet.response.LoginResponsePacket;
import zhang.wechat.utils.LoginUtils;

import java.util.Scanner;
import java.util.UUID;

/**
 * description
 *
 * @author zb 2019/07/04 22:37
 */
@Slf4j
public class LoginClientHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    /**
     * 读取数据
     * @param resp
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket resp) throws Exception {
        if (resp.isSuccess()) {
            log.info("{clien:}"+"【服务端响应：登陆成功！】");
            /**
             * 登陆成功标记
             * 写入数据库
             */
            LoginUtils.markLogin(ctx.channel());
            startConsoleThread(ctx.channel());
        } else {
            log.info("{clien:}"+"【服务端响应：登陆失败！】");
        }
    }


    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtils.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    channel.writeAndFlush(packet);
                }
            }
        }).start();
    }

    /**
     * 写入数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端开始登陆！");
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = LoginRequestPacket.builder().build();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setUserPassword("pwd");
        /**
         * 发送数据
         */
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
