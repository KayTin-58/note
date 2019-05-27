package com.zhang.Design_Patterns.delegation_mode;

/**
 * description
 *
 * @author zb 2019/05/26 22:14
 */
public class StaffB implements Staff {

    @Override
    public Object excute(String command) {
        System.out.println("员工B开始执行："+command);
        return null;
    }
}
