package com.zhang.Design_Patterns.proxy_mode.pck_02;

/**
 * description
 *
 * @author zb 2019/05/21 23:03
 */
public class XiaoMingDanshenDog implements DanShenDog {

    @Override
    public void DanshenDogFindF() {
        System.out.println("单身狗小明想找女朋友！");
    }

    @Override
    public void playGame() {
        System.out.println("单身狗无聊打游戏！");
    }
}
