package zhang.wechat.packet;


import lombok.Data;
import zhang.wechat.packet.command.Command;

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
     * 
     * @return
     */
    public abstract Byte getCommand();
}
