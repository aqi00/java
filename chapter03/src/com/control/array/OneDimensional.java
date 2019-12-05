package com.control.array;

// 演示一维数组的简单用法
public class OneDimensional {

	public static void main(String[] args) {
		// 以下是声明数组的第一种形式：“变量类型 数组名称[]”
		int numbers[];
		// 以下是分配数组空间的第一种形式：在方括号内填入数字，表示数组有多大
		numbers = new int[4];
		// 数组名称后面的“[数字]”，就是数组元素的下标，表示当前操作的是第几个数组元素
		numbers[0] = 2; // 给下标为0的数组元素赋值，下标0对应日常生活中的第一个
		numbers[1] = 3; // 给下标为1的数组元素赋值，下标1对应日常生活中的第二个
		numbers[2] = 5; // 给下标为2的数组元素赋值，下标2对应日常生活中的第三个
		numbers[3] = 7; // 给下标为3的数组元素赋值，下标3对应日常生活中的第四个
		// 下面通过循环语句依次读出数组中的所有元素，“数组名称.length”表示获取该数组的长度（数组大小）
		for (int i = 0; i < numbers.length; i++) {
			System.out.println("number = " + numbers[i]); // 打印下标为i的数组元素
		}
	}
}
