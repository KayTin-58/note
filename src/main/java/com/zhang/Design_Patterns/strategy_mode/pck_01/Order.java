package com.zhang.Design_Patterns.strategy_mode.pck_01;

import java.util.Date;

/**
 * description
 *
 * @author zb 2019/05/23 23:43
 */
public class Order {

    private String orderId;
    private Double account;
    private Date date;


    PayStatues pay(PayMent payMent) {
        return payMent.pay(this);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", account=" + account +
                ", date=" + date +
                '}';
    }
}
