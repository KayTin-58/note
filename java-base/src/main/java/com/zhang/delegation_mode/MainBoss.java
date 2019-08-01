package com.zhang.delegation_mode;

/**
 * description:
 * https://www.jianshu.com/p/38acf37b1e1f
 *
 * @author zb 2019/05/26 22:13
 */
public class MainBoss {
    public static void main(String[] args) {
       Staff staff = new GroupLeader();
       staff.excute("大数据");
    }
}
