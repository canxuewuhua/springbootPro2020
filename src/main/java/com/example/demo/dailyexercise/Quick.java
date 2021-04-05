package com.example.demo.dailyexercise;

public class Quick {

    public static void main(String[] args) {
        int[] arr = {2,50,24,15,8,9,18};
        quick(arr, 0, arr.length-1);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void quick(int[] arr, int left, int right){
        if (left< right){
            int base = devide(arr,left, right);
            quick(arr,left, base-1);
            quick(arr,base+1, right);
        }
    }

    public static int devide(int[] arr, int left, int right){
        int base = arr[left];
        while (left < right){
            while (left < right && arr[right] >=base){
                right--;
            }
            arr[left] = arr[right];

            while (left<right && arr[left] <= base){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        return left;
    }
}
