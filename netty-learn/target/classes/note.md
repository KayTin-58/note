````1、心跳感知
在App与Netty连接的时候，如果App调成飞行模式,netty是无法感知的

核心Handler:
 # IdleStateHandler
 # HeartServerHandler


Http
 # 无状态的
 # 基于请求和响应的

 请求头：Header
 响应体：Body

 # http1.0
   1、响应之后即链接断掉

 # http1.1
   1、可以在指定时间内保持链接

  //基础Handler

WebSocker协议：
 # ws://localhost:port
 //基础Handler
 ChannelPipeline channelPipeline = ch.pipeline();
 channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
 channelPipeline.addLast("HttpServerCodec",new ChunkedWriteHandler());
 channelPipeline.addLast("HttpServerCodec",new HttpObjectAggregator(8192));
 channelPipeline.addLast(new WebSocketServerProtocolHandler(PATH));

 # 基于一次的连接

ProtoBuf使用：
  1、定义说明文件：对象的结构体、方法、属性等信息
 # 下载编译器 https://github.com/protocolbuffers/protobuf/releases
 # 配置环境变量 D:\protocal\protoc-3.9.0-win64\bin

 # 多消息类型传输
   com.example.tutorial.Messages
 # 支持Enum
   enum MsgType {
       Message  = 1;
       Login  = 2;
       CreateGroup  = 3;
    }

 考虑：
   怎样在客户端和服务端 共享 protobuf生成的共享消息代码

 # Grpc学习
 类似于Thrift:
 https://www.grpc.io/
 http://doc.oschina.net/grpc?t=60134
 https://www.grpc.io/docs/quickstart/

     // The greeting service definition.
     service Greeter {
       // Sends a greeting
       rpc SayHello (HelloRequest) returns (HelloReply) {}
       // Sends another greeting
       rpc SayHelloAgain (HelloRequest) returns (HelloReply) {}
     }

     // The request message containing the user's name.
     message HelloRequest {
       string name = 1;
     }

     // The response message containing the greetings
     message HelloReply {
       string message = 1;
     }
     
 #回调钩子
 Runtime
 
 
 
 #GRPC的调用方式
 ```
 rpc SayHello(HelloRequest) returns (HelloResponse){
 }

 rpc LotsOfReplies(HelloRequest) returns (stream HelloResponse){
 }
 
 rpc LotsOfGreetings(stream HelloRequest) returns (HelloResponse) {
 }
 
 rpc BidiHello(stream HelloRequest) returns (stream HelloResponse){
 }
 ```
 


Thrift的使用：
  RPC特点：支持多种语言数据类型的交集
  RPC数据传输：Socket
  # Struct:结构体
  # Service:服务方法

  比较灵活
  # 可以自成一套体系 不依赖于Netty



-DskipTests，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。
-Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类。




### **IO**

io中的设计模式
  装饰模式  装饰的是对象
  
### Nio
三个核心概念：

1、selecter：选择器
2、channel ：通道
3、buffer ：一块内存，实际是一个数组。既可以读又可以写

```
一般建议在把一个Buffer的容量使用完之后再flip
否则buffer的limit会越来越小
当然也可以使用clear

 /**
         * public final Buffer flip() {
         *         limit = position;
         *         position = 0;
         *         mark = -1;
         *         return this;
         *     }
         * 读写切换
         */
        buffer.flip();
```


buffer:
  四个核心属性
  
  ```
  /**
         * public final Buffer flip() {
         *         limit = position;
         *         position = 0;
         *         mark = -1;
         *         return this;
         *     }
         * 读写切换
         */
   buffer.flip();
  ```
  
  
  ```
    mark <= position <= limit <= capacity
    
    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;
  ```
  
  ```
  读时：
       int mark :记录当前的position位置，mark如果在大于position或者limit时，会被丢弃
       int position :是下一个读取操作的index
       int limit :：第一个不应该读取或写入的数据的索引，即位于 limit 后的数据不可读写
       int capacity :代表着缓冲区的大小
    
    写时：
       int mark = -1;
       int position = 0;
       int limit;
       int capacity;
  ```
  
  ###**直接缓冲与间接缓冲**
  
  ```
   DirectByteBuffer
   直接在内存中分配数据存储空间（堆外内存）
   不属于jvm的虚拟内存区域
   效率更高
   零拷贝到的概念便是来于于此
   
   HeapIntBuffer
   在jvm分配的内存空间
   受jvm的直接管理
   在实际操作时，会将jvm堆中的数据拷贝到native内存中
   
  ```
  
  ```
   buffer.position(2);
   buffer.limit(10);
   buffer.slice();
   
   底层共享相同的数组
  ```
  
