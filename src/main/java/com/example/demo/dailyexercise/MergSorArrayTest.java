package com.example.demo.dailyexercise;

/**
 * 合并两个有序数组
 */
public class MergSorArrayTest {
    public static void main(String[] args) {

    }

    /**
     * arr1数组足够大
     */
    public static void mergeArr(int[] arr1, int m, int[] arr2, int n){
        // 倒叙比较
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;

        while (i>=0 && j>=0){
            if (arr1[i] < arr2[j]){
                arr1[k--] = arr2[j--];
            }else{
                arr1[k--] = arr1[i--];
            }
        }

        while (j>=0){
            arr1[k--] = arr2[j--];
        }
    }
}
