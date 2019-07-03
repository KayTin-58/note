package com.zhang.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 客户端
 *
 * @author zb
 *
 */
public class MyClient
{
	public static void main(String args[])
	{
		try
		{
			MyClient client = new MyClient();
			client.work(8080);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	SocketChannel sc = null;

	Selector selector = null;

	// 发送接收缓冲区
	ByteBuffer send = ByteBuffer.wrap("data come from client".getBytes());
	ByteBuffer receive = ByteBuffer.allocate(1024);

	public void work(int port) throws IOException
	{

		try
		{

			sc = SocketChannel.open();
			selector = Selector.open();

			// 注册为非阻塞通道
			sc.configureBlocking(false);

			sc.connect(new InetSocketAddress("localhost", 8080));

			//
			sc.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set<SelectionKey> selectionKeys = null;
		/**
		 * 同步
		 */
		while (true)
		{
			// 选择
			if (selector.select() == 0)
			{
				continue;
			}

			Iterator<SelectionKey> it = selector.selectedKeys().iterator();

			/**
			 * 非组赛
			 */
			while (it.hasNext())
			{
				SelectionKey key = it.next();

				// 必须由程序员手动操作
				it.remove();

				sc = (SocketChannel) key.channel();

				if (key.isConnectable())
				{
					if (sc.isConnectionPending())
					{
						// 结束连接，以完成整个连接过程
						sc.finishConnect();
						System.out.println("connect completely");

						try
						{
							sc.write(send);
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

//						sc.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
					}

				}
				else
					if (key.isReadable())
					{
						try
						{
							receive.clear();

							sc.read(receive);

							System.out.println(new String(receive.array()));

						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					else
						if (key.isWritable())
						{
							receive.flip();
							try
							{
								send.flip();

								sc.write(send);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

			}// end while

		}// end while(true)

	}// end work()

}