package zhang.netty.base.server.handler;

import com.example.tutorial.Persons;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 *
 * @author zb 2019/07/27 14:46
 */
@Slf4j
public class MyProtoBufServerHandler extends SimpleChannelInboundHandler<Persons.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Persons.Person person) throws Exception {
        log.info("服务端收到消息："+person.getEmail()+"\n"+person.getName());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("===handlerAdded===");
    }





}
