package com.zhang.strategy_mode.pck_02;

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
