package zhang.netty.wechat.packet.request;


import lombok.Getter;
import lombok.Setter;
import zhang.netty.wechat.packet.Packet;

import java.util.List;

import static zhang.netty.wechat.packet.command.Command.GROUPCHAT_REQUEST;

/**
 * description:创建群组的请求
 *
 * @author zb 2019/07/23 14:41
 */
@Setter
@Getter
public class CreateGroupRequestPacket extends Packet {

    /**
     * 参加群聊的用户ID
     */
    private List<String> useridList;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUPCHAT_REQUEST;
    }
}
