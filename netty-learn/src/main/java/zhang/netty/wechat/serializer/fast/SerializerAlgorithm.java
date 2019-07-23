package zhang.netty.wechat.serializer.fast;

import zhang.netty.wechat.serializer.Serializer;

public interface SerializerAlgorithm extends Serializer {


    /**
     * json 序列化标识
     */
    byte FAST_JSON = 1;
}