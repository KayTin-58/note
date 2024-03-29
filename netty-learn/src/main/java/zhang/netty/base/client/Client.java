package zhang.netty.base.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author zb 2019/07/24 18:51
 */
@Slf4j
public class Client {
    private static final int MAX_RETRY = 10;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClinetChannelInitilizer());

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("{client}:"+"【成功连接！】");
                /**
                 * 开启输入端
                 */
            } else if (retry == 0) {
                log.info("{client}:"+"【重试次数已用完，放弃连接！】");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                log.info("{client}:"+new Date() + ": 【连接失败，第" + order + "次重连……】");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }
}
