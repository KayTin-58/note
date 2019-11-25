package com.zhang.container;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description
 *
 * @author zb 2019/07/11 15:27
 */
public class ListLearn {

    public static void main(String[] args) {

    }


    static void arrayList() {
        // private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
        List list = new ArrayList();
        // Appends the specified element to the end of this list
        /**
         * if ((p = tab[i = (n - 1) & hash]) == null)
         */
        list.add("999");


    }

    static void linkList() {

        /**
         * private static class Node<E> { E item; Node<E> next; Node<E> prev;
         *
         * Node(Node<E> prev, E element, Node<E> next) { this.item = element; this.next = next; this.prev =
         * prev; } }
         */


        /**
         *
         * 扩容核心 private void grow(int minCapacity) { // overflow-conscious code int oldCapacity =
         * elementData.length; int newCapacity = oldCapacity + (oldCapacity >> 1); if (newCapacity -
         * minCapacity < 0) newCapacity = minCapacity; if (newCapacity - MAX_ARRAY_SIZE > 0) newCapacity =
         * hugeCapacity(minCapacity); // minCapacity is usually close to size, so this is a win: elementData
         * = Arrays.copyOf(elementData, newCapacity); }
         */

        List list = new LinkedList();
        list.add("999");
    }
}


