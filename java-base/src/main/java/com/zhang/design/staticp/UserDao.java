package com.zhang.design.staticp;

/**
 * Created by zb on 2018/12/16.
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("被代理对象的方法体执行！");
    }
}
