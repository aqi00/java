package com.control.array;

//演示二维数组的初始化赋值
public class TwoDimensional2 {

	public static void main(String[] args) {
		// 以下是声明二维数组的第二种形式：“变量类型[][] 数组名称”
		// 其中“变量类型[][]”构成了数组类型，例如int[][]被称作二维整型数组
		// 以下是分配二维数组空间的第二种形式：
		// 方括号内留空，然后紧跟花括号，花括号内部是以逗号分隔的几个一维数组
		double[][] triangle = new double[][] { new double[] { -2.0, 0.0 },
				new double[] { 0.0, -1.0 }, new double[] { 2.0, 1.0 } };
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
