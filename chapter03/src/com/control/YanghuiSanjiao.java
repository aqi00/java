package com.control;

//演示如何通过递推计算杨辉三角
public class YanghuiSanjiao {
	
	public static void main(String[] args) {
		int MAX_ROW = 10; // 杨辉三角的总行数
		// 利用二维数组保存杨辉三角的各项系数
		int[][] triangles = new int[MAX_ROW][];
		for (int i = 0; i < MAX_ROW; i++) {
			triangles[i] = new int[i + 1]; // 分配每行的系数数组
		}

		for (int i = 0; i < triangles.length; i++) { // 计算每行每列的各项系数
			for (int j = 0; j < triangles[i].length; j++) {
				if (j==0 || j==triangles[i].length-1) { // 每行的头尾系数都是1
					triangles[i][j] = 1;
				} else { // 非头尾的系数值为上一行前一列与上一行当前列的系数之和
					triangles[i][j] = triangles[i-1][j-1] + triangles[i-1][j];
				}
			}
		}

		for (int[] row : triangles) { // 遍历并打印杨辉三角的所有系数
			for (int number : row) {
				System.out.printf("%5d", number); // 该数字占据五个数字位，靠右对齐且左边留空
			}
			System.out.println(); // 每行末尾打印换行符，下个循环另起一行
		}
	}
}
