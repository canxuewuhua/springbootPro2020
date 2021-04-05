package com.example.demo.dailyexercise;

public class CombinArray {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,0,0,0};
        int[] arr2 = {2,5,6};

        // num1.length == m+n
        // num2.length == n

        // 双指针 从后往前 定义三个指针
        // 用num1的最后一个元素i  与 num2的最后一个元素j  比较
        // 再用一个指针指向混合数组的末尾  k


    }

    /**
     * 双指针，从后往前
     * 首先定义三个指针，用num1的最后一个元素i与num2的最后一个元素j比较
     * 在用一个指针指向混合数组的末尾 k
     * 如果num1[i] > num2[j],把num1的数放到混合数组的末尾,同时 i 和 k 要自减1
     * 如果num1[i] < num2[j],把num2的数放到混合数组的末尾,同时 j 和 k 要自减1
     * 循环结束后还会有可能i或j大于0，如果j大于0，继续循环，将num2的数拷贝进num1
     * 如果i大于0，就不用管了，因为混合数组本身就是num1中。
     */

    public static void merge(int[] arr1, int m, int[] arr2, int n){
        int i = m - 1;
        int j = n - 1;
        int k = m+n-1;

        while (i>=0 && j>=0){
            arr1[k--] = arr1[i] > arr2[j] ? arr1[i--] : arr2[j--];
        }

        //System.arraycopy(src, srcPos, dest, destPos, length);
        //src：要复制的数组； srcPos从哪个位置开始复制；dest：复制到的目标数组， destPos：复制到的位置,length复制的长度
        System.arraycopy(arr2,0, arr1,0,j+1);
    }
}
