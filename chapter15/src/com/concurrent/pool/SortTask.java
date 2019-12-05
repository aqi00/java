package com.concurrent.pool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

//定义一个排序的递归任务
public class SortTask extends RecursiveTask<int[]> {
	private static final long serialVersionUID = 1L;
	private int src[]; // 待排序的整型数组

	public SortTask(int src[]) {
		this.src = src;
	}

	@Override
	protected int[] compute() {
		int srcLen = src.length;
		if (srcLen > 2) { // 如果条件成立，说明任务中要进行排序的集合还不够小
			int midIndex = srcLen / 2;
			// 拆分成两个子任务
			SortTask task1 = new SortTask(Arrays.copyOf(src, midIndex));
			task1.fork();
			SortTask task2 = new SortTask(Arrays.copyOfRange(src, midIndex, srcLen));
			task2.fork();
			// 将两个有序的数组，合并成一个有序的数组
			int result1[] = task1.join();
			int result2[] = task2.join();
			int mer[] = joinInts(result1, result2);
			return mer;
		} else { // 否则说明集合中只有一个或者两个元素，可以进行这两个元素的比较排序了
			// 如果条件成立，说明数组中只有一个元素，或者是数组中的元素都已经排列好位置了
			if (srcLen == 1 || src[0] <= src[1]) {
				return src;
			} else {
				int targetp[] = new int[srcLen];
				targetp[0] = src[1];
				targetp[1] = src[0];
				return targetp;
			}
		}
	}

	// 把两个整型数组合并到新的整型数组
	private int[] joinInts(int array1[], int array2[]) {
		int destInts[] = new int[array1.length + array2.length];
		int array1Len = array1.length;
		int array2Len = array2.length;
		int destLen = destInts.length;
		// 只需要以新的集合destInts的长度为标准，遍历一次即可
		for (int index = 0, array1Index = 0, array2Index = 0; index < destLen; index++) {
			int value1 = array1Index >= array1Len ? Integer.MAX_VALUE : array1[array1Index];
			int value2 = array2Index >= array2Len ? Integer.MAX_VALUE : array2[array2Index];
			if (value1 < value2) { // 如果条件成立，说明应该取数组array1中的值
				array1Index++;
				destInts[index] = value1;
			} else { // 否则取数组array2中的值
				array2Index++;
				destInts[index] = value2;
			}
		}
		return destInts;
	}
}
