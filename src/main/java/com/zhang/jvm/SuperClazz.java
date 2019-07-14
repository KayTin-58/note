package com.zhang.jvm;

/**
 * description
 *
 * @author zb 2019/07/11 11:57
 */
public class SuperClazz {
    static {
        System.out.println("SuperClass init!");
    }

    public static String value = "123";
    /**
     * 会被加载到常量池中
     */
    public static final String HE = "Hello";


}
