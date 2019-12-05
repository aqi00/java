package com.collect.handle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.collect.generic.Apple;

//演示如何使用容器工具Collections
public class TestCollection {
	
	public static void main(String[] args) {
//		Collections.addAll(c, elements); // 添加若干元素到指定容器
//		Collections.fill(list, obj); // 给指定清单填满某元素
//		Collections.swap(list, i, j); // 交换清单中两个元素的位置
//		Collections.max(coll, comp); // 获取指定容器的最大元素
//		Collections.min(coll, comp); // 获取指定容器的最小元素
//		Collections.sort(list, c); // 给指定清单排序
		testMaxAndMin(); // 演示如何获取最大值和最小值
		testSort(); // 演示如何给容器排序
	}

	// 获取默认的苹果清单
	private static ArrayList<Apple> getAppleList() {
		ArrayList<Apple> appleList = new ArrayList<Apple>();
		appleList.add(new Apple("红苹果", "RED", 150d, 10d));
		appleList.add(new Apple("大苹果", "green", 250d, 10d));
		appleList.add(new Apple("红苹果", "red", 300d, 10d));
		appleList.add(new Apple("大苹果", "yellow", 200d, 10d));
		appleList.add(new Apple("红苹果", "green", 100d, 10d));
		appleList.add(new Apple("大苹果", "Red", 250d, 10d));
		return appleList;
	}

	// 演示如何获取最大值和最小值
	private static void testMaxAndMin() {
		List<Apple> appleList = getAppleList();
		// 匿名内部类方式获取容器的最大值
//		Apple heavestApple = Collections.max(appleList, new Comparator<Apple>() {
//			@Override
//			public int compare(Apple o1, Apple o2) {
//				return o1.getWeight().compareTo(o2.getWeight());
//			}
//		});
		// Lambda表达式获取容器的最大值。求最重的苹果
		Apple heavestApple = Collections.max(appleList, 
				(o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
		System.out.println("最重的苹果="+heavestApple.toString());
		// 匿名内部类方式获取容器的最小值
//		Apple lightestApple = Collections.min(appleList, new Comparator<Apple>() {
//			@Override
//			public int compare(Apple o1, Apple o2) {
//				return o1.getWeight().compareTo(o2.getWeight());
//			}
//		});
		// Lambda表达式获取容器的最小值。求最轻的苹果
		Apple lightestApple = Collections.min(appleList, 
				(o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
		System.out.println("最轻的苹果="+lightestApple.toString());
	}
	
	// 演示如何给清单排序
	private static void testSort() {
		List<Apple> appleList = getAppleList();
		// 匿名内部类方式给清单排序。按照苹果的重量升序排列
//		Collections.sort(appleList, new Comparator<Apple>() {
//			@Override
//			public int compare(Apple o1, Apple o2) {
//				return o1.getWeight().compareTo(o2.getWeight());
//			}
//		});
		// Lambda表达式给清单排序
		Collections.sort(appleList, 
				(o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
		System.out.println("排序后的苹果清单="+appleList.toString());
	}
	
}
