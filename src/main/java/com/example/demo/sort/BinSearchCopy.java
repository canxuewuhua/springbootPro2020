package com.example.demo.sort;

public class BinSearchCopy {

    public static void main(String[] args) {
        int[] arr = {3,5,7,10,12,15};
        System.out.println(BinSearch(arr, arr.length, 15));

        System.out.println(BinSearchRoll(arr,0,arr.length-1,15));
    }

    /**
     * 非递归
     * @return
     */
    public static int BinSearch(int[] arr, int len, int key){
        int low=0,hight = len -1;
        int mid;
        while (low <= hight){
            mid = (low+hight)/2;
            if (key == arr[mid])
                return mid;
            else if(key<arr[mid])
                hight = mid -1;
            else if(key>arr[mid])
                low = mid+1;
        }
        return -1;
    }

    public static int BinSearchRoll(int[] arr ,int low, int hight,int key){
        if (low<=hight){
            int mid = (low+hight)/2;
            if (key == arr[mid])
                return mid;
            else if(key<arr[mid])
                return BinSearchRoll(arr,low,mid-1,key);
            else if(key>arr[mid])
                return BinSearchRoll(arr,mid+1,hight,key);
            return mid;
        }else{
            return -1;
        }
    }
}
