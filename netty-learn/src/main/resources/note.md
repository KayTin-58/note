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