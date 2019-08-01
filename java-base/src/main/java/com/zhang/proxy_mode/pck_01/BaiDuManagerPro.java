package com.zhang.proxy_mode.pck_01;

/**
 * description:代理类
 *
 * @author zb 2019/05/21 0:25
 */
public class BaiDuManagerPro implements Manager {

    private Manager baiDuManager = new BaiDuManager();
    @Override
    public void approval(String name) {
        System.out.println("在经理审批之前部门经理进行评估并给出意见！");
        baiDuManager.approval(name);
        System.out.println("在经理审批之后,会计人员重新计算"+name+"的薪资！");
    }
}
