package com.arithmetic.math;

//演示数学函数库中的取整函数用法
public class Trunc {

	public static void main(String[] args) {
		double decimalPositive = 9.9;
		// 注意下面的9.4999999999999999，取整之后会变成10。
		// 这是因为9.4999999999999999超出了双精度数的精度范围，Java会先将其转为近似9.5的小数，然后再进行取整操作
		// double decimalPositive = 9.4999999999999999;
		long roundPositive = Math.round(decimalPositive); // 四舍五入
		System.out.println("roundPositive=" + roundPositive);
		double floorPositive = Math.floor(decimalPositive); // 往下取整，也就是往数值小的方向取整
		System.out.println("floorPositive=" + floorPositive);
		double ceilPositive = Math.ceil(decimalPositive); // 往上取整，也就是往数值大的方向取整
		System.out.println("ceilPositive=" + ceilPositive);

		double decimalNegative = -9.9;
		long roundNegative = Math.round(decimalNegative); // 四舍五入
		System.out.println("roundNegative=" + roundNegative);
		double floorNegative = Math.floor(decimalNegative); // 往下取整，也就是往数值小的方向取整
		System.out.println("floorNegative=" + floorNegative);
		double ceilNegative = Math.ceil(decimalNegative); // 往上取整，也就是往数值大的方向取整
		System.out.println("ceilNegative=" + ceilNegative);

		double absoluteValue = Math.abs(decimalNegative); // 取绝对值
		System.out.println("absoluteValue=" + absoluteValue);
	}

}
