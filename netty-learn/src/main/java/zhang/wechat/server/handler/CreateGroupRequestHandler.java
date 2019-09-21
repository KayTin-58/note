package zhang.wechat.server.handler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import zhang.wechat.packet.request.CreateGroupRequestPacket;
import zhang.wechat.packet.response.CreateGroupResponsePacket;
import zhang.wechat.utils.LoginUtils;


import java.util.List;
import java.util.UUID;

/**
 * description
 *
 * @author zb 2019/07/23 14:47
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> useridList = createGroupRequestPacket.getUseridList();
        ChannelGroup channels =  new DefaultChannelGroup(ctx.executor());
        for(String userid:useridList) {
            Channel channel = LoginUtils.getChannel(userid);
            if(channel !=null && LoginUtils.hasLogin(channel)) {
                channels.add(channel);
            }
        }

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();

        /**
         * 创建群组的响应信息
         */
        createGroupResponsePacket.setMessage(createGroupRequestPacket.getMessage());
        createGroupResponsePacket.setFromUser(LoginUtils.getSession(ctx.channel()).getUserId());
        createGroupResponsePacket.setUseridList(useridList);
        createGroupResponsePacket.setGroupId(UUID.randomUUID().toString());

        channels.writeAndFlush(createGroupResponsePacket);
    }



}
