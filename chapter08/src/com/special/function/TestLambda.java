package com.special.function;

import java.util.Arrays;
import java.util.Comparator;

//演示利用Lambda表达式对数组排序
public class TestLambda {

	public static void main(String[] args) {
		sortIntArrayByLambda(); // 通过匿名内部类完成自定义的排序操作
		sortStrArrayByLambda(); // 通过匿名内部类对字符串数组按照字符串长度排序
	}

	// 通过Lambda表达式完成自定义的排序操作
	private static void sortIntArrayByLambda() {
		Integer[] intArray = { 89, 3, 67, 12, 45 };
		// Lambda表达式第一招。去掉了new、接口名称、方法名称
		Arrays.sort(intArray, (Integer o1, Integer o2) -> {
			return Integer.compare(o2, o1); // 按照降序排列
			});
		// Lambda表达式第二招。去掉了输入参数的变量类型
		Arrays.sort(intArray, (o1, o2) -> {
			return Integer.compare(o2, o1); // 按照降序排列
			});
		// Lambda表达式第三招。去掉了方法体的花括号，以及方法返回的return和分号
		Arrays.sort(intArray, (o1, o2) -> Integer.compare(o2, o1));

		String descDesc = "整型数组采取Lambda表达式的降序结果为：";
		for (Integer item : intArray) {
			descDesc = descDesc + item + ", ";
		}
		System.out.println(descDesc);
	}

	// 通过Lambda表达式对字符串数组按照字符串长度排序
	private static void sortStrArrayByLambda() {
		String[] strArray = { "说曹操曹操就到", "东道主", "风马牛不相及", "亡羊补牢", "无巧不成书",
				"冰冻三尺非一日之寒", "同窗", "青出于蓝而胜于蓝" };
		// 字符串数组的默认排序方式为根据首字母的拼写顺序，
		// 下面的Lambda表达式把排序方式改成了按照字符串长度排序
		Arrays.sort(strArray, (o1, o2) -> o1.length() < o2.length() ? -1 : 1);
		String desc = "字符串数组比较字符串长度的升序结果为：";
		for (String item : strArray) {
			desc = desc + item + ", ";
		}
		System.out.println(desc);
	}

}
