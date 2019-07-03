package com.zhang.netty.wechat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * description
 *
 * @author zb 2019/07/01 21:05
 */
public class NettyServer {

    private static int nThreads_boss = Runtime.getRuntime().availableProcessors() * 2;
    private static int nThreads_worker = 100;
    private static int port = 8000;

    public static void main(String[] args) {
        //
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        /**
         * 相当于BIO中accept，接受连接
         */
        NioEventLoopGroup boss = new NioEventLoopGroup(nThreads_boss);
        /**
         * workerGroup表示处理每一条连接的数据读写的线程组
         */
        NioEventLoopGroup worker = new NioEventLoopGroup(nThreads_worker);

        ChannelFuture future = serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                //.handler(null) //用来处理服务启动过程中的逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new TcpHandler());
                    }
                }).bind(8000);
        future.addListener((o) -> {
            if (o.isSuccess()) {
                System.out.println("端口绑定成功！");
            } else {
                serverBootstrap.bind(port + 1);
            }
        });

    }

    static class TcpHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
            System.out.println("server端0：" + msg);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("server端：" + msg);
        }
    }
}
