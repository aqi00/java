package com.method;

import java.math.BigInteger;

//演示如何通过方法递归实现阶乘函数
public class RecursionFactorial {

	public static void main(String[] arg) {
		calculateFactorialByLong(); // 利用长整数计算阶乘函数
		calculateFactorialByBigInteger(); // 利用大整数计算阶乘函数
	}

	// 利用长整数计算阶乘函数
	private static void calculateFactorialByLong() {
		int n = 20;
		// 注意：使用long类型，阶乘方法只能算到“20!”，再往上面算只能癫狂了
		long resultLong = factorialSimplify(n);
		System.out.println(n+"!的长整数阶乘结果="+resultLong);
	}

	// 利用大整数计算阶乘函数
	private static void calculateFactorialByBigInteger() {
		int n = 100;
		// 使用大数字类型，阶乘方法可以一直算下去，算到“1000!”都没问题
		BigInteger resultBig = factorialBig(n);
		System.out.println(n+"!的大整数阶乘结果="+resultBig);
	}

	// 利用循环语句实现阶乘函数n!
	private static long factorialRepeat(int n) {
		long result = n;
		for (int i = n - 1; i > 1; i--) {
			result = result * i; // 只要i大于1，就乘上它
		}
		return result;
	}

	// 利用方法递归实现阶乘函数n!
	private static long factorialRecursion(int n) {
		if (n <= 1) { // n小于等于1，结束递归
			return n;
		} else { // n是个大于1的整数，则重复递归调用
			return n * factorialRecursion(n - 1);
		}
	}

	// 利用三元运算符“?:”简化阶乘函数n!
	private static long factorialSimplify(int n) {
		return (n <= 1) ? n : n * factorialSimplify(n - 1);
	}

	// 利用大数字实现精确计算的阶乘方法
	private static BigInteger factorialBig(int n) {
		BigInteger bigN = BigInteger.valueOf(n); // 把整型的n转换为大整数类型
		return (n <= 1) ? bigN : bigN.multiply(factorialBig(n - 1));
	}

}
