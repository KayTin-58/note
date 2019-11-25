package zhang.wechat.serializer.fast;

import com.alibaba.fastjson.JSON;
import zhang.wechat.serializer.Serializer;

/**
 * description
 *
 * @author zb 2019/07/03 19:25
 */
public class JSONSerializer implements SerializerAlgorithm {

    public static Serializer INSTANCE = new JSONSerializer();


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
        return JSON.parseObject(bytes, clazz);
    }
}
