package zhang.netty.wechat.packet.response;

import lombok.Getter;
import lombok.Setter;
import zhang.netty.wechat.packet.Packet;

import static zhang.netty.wechat.packet.command.Command.LOGIN_RESPONSE;


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
