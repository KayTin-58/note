package com.test;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2018/06/07 13:48
 */

public class Main {

    public static final String TYPE_OF_DATA = "%s(%s)";

    public static void main(String[] args) {
        Person person = Person.builder().address("武汉").id(90).name("张").build();
        test(person);
        System.out.println(person);

        System.out.println(String.format(TYPE_OF_DATA, "varchar", 36));
    }



    public static Boolean test(Person person) {
        person.setAddress("上海");
        return true;
    }
}
