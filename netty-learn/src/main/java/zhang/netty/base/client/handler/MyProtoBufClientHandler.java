package zhang.netty.base.client.handler;

import com.example.tutorial.Persons;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * description
 *
 * @author zb 2019/07/27 15:49
 */
public class MyProtoBufClientHandler extends SimpleChannelInboundHandler<Persons.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Persons.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Persons.Person person =Persons.Person.newBuilder().setEmail("1511591917@qq.com")
                .setId(1)
                .setName("张锡凯")
                .build();
        ctx.channel().writeAndFlush(person);
        System.out.println("---------------");
    }
}
