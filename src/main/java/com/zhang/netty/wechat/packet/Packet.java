package com.zhang.netty.wechat.packet;

import com.zhang.netty.wechat.packet.command.Command;
import lombok.Data;

/**
 * description
 *
 * @author zb 2019/07/03 19:15
 */
@Data
public abstract class Packet implements Command {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();
}
