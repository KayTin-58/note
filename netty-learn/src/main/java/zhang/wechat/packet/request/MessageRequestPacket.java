package zhang.wechat.packet.request;


import lombok.Getter;
import lombok.Setter;
import zhang.wechat.packet.Packet;


/**
 * description
 *
 * @author zb 2019/07/04 19:40
 */
@Setter
@Getter
public class MessageRequestPacket extends Packet {

    private String message;
    private String toUserId;


    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    @Override
    public String toString() {
        return "MessageRequestPacket{" + "message='" + message + '\'' + ", toUserId='" + toUserId + '\'' + '}';
    }
}
