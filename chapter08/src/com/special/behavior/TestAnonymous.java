package com.special.behavior;

import java.util.Arrays;
import java.util.Comparator;

//演示匿名内部类的应用场合及其实现方式
public class TestAnonymous {

	public static void main(String[] args) {
		sortIntArrayAsc(); // 演示数组工具的默认升序排列
		sortIntArrayDesc(); // 利用新定义的降序比较器实现对数组的降序排列
		sortIntArrayDescAnonymous(); // 通过匿名内部类完成自定义的排序操作
		sortStrArrayByLength(); // 通过匿名内部类对字符串数组按照字符串长度排序
	}

	// 演示数组工具的默认升序排列
	private static void sortIntArrayAsc() {
		Integer[] intArray = { 89, 3, 67, 12, 45 };
		Arrays.sort(intArray); // Arrays的sort方法默认为升序
		String ascDesc = "整型数组的升序结果为：";
		for (Integer item : intArray) { // 拼接排序后的数组元素
			ascDesc = ascDesc + item + ", ";
		}
		System.out.println(ascDesc);
	}

	// 利用新定义的降序比较器实现对数组的降序排列
	private static void sortIntArrayDesc() {
		Integer[] intArray = { 89, 3, 67, 12, 45 };
		// sort方法支持按照指定的排序器进行排列判断，新定义的SortDescend类实现了降序排列
		Arrays.sort(intArray, new SortDescend());
		String descDesc = "整型数组的降序结果为：";
		for (Integer item : intArray) { // 拼接排序后的数组元素
			descDesc = descDesc + item + ", ";
		}
		System.out.println(descDesc);
	}

	// 通过匿名内部类完成自定义的排序操作
	private static void sortIntArrayDescAnonymous() {
		Integer[] intArray = { 89, 3, 67, 12, 45 };
		// 匿名内部类无需专门定义形态完整的类，只需指明新创建的实例从哪个接口扩展而来
		Arrays.sort(intArray, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1); // 倒过来的参数顺序变成了降序
			}
		});
		String descDesc = "整型数组采取匿名内部类的降序结果为：";
		for (Integer item : intArray) { // 拼接排序后的数组元素
			descDesc = descDesc + item + ", ";
		}
		System.out.println(descDesc);
	}

	// 通过匿名内部类对字符串数组按照字符串长度排序
	private static void sortStrArrayByLength() {
		String[] strArray = { "说曹操曹操就到", "东道主", "风马牛不相及", "亡羊补牢", "无巧不成书",
				"冰冻三尺非一日之寒", "同窗", "青出于蓝而胜于蓝" };
		// 字符串数组的默认排序方式为根据首字母的拼写顺序，
		// 下面的匿名内部类把排序方式改成了按照字符串长度排序
		Arrays.sort(strArray, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() < o2.length() ? -1 : 1; // 比较前后两个数组元素的字符串长度大小
			}
		});
		String desc = "字符串数组比较字符串长度的升序结果为：";
		for (String item : strArray) { // 拼接排序后的数组元素
			desc = desc + item + "，";
		}
		System.out.println(desc);
	}

}
