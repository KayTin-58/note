package com.zhang.factory_mode.pck_02;


import com.zhang.factory_mode.pck_01.Apple;
import com.zhang.factory_mode.pck_01.Fruit;

public class AppleFactory implements FruitFactory {
    @Override
    public Fruit createFruit() {
        return new Apple();
    }
}