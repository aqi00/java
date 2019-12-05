package com.arithmetic.operator;

//演示四则运算符的用法
public class Four {

	public static void main(String[] args) {
		int sum = 1 + 2; // 求两数相加之和
		System.out.println("sum=" + sum);
		int differ = 7 - 3; // 求两数相减之差
		System.out.println("differ=" + differ);
		int product = 5 * 6; // 求两数相乘之积
		System.out.println("product=" + product);
		int quotient = 81 / 9; // 求两数相除之商
		System.out.println("quotient=" + quotient);
		int remainder = 40 % 3; // 求两数相除之余数
		System.out.println("remainder=" + remainder);
		// 被除数和除数都是整数，则求得的商为去掉小数部分的整数
		int quotientInt = 25 / 4;
		System.out.println("quotientInt=" + quotientInt);
		// 被除数和除数只要有一个是浮点或双精度数，则求得的商保留小数部分
		double quotientDouble = 25.0 / 4; // 25/4.0的运算结果跟25.0/4是一样的
		System.out.println("quotientDouble=" + quotientDouble);
		// 因float和double类型为约数表示，故相除得到的商也是约数，不能保证小数部分是精确的
		double quotientDecimal = 8.1 / 3;
		System.out.println("quotientDecimal=" + quotientDecimal);
		// 对浮点数和双精度数求余数，也存在约数造成的问题，即余数的小数部分可能并不准确
		double remainderDecimal = 5.1 % 2;
		System.out.println("remainderDecimal=" + remainderDecimal);
	}
}
