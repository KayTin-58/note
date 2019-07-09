package com.zhang.netty.wechat.constant;

import com.zhang.netty.wechat.serializer.Serializer;
import com.zhang.netty.wechat.serializer.fast.JSONSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 *
 * @author zb 2019/07/04 21:24
 */

public class SerializeManager {

    private static final Map<Byte, Serializer>  serializelMap = new ConcurrentHashMap<>();

    static {
        serializelMap.put(JSONSerializer.FAST_JSON,JSONSerializer.INSTANCE);
    }

    public static Serializer getSerializer(byte type) {
        return serializelMap.get(type);
    }
}
