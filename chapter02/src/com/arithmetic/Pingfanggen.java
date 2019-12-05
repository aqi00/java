package com.arithmetic;

//演示利用牛顿迭代法计算平方根
public class Pingfanggen {

	public static void main(String[] arg) {
//		double number = 3; // 需要求n次方根的数字
//		int n = 2; // n次方根的n
//		double Xm = number; // 每次迭代后的数值
//		double slope; // 曲线的导数，切线的斜率
		
//		slope = n * Math.pow(Xm, n-1); // 曲线的导数，切线的斜率
//		Xm = Xm - (Math.pow(Xm, n)-number)/slope; // 求n次方根的牛顿迭代式子
		
//		slope = 2 * Xm; // 曲线的导数，切线的斜率
//		Xm = Xm - (Xm*Xm-number)/(2 * Xm); // 求2次方根的牛顿迭代式子
//		Xm = (Xm*Xm +number)/(2 * Xm);
//		Xm = (Xm + number/Xm) / 2; // 简化之后求平方根的迭代式子
		
		double number = 3; // 需要求平方根的数字
		double Xm = number; // 每次迭代后的数值
		
		Xm = (Xm + number/Xm) / 2; // 第一次迭代后的平方根
		System.out.println(number+"的平方根="+Xm);

		Xm = (Xm + number/Xm) / 2; // 第二次迭代后的平方根
		System.out.println(number+"的平方根="+Xm);

		Xm = (Xm + number/Xm) / 2; // 第三次迭代后的平方根
		System.out.println(number+"的平方根="+Xm);
	}
}
