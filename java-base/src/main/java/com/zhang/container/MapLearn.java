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

        HashMap<Integer, String> hashMap = new HashMap<>(22);

        for (int i = 0; i < 20; i++) {
            /**
             * (newCap = oldCap << 1)
             */
            hashMap.put(i, String.valueOf(i + 1));
        }



        String key = "9999";


        int oldCap = 16;
        int hash = hash(key);
        System.out.println("hash:" + hash);
        int index = (oldCap - 1) & hash;

        System.out.println("oldIndex:" + index);

        int hashOld = hash & oldCap;

        System.out.println("old+16:" + (index + 16));
        System.out.println((32 - 1) & hash);


        System.out.println(5 & 3);

    }


    static final int hash(Object key) {
        int h;
        // key.hashCode()：返回散列值也就是hashcode
        // ^ ：按位异或
        // >>>:无符号右移，忽略符号位，空位都以0补齐
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    static void hashMap() {
        HashMap<String, Integer> hashMap = new HashMap();

        // putVal(hash(key), key, value, false, true);
        /**
         * Node<K,V>[] tab; Node<K,V> p; int n, i;
         *
         * Node: final int hash; final K key; V value; Node<K,V> next;
         *
         * //table== null 或者 长度为0 ，进行扩容 if ((tab = table) == null || (n = tab.length) == 0) n = (tab =
         * resize()).length;
         *
         *
         * if ((p = tab[i = (n - 1) & hash]) == null) //说明没有尾巴 tab[i] = newNode(hash, key, value, null);
         * else { //有尾巴 Node<K,V> e; K k; if (p.hash == hash && ((k = p.key) == key || (key != null &&
         * key.equals(k)))) e = p; //链表长度大于8 变成红黑树 else if (p instanceof TreeNode) e =
         * ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
         *
         *
         * resize: 两倍扩容 if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&oldCap >= DEFAULT_INITIAL_CAPACITY)
         * 将旧的tableNode赋值给新的table for (int j = 0; j < oldCap; ++j) //判断节点是否有链表 if (e.next == null)
         * newTab[e.hash & (newCap - 1)] = e; //是否是红黑树（链表超过8会转为红黑树） else if (e instanceof TreeNode)
         *
         * 0.75 16 newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
         *
         */
        hashMap.put("word", 3);
    }

    static String hh(String a, String b, String c, Integer g) {

        return null;
    }



}
