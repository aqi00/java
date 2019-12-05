package com.method;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//演示如何利用牛顿迭代法求大数开方
public class BigNewton {

	public static void main(String[] arg) {
		//testSqrtByDouble(); // 测试双精度数的开方运算
		//testSqrtByDouble2(); // 测试双精度数的开方运算
		testSqrtByBigDecimal(); // 测试大小数的开方运算
	}

	// 测试双精度数的开方运算
	private static void testSqrtByDouble() {
		double number = 2; // 需要求方根的数字
		double root = sqrtByDouble(number); // 计算双精度数的平方根（使用while循环）
		System.out.println("双精度数开方运算，原始数字=" + number + ", 它的平方根=" + root);
	}

	// 测试双精度数的开方运算
	private static void testSqrtByDouble2() {
		double number = 2; // 需要求方根的数字
		double root = sqrtByDoubleWithDo(number); // 计算双精度数的平方根（使用do/while循环）
		System.out.println("双精度数开方运算，原始数字=" + number + ", 它的平方根=" + root);
	}

	// 测试大小数的开方运算
	private static void testSqrtByBigDecimal() {
		BigDecimal number = BigDecimal.valueOf(2); // 需要求方根的数字
		// 求得的平方根保留小数点后面一百位
		BigDecimal root = sqrtByBigDecimal(number, 100); // 计算大小数的平方根
		System.out.println("大小数开方运算，原始数字=" + number + ", 它的平方根=" + root);
	}

	// 计算双精度数的平方根（使用while循环）
	private static double sqrtByDouble(double number) {
		double Xm = number; // 每次迭代后的数值
		while (true) {
			double lastXm = Xm; // 上次迭代的平方根
			Xm = (Xm + number/Xm) / 2; // 本次迭代后的平方根
			// 迭代前后的两个平方根相等，表示已经达到变量精度，再算下去也没意义了
			if (Xm >= lastXm) {
				break;
			}
		}
		return Xm;
	}

	// 计算双精度数的平方根（使用do/while循环）
	private static double sqrtByDoubleWithDo(double number) {
		double Xm = number; // 每次迭代后的数值
		double lastXm = Xm; // 上次迭代的平方根
		do {
			lastXm = Xm; // 保存上次迭代的平方根
			Xm = (Xm + number/Xm) / 2; // 本次迭代后的平方根
		} while (Xm < lastXm); // 只有迭代前后的两个平方根不等的时候，才要继续执行循环
		return Xm;
	}

	// 计算大小数的平方根
	private static BigDecimal sqrtByBigDecimal(BigDecimal number, int precision) {
		BigDecimal two = BigDecimal.valueOf(2);
		// 指定运算精度，保留若干位数，最后一位四舍五入取整
		MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
		if (number.compareTo(BigDecimal.ZERO) <= 0) { // 0和负数不允许开方
			return BigDecimal.valueOf(0);
		} else {
			BigDecimal X = number; // 上次迭代的平方根
			// 下面利用牛顿迭代法计算某个大小数的平方根
			while (true) {
				// 简化之后求平方根的迭代式子：Xm = (Xm + number/Xm) / 2
				BigDecimal Xm = (X.add(number.divide(X, mc))).divide(two, mc);
				// 如果运算前后的结果相等，就跳出循环。因为已经达到运算精度，再算下去也无用
				if (X.equals(Xm)) {
					break;
				}
				X = Xm; // 保留本次迭代后的方根
			}
			return X;
		}
	}

}
