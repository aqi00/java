package com.collect.algorithm;

import java.util.Random;

//演示如何运用常见的几种排序算法，包括冒泡排序、快速排序、堆排序
public class TestSort {

	public static void main(String[] args) {
		Integer item = 0; // 随机数变量
		Integer[] numberArray = new Integer[10]; // 随机数构成的数组
		// 以下生成一个包含随机整数的数组
		loop: for (int i = 0; i < numberArray.length; i++) {
			item = new Random().nextInt(100); // 生成一个小于100的随机整数
			for (int j = 0; j < i; j++) { // 遍历数组进行检查，避免塞入重复数字
				// 数组中已存在该整数，则重做本次循环，以便重新生成随机数
				if (numberArray[j] == item) {
					i--;
					continue loop; // 直接继续上一级循环
				}
			}
			numberArray[i] = item; // 往数组填入新生成的随机数
		}
		for (int number : numberArray) {
			System.out.println("开始排序之前，Number = " + number);
		}

		testBubble(numberArray.clone()); // 测试冒泡排序算法
		testQuick(numberArray.clone()); // 测试快速排序算法
		testHeap(numberArray.clone()); // 测试堆排序算法
		System.out.println("结束排序");
	}

	// 测试冒泡排序算法
	private static void testBubble(Integer[] numberArray) {
		// 调用工具类的排序方法时，传入冒泡排序的策略
		ArraySort.sort(numberArray, new SortBubble());
		for (int number : numberArray) {
			System.out.println("冒泡排序之后，Number = " + number);
		}
	}

	// 测试快速排序算法
	private static void testQuick(Integer[] numberArray) {
		// 调用工具类的排序方法时，传入快速排序的策略
		ArraySort.sort(numberArray, new SortQuick());
		for (int number : numberArray) {
			System.out.println("快速排序之后，Number = " + number);
		}
	}

	// 测试堆排序算法
	private static void testHeap(Integer[] numberArray) {
		// 调用工具类的排序方法时，传入堆排序的策略
		ArraySort.sort(numberArray, new SortHeap());
		for (int number : numberArray) {
			System.out.println("堆排序之后，Number = " + number);
		}
	}

}
