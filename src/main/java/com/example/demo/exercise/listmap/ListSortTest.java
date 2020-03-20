package com.example.demo.exercise.listmap;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yongqiang.zhu
 * @date 2020/2/28 16:17
 * 实现功能：将一个对象放入数组的第一位，重新调整顺序打印
 */
public class ListSortTest {

	public static void main(String[] args) {
		List<Partner> partners = new ArrayList<>();
		Partner partner1 = new Partner().setName("张三").setSort(4);
		Partner partner2 = new Partner().setName("李四").setSort(5);
		Partner partner3 = new Partner().setName("王五").setSort(6);
		partners.add(partner1);
		partners.add(partner2);
		partners.add(partner3);

		partners = filterPartner(partners);
		System.out.println(partners);




	}

	public static List<Partner> filterPartner(List<Partner> partners){
		return partners.stream().filter(usl -> usl.getName().equals("张三")).collect(Collectors.toList());
	}
}
@Data
@Accessors(chain=true)
class Partner{
	private String name;
	private int sort;
}
