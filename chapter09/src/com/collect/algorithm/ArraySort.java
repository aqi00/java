package com.collect.algorithm;

//定义一个数组排序的工具类
public class ArraySort {

	// 定义了一个排序方法，支持给所有实现了Comparable接口的数组类型排序。
	// 第一个参数是待排序的数组，第二个参数是排序策略。
	public static <T extends Comparable<T>> void sort(T[] array, SortInterface<T> sorter) {
		sorter.sort(array);
	}
}
