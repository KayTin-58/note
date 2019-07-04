package com.zhang.netty.wechat.packet.request;

import com.zhang.netty.wechat.packet.Packet;
import lombok.Getter;
import lombok.Setter;

/**
 * description
 *
 * @author zb 2019/07/04 19:40
 */
@Setter
@Getter
public class MessageRequestPacket extends Packet {

    private String message;


    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
