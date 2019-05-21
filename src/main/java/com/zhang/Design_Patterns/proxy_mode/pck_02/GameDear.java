package com.zhang.Design_Patterns.proxy_mode.pck_02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description
 *
 * @author zb 2019/05/21 23:24
 */
public class GameDear implements InvocationHandler {

    private DanShenDog danShenDog;

    public Object getInstance(DanShenDog danShenDog) {
        this.danShenDog = danShenDog;
        Class<?> c = this.danShenDog.getClass();
        return Proxy.newProxyInstance(c.getClassLoader(),c.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("你的游戏情驴已上线！");
        method.invoke(danShenDog,args);
        System.out.println("游戏结束！");
        return null;
    }
}
