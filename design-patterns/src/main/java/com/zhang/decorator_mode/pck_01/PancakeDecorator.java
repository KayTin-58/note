package com.zhang.decorator_mode.pck_01;

/**
 * 实现接口的抽象装饰类，建议设置成abstract.
 * Created by BrightLoong on 2018/4/22.
 */
public abstract  class PancakeDecorator implements IPancake {

    /***/
    private IPancake pancake;

    public PancakeDecorator(IPancake pancake) {
        this.pancake = pancake;
    }

    @Override
    public void cook() {
        if (this.pancake != null) {
            pancake.cook();
        }
    }
}
