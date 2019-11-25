package zhang.netty.base.client.handler;

import com.example.tutorial.Messages;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * description
 *
 * @author zb 2019/07/27 16:33
 */
@SuppressWarnings("all")
public class MessageClientHandler extends SimpleChannelInboundHandler<Messages.ChatMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Messages.ChatMsg msg) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = new Random().nextInt(3);
        Messages.ChatMsg message = null;
        switch (i) {
            case 0: {
                message = Messages.ChatMsg.newBuilder().setMsgType(Messages.ChatMsg.MsgType.Login)
                                .setLogin(Messages.Login.newBuilder().setId("123").setUserName("张锡凯")
                                                .setUserPassword("123").build())
                                .build();
                break;
            }
            case 1: {
                message = Messages.ChatMsg.newBuilder().setMsgType(Messages.ChatMsg.MsgType.Message)
                                .setMsg(Messages.Message.newBuilder().setId("123").setMsg("来自外星球的问候")
                                                .setFromUser("未知星球").setToUser("地球").build())
                                .build();
                break;
            }
            case 2: {
                message = Messages.ChatMsg.newBuilder().setMsgType(Messages.ChatMsg.MsgType.CreateGroup)
                                .setCreateGroup(Messages.CreateGroup.newBuilder().setId("123").setGroupName("世界大happy")
                                                .setId("00007").setUserId("8848").build())
                                .build();
                break;
            }
        }
        ctx.channel().writeAndFlush(message);
    }
}
