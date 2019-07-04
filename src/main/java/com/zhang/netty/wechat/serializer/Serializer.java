package com.zhang.netty.wechat.serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author zb 2019/07/03 19:21
 */
public interface Serializer {

    /**
     * 用来管理Service
     */
    Map<Byte,Class> map = new HashMap<>();

    /**
     * 注册Service
     */
    void register();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
