1、心跳感知
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
  
  