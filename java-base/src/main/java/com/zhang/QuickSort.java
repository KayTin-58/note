package com.zhang;

/**
 * description
 *
 * @author zb 2019/07/11 16:52
 */
public class QuickSort {
    public static void main(String[] args) {

         int[] arrays = {9,5,6,19,3,6,23,11,4};

         qucikSort(arrays,0,arrays.length-1);
         for(int i = 0; i < arrays.length; i++) {
             System.out.println(arrays[i]);
         }
    }


    /**
     * @param arrays 目标数组
     * @param left   左起点
     * @param right  右七点
     */
    static void qucikSort(int[] arrays, int left, int right) {
        int temp = 0;//基数
        int i = left;
        int j = right;

        /**
         * 递归的边界
         */
        if(left > right) {
            return;
        }

        temp = arrays[left];

        while(i < j) {
            while (arrays[j] >= temp && j > i) {
                j--;//左移
            }

            while (arrays[i] <= temp && i<j) {
                i++;//右移
            }
            if(i<j) {
                //交换位置
                arrays[i] = arrays[i]^arrays[j];
                arrays[j] = arrays[i]^arrays[j];
                arrays[i] = arrays[i]^arrays[j];
            }
        }


        //当i==j的时候

        arrays[left] = arrays[i];
        arrays[i] = temp;

        //递归
        qucikSort(arrays,left,j-1);
        qucikSort(arrays,j+1,right);
    }
}
