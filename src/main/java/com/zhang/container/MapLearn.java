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

        HashMap<Integer,String> hashMap = new HashMap<>();

        for(int i = 0; i < 20; i++) {
            hashMap.put(i,String.valueOf(i+1));
        }




        String key = "9999";
        int oldCap = 16;
        int hash = hash(key);
        int index = (oldCap - 1) & hash;

        System.out.println("oldIndex:"+index);

        int hashOld = hash&oldCap;

        System.out.println("old+16:"+(index+16));
        System.out.println((32-1)&hash);


        System.out.println(5&3);

    }


    static final int hash(Object key) {
        int h;
        // key.hashCode()：返回散列值也就是hashcode
        // ^ ：按位异或
        // >>>:无符号右移，忽略符号位，空位都以0补齐
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
