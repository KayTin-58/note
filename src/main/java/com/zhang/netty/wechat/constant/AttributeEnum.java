package com.zhang.netty.wechat.constant;

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
    IS_LOGIN(AttributeKey.newInstance("LOGIN"));
    private AttributeKey attributeKey_Login;

    AttributeEnum(AttributeKey attributeKey) {
         this.attributeKey_Login = attributeKey;
    }
    public AttributeKey getAttributeKey() {
        return attributeKey_Login;
    }

    /*public void setAttributeKey(AttributeKey attributeKey) {
        this.attributeKey_Login = attributeKey;
    }*/}
