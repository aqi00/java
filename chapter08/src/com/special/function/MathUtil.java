package com.special.function;

//定义数学工具类
public class MathUtil {

	// 加法运算
	public double add(double x, double y) {
		return x + y;
	}

	// 减法运算
	public double minus(double x, double y) {
		return x - y;
	}

	// 乘法运算
	public double multiply(double x, double y) {
		return x * y;
	}

	// 除法运算
	public double divide(double x, double y) {
		return x / y;
	}

	// 取余数运算
	public double remainder(double x, double y) {
		return x % y;
	}

	// 取两数的较大值
	public double max(double x, double y) {
		return Math.max(x, y);
	}

	// 取两数的较小值
	public double min(double x, double y) {
		return Math.min(x, y);
	}

	// 幂运算，即乘方
	public double pow(double x, double y) {
		return Math.pow(x, y);
	}

	// 求方根运算，即开方
	public double sqrt(double x, double y) {
		double number = x; // 需要求n次方根的数字
		double root = x; // 每次迭代后的数值
		double n = y; // n次方根的n
		for (int i = 0; i < 5; i++) { // 下面利用牛顿迭代法求n次方根
			root = (root * (n - 1) + number / Math.pow(root, n - 1)) / n;
		}
		return root;
	}

}
