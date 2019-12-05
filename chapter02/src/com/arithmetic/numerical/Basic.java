package com.arithmetic.numerical;

//演示基本变量类型的用法
public class Basic {

	public static void main(String[] args) {
		int zhumulanma; // 先声明变量
		// 8844是2005年中国测量得到的珠穆朗玛峰岩面高度，8848是冰雪高度
		zhumulanma = 8844; // 再对变量赋值
		System.out.println("珠穆朗玛峰的高度=" + zhumulanma);
		double yuanzhoulv = 3.1415926; // 在声明变量之时就初始化赋值
		System.out.println("圆周率=" + yuanzhoulv);
	}
}
