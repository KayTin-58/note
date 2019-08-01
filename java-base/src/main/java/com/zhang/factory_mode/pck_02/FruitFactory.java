package com.zhang.factory_mode.pck_02;


import com.zhang.factory_mode.pck_01.Fruit;

/**
 * 抽象工厂
 */
public interface FruitFactory {
    Fruit createFruit();//生产水果
}