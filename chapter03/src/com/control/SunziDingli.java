package com.control;

import java.util.Arrays;

//演示孙子定理的求解过程（西方称作“中国剩余定理”）
public class SunziDingli {

	public static void main(String[] arg) {
		// 有物不知其数，三三数之剩二，五五数之剩三，七七数之剩二。问物几何？
		int count = 0; // 数组的容量计数
		int[] numbers = new int[0]; // 符合条件的整数都放在这个数组
		for (int i = 1; i < 1000; i++) { // 查找一千之内所有符合要求的整数
			if (i%3==2 && i%5==3 && i%7==2) { // 找到了一个满足条件的整数
				count++; // 计数加一
				numbers = Arrays.copyOf(numbers, count); // 数组容量增大一个
				numbers[count-1] = i; // 往数组末尾填入刚才找到的整数
			}
		}
		for (int number : numbers) { // 遍历并打印所有找到的整数解
			System.out.println("符合孙子定理的整数number=" + number);
		}
	}

}
