package zhang.wechat.packet.request;



import lombok.Builder;
import lombok.Data;
import zhang.wechat.packet.Packet;

/**
 * description:
 *
 * @author zb 2019/07/03 19:18
 */
@Data
@Builder
public class LoginRequestPacket extends Packet {

    public LoginRequestPacket(String userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public LoginRequestPacket() {
    }

    private String  userId;
    private String userName;
    private String userPassword;


    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

}
