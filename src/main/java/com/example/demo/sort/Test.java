package com.example.demo.sort;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] arr = {-1,-1};

        int[] list = topKFrequent(arr,1);

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                int count = map.get(nums[i]) + 1;
                map.put(nums[i], count);
            }else{
                map.put(nums[i], 1);
            }
        }

        // 找出所有的key出现次数的集合
        List<Integer> records = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            records.add(entry.getValue());
        }

        // 对records进行排序
        Collections.sort(records, Collections.reverseOrder());


        List<Integer> findRecords = new ArrayList<>();
        if (records.size() <= k){
            // 要做去重
            List<Integer> finlist = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (!finlist.contains(nums[i])){
                    finlist.add(nums[i]);
                }
            }
            int[] finArr = new int[finlist.size()];
            for (int i = 0; i < finlist.size(); i++) {
                finArr[i]=finlist.get(i);
            }

            return finArr;
        }else{
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                if (!list.contains(records.get(i))){
                    list.add(records.get(i));
                }
            }


            for (int j = 0; j < list.size(); j++) {
                // 然后根据次数也即value 找key
                // 2 2 1 1 1
                int val = records.get(j);
                // 循环map根据value找key
                for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                    if (entry.getValue() == val){
                        findRecords.add(entry.getKey());
                    }
                }
            }
        }



        Set<Integer> listSet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (findRecords.contains(nums[i])){
                listSet.add(nums[i]);
            }
        }

        List<Integer> ll = new ArrayList<>();


        Iterator<Integer> iterator = listSet.iterator();
        while (iterator.hasNext()){
            ll.add(iterator.next());
        }

        int[] finalArr = new int[ll.size()];
        for (int i = 0; i < ll.size(); i++) {
            finalArr[i] = ll.get(i);
        }



        return finalArr;
    }
}
