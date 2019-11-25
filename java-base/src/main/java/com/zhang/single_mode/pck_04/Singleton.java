package com.zhang.single_mode.pck_04;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description:注册式单列模式
 *
 * @author zb 2019/05/18 22:58
 */
public class Singleton {
    private Singleton singleton;

    private Singleton() {}

    private static Map<String, Object> singletonMap = new ConcurrentHashMap<>();

    public static Singleton getInstance(String name) {
        if (name == null) {
            name = Singleton.class.getName();
        }

        if (singletonMap.get(name) == null) {
            // 这里是不安全的
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                // 这里充分利用了
                singletonMap.put(name, Singleton.class.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (Singleton) singletonMap.get(name);
    }
}
