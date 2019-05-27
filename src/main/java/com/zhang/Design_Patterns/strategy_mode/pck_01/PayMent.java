package com.zhang.Design_Patterns.strategy_mode.pck_01;

/**
 * description
 *
 * @author zb 2019/05/23 23:45
 */
public interface PayMent {

    /**
     * 支付方法
     * @param order
     * @return
     */
    PayStatues pay(Order order);
}
