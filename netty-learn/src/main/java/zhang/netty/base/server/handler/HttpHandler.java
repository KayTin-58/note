package zhang.netty.base.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import zhang.netty.base.server.bean.RequestHeaders;


/**
 * description
 *
 * @author zb 2019/07/24 11:34
 */
@Slf4j
public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {


    /**
     * 响应客户端请求
     * @param ctx
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {
        log.info("{channelRead0}:"+"【执行！】");

        if(httpObject instanceof HttpRequest) {

            HttpRequest httpRequest = (HttpRequest)httpObject;
            RequestHeaders requestHeaders = packageRequestHeaders(httpRequest);
            log.info("{server}:"+requestHeaders.toString());
            if(requestHeaders.getUrl().contains("/favicon.ico")) {
                log.warn(requestHeaders.getUrl());
                return;
            }

            /**
             * 响应内容
             */
            ByteBuf context = Unpooled.copiedBuffer("hello China", CharsetUtil.UTF_8);
            /**
             * 构造响应头
             */
            FullHttpResponse response =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,context);
            /**
             * 返回的数据类型
             */
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            /**
             * 数据长度
             */
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,context.readableBytes());
            /**
             * 写入返回数据
             */
            ctx.writeAndFlush(response);
        }

    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("{server}:"+"【channelRegistered】");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("{server}:"+"【channelUnregistered】");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{server}:"+"【channelActive】");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{server}:"+"【channelInactive】");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("{server}:"+"【channelReadComplete】");
        super.channelReadComplete(ctx);
    }

    /**
     * 封装请求头参数
     * @param httpRequest
     * @return
     */
    static RequestHeaders packageRequestHeaders(HttpRequest httpRequest) {
        String methodName = httpRequest.method().name();
        String url = httpRequest.uri();
        String version = httpRequest.protocolVersion().toString();
        RequestHeaders requestHeaders = RequestHeaders.builder()
                .menthodName(methodName)
                .url(url)
                .version(version)
                .build();
        return requestHeaders;
    }
}