****MappedByteBuffer****

Forces any changes made to this buffer's content to be written to the
storage device containing the mapped file.


位于堆外内存
  ```
  RandomAccessFile rw = new RandomAccessFile(file, "rw");
          FileChannel channel = rw.getChannel();
          //将磁盘文件映射到内存中
          MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
          int writeSize = 0;
          Long start = System.currentTimeMillis();
          while (writeSize < fileSize) {
              //这里的操作实际操作的是内存 但是最终是作用到了file上面
              map.put(new byte[size]);
              //Forces any changes made to this buffer's content to be written to the
              // storage device containing the mapped file.
              map.force();
              writeSize += size;
          }
  ```


****Selector选择器****

传统的模型
Servercoket s = new 
while(true) {
  Socket socket = s.accept()
  new Thread(socket)
}

1、事件
 
 // 通过open()方法找到Selector
 selector = Selector.open();

 //Channel注册到Selector上面
 serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

 //获取注册当当前Selector上面的Channel
 Set<SelectionKey> selectionKeys = selector.selectedKeys();

 //判断事件类型
 while (true)
 		{
 			// 选择一组键，并且相应的通道已经打开
 			// It returns only after at least one channel is selected
 			int count = selector.select();
 			// 返回此选择器的已选择键集。Returns this selector's selected-key set.
 			Set<SelectionKey> selectionKeys = selector.selectedKeys();
 
 			Iterator<SelectionKey> iterator = selectionKeys.iterator();
 			while (iterator.hasNext())
 			{
 				SelectionKey selectionKey = iterator.next();
 
 				// 这里记得手动的把他remove掉，不然selector中的selectedKeys集合不会自动去除
 				iterator.remove();
 				dealKey(selectionKey);
 			}
 		}

dealKey(key) {
 if (selectionKey.isAcceptable())
 		{
 			// 返回为之创建此键的通道。
 			server = (ServerSocketChannel) selectionKey.channel();
 
 			// 此方法返回的套接字通道（如果有）将处于阻塞模式。
 			client = server.accept();
 			// 配置为非阻塞
 			client.configureBlocking(false);
 			// 注册到selector，等待连接
 			client.register(selector, SelectionKey.OP_READ
 					| SelectionKey.OP_WRITE);
 		}
 else if (selectionKey.isReadable())
 			{
 				// 返回为之创建此键的通道。
 				client = (SocketChannel) selectionKey.channel();
 				// 将缓冲区清空以备下次读取
 				receive.clear();
 				// 读取服务器发送来的数据到缓冲区中
 				client.read(receive);
 
 				System.out.println(new String(receive.array()));
 
 				selectionKey.interestOps(SelectionKey.OP_WRITE);
 			}
 else if (selectionKey.isWritable())
 				{
 					// 将缓冲区清空以备下次写入
 					send.flip();
 					// 返回为之创建此键的通道。
 					client = (SocketChannel) selectionKey.channel();
 
 					// 输出到通道
 					client.write(send);
 
 					selectionKey.interestOps(SelectionKey.OP_READ);
 				}
}

//服务端
ServerSocketChannel 等待连接
//客户端
ocketChannel 等待读写


**知识回顾**






**##Netty底层源码**

###**NIO零拷贝**

IO数据流向：
  在磁盘上的数据 =》 内存 =》程序 =》 新的目标地点

用户空间                        内核空间                     磁盘空间
     ->      read() syscall              ->     ask for data       
     <-      copy data to user           <-     data to 内核  <-
     ->      write() syscall    to 内核   ->    write to 磁盘或者网络
 


