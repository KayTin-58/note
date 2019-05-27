package com.zhang.Design_Patterns.strategy_mode.pck_01;

/**
 * description
 *
 * @author zb 2019/05/23 23:51
 */
public class WeChatPayMent implements PayMent {

    @Override
    public PayStatues pay(Order order) {
        System.out.println("微信支付！");
        PayStatues payStatues = new PayStatues();
        payStatues.setMsg("支付宝支付成功！");
        payStatues.setCode(200);
        payStatues.setOrderId(order.getOrderId());
        return payStatues;
    }
}
