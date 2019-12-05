package com.control;

//利用格里高利公式（又称格里高利级数）求圆周率
//π=4*(1/1-1/3+1/5-1/7+1/9......)
public class Geligaoli {

	public static void main(String[] arg) {
		double π = 0; // 圆周率
		double zhengfu = 1; // 正负标志
		for (int i=1; i<800000; i+=2) { // 使用循环语句计算有限次数的无穷级数
			π = π + zhengfu*4/i; // 通过无穷级数逼近圆周率
			zhengfu = -zhengfu; // 每次计算之后，翻转下次的正负号
		}
		System.out.println("圆周率="+π);
	}

}
