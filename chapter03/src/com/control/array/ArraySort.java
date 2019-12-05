package com.control.array;

import java.util.Arrays;
import java.util.Random;

// 演示如何使用Arrays工具的sort方法
public class ArraySort {

	public static void main(String[] args) {
		int[] prices = { 99, 80, 18, 68, 8 };
		// 对整型数组prices里的元素排序，sort方法得到的结果是升序排列
		Arrays.sort(prices);
		for (int price : prices) { // 循环遍历并打印整型数组的所有元素数值
			System.out.println("price = " + price);
		}

		int[] numbers = new int[10]; // 创建一个大小为10的整型数组
		loop: for (int i = 0; i < numbers.length; i++) {
			int item = new Random().nextInt(100); // 生成一个小于100的随机整数
			// 下面的循环用来检查数组中是否已经存在该随机数
			for (int j = 0; j < i; j++) {
				if (numbers[j] == item) { // 已经存在该随机数，则继续第一层循环，重新生成随机数
					i--; // 本次循环做了无用功，取消当前的计数
					continue loop; // 继续以loop标记的外层循环
				}
			}
			numbers[i] = item; // 原数组不存在该随机数，则把随机数加入到数组中
		}
		// 对整型数组numbers里的元素排序，sort方法得到的结果是升序排列
		Arrays.sort(numbers);
		for (int number : numbers) { // 循环遍历并打印整型数组的所有随机数
			System.out.println("number = " + number);
		}
	}
}
