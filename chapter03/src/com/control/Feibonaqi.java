package com.control;

// 演示利用数组求解斐波那契数列
public class Feibonaqi {

	public static void main(String[] args) {
		int[] rabbitNumbers; // 声明一个兔子数量（多少对）的数组变量
		rabbitNumbers = new int[12]; // 一年有12个月，故兔子数组大小为12
		// 循环计算兔子数组在每个月的兔子对数
		for (int i = 0; i < rabbitNumbers.length; i++) {
			if (i < 2) { // 数列的头两个元素都是1
				rabbitNumbers[i] = 1;
			} else { // 从第三个元素开始，每个元素都等于它的前面两个元素之和
				rabbitNumbers[i] = rabbitNumbers[i - 2] + rabbitNumbers[i - 1];
			}
			int month = i + 1;
			// 打印当前的月份和兔子对数
			System.out.println("第" + month + "个月，兔子对数=" + rabbitNumbers[i]);
		}
	}
}
