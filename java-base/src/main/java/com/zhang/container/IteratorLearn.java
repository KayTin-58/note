package com.zhang.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2018/06/07 13:48
 */
public class IteratorLearn {
    public static void main(String[] args) {
        Collection<User> users  = new ArrayList<User>() ;
        users.add(new User("张三",28));
        users.add(new User("李四",25));
        users.add(new User("王五",31));
        Iterator<User> itrUsers = users.iterator();

        while(itrUsers.hasNext()){
            System.out.println("aaaa");
            User user = (User)itrUsers.next();
            if("张三".equals(user.getName())){
                users.remove(user);
                //itrUsers.remove();
            } else {
                System.out.println(user);
            }
        }
    }
}


final class User implements Cloneable{
    private String name;
    private int age;


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof User)) {
            return false;
        }
        User user = (User)obj;
        //if(this.name==user.name && this.age==user.age)
        if(this.name.equals(user.name)
                && this.age==user.age) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }
    @Override
    public String toString() {
        return "{name:'" + name + "',age:" + age + "}";
    }
    @Override
    public Object clone()  {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {}
        return object;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
}
