package com.example.demo.sort;


import java.util.Collections;

/**
 * 二分查找/折半查找
 * 必须是有序
 *
 * 分为递归 非递归 两种算法
 */
public class BinSearchTest {

    public static void main(String[] args) {
        int[] arr = {1,2,5,7,9};
        System.out.println(BinSearch(arr, arr.length, 7));

        System.out.println(BinSearchRoll(arr,0,arr.length -1,7));
    }

    /**
     * 非递归算法找下标
     * @param arr
     * @param len
     * @param key
     * @return
     */
    public static int BinSearch(int[] arr, int len, int key){
        int low = 0,hight=len-1;
        int mid;
        while(low<=hight){
            mid = (low+hight)/2;
            if (key == arr[mid])
                return mid;
            else if (key <arr[mid])
                hight=mid-1;
            else if (key>arr[mid])
                low = mid+1;
        }
        return -1;
    }

    /**
     * 递归算法找下标
     * @param arr
     * @param low
     * @param high
     * @param key
     * @return
     */
    public static int BinSearchRoll(int[] arr, int low, int high, int key){
        if (low<=high){
            int mid = (low+high)/2;
            if (key == arr[mid])
                return mid;
            else if (key<arr[mid])
                return BinSearchRoll(arr, low, mid-1,key);
            else if(key>arr[mid])
                return BinSearchRoll(arr,mid+1,high,key);
            return mid;
        }else{
            return -1;
        }
    }
}
