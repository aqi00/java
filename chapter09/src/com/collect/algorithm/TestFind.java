package com.collect.algorithm;

import java.util.Arrays;
import java.util.Random;

//演示如何使用二分查找法在某个数组里面找到目标值
public class TestFind {

	public static void main(String[] args) {
		testIntFind(); // 测试整型数组的查找
		testStrFind(); // 测试字符串数组的查找
	}
	
	// 测试整型数组的查找
	private static void testIntFind() {
		Integer item = 0; // 随机数变量
		Integer[] numberArray = new Integer[20]; // 随机数构成的数组
		// 以下生成一个包含随机整数的数组
		loop: for (int i = 0; i < numberArray.length; i++) {
			item = new Random().nextInt(100); // 生成一个小于100的随机整数
			for (int j = 0; j < i; j++) { // 遍历数组进行检查，避免塞入重复数字
				// 数组中已存在该整数，则重做本次循环，以便重新生成随机数
				if (numberArray[j] == item) {
					i--; // 本次循环做了无用功，取消当前的计数
					continue loop; // 直接继续上一级循环
				}
			}
			numberArray[i] = item; // 往数组填入新生成的随机数
		}
		Arrays.sort(numberArray); // 对整数数组排序（默认升序排列）
		System.out.println();
		for (int seq=0; seq<numberArray.length; seq++) { // 打印数组中的所有数字
			System.out.println("序号="+seq+", 数字="+numberArray[seq]);
		}
		
		// 下面通过二分查找法确定目标数字排在第几位
		Integer aim_item = item; // 最后生成的整数
		System.out.println("准备查找的目标数字="+aim_item);
		// 通过泛型的二分查找方法来查找目标数字的位置
		int position = ArrayFind.binarySearch(numberArray, aim_item);
		System.out.println("查找到的位置序号="+position);
	}

	// 测试字符串数组的查找
	private static void testStrFind() {
		String item = ""; // 随机字符串变量
		String[] stringArray = new String[20]; // 随机字符串构成的数组
		// 以下生成一个包含随机字符串的数组
		loop: for (int i = 0; i < stringArray.length; i++) {
			int random = new Random().nextInt(26); // 生成一个小于26的随机整数
			item = "" + (char) (random + 'A'); // 利用随机数获取从"A"到"Z"的随机字符串
			for (int j = 0; j < i; j++) { // 遍历数组进行检查，避免塞入重复字符串
				// 数组中已存在该整数，则重做本次循环，以便重新生成随机字符串
				if (stringArray[j].equals(item)) {
					i--; // 本次循环做了无用功，取消当前的计数
					continue loop; // 直接继续上一级循环
				}
			}
			stringArray[i] = item; // 往数组填入新生成的随机字符串
		}
		Arrays.sort(stringArray); // 对字符串数组排序（默认升序排列）
		System.out.println();
		for (int seq=0; seq<stringArray.length; seq++) { // 打印数组中的所有字符串
			System.out.println("序号="+seq+", 字符串="+stringArray[seq]);
		}
		
		// 下面通过二分查找法确定目标字符串排在第几位
		String aim_item = item; // 最后生成的字符串
		System.out.println("准备查找的目标字符串="+aim_item);
		// 通过泛型的二分查找方法来查找目标字符串的位置
		int position = ArrayFind.binarySearch(stringArray, aim_item);
		System.out.println("查找到的位置序号="+position);
	}

}
