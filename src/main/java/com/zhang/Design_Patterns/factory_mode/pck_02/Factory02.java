package com.zhang.Design_Patterns.factory_mode.pck_02;

import com.zhang.Design_Patterns.factory_mode.pck_01.Apple;
import com.zhang.Design_Patterns.factory_mode.pck_01.Pear;

/**
 * description
 *
 * @author zb 2019/05/18 21:56
 */
public class Factory02 {
    public static void main(String[] args) {
        AppleFactory appleFactory = new AppleFactory();
        PearFactory pearFactory = new PearFactory();
        Apple apple = (Apple) appleFactory.createFruit();//获得苹果
        Pear pear = (Pear) pearFactory.createFruit();//获得梨
    }
}
