package com.javafx.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//算术工具类
public class Arithmetic {
	// 默认的除法运算精度
	private static final int DEF_SCALE = 20;

	// 加法运算
	public static String add(String str1, String str2) {
		BigDecimal b1 = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		return String.valueOf(b1.add(b2));
	}

	// 减法运算
	public static String sub(String str1, String str2) {
		BigDecimal b1 = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		return String.valueOf(b1.subtract(b2));
	}

	// 乘法运算
	public static String mul(String str1, String str2) {
		BigDecimal b1 = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		return String.valueOf(b1.multiply(b2));
	}

	// 除法运算。如果除不尽，则小数点后精确到默认位数
	public static String div(String str1, String str2) {
		BigDecimal dividend = new BigDecimal(str1);
		BigDecimal divisor = new BigDecimal(str2);
		BigDecimal result = null;
		try { // 开始除法运算
			result = dividend.divide(divisor);
		} catch (ArithmeticException e) { // 捕捉到除不尽异常
			result = dividend.divide(divisor, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
		}
		return String.valueOf(result);
	}

	// 求余数运算。如果除不尽，则小数点后精确到默认位数
	public static String rem(String str1, String str2) {
		BigDecimal dividend = new BigDecimal(str1);
		BigDecimal divisor = new BigDecimal(str2);
		BigDecimal[] results = null;
		try { // 开始求余数运算
			results = dividend.divideAndRemainder(divisor);
		} catch (ArithmeticException e) { // 捕捉到除不尽异常
			// 指定运算精度，保留默认位数，最后一位四舍五入取整
			MathContext mc = new MathContext(DEF_SCALE, RoundingMode.HALF_UP);
			results = dividend.divideAndRemainder(divisor, mc);
		}
		return String.valueOf(results[1]);
	}

	// 求倒数运算。如果除不尽，则小数点后精确到默认位数
	public static String rec(String str) {
		BigDecimal dividend = new BigDecimal("1");
		BigDecimal divisor = new BigDecimal(str);
		BigDecimal result = null;
		try { // 开始除法运算
			result = dividend.divide(divisor);
		} catch (ArithmeticException e) { // 捕捉到除不尽异常
			result = dividend.divide(divisor, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
		}
		return String.valueOf(result);
	}

	// 取反运算
	public static String neg(String str) {
		BigDecimal src = new BigDecimal(str);
		return String.valueOf(src.negate());
	}

	// 开平方运算
	public static BigDecimal sqrt(String str) {
		BigDecimal number = new BigDecimal(str);
		BigDecimal two = BigDecimal.valueOf(2);
		// 指定运算精度，保留默认位数，最后一位四舍五入取整
		MathContext mc = new MathContext(DEF_SCALE, RoundingMode.HALF_UP);
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