package com.control.array;

// 演示如何通过二维数组计算三角形的三条边长
public class TwoDimensional3 {

	public static void main(String[] args) {
		// 以下是分配二维数组空间的第三种形式：
		// 赋值等号右边直接跟着花括号，花括号又内嵌好几个花括号分别表示对应的一维数组
		double[][] triangle = { { -2.0, 0.0 }, { 0.0, -1.0 }, { 2.0, 1.0 } };
		// 下面通过循环语句依次计算三角形每条边的长度
		// 假设第一个数组元素代表点A，第二个数组元素代表点B，第三个数组元素代表点C，
		// 则本循环将依次求得AB、AC、BC这三条边的长度
		for (int i = 0; i < triangle.length - 1; i++) {
			for (int j = i + 1; j < triangle.length; j++) {
				// 获取两个顶点在横轴方向的距离
				double xDistance = Math.abs(triangle[j][0] - triangle[i][0]);
				// 获取两个顶点在纵轴方向的距离
				double yDistance = Math.abs(triangle[j][1] - triangle[i][1]);
				// 根据勾股定理计算连接两顶点的斜边长度
				double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
				System.out.println("i=" + i + ", j=" + j + ", distance=" + distance);
			}
		}
	}
}
