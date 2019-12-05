package com.control.logic;

//演示几种运算符类型的优先级顺序
public class Priority {

	public static void main(String[] args) {
		// 比较加减乘除以及取余数运算的优先级顺序
		int fiveArithmetic = 7 + 5 - 4 * 6 / 3 % 9; // 等价于“7+5-(4*6/3%9)”
		System.out.println("fiveArithmetic=" + fiveArithmetic);
		// 比较负号与乘除运算的优先级顺序
		int negativeArithmetic = -8 / 4 + 2 * -3; // 等价于“(-8)/4+2*(-3)”
		System.out.println("negativeArithmetic=" + negativeArithmetic);
		// 以下比较算术运算符和关系运算符的优先级顺序
		boolean greaterResult = 1 + 2 > 3 + 4; // 等价于“(1+2)>(3+4)”
		System.out.println("greaterResult=" + greaterResult);
		boolean lessResult = 1 + 2 < 3 + 4; // 等价于“(1+2)<(3+4)”
		System.out.println("lessResult=" + lessResult);
		// 比较逻辑与运算以及关系运算符的优先级顺序
		boolean andResult = 1 > 2 & 3 < 4; // 等价于“(1>2)&(3<4)”
		System.out.println("andResult=" + andResult);
		// 比较逻辑或运算以及关系运算符的优先级顺序
		boolean orResult = 1 > 2 | 3 < 4; // 等价于“(1>2)|(3<4)”
		System.out.println("orResult=" + orResult);
		// 比较逻辑异或运算以及关系运算符的优先级顺序
		boolean xorResult = 1 > 2 ^ 3 < 4; // 等价于“(1>2)^(3<4)”
		System.out.println("xorResult=" + xorResult);
		// 比较逻辑非运算以及关系运算符的优先级顺序
		boolean zhen = true;
		boolean jia = false;
		boolean notResult = zhen == !jia; // 等价于“zhen==(!jia)”
		System.out.println("notResult=" + notResult);
	}
}
