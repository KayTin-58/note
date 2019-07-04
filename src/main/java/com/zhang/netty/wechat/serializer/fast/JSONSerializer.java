package com.zhang.netty.wechat.serializer.fast;

import com.alibaba.fastjson.JSON;
import com.zhang.netty.wechat.serializer.Serializer;

import java.util.Map;

/**
 * description
 *
 * @author zb 2019/07/03 19:25
 */
public class JSONSerializer implements SerializerAlgorithm{




    @Override
    public void register() {
       map.put(getSerializerAlgorithm(),JSONSerializer.class);
    }

    @Override
    public byte getSerializerAlgorithm() {
        return FAST_JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
