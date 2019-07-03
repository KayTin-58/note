package com.zhang.netty.wechat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private static int MAX_ = 15;

    /**
     * 重连 指数退避
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     * @return
     */
    private static Channel connect(Bootstrap bootstrap, String host, int port, int retry) {
        return bootstrap.connect(host, port).addListener((future) -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
                return;
            } else {
                if (retry <= 0) {
                    System.out.println("重试次数已经用完了！");
                    return;
                } else {
                    int newReTry = retry - 1;
                    int order = (MAX_ - retry) + 1;
                    System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                    int delay = 1 << order;
                    //connect(bootstrap,host,port,newReTry);
                    bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, newReTry), delay, TimeUnit
                            .SECONDS);

                }

            }
        }).channel();
    }

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });


        connect(bootstrap, "127.0.0.1", 8000, MAX_);


    }


    static class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            while (true) {
                ctx.writeAndFlush(getByteBuf(ctx, new Date() + ": hello world!"));
                Thread.sleep(2000);
            }
        }


        private ByteBuf getByteBuf(ChannelHandlerContext ctx, String msg) {
            ByteBuf byteBuf = ctx.alloc().buffer();
            byte[] bytes = msg.getBytes(Charset.forName("utf-8"));
            byteBuf.writeBytes(bytes);
            return byteBuf;
        }
    }
}


