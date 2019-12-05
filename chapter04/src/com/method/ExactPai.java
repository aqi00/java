package com.method;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

//演示如何利用大数字求更精确的圆周率
public class ExactPai {

	public static void main(String[] arg) {
		calculateByDouble(); // 利用双精度数计算圆周率
		calculateByBigDecimal(); // 利用大小数计算圆周率
	}

	// 利用双精度数计算圆周率
	private static void calculateByDouble() {
		int n = 60; // double类型最多只能割60次
		long edgeNumber = (long) (Math.pow(2, 60) * 6);
		System.out.println("割圆次数=" + n + ", 内接正N边形的边数=" + edgeNumber);
		// 使用double类型，割圆术只能内接到正6917529027641081856边形（n=60），
		// 再往上割圆的话，Java程序就会神经错乱。
		double π_rough = getPaiRough(n);
		System.out.println("粗略的圆周率数值=" + π_rough);
	}

	// 利用大小数计算圆周率
	private static void calculateByBigDecimal() {
		int n = 165; // 大数字割到第165次，求得的圆周率可精确到小数点后100位
		BigInteger edgeNumber = BigInteger.valueOf(2).pow(n).multiply(BigInteger.valueOf(6));
		System.out.println("割圆次数=" + n + ", 内接正N边形的边数=" + edgeNumber);
		// 割圆术内接到正280608314367533360295107487881526339773939048251392边形（n=165），
		// 计算出来的圆周率才精确到小数点后一百位。
		BigDecimal π_exact = getPaiExact(n);
		System.out.println("精确的圆周率数值=" + π_exact);
	}

	// 计算粗略的圆周率
	private static double getPaiRough(int n) {
		int r = 1; // 圆的半径
		int d = 2 * r; // 圆的直径
		double edgeLength = r; // 正n边形的边长
		long edgeNumber = 6L; // 正n边形的边数
		double π = edgeLength * edgeNumber / d; // 正六边形对应的圆周率
		for (int i = 0; i < n; i++) { // 利用for循环实现迭代功能
			edgeNumber = edgeNumber * 2; // 正n边形的边数乘二
			double gou = edgeLength / 2.0; // 计算勾
			double gu = r - Math.sqrt(Math.pow(r, 2) - Math.pow(gou, 2)); // 计算股
			// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
			edgeLength = Math.sqrt(Math.pow(gou, 2) + Math.pow(gu, 2));
			// 正n边形的周长除以直径，即可得到近似的圆周率数值
			π = edgeLength * edgeNumber / d;
		}
		return π;
	}

	// 计算精确的圆周率
	private static BigDecimal getPaiExact(int n) {
		BigDecimal two = BigDecimal.valueOf(2.0);
		BigDecimal r = BigDecimal.valueOf(1); // 圆的半径
		BigDecimal d = r.multiply(two); // 圆的直径
		BigDecimal edgeLength = r; // 正n边形的边长
		BigDecimal edgeNumber = BigDecimal.valueOf(6); // 正n边形的边数
		BigDecimal π = edgeLength.multiply(edgeNumber).divide(d); // 正六边形对应的圆周率
		int precision = 110; // 默认保留小数点后面一百一十位
		// 设定小数的精度，保留小数点若干位，最后一位四舍五入
		MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
		for (int i = 0; i < n; i++) { // 利用for循环实现迭代功能
			edgeNumber = edgeNumber.multiply(two); // 正n边形的边数乘二
			BigDecimal gou = edgeLength.divide(two, mc); // 计算勾
			BigDecimal gu = r.subtract(sqrt(r.pow(2).subtract(gou.pow(2)), precision)); // 计算股
			// 通过勾股定理求斜边长（即勾三股四弦五），斜边长也是新正n边形的边长
			edgeLength = sqrt(gu.pow(2).add(gou.pow(2)), precision);
			// 正n边形的周长除以直径，即可得到近似的圆周率数值
			π = edgeLength.multiply(edgeNumber).divide(d, mc);
		}
		return π;
	}

	// 计算大小数的平方根
	private static BigDecimal sqrt(BigDecimal number, int precision) {
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
