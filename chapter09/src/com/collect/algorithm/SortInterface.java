package com.collect.algorithm;

//定义排序接口
public interface SortInterface<T extends Comparable<T>> {

	// 声明一个排序操作的抽象方法
	public void sort(T[] array);

	// 定义一个交换数组元素的默认方法
	default void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
