package com.collect.algorithm;

//冒泡排序的算法实现
public class SortBubble implements SortInterface {

	// 以下的sort方法实现了冒泡排序算法的升序排列
	@Override
	public void sort(Comparable[] array) {
		for (int i = 1; i < array.length; i++) { // 假设数组长度为n，则需要n-1个循环
			for (int j = 0; j < array.length - i; j++) { // 每个循环都把最大的元素挪到本区间的末尾
				if (array[j].compareTo(array[j + 1]) > 0) { // 前一个元素比后一个元素要大
					swap(array, j, j + 1); // 交换两个数组元素，也就是把更大的元素往后挪
				}
			}
		}
	}
}
