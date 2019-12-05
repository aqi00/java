package com.addition.annotation;

//定义一个算术类
public class Arithmetic {

	// 定义一个静态的计算方法，根据传入的计算器接口，对后面两个数字进行运算
	public static double calculate(Calculator calculator, double x, double y) {
		// 这里调用了计算器接口的运算方法
		return calculator.operate(x, y);
	}

}
