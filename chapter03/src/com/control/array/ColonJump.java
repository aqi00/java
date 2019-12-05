package com.control.array;

//演示如何利用冒号实现多重循环的定制跳转
public class ColonJump {

	public static void main(String[] args) {
		double[][] triangle = { { -2.0, 0.0 }, { 0.0, -1.0 }, { 2.0, 1.0 } };
		// 下面通过多重循环依次打印二维数组里面的所有元素
		for (int i = 0; i < triangle.length; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				System.out.println("value = " + triangle[i][j]);
			}
		}
		// 处理要求：一旦发现数组元素等于0.0，就立即从第二层循环跳出第一层循环（跳出两层循环）
		for (int i = 0; i < triangle.length; i++) {
			boolean isFound = false; // 该布尔变量用来标记是否找到0.0
			for (int j = 0; j < triangle[i].length; j++) {
				if (triangle[i][j] == 0.0) {
					isFound = true; // 找到了0.0
					System.out.println("simple found 0.0");
					break; // 跳出第二层循环
				}
			}
			if (isFound) { // 根据布尔变量判断是否找到了0.0
				break; // 跳出第一层循环
			}
		}
		// 下面的loop1是一个记号，连同后面的冒号加在for前面，表示它指代这个for循环
		loop1: for (int i = 0; i < triangle.length; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				if (triangle[i][j] == 0.0) { // 找到了0.0，准备跳出外层循环
					System.out.println("loop1 found 0.0");
					break loop1; // 跳出loop1代表的循环，也就是跳出第一层循环
				}
			}
		}
		// 下面用到了两种冒号，一种用来标记循环，另一种用来简化数组遍历
		loop2: for (double[] dot : triangle) { // dot等价于前面的triangle[i]
			for (double coordinate : dot) { // coordinate等价于前面的triangle[i][j]
				if (coordinate == 0.0) { // 找到了0.0，准备跳出外层循环
					System.out.println("loop2 found 0.0");
					break loop2; // 跳出loop2代表的循环
				}
			}
		}
	}
}
