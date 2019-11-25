package com.zhang.jvm.loadclass;

/**
 * description
 *
 * @author zb 2019/07/12 10:59
 */
public class Main {
    public static void main(String[] args) {
        MyClassLoader mcl = new MyClassLoader();
        Object obj = null;
        try {
            Class<?> clazz = Class.forName("People", true, mcl);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());// 打印出我们的自定义类加载器
    }
}
