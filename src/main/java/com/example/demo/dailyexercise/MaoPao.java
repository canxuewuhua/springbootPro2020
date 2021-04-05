package com.example.demo.dailyexercise;

public class MaoPao {

    public static void main(String[] args) {

        int[] arr = {2,50,24,15,8,9,18};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i- 1; j++) {
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
    }
}
