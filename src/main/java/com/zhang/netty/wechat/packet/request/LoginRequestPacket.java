package com.zhang.netty.wechat.packet.request;


import com.zhang.netty.wechat.packet.Packet;
import lombok.Builder;
import lombok.Data;

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
