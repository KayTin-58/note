package com.zhang.Design_Patterns.proxy_mode.pck_02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description
 *
 * @author zb 2019/05/21 23:05
 */
public class WuDiMeiPo implements InvocationHandler {

    private DanShenDog danShenDog;


    public Object getInstance(DanShenDog danShenDog) {
        this.danShenDog = danShenDog;
        Class<?> c = danShenDog.getClass();
        return Proxy.newProxyInstance(c.getClassLoader(),c.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("媒婆开始物色！");
        method.invoke(this.danShenDog,args);
        System.out.println("媒婆完工收钱！");
        return null;
    }
}
