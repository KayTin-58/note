package com.zhang.single_mode.pck_01;

/**
 * description：
 * 静态代码快
 *
 * @author zb 2019/05/18 22:03
 */
public class Singleton01 {
    private static Singleton01 instance;

    static {
        instance = new Singleton01();
    }

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        return instance;
    }
}
