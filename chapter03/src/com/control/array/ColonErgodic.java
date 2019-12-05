package com.control.array;

//演示如何利用冒号简化数组元素的遍历操作
public class ColonErgodic {

	public static void main(String[] args) {
		int[] numbers = { 2, 3, 5, 7 };
//		for (int i = 0; i < numbers.length; i++) {
//			int number = numbers[i]; // 获取下标为i的元素，并赋值给名为number的变量
//			System.out.println("number = " + number);
//		}
		// 在for循环中，可以利用“变量类型 变量名称 : 数组名称”的形式，直接把数组元素赋值给该变量
		for (int number : numbers) {
			System.out.println("number = " + number);
		}
	}
}
