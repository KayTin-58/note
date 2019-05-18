package com.zhang.Design_Patterns.factory_mode.pck_03;

public class HongMiFactory implements PhoneFactory {

    @Override
    public Cpu getCpu() {
        return new Cpu.Cpu650();//高效处理器
    }

    @Override
    public Screen getScreen() {
        return new Screen.Screen5();//小屏手机
    }
}