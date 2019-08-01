package com.zhang.prototype_mode.pck_01;

import java.io.Serializable;

/**
 * description
 *
 * @author zb 2019/05/19 17:30
 */
public class Person implements Serializable {
    private String name;
    private String addre;
    private Integer age;

    public Person(String name, String addre, Integer age) {
        this.name = name;
        this.addre = addre;
        this.age = age;
    }


    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddre() {
        return addre;
    }

    public void setAddre(String addre) {
        this.addre = addre;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", addre='" + addre + '\'' +
                ", age=" + age +
                '}';
    }
}
