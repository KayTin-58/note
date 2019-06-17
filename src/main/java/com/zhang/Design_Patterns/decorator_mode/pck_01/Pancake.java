package com.zhang.Design_Patterns.decorator_mode.pck_01;

/**
 * 具体的煎饼对象，可用其他装饰类进行动态扩展。
 * Created by BrightLoong on 2018/4/22.
 */
public class Pancake implements IPancake{
    @Override
    public void cook() {
        System.out.println("的煎饼");
    }
}

