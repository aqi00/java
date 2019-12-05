package com.arithmetic;

//演示利用牛顿迭代法计算n次方根
public class Niudundiedai {

	public static void main(String[] arg) {
		double number = 7; // 需要求n次方根的数字
		int n = 3; // n次方根的n
		double Xm = number; // 每次迭代后的数值
		double slope; // 曲线的导数，切线的斜率，物理学的瞬时速度
		
		slope = n * Math.pow(Xm, n-1); // f(Xm)的导数，也就是经过该点的切线斜率
		Xm = Xm - (Math.pow(Xm, n)-number)/slope; // 第一次迭代后的n次方根
		System.out.println(number+"的"+n+"次方根="+Xm);

		slope = n * Math.pow(Xm, n-1); // f(Xm)的导数，也就是经过该点的切线斜率
		Xm = Xm - (Math.pow(Xm, n)-number)/slope; // 第二次迭代后的n次方根
		System.out.println(number+"的"+n+"次方根="+Xm);

		slope = n * Math.pow(Xm, n-1); // f(Xm)的导数，也就是经过该点的切线斜率
		Xm = Xm - (Math.pow(Xm, n)-number)/slope; // 第三次迭代后的n次方根
		System.out.println(number+"的"+n+"次方根="+Xm);

		slope = n * Math.pow(Xm, n-1); // f(Xm)的导数，也就是经过该点的切线斜率
		Xm = Xm - (Math.pow(Xm, n)-number)/slope; // 第四次迭代后的n次方根
		System.out.println(number+"的"+n+"次方根="+Xm);

		slope = n * Math.pow(Xm, n-1); // f(Xm)的导数，也就是经过该点的切线斜率
		Xm = Xm - (Math.pow(Xm, n)-number)/slope; // 第五次迭代后的n次方根
		System.out.println(number+"的"+n+"次方根="+Xm);
	}
}
