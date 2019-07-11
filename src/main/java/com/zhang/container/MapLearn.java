package com.zhang.container;

import java.util.HashMap;

/**
 * description
 *
 * @author zb 2019/07/11 15:29
 */
public class MapLearn {
    public static void main(String[] args) {
        System.out.println(hash("9999"));

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("9999","9999");

        int hash = hash("9999");
        int n = hashMap.size();
        System.out.println("n:"+n);
        int index = (n - 1) & hash;
        System.out.println("index:"+index);


        System.out.println((3&1754714));
    }


    static final int hash(Object key) {
        int h;
        // key.hashCode()：返回散列值也就是hashcode
        // ^ ：按位异或
        // >>>:无符号右移，忽略符号位，空位都以0补齐
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
