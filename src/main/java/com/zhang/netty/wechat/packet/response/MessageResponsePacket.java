package com.zhang.netty.wechat.packet.response;

import com.zhang.netty.wechat.packet.Packet;
import lombok.Getter;
import lombok.Setter;

/**
 * description
 *
 * @author zb 2019/07/04 19:41
 */
@Setter
@Getter
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
