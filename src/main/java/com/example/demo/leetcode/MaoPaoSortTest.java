package com.example.demo.leetcode;

public class MaoPaoSortTest {

    public static void main(String[] args) {
        int[] arr = {5,2,40,27,7,15};

        /*
        //1>从前往后比，每次筛选出最后一位
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                int temp = 0;
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
         */

        /**
         * 取一个位置上的数，分别和其他数做对比,确定该位置的准确数
         */
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int temp = 0;
                if (arr[i] < arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
