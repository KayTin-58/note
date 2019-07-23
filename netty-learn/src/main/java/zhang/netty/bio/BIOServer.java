package zhang.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description
 *
 * @author zb 2019/06/29 23:00
 */
@Slf4j
public class BIOServer {

    private static final Integer port = 7777;

    /**
     * 单列模式初始化
     */
    private static class SingletonInstance {
        private static ServerSocket INSTANCE = null;

        public static ServerSocket INIT() {
            if (INSTANCE == null) {
                try {
                    INSTANCE = new ServerSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return INSTANCE;
        }
    }


    public static void start() {
        ServerSocket serverSocket = SingletonInstance.INIT();
        log.info("服务端已经启动，端口号为：", port);
        try {
            /**
             * Acceptor :接收新的Socket连接
             */
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    log.info("客户端Socket信息：", socket.toString());
                    BufferedReader bReader = null;
                    BufferedWriter bWriter = null;
                    try {
                        bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        bWriter = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
                        /**
                         * 处理消息
                         */
                        while (true) {
                            //自旋
                            String msg;
                            if ((msg = bReader.readLine()) != null) {
                                bWriter.write("服务端接受到消息并处理,消息内容：" + msg);
                            } else {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (bReader != null) {
                            try {
                                bReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (bWriter != null) {
                            try {
                                bWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
