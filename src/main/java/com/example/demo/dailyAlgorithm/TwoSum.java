package com.example.demo.dailyAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int m = target - nums[i];
            if(map.containsKey(m)){
                return new int[]{map.get(m),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("NO");
    }
}
