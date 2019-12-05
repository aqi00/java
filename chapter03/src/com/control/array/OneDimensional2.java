package com.control.array;

// 演示一维数组的初始化赋值
public class OneDimensional2 {

	public static void main(String[] args) {
		// 以下是声明数组的第二种形式：“变量类型[] 数组名称”
		// 其中“变量类型[]”构成了数组类型，例如int[]被称作整型数组
		// 以下是分配数组空间的第二种形式：
		// 方括号内留空，然后紧跟花括号，花括号内部是以逗号分隔的一组数值
		int[] numbers = new int[] { 2, 3, 5, 7 };
		// 下面通过循环语句依次读出数组中的所有元素
		// “数组名称.length”表示获取该数组的长度（数组大小）
		for (int i = 0; i < numbers.length; i++) {
			// 打印下标为i的数组元素
			System.out.println("number = " + numbers[i]);
		}
	}
}
