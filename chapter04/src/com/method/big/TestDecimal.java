package com.method.big;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//演示大小数BigDecimal的用法
public class TestDecimal {

	public static void main(String[] args) {
		BigDecimal sevenAndHalf = BigDecimal.valueOf(7.5); // 生成一个指定数值的大小数变量
		BigDecimal three = BigDecimal.valueOf(3); // 生成一个指定数值的大小数变量
		BigDecimal sum = sevenAndHalf.add(three); // add方法用来替代加法运算符“+”
		System.out.println("sum="+sum);
		BigDecimal sub = sevenAndHalf.subtract(three); // subtract方法用来替代减法运算符“-”
		System.out.println("sub="+sub);
		BigDecimal mul = sevenAndHalf.multiply(three); // multiply方法用来替代乘法运算符“*”
		System.out.println("mul="+mul);
		BigDecimal div = sevenAndHalf.divide(three); // divide方法用来替代除法运算符“/”
		System.out.println("div="+div);
		BigDecimal remainder = sevenAndHalf.remainder(three); // remainder方法用来替代取余数运算符“%”
		System.out.println("remainder="+remainder);
		BigDecimal neg = sevenAndHalf.negate(); // negate方法用来替代负号运算符“-”
		System.out.println("neg="+neg);
		BigDecimal abs = sevenAndHalf.abs(); // abs方法用来替代数学库函数Math.abs
		System.out.println("abs="+abs);
		BigDecimal pow = sevenAndHalf.pow(2); // pow方法用来替代数学库函数Math.pow
		System.out.println("pow="+pow);
		// 只有一个输入参数的divide方法，要求被除数能够被除数除得尽。
		// 倘若除不尽，也就是商为无限循环小数，则程序会异常退出，
		// 报错“Non-terminating decimal expansion; no exact representable decimal result.”
//		BigDecimal seven = BigDecimal.valueOf(7);
//		BigDecimal divTest = sevenAndHalf.divide(seven);
//		System.out.println("divTest="+divTest);
		
		testDivide();
	}

	// testDivide方法用于测试大数字的除法精度
	private static void testDivide() {
		// ROUND_CEILING：往数值较小的方向取整，类似于Math库的ceiling函数。
		// ROUND_FLOOR：往数值较大的方向取整，类似于Math库的floor函数。
		// ROUND_HALF_UP：四舍五入取整，若多余的数字等于.5，则前一位进1，类似于Math库的round函数。
		// ROUND_HALF_DOWN：类似四舍五入取整，区别在于：若多余的数字等于.5，则直接舍弃。
		// ROUND_HALF_EVEN：如果保留位数的末尾为奇数，则按照ROUND_HALF_UP方式取整。如果保留位数的末尾为偶数，则按照ROUND_HALF_DOWN方式取整。
		BigDecimal one = BigDecimal.valueOf(100);
		BigDecimal three = BigDecimal.valueOf(3);
		// 大小数的除法运算，小数点后面保留64位，其中最后一位做四舍五入
		BigDecimal div = one.divide(three, 64, BigDecimal.ROUND_HALF_UP);
		System.out.println("div="+div);
		// 利用工具MathContext，可以把divide方法的输入参数减少为两个
		MathContext mc = new MathContext(64, RoundingMode.HALF_UP);
		BigDecimal divByMC = one.divide(three, mc); // 根据指定的精度规则执行除法运算
		System.out.println("divByMC="+divByMC);
	}

}
