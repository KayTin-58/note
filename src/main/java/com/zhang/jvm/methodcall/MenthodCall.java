package com.zhang.jvm.methodcall;

/**
 * description:
 * 1、弄清楚几种分派的差别
 * a\ 静态分派：在代码运行前已经确定，代码运行期不可以改变（方法重载）
 * b\ 动态分派：在代码运行期是可以改变的，也是多态的根本（方法重写）
 * c\ 解析：类的静态方法、构造方法、私有方法
 *
 * 方法的重载是由静态类型决定的
 *         （静态类型）
 *         Human human01 = new Man();
 *         Human human02 = new Woman();
 *         main.sayHello(human01);
 *         main.sayHello(human02);
 *
 *
 * @author zb 2019/07/12 11:54
 */
public class MenthodCall {

    static abstract class Human {
        public void sayHello() {
            System.out.println("Hello human!");
        }
    }

    static class Man extends Human {
        @Override
        public void sayHello() {
            System.out.println("Hello man!");
        }
    }

    static class Woman extends Human {
        @Override
        public void sayHello() {
            System.out.println("Hello woman!");
        }
    }

    public void sayHello(Human hum) {
        hum.sayHello();
        System.out.println("hum,call");
    }

    public void sayHello(Man man) {
        man.sayHello();
        System.out.println("man,call");
    }

    public void sayHello(Woman woman) {
        woman.sayHello();
        System.out.println("woman,call");
    }


    public static void main(String[] args) {
        MenthodCall main = new MenthodCall();

        Human human01 = new Man();
        Human human02 = new Woman();
        main.sayHello(human01);
        main.sayHello(human02);

        human01.sayHello();
        human02.sayHello();

        /**
         * 输出结果：
         *
         * Hello man!
         * hum,call
         * Hello woman!
         * hum,call
         * Hello man!
         * Hello woman!
         */

    }
}
