package com.zhang.decorator_mode.pck_01;

/**
 * 对煎饼加火腿的装饰类，继承PancakeDecorator，覆盖cook操作
 * Created by BrightLoong on 2018/4/22.
 */
public class HamDecorator extends PancakeDecorator {
    public HamDecorator(IPancake pancake) {
        super(pancake);
    }

    /**
     * 覆盖cook方法，加入自身的实现，并且调用父类的cook方法，也就是构造函数中
     * EggDecorator(IPancake pancake)，这里传入的pancake的cook操作
     */
    @Override
    public void cook() {
        System.out.println("加了一根火腿，");
        super.cook();
    }
}
