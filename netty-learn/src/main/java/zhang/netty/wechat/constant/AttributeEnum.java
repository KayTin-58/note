package zhang.netty.wechat.constant;

import io.netty.util.AttributeKey;

/**
 * description
 *
 * @author zb 2019/07/04 19:50
 */
public enum AttributeEnum {

    /**
     * 登陆标记
     */
    IS_LOGIN(AttributeKey.newInstance("LOGIN")),
    /**
     * Session标记
     */
    SESSION(AttributeKey.newInstance("SESSION"));

    private AttributeKey attributeKey;

    AttributeEnum(AttributeKey attributeKey) {
        this.attributeKey = attributeKey;
    }

    public AttributeKey getAttributeKey() {
        return attributeKey;
    }

}
