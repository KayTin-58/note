package com.zhang.factory_mode.pck_02;

import com.zhang.Design_Patterns.factory_mode.pck_01.Fruit;
import com.zhang.Design_Patterns.factory_mode.pck_01.Pear;

public class PearFactory implements FruitFactory {
    @Override
    public Fruit createFruit() {
        return new Pear();
    }
}