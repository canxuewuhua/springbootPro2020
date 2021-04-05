package com.example.demo.dailyexercise;

public class BinSearchTest {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println(BinSearch(arr, arr.length, 3));

        System.out.println(binsearchCopy(arr, 0, arr.length-1,3));
    }


        // 二分查找 前提有序 ，找数组索引下标
    public static int BinSearch(int[] arr,int len,int key){
        int low=0,hight=len -1;
        int mid;
        while (low<=hight){
            mid = (low+hight)/2;
            if (key == arr[mid])
                return mid;
            else if(key>arr[mid])
                low = mid+1;
            else if(key<arr[mid])
                hight = mid -1;
        }
        return -1;
    }

    public static int binsearchCopy(int[] arr, int low, int high, int key){
        if (low<=high){
            int mid = (low+high)/2;
            if (key == arr[mid])
                return mid;
            else if (key>arr[mid])
                return binsearchCopy(arr, mid+1, high, key);
            else if (key<arr[mid])
                return binsearchCopy(arr, low, mid-1,key);
            return mid;
        }else{
            return -1;
        }
    }
}
