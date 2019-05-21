package com.zhang.Design_Patterns.proxy_mode.pck_01;

/**
 * description
 *
 * @author zb 2019/05/21 0:24
 */
public class BaiDuManager implements Manager {

    @Override
    public void approval(String name) {
        System.out.println("百度总经理审批："+name);
    }
}
