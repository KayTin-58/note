package com.zhang.FenBuShi_Base.bitmap.pck_01;

import java.util.ArrayList;
import java.util.List;

public class BitMap {

    private static final int N = 10000000;

    private int[] a = new int[N/32 + 1];

    /**
     * 设置所在的bit位为1
     * @param n
     */
    public void addValue(int n){
        //row = n / 32 求十进制数在数组a中的下标
        int row = n >> 5;
        //相当于 n % 32 求十进制数在数组a[i]中的下标
        a[row] |= 1 << (n & 0x1F);
    }

    // 判断所在的bit为是否为1
    public boolean exits(int n){
        int row = n >> 5;
        return (a[row] & ( 1 << (n & 0x1F))) != 1;
    }

    public void display(int row){
        System.out.println("BitMap位图展示");
        for(int i=0;i<row;i++){
            List<Integer> list = new ArrayList<Integer>();
            int temp = a[i];
            for(int j=0;j<32;j++){
                list.add(temp & 1);
                temp >>= 1;
            }
            System.out.println("a["+i+"]" + list);
        }
    }

    public static void main(String[] args){
        int num[] = {1,5,30,32,64,56,159,120,21,17,35,45};
        BitMap map = new BitMap();

        for(int i=0;i<num.length;i++){
            map.addValue(num[i]);
        }

        int temp = 120;
        if(map.exits(temp)){
            System.out.println("temp:" + temp + "has already exists");
        }
        map.display(5);
    }
}