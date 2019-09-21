package zhang.wechat.packet.response;

import lombok.Getter;
import lombok.Setter;
import zhang.wechat.packet.Packet;


/**
 * description
 *
 * @author zb 2019/07/04 19:21
 */
@Setter
@Getter
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
