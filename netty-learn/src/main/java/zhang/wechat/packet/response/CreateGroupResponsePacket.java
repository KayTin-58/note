package zhang.wechat.packet.response;


import lombok.Getter;
import lombok.Setter;
import zhang.wechat.packet.Packet;

import java.util.List;

/**
 * description:创建群组的响应信息
 *
 * @author zb 2019/07/23 14:54
 */
@Setter
@Getter
public class CreateGroupResponsePacket extends Packet {
    /**
     * 谁发送的消息
     */
    private String fromUser;
    /**
     * 群组有哪些人
     */
    private List<String> useridList;
    /**
     * 建群的附带信息
     */
    private String message;
    /**
     * 群组ID
     */
    private String groupId;

    @Override
    public Byte getCommand() {
        return GROUPCHAT_RESPONSE;
    }

    @Override
    public String toString() {
        return "CreateGroupResponsePacket{" +
                "fromUser='" + fromUser + '\'' +
                ", useridList='" + useridList + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
