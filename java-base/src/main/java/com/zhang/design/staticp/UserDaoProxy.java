package com.zhang.design.staticp;

/**
 * 代理对象,静态代理
 *
 * 静态代理的组成元素： 1、统一接口 2、被代理对象(需要实现统一接口) 3、代理类。需要实现统一接口
 *
 *
 * 静态代理的优点： 1、可以做到在不修改目标对象的功能前提下,对目标功能扩展. 缺点：
 * 2、因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
 *
 *
 *
 */
public class UserDaoProxy implements IUserDao {

    // 接收保存目标对象
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    public void save() {
        System.out.println("开始事务...");
        target.save();// 执行目标对象的方法
        System.out.println("提交事务...");
    }
}
