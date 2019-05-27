package com.zhang.Design_Patterns.strategy_mode.pck_02;

/**
 * description
 *
 * @author zb 2019/05/23 23:45
 */
public class PayStatues {
    private Integer code;
    private String msg;
    private String orderId;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "PayStatues{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
