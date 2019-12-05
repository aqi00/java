package com.control.logic;

//演示按位逻辑与短路逻辑之间的区别
public class ShortCircuit {

	public static void main(String[] args) {
		// 3的二进制为00000011，7的二进制为00000111
//		int andNumber = 3&7;  // 对两个数字开展“按位与”运算
//		System.out.println("andNumber="+andNumber);
//		int orNumber = 3|7;  // 对两个数字开展“按位或”运算
//		System.out.println("orNumber="+orNumber);
//		int xorNumber = 3^7;  // 对两个数字开展“按位异或”运算
//		System.out.println("xorNumber="+xorNumber);

		// 3的二进制为00000011，7的二进制为00000111
		int andNumber = 1 + 2 & 3 + 4; // 等价于“(1+2)&(3+4)”
		System.out.println("andNumber=" + andNumber);
		int orNumber = 1 + 2 | 3 + 4; // 等价于“(1+2)|(3+4)”
		System.out.println("orNumber=" + orNumber);
		int xorNumber = 1 + 2 ^ 3 + 4; // 等价于“(1+2)^(3+4)”
		System.out.println("xorNumber=" + xorNumber);
		
		// 下面写法是错误的，因为“与”、“或”、“异或”的优先级低于关系运算符
		// boolean logicalResult = 1&2 > 3|4;
		// 下面写法是正确的，使用圆括号把需要先计算的语句括起来
		boolean logicalResult = (1 & 2) > (3 | 4);
		System.out.println("logicalResult=" + logicalResult);
		int i = 1, j = 1;
		// 对于按位逻辑运算，需要等待左右两边都计算完毕，然后进行按位逻辑判断
		boolean result1 = 3 > 4 & ++i < 5;
		System.out.println("result1=" + result1 + ", i=" + i);
		// 对于短路逻辑运算，一旦左边的计算能够确定结果，就立即返回判断结果，不再进行右边的计算
		boolean result2 = 3 > 4 && ++j < 5;
		System.out.println("result2=" + result2 + ", j=" + j);
	}
}
