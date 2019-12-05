package com.control.array;

import java.util.Arrays;

//演示如何使用Arrays工具的fill方法
public class ArrayFill {

	public static void main(String[] args) {
		// 构造一个包含十个99的数组变量
		// int[] prices = {99, 99, 99, 99, 99, 99, 99, 99, 99, 99};
		int[] prices = new int[10]; // 声明一个整型数组，数组大小为10
		Arrays.fill(prices, 99); // 给整型数组的每个元素全部填写99
		for (int price : prices) { // 循环遍历并打印整型数组的所有元素数值
			System.out.println("price = "+price);
		}
	}
}
