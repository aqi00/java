package com.control.array;

import java.util.Arrays;

//演示如何使用Arrays工具的copyOf方法
public class ArrayCopy {

	public static void main(String[] args) {
		int[] pricesOrigin = { 99, 99, 99, 99, 99 }; // 声明一个整型数组，数组大小为5，且5个元素全为99
//		for (int price : pricesOrigin) {
//			System.out.println("origin price = " + price);
//		}

		// 复制数组的第一个办法：利用等号直接赋值。新数组只是原数组的别名
		int[] pricesAssign = pricesOrigin;
		pricesOrigin[1] = 80;
		for (int price : pricesAssign) { // 循环遍历并打印整型数组的所有元素数值
			System.out.println("assign price = " + price);
		}

		// 复制数组的第二个办法：调用原数组的clone方法。新数组由原数组克隆而来
//		int[] pricesClone = pricesOrigin.clone();
//		pricesOrigin[1] = 80;
//		for (int price : pricesClone) { // 循环遍历并打印整型数组的所有元素数值
//			System.out.println("clone price = " + price);
//		}

		// 复制数组的第三个办法：调用Arrays工具的copyOf方法。允许复制部分元素
//		int[] pricesCopy = Arrays.copyOf(pricesOrigin, pricesOrigin.length);
//		for (int price : pricesCopy) { // 循环遍历并打印整型数组的所有元素数值
//			System.out.println("copy price = " + price);
//		}

		// 改变copyOf方法的第二个参数值，允许复制指定大小的数组元素
		int[] pricesPart = Arrays.copyOf(pricesOrigin, pricesOrigin.length - 1);
		for (int price : pricesPart) { // 循环遍历并打印整型数组的所有元素数值
			System.out.println("part price = " + price);
		}

		// 把copyOf方法的返回值赋给原数组，可以动态调整该数组的大小
		pricesOrigin = Arrays.copyOf(pricesOrigin, pricesOrigin.length + 1);
		for (int price : pricesOrigin) { // 循环遍历并打印整型数组的所有元素数值
			System.out.println("origin price = " + price);
		}
	}
}
