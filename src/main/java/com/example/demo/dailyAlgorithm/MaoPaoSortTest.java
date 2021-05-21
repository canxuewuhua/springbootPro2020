package com.example.demo.dailyAlgorithm;

public class MaoPaoSortTest {

    public static void main(String[] args) {
        int[] arr = {15,3,8,54,5,10};
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length - i -1 ; j++) {
                int temp = 0;
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
