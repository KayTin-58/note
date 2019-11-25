package com.zhang.factory_mode.pck_01;

/**
 * description: 简单工厂设计模式
 *
 * @author zb 2019/05/18 21:51
 */
public class Factory01 {


    public static void main(String[] args) {
        // 具体的工厂使用
        FruitFactory mFactory = new FruitFactory();
        Apple apple = (Apple) mFactory.createFruit("apple");// 获得苹果
        Pear pear = (Pear) mFactory.createFruit("pear");// 获得梨
    }
}
