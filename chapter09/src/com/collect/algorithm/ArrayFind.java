package com.collect.algorithm;

//二分查找算法的工具类。使用了泛型方法
public class ArrayFind {
	private static int count; // 查找次数

	// 二分查找的入口方法。注意泛型类型T必须实现了接口Comparable
	// 请求参数为待查找的数组及目标元素，返回参数为目标元素的数组下标（位置）
	public static <T extends Comparable<T>> int binarySearch(T[] array, T aim) {
		count = 0; // 开始查找前先把查找次数清零
		return binarySearch(array, 0, array.length - 1, aim);
	}

	// 使用递归实现的二分查找
	private static <T extends Comparable<T>> int binarySearch(T[] array, int start, int end, T aim) {
		count++; // 查找次数加一
		if (start>=end && aim.compareTo(array[start])!=0) { // 起点和终点都重合了还没找到
			return -1; // 返回-1表示没找到
		}
		int middle = (start + end) / 2; // 计算中间的位置
		if (aim.compareTo(array[middle]) == 0) { // 找到目标值，返回目标值所处的位置
			System.out.println("查找次数="+count);
			return middle;
		} else if (aim.compareTo(array[middle]) < 0) { // 目标值在前半段，继续查找
			return binarySearch(array, start, middle - 1, aim);
		} else { // 目标值在后半段，继续查找
			return binarySearch(array, middle + 1, end, aim);
		}
	}
}
