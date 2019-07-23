package zhang.netty.wechat.packet.command;

/**
 * description
 *
 * @author zb 2019/07/03 19:20
 */

public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte GROUPCHAT_REQUEST = 5;

    Byte GROUPCHAT_RESPONSE = 6;
}
