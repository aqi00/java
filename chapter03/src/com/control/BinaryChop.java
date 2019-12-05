package com.control;

import java.util.Arrays;
import java.util.Random;

//演示如何利用二分查找算法定位数组元素
public class BinaryChop {

	public static void main(String[] args) {
		int item = 0; // 随机数变量
		int[] numbers = new int[20]; // 随机数构成的数组
		// 以下生成一个包含随机整数的数组
		loop: for (int i = 0; i < numbers.length; i++) {
			item = new Random().nextInt(100); // 生成一个小于100的随机整数
			for (int j = 0; j < i; j++) { // 遍历数组进行检查，避免塞入重复数字
				if (numbers[j] == item) { // 已经存在该随机数，则继续第一层循环，重新生成随机数
					i--; // 本次循环做了无用功，取消当前的计数
					continue loop; // 直接继续上一级循环
				}
			}
			numbers[i] = item; // 往数组填入新生成的随机数
		}
		Arrays.sort(numbers); // 对整数数组排序（默认升序排列）
		for (int seq=0; seq<numbers.length; seq++) { // 打印数组中的所有数字
			System.out.println("序号="+seq+", 数字="+numbers[seq]);
		}
		System.out.println("最后生成的随机数="+item);

		// 下面通过二分查找法确定目标数字排在第几位
		int aim_item = item; // 最后生成的整数
		System.out.println("准备查找的目标数字="+aim_item);
		int start = 0; // 二分查找的开始位置
		int end = numbers.length - 1; // 二分查找的结束位置
		int middle = 0; // 开始位置与结束位置之间的中间位置
		for (int count = 1, position = -1; start <= end; count++) {
			middle = (start + end) / 2; // 折半获得中间的位置
			System.out.println("折半查找的中间数字="+numbers[middle]);
			if (numbers[middle] > aim_item) {
				// 该位置的数字比目标数字大，表示目标数字在该位置左边
				end = middle - 1;
			} else if (numbers[middle] < aim_item) {
				// 该位置的数字比目标数字大，表示目标数字在该位置右边
				start = middle + 1;
			} else { // 找到目标数字，跳出循环
				position = middle;
				System.out.println("查找次数="+count+", 序号位置="+position);
				break;
			}
		}
	}
}
