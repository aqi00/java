package com.control.array;

// 演示如何通过循环语句遍历并修改每个元素的数值
public class OneDimensional3 {

	public static void main(String[] args) {
		// 以下是分配数组空间的第三种形式：赋值等号右边直接跟着花括号
		int[] numbers = { 2, 3, 5, 7 };
		// 下面通过循环语句给数组的每个元素都加一
		for (int i = 0; i < numbers.length; i++) {
			// 给下标为i的数组元素加一
			numbers[i]++;
			// 打印下标为i的数组元素
			System.out.println("number = " + numbers[i]);
		}
	}
}
