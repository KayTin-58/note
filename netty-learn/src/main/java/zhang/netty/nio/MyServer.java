package zhang.netty.nio;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器端
 *
 * @author zb
 *
 */
public class MyServer {
    public ConcurrentHashMap<String, SocketChannel> map = new ConcurrentHashMap(100);

    public static void main(String args[]) throws Exception {
        MyServer server = new MyServer(8080);
        server.listen();
    }

    // 接受和发送数据缓冲区
    private ByteBuffer send = ByteBuffer.allocate(1024);
    private ByteBuffer receive = ByteBuffer.allocate(1024);

    public int port = 0;

    ServerSocketChannel ssc = null;

    Selector selector = null;


    /**
     * 服务
     * 
     * @param port
     * @throws Exception
     */
    public MyServer(int port) throws Exception {
        // 打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 检索与此通道关联的服务器套接字
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 进行服务的绑定
        serverSocket.bind(new InetSocketAddress(port));
        // 通过open()方法找到Selector
        selector = Selector.open();

        // 注册到selector，等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server Start----8888:");

        // 向发送缓冲区加入数据
        send.put("data come from server".getBytes());
    }

    // 监听
    private void listen() throws IOException {
        while (true) {
            // 选择一组键，并且相应的通道已经打开
            // It returns only after at least one channel is selected
            int count = selector.select();
            // 返回此选择器的已选择键集。Returns this selector's selected-key set.
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                // 这里记得手动的把他remove掉，不然selector中的selectedKeys集合不会自动去除
                iterator.remove();
                dealKey(selectionKey);
            }
        }
    }

    // 处理请求
    private void dealKey(SelectionKey selectionKey) throws IOException {

        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count = 0;

        // 测试此键的通道是否已准备好接受新的套接字连接。
        if (selectionKey.isAcceptable()) {
            // 返回为之创建此键的通道。 （事件）
            server = (ServerSocketChannel) selectionKey.channel();

            // 此方法返回的套接字通道（如果有）将处于阻塞模式。
            client = server.accept();
            // 配置为非阻塞
            client.configureBlocking(false);
            // 注册到selector，等待连接
            client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            /**
             * 记录客户端
             */
            map.put(RandomStringUtils.random(10), client);
        } else if (selectionKey.isReadable()) {
            // 返回为之创建此键的通道。
            client = (SocketChannel) selectionKey.channel();
            // 将缓冲区清空以备下次读取
            receive.clear();
            // 读取服务器发送来的数据到缓冲区中
            client.read(receive);

            String message = new String(receive.array());
            System.out.println("客户端信息：" + message);

            // 向所有的连接的客户端推送此条消息
            for (Map.Entry<String, SocketChannel> object : map.entrySet()) {
                SocketChannel channel = object.getValue();
                if (channel.isConnected()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    byteBuffer.put(("来自于xx的消息：" + message).getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                } else {
                    // 移除
                }
            }

            selectionKey.interestOps(SelectionKey.OP_WRITE);
        } else if (selectionKey.isWritable()) {
            // 将缓冲区清空以备下次写入
            send.flip();
            // 返回为之创建此键的通道。
            client = (SocketChannel) selectionKey.channel();

            // 输出到通道
            client.write(send);

            selectionKey.interestOps(SelectionKey.OP_READ);
        }
    }
}
