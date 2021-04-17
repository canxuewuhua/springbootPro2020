package com.example.demo.dailyexercise;

public class MountainArray {

    /**
     * 从左往右遍历，求得「左山峰」
     * 从右往左遍历，求得「右山峰」
     * 判断「左山峰」是否等于「右山峰」
     * @param args
     */
    public static void main(String[] args) {

    }

    public boolean validMountainArray1(int[] A) {
        int len = A.length;
        int left = 0;
        int right = len - 1;

        // 从左往右遍历，找左山峰
        while (left < len - 1 && A[left] < A[left + 1]) {
            left++;
        }

        // 左山峰为起点或终点，都不合法
        if (left == 0 || left == len -1) {
            return false;
        }

        // 从右往左遍历，找右山峰
        while (right > 0 && A[right] < A[right - 1]) {
            right--;
        }

        return left == right;
    }

}
