package com.zhang.jvm;

/**
 * description
 *
 * @author zb 2019/07/11 12:50
 */
class SingleTon {

    private static SingleTon singleTon = new SingleTon();

    public static int count1;
    public static int count2 = 0;


    private SingleTon() {

        count1++;
        count2++;
    }


    /*private static class SingleTonHandler{
        private static final SingleTon INSTANCE= new SingleTon();
    }*/



    public static SingleTon getInstance() {
        return singleTon;
    }
}


  public class Test {
    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("count1=" + singleTon.count1);
        System.out.println("count2=" + singleTon.count2);
    }
}