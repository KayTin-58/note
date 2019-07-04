package com.zhang.netty.wechat;

import com.zhang.netty.wechat.packet.PacketCodec;
import com.zhang.netty.wechat.packet.request.LoginRequestPacket;
import com.zhang.netty.wechat.packet.Packet;
import com.zhang.netty.wechat.serializer.fast.JSONSerializer;
import com.zhang.netty.wechat.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;


/**
 * description
 *
 * @author zb 2019/07/03 20:11
 */
public class Main {

   /* @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUserName("zhangsan");
        loginRequestPacket.setUserPassword("password");


        PacketCodec packetCodeC = PacketCodec.INSTANCE
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }*/
}
