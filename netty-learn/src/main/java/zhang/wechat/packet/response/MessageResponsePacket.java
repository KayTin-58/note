package zhang.wechat.packet.response;


import lombok.Getter;
import lombok.Setter;
import zhang.wechat.packet.Packet;

/**
 * description
 *
 * @author zb 2019/07/04 19:41
 */
@Setter
@Getter
public class MessageResponsePacket extends Packet {

    private String fromUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }

    @Override
    public String toString() {
        return "MessageResponsePacket{" + "fromUserId='" + fromUserId + '\'' + ", message='" + message + '\'' + '}';
    }
}
