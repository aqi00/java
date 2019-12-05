package com.arithmetic.math;

//演示数学函数库中的科学函数用法
public class Science {

	public static void main(String[] args) {
		double nine = 9;
		double sqrt = Math.sqrt(nine); // 开平方。对应数学符号√￣
		System.out.println("sqrt=" + sqrt);
		// 求n次方。pow的第一个参数为幂运算的底数，第二个参数为幂运算的指数
		double pow = Math.pow(nine, 2);
		System.out.println("pow=" + pow);
		
		double five = 5;
		double exp = Math.exp(five); // 求自然常数e的n次方
		System.out.println("exp=" + exp);
		double log = Math.log(exp); // 求自然对数，为exp方法的逆运算。对应数学函数lnN
		System.out.println("log=" + log);
		double log10 = Math.log10(100); // 求底数为10的对数。对应数学函数logN
		System.out.println("log10=" + log10);
	}

}
