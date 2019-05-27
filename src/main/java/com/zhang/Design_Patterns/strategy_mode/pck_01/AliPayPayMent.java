package com.zhang.Design_Patterns.strategy_mode.pck_01;

/**
 * description
 *
 * @author zb 2019/05/23 23:49
 */
public class AliPayPayMent implements PayMent {

    @Override
    public PayStatues pay(Order order) {
        System.out.println("支付宝支付策略！");
        PayStatues payStatues = new PayStatues();
        payStatues.setOrderId(order.getOrderId());
        payStatues.setCode(200);
        payStatues.setMsg("支付宝完成支付！");
        return payStatues;
    }
}
