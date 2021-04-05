package com.example.demo.dailyAlgorithm;

public class QuickSortTest {

    public static void main(String[] args) {
        int[] arr = {15,3,8,54,5,10};
        quickSort(arr, 0, arr.length -1);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void quickSort(int[] arr, int left, int right){
        if (left < right){
            int base = devide(arr, left, right);
            quickSort(arr, left, base-1);
            quickSort(arr,base+1, right);
        }
    }

    public static int devide(int[] arr, int left, int right){
        int base = arr[left];
        while (left < right){
            while (left<right && arr[right]>=base){
                right--;
            }
            arr[left] = arr[right];
            while (left<right && arr[left] <=base){
                left++;
            }
            arr[right]= arr[left];
        }
        arr[left] = base;
        return left;
    }
}
