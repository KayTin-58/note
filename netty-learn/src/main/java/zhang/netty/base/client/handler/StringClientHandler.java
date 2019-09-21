package zhang.netty.base.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Scanner;

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
        startConsoleThread(ctx.channel());
        System.out.println("----------");
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
           for(;;) {
               System.out.println("输入消息发送至服务端: ");
               Scanner sc = new Scanner(System.in);
               String line = sc.nextLine();
               channel.writeAndFlush(line);
           }
        }).start();
    }
}
