package com.arithmetic.numerical;

//演示数字的后缀，主要是特殊数字的表达
public class Suffix {

	public static void main(String[] args) {
		// long worldPopulation = 7444443881; // 这样写会报错，因为整数默认是int类型
		// 截至2018年元旦，世界人口大约有74亿
		long worldPopulation = 7444443881L; // 长整型数要在数值末尾加上l或者L
		System.out.println("worldPopulation=" + worldPopulation);
		// float huilv = 3.14; // 这样写会报错，因为小数默认是double类型
		// 3.14是中国古代数学家刘徽求得的圆周率数值，又称徽率
		float huilv = 3.14F; // 浮点数要在数值末尾加上f或者F
		System.out.println("huilv=" + huilv);
		// double yuanzhoulv = 3.1415926D; // 小数默认是double类型，此时末尾的D可加可不加
		// 中国的领土面积是960万平方千米
		int chinaArea = 960_0000; // 从Java7开始，数字中间允许添加下划线，从而可以更方便地区分位数
		System.out.println("chinaArea=" + chinaArea);
		// 太阳距离地球1.5亿千米
		double sunDistance = 1.5E8; // E8表示乘以10的8次方，E是exponent（指数）的首字母
		System.out.println("sunDistance=" + sunDistance);
	}
}
