package com.zhang.prototype_mode.pck_01;

/**
 * description
 *
 * @author zb 2019/05/19 17:32
 */
public class Main {

    public static void main(String[] args) {
        SuperPerson superPerson = new SuperPerson();
        superPerson.setPerson(new Person("张三", "上海", 23));

        SuperPerson superPerson1 = null;
        try {
            superPerson1 = (SuperPerson) superPerson.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(superPerson == superPerson1);
        System.out.println(superPerson.getPerson() == superPerson1.getPerson());
        System.out.println(superPerson.getPerson().toString());
        System.out.println(superPerson1.getPerson().toString());

        /**
         * false true Person{name='张三', addre='上海', age=23} Person{name='张三', addre='上海', age=23}
         */

    }
}
