package com.zhang.Design_Patterns.strategy_mode.pck_02;

/**
 * description
 *
 * @author zb 2019/05/24 0:06
 */
public enum PayTypes {
    ALI_PAY(new AliPayPayMent()), WECHAT_PAY(new WeChatPayMent());
    private PayMent payMent;

    PayTypes(PayMent payMent) {
        this.payMent = payMent;
    }

    protected PayMent get() {
        return this.payMent;
    }
}
