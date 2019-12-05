package com.collect.algorithm;

//快速排序的算法实现
public class SortQuick implements SortInterface {

	@Override
	public void sort(Comparable[] array) {
		sort(array, 0, array.length - 1);
	}

	private void sort(Comparable[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int index = partition(array, start, end);
		sort(array, start, index - 1);
		sort(array, index + 1, end);
	}

	private int partition(Comparable[] array, int start, int end) {
		// 固定的切分方式
		Comparable key = array[start];
		while (start < end) {
			while (array[end].compareTo(key) > 0 && end > start) { // 从后半部分向前扫描
				end--;
			}
			array[start] = array[end];
			while (array[start].compareTo(key) < 0 && end > start) { // 从前半部分向后扫描
				start++;
			}
			array[end] = array[start];
		}
		array[end] = key;
		return end;
	}

}
