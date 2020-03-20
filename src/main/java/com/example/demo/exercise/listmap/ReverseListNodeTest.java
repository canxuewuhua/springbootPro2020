package com.example.demo.exercise.listmap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2020/3/15 14:08
 */
public class ReverseListNodeTest {
	public  static  List<Integer> tmp = new ArrayList<Integer>();

	public static void main(String[] args) {
		int max = (int)Math.pow(10, 1);
		int[] arr = new int[max - 1];
		for (int i=1;i<max-1;i++){
			arr[i] = i;
		}
		System.out.println(arr);
	}

	static List<Integer> recur(ListNode head) {
		if(head == null) return null;
		recur(head.next);
		tmp.add(head.val);
		return tmp;
	}
}
