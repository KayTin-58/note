package com.zhang.container;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/11/25
 */
public class CopyOnWriteArrayListLearn {

    public static void main(String[] args) {

        /**
         * private transient volatile Object[] array;
         *
         *
         */
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("hello");
        copyOnWriteArrayList.add(12);

        copyOnWriteArrayList.contains(12);

        copyOnWriteArrayList.get(1);

        copyOnWriteArrayList.remove(0);
    }
}
