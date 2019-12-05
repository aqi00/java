package com.collect.generic;

import java.util.Arrays;
import java.util.List;

//演示如何使用自定义的泛型类
public class TestClass {

	public static void main(String[] arg) {
		// 清单List是系统自带的泛型类
		// 数组工具Arrays的asList方法可以把一系列元素直接赋值给清单对象
		List<Double> doubleList = Arrays.asList(1.1, 2D, 3.1415926, 11.11);
		System.out.println("doubleList.toString()=" + doubleList.toString());
		// 泛型类允许在声明变量时指定某几个内部参数的参数类型
		// 泛型实例的参数类型跟在类名称后面，以尖括号包裹
		SimpleList<Double> simpleList = new SimpleList<Double>(doubleList);
		simpleList.setDelimiter(" | ");
		System.out.println("simpleList.toString()=" + simpleList.toString());
		// 打印清单中最长的元素
		System.out.println("最长的元素=" + simpleList.getMaxLengthItem());
		// 打印清单中最短的元素
		System.out.println("最短的元素=" + simpleList.getMinLengthItem());
	}
}
