package com.arithmetic.math;

import java.util.Random;

//演示如何生成随机数
public class Rand {

	public static void main(String[] args) {
		double decimal = Math.random(); // 生成小于一的随机小数（包括0和正小数）
		System.out.println("decimal=" + decimal);
		int integer = new Random().nextInt(); // 生成随机整数（包括负数）
		System.out.println("integer=" + integer);
		long long_integer = new Random().nextLong(); // 生成随机长整数（包括负数）
		System.out.println("long_integer=" + long_integer);
		float float_decimal = new Random().nextFloat(); // 生成随机的浮点小数（不包括负数）
		System.out.println("float_decimal=" + float_decimal);
		double double_decimal = new Random().nextDouble(); // 生成随机的双精度小数（不包括负数）
		System.out.println("double_decimal=" + double_decimal);
		int hundred = new Random().nextInt(100); // 生成一百以内的随机整数（0≤随机整数＜100，即包括0但不包括100）
		System.out.println("hundred=" + hundred);
	}

}
