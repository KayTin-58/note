package com.zhang.strategy_mode.pck_02;

/**
 * description
 *
 * @author zb 2019/05/24 0:01
 */
public class Main {
    public static void main(String[] args) {
        Order order = new Order();
        System.out.println(order.pay(PayTypes.ALI_PAY));
    }
}
