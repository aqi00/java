package com.control.array;

// 演示二维数组的简单用法
public class TwoDimensional {

	public static void main(String[] args) {
		// 以下是声明二维数组的第一种形式：“变量类型 数组名称[][]”
		double triangle[][];
		// 以下是分配二维数组空间的第一种形式：在两对方括号内分别填入数字，表示数组有几行几列
		triangle = new double[3][2];
		// 数组名称后面的“[数字][数字]”为数组元素的行列下标，表示当前操作第几行第几列的元素
		triangle[0][0] = -2.0;
		triangle[0][1] = 0.0;
		triangle[1][0] = 0.0;
		triangle[1][1] = -1.0;
		triangle[2][0] = 2.0;
		triangle[2][1] = 1.0;
		// 通过循环语句依次读出数组中的所有元素。 “二维数组名称.length”表示获取该数组的行数
		for (int i = 0; i < triangle.length; i++) {
			// “triangle[i].length”表示获取该数组第i行的列数
			for (int j = 0; j < triangle[i].length; j++) {
				// 打印第i行第j列的数组元素
				System.out.println("triangle[" + i + "][" + j + "]=" + triangle[i][j]);
			}
		}
	}
}
