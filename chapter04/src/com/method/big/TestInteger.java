package com.method.big;

import java.math.BigInteger;

//演示大整数BigInteger的用法
public class TestInteger {

	public static void main(String[] args) {
		BigInteger nine = BigInteger.valueOf(9); // 生成一个指定数值的大整数变量
		BigInteger four = BigInteger.valueOf(4); // 生成一个指定数值的大整数变量
		BigInteger sum = nine.add(four); // add方法用来替代加法运算符“+”
		System.out.println("sum="+sum);
		BigInteger sub = nine.subtract(four); // subtract方法用来替代减法运算符“-”
		System.out.println("sub="+sub);
		BigInteger mul = nine.multiply(four); // multiply方法用来替代乘法运算符“*”
		System.out.println("mul="+mul);
		BigInteger div = nine.divide(four); // divide方法用来替代除法运算符“/”
		System.out.println("div="+div);
		BigInteger remainder = nine.remainder(four); // remainder方法用来替代取余数运算符“%”
		System.out.println("remainder="+remainder);
		BigInteger neg = nine.negate(); // negate方法用来替代负号运算符“-”
		System.out.println("neg="+neg);
		BigInteger abs = nine.abs(); // abs方法用来替代数学库函数Math.abs
		System.out.println("abs="+abs);
		BigInteger pow = nine.pow(2); // pow方法用来替代数学库函数Math.pow
		System.out.println("pow="+pow);
	}

}
