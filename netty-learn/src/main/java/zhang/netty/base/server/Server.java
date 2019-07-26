package zhang.netty.base.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;


/**
 * description
 *
 * @author zb 2019/07/24 11:23
 */
@Slf4j
public class Server {
    public static void main(String[] args) {
       server();
    }


    static void server() {
        /**
         * 接受连接
         */
        NioEventLoopGroup boss = new NioEventLoopGroup();
        /**
         * 处理连接
         */
        NioEventLoopGroup worker = new NioEventLoopGroup();
        /**
         * 服务启动
         */
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //EventLoopGroup parentGroup, EventLoopGroup childGroup
        serverBootstrap.group(boss,worker)
                //设置通信管道
                .channel(NioServerSocketChannel.class)
                //.handler(new IdleStateHandler(5,7,10))
                //worker
                .childHandler(new WebSocketChannelInitilizer());
        startServer(serverBootstrap,8888);
    }

    static void startServer(ServerBootstrap serverBootstrap,Integer port) {
        try {
            serverBootstrap.bind(port).addListener((future) -> {
                if(future.isSuccess()) {
                    log.info("{Server}:"+"【端口绑定成功！】");
                }else {
                    log.error("{Server}:"+"【端口绑定失败！】");
                }
            }).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
